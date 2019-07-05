CREATE TABLE `consignee` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` varchar(20) NOT NULL DEFAULT '' COMMENT '英文名称',
  `user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '中文名称',
  `mail_group` varchar(50) NOT NULL DEFAULT '' COMMENT '群组名称',
  `user_role` varchar(50) NOT NULL DEFAULT '' COMMENT '角色',
  `mail_addr` varchar(200) NOT NULL DEFAULT '' COMMENT '邮箱地址',
  `phone_num` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号码',
  `is_enable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '无效0，生效1',
  `create_by` varchar(20) NOT NULL DEFAULT '' COMMENT '新增人',
  `update_by` varchar(20) NOT NULL DEFAULT '' COMMENT '修改人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 COMMENT='收件人列表';

