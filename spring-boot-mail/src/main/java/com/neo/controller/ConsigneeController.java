package com.neo.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageSerializable;
import com.neo.bo.Consignee;
import com.neo.common.entity.BasicResponse;
import com.neo.common.entity.DataRes;
import com.neo.common.entity.EmailQueryReq;
import com.neo.common.entity.PageQueryReq;
import com.neo.common.entity.ResponseCode;
import com.neo.service.ConsigneeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


/**
 * 收件人 列表  控制层
 * @author zhouhb
 * @date 2019/07/01
 *
 * 接口API： http://localhost:8080/swagger-ui.html#/
 *
 */

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/consignee")
@Api(value="接收人群组管理",tags = {"接收人群组管理"})
public class ConsigneeController {
	
	@Autowired
	ConsigneeService  consigneeService;
	
	
	/**
	 * 新增
	 * 
	 * @param 
	 * @return
	 */
	@ApiOperation(value="新增",notes="新增",httpMethod="POST")
	@PostMapping
	public  BasicResponse insert(@RequestBody @Valid Consignee consignee){
		return consigneeService.insert(consignee);
	}
	
	/**
	 * 修改
	 * 
	 * @param 
	 * @return
	 */
	@ApiOperation(value="修改",notes="修改",httpMethod="PUT")
	@PutMapping
	public  BasicResponse update(@RequestBody @Valid Consignee consignee){
		return consigneeService.update(consignee);
	}
	
	/**
	 * 批量删除（单个和批量共用）
	 * 
	 * @param 
	 * @return
	 */
	@ApiOperation(value="批量删除",notes="批量删除",httpMethod="DELETE")
	@DeleteMapping
	public  BasicResponse  deleteBatch(@RequestBody List<Long> ids){
		boolean  bool = consigneeService.deleteBatch(ids);
		if(bool){
			return  new BasicResponse(ResponseCode.SUCCESS,"删除成功");
		}else{
			return  new BasicResponse(ResponseCode.ERROR,"删除失败");
		} 
	}
	
	/**
	 * 分页查询
	 * 
	 * @param 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value="分页查询",notes="分页查询",httpMethod="GET")
	@PostMapping("/page")
	public  DataRes<PageSerializable>  update(@RequestBody @Valid PageQueryReq pageQueryReq){
		try {
			PageSerializable pagelist = consigneeService.searchWithPagebable(pageQueryReq);
			return  new DataRes(ResponseCode.SUCCESS,"分页查询成功",pagelist);
		    }catch(Exception e) {
			log.error("分页查询出错",e);
			return  new DataRes(ResponseCode.ERROR,"分页查询失败");
		}
	}
	
	
	/**
	 * 邮件发送 
	 * @param 
	 * @return
	 */
	@ApiOperation(value="混合模式发送邮件功能",notes="混合模式发送邮件功能",httpMethod="POST")
	@PostMapping("/sendMail")
	public  BasicResponse  sendMail(@RequestBody EmailQueryReq emailQueryReq){
		try {
			consigneeService.sendMail(emailQueryReq);
			return  new BasicResponse(ResponseCode.SUCCESS,"发送邮件成功");
		}catch(Exception e) {
			log.error("发送邮件出错",e);
			return  new BasicResponse(ResponseCode.ERROR,"发送邮件失败");
		}
	}

}
