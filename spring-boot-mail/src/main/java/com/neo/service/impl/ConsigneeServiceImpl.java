package com.neo.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.neo.bo.Consignee;
import com.neo.common.entity.BasicResponse;
import com.neo.common.entity.EmailQueryReq;
import com.neo.common.entity.PageQueryReq;
import com.neo.common.entity.ResponseCode;
import com.neo.mapper.ConsigneeMapper;
import com.neo.service.ConsigneeService;
import com.neo.service.MailService;
import lombok.extern.slf4j.Slf4j;

/**
 * 收件人 列表  Service 层  接口 实现
 * @author zhouhb
 * @date 2019/07/01
 *
 */
@Service
@Slf4j
public class ConsigneeServiceImpl implements ConsigneeService{
	
	@Autowired
	ConsigneeMapper  consigneeMapper;
	
	@Autowired
	MailService  mailService;

	@Override
	public BasicResponse insert(Consignee record) {
		
		/*
	   	 * 新增，修改的相关校验
	   	 */
		BasicResponse basicResponse = this.checkConsignee(record);
		if(ResponseCode.SUCCESS.equals(basicResponse.getResultCode())){
			/*
		   	 * 查询该用户是否唯一
		   	 */
			Consignee  consignee = consigneeMapper.findConsigneeByUserId(record.getUserId());
			if(null !=consignee) {
				return  new BasicResponse(ResponseCode.ERROR,"数据库中存在该UserId，新增失败");	
			}
			consigneeMapper.insertSelective(record);
			return  new BasicResponse(ResponseCode.SUCCESS,"新增成功");
		}else{
			return  new BasicResponse(ResponseCode.ERROR,"新增失败");
		} 
		
	}

	private BasicResponse checkConsignee(Consignee record) {
		if(null == record) {
			return  new BasicResponse(ResponseCode.ERROR,"Consignee对象不能为空");
		}
		if(StringUtils.isEmpty(record.getMailAddr())) {
			return  new BasicResponse(ResponseCode.ERROR,"邮箱地址不能为空");
		}
		if(StringUtils.isEmpty(record.getUserId())) {
			return  new BasicResponse(ResponseCode.ERROR,"邮箱用户名不能为空");
		}
		if(StringUtils.isEmpty(record.getMailGroup())) {
			return  new BasicResponse(ResponseCode.ERROR,"邮箱群组不能为空");
		}
		if(StringUtils.isEmpty(record.getUserRole())) {
			return  new BasicResponse(ResponseCode.ERROR,"邮箱角色不能为空");
		}
		
		return  new BasicResponse(ResponseCode.SUCCESS,"成功");	
	}

	@Override
	public BasicResponse update(Consignee record) {
		/*
	   	 * 新增，修改的相关校验
	   	 */
		BasicResponse basicResponse = this.checkConsignee(record);
		if(ResponseCode.SUCCESS.equals(basicResponse.getResultCode())){
			/*
		   	 * 查询该用户是否唯一
		   	 */
			Consignee  consignee = consigneeMapper.findConsigneeByUserId(record.getUserId());
			if(null !=consignee) {
				return  new BasicResponse(ResponseCode.ERROR,"数据库中存在该UserId，修改失败");	
			}
			consigneeMapper.updateByPrimaryKeySelective(record);
			return  new BasicResponse(ResponseCode.SUCCESS,"修改成功");
		}else{
			return  new BasicResponse(ResponseCode.ERROR,"修改失败");
		} 
	}

	@Override
	public Consignee selectById(Long id) {
		return consigneeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteById(Long id) {
		return consigneeMapper.deleteByPrimaryKey(id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public PageSerializable searchWithPagebable(PageQueryReq pageQueryReq) {
		return  PageHelper.startPage(pageQueryReq.getPageNum(), pageQueryReq.getPageSize())
				.setOrderBy(pageQueryReq.getOrder() +" "+ pageQueryReq.getSort()).doSelectPageInfo(()->{
					try {
						// consigneeMapper.findByPage(pageQueryReq.getParams());
						consigneeMapper.findAll();
					}catch(Exception e) {
						log.error("分页查询出错",e);
					}
				});		
	}

	@Override
	public boolean deleteBatch(List<Long> ids) {
		try {
			ids.parallelStream().forEach(id ->{
				this.deleteById(id);
			});
			return true;
		}catch(Exception e) {
			log.error("批量删除出错",e);
			return false;
		}
	}

	@Override
	public void sendMail(EmailQueryReq emailQueryReq) {
		
		String userId = emailQueryReq.getUserId();  
		
	    List<String>  mailGroups = emailQueryReq.getMailGroups();

	    List<Long> ids = emailQueryReq.getIds();
	    
	    String  content = emailQueryReq.getContent();
	    
	    String subject = emailQueryReq.getSubject();
	    
	    
	    if(null != userId && null == mailGroups && null == ids) {
	    	/*
		   	 * 查询 邮件的条件 ：根据名称单发邮件功能
		   	 */
	    	Consignee  consignee = consigneeMapper.findConsigneeByUserId(userId);
			 if(null != consignee) {
				 log.info("模拟发送邮件过程==收件人:"+consignee.getMailAddr()+"==主题："+content+"==内容："+subject);
				// mailService.sendSimpleMail(consignee.getMailAddr(), content, subject); 
			 }	 
	    }
	    
	    if(null == userId && null == mailGroups && null != ids) {
	    	/*
		   	 * 查询 邮件的条件 ：批量根据id发邮件功能（支持单选或者多选）
		   	 */
	    	ids.parallelStream().forEach(id ->{
	    		Consignee consignee = consigneeMapper.selectByPrimaryKey(id);
	    		if(null != consignee) {
	    			log.info("模拟发送邮件过程==收件人:"+consignee.getMailAddr()+"==主题："+content+"==内容："+subject);
					// mailService.sendSimpleMail(consignee.getMailAddr(),content, subject); 
				 }
			}); 
	    }
	    
	    if(null == userId && null != mailGroups && null == ids) {
	    	 /*
		   	 * 查询 邮件的条件 ：根据群组发送邮件功能（支持单选或者多选）
		   	 */
	    	mailGroups.parallelStream().forEach(mailGroup ->{
	    		List<Consignee> consigneeList = consigneeMapper.selectByMailGroup(mailGroup);
	    		consigneeList.parallelStream().forEach(consignee ->{
	    			if(null != consignee) {
	    				log.info("模拟发送邮件过程==收件人:"+consignee.getMailAddr()+"==主题："+content+"==内容："+subject);
	    				// mailService.sendSimpleMail(consignee.getMailAddr(),content, subject);
	    			}
	    		});
			}); 
			 
	    }
	    
	  
	    /*
	   	 * 查询 邮件的条件 ：混合模式(群发+多收件人模式)发送邮件功能 
	   	 */
	    if(null == userId && null != mailGroups && null != ids) {
	    	/*
		   	 * 利用 set 对 ID去重
		   	 */
	    	Set<String>  setMailAddr = this.setByIdAndGroups(ids,mailGroups);
	    	
	    	setMailAddr.parallelStream().forEach(mailAddr ->{
	    		if(null != mailAddr) {
	    			log.info("模拟发送邮件过程==收件人:"+mailAddr+"==主题："+content+"==内容："+subject);
					// mailService.sendSimpleMail(mailAddr,content, subject); 
				 }
			}); 
	    }	 
	}

	/*
	 * 利用 ids 以及 mailGroups 去重 并找出 邮件 地址
	 * 
	 */
	private Set<String> setByIdAndGroups(List<Long> ids, List<String> mailGroups) {
	   Set<String> set=new HashSet<>();
	   ids.parallelStream().forEach(id ->{
   		Consignee consignee = consigneeMapper.selectByPrimaryKey(id);
	   		if(null != consignee) {
	   			set.add(consignee.getMailAddr());
			}
		}); 

		mailGroups.parallelStream().forEach(mailGroup ->{
    		List<Consignee> consigneeList = consigneeMapper.selectByMailGroup(mailGroup);
    		consigneeList.parallelStream().forEach(consignee ->{
    			if(null != consignee) {
    	   			set.add(consignee.getMailAddr());
    			}
    		});
		});

        return set;    
	}
}
