package com.neo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.neo.mapper.ConsigneeMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {
	
	@Autowired
	ConsigneeMapper  consigneeMapper;
	
	@Test
	public  void  testMybatis() {
		System.out.println("====="+consigneeMapper.selectByPrimaryKey(111L));
	}

}
