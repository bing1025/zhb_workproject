package com.neo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neo.bo.Consignee;

/**
 * 收件人 列表  DAO 层
 * @author zhouhb
 * @date 2019/07/01
 *
 */
public interface ConsigneeMapper {
	
	/**
	 * 根据主键删除
	 * @param  id
	 * @return  int
	 *
	 */
    int deleteByPrimaryKey(Long id);

    /**
	 * 新增
	 * @param  record
	 * @return  int
	 *
	 */
    int insert(Consignee record);
    
    /**
	 * 新增
	 * @param  record
	 * @return  int
	 *
	 */
    int insertSelective(Consignee record);

    /**
  	 * 根据主键查询
  	 * @param  id
  	 * @return  Consignee
  	 *
  	 */
    Consignee selectByPrimaryKey(Long id);

    /**
  	 * 修改
  	 * @param  record
  	 * @return  int
  	 *
  	 */
    int updateByPrimaryKeySelective(Consignee record);

    /**
  	 * 修改
  	 * @param  record
  	 * @return  int
  	 *
  	 */
    int updateByPrimaryKey(Consignee record);

    /**
  	 * 查询全部
  	 * @param 
  	 * @return  List<Consignee>
  	 *
  	 */
	List<Consignee>  findAll();

	 /**
  	 * 根据userId 查询
  	 * @param userId
  	 * @return  Consignee
  	 *
  	 */
	Consignee findConsigneeByUserId(@Param("userId")String userId);

	 /**
  	 * 根据mailGroup 查询
  	 * @param userId
  	 * @return  List<Consignee>
  	 *
  	 */
	List<Consignee> selectByMailGroup(@Param("mailGroup")String mailGroup);
	
	
}