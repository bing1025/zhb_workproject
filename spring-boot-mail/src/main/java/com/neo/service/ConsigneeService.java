package com.neo.service;

import java.util.List;

import com.github.pagehelper.PageSerializable;
import com.neo.bo.Consignee;
import com.neo.common.entity.BasicResponse;
import com.neo.common.entity.EmailQueryReq;
import com.neo.common.entity.PageQueryReq;

/**
 * 收件人 列表  Service 层  接口
 * @author zhouhb
 * @date 2019/07/01
 *
 */
public interface ConsigneeService {
	
	public  BasicResponse  insert(Consignee record);
	
	public  BasicResponse  update(Consignee record);
	
	public  Consignee selectById(Long id);
	
	public  int  deleteById(Long id);
	
	@SuppressWarnings("rawtypes")
	public  PageSerializable  searchWithPagebable(PageQueryReq pageQueryReq);

	public boolean deleteBatch(List<Long> ids);


	public void sendMail(EmailQueryReq emailQueryReq);
	
	

}
