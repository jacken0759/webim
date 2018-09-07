ALTER TABLE uk_chat_message ADD title varchar(255) COMMENT '标题';
ALTER TABLE uk_chat_message ADD headimgurl varchar(255) COMMENT '缩略图';
ALTER TABLE uk_chat_message ADD qusid varchar(32) COMMENT '问题ID';
ALTER TABLE uk_chat_message ADD code varchar(32) COMMENT '代码';

ALTER TABLE uk_chat_message ADD usetime datetime COMMENT '最后一次评价时间';
ALTER TABLE uk_chat_message ADD score varchar(32) COMMENT '机器人回答置信度';
ALTER TABLE uk_chat_message ADD url varchar(255) COMMENT '图文跳转URL';

ALTER TABLE uk_chat_message ADD bustype varchar(32) COMMENT '扩展消息类型';

ALTER TABLE uk_xiaoe_config ADD hotmsg text COMMENT '热点问题';
ALTER TABLE uk_xiaoe_config ADD topicshot tinyint(4) DEFAULT '0' COMMENT '是否开启热点问题推荐';


ALTER TABLE uk_callcenter_event ADD qualitystatus varchar(20) COMMENT '质检状态';
ALTER TABLE uk_callcenter_event ADD qualitydisorgan varchar(32) COMMENT '分配的质检部门';
ALTER TABLE uk_callcenter_event ADD qualitydisuser varchar(32) COMMENT '分配的质检用户';

ALTER TABLE uk_callcenter_event ADD qualityorgan varchar(32) COMMENT '实际质检部门';
ALTER TABLE uk_callcenter_event ADD qualityuser varchar(32) COMMENT '实际质检人';

ALTER TABLE uk_callcenter_event ADD qualityscore int DEFAULT 0 COMMENT '质检得分';
ALTER TABLE uk_callcenter_event ADD qualitytime datetime COMMENT '质检时间';
ALTER TABLE uk_callcenter_event ADD qualitytype varchar(20) COMMENT '质检类型';


CREATE TABLE `uk_workservice_time` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `timetype` varchar(32) DEFAULT NULL COMMENT '日期类型',
  `scope` varchar(50) DEFAULT NULL COMMENT '日期范围类型',
  `begin` varchar(32) DEFAULT NULL COMMENT '日期开始',
  `end` varchar(32) DEFAULT NULL COMMENT '日期开始',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


ALTER TABLE uk_consult_invite ADD fullscreen tinyint DEFAULT 0 COMMENT '启用全屏';


ALTER TABLE uk_chat_message ADD notreason varchar(32) DEFAULT NULL COMMENT '知识点无用的原因';

ALTER TABLE uk_xiaoe_config ADD topicuseful tinyint DEFAULT 0 COMMENT '是否开启知识点评价功能';

ALTER TABLE uk_xiaoe_config ADD topicusefulmsg varchar(255) DEFAULT NULL COMMENT '评价引导提示消息';

ALTER TABLE uk_xiaoe_config ADD topicusefulok varchar(50) DEFAULT NULL COMMENT '评价引导提示已解决按钮';

ALTER TABLE uk_xiaoe_config ADD topicusefulno varchar(50) DEFAULT NULL COMMENT '评价引导提示未解决按钮';

ALTER TABLE uk_xiaoe_config ADD topicusefultip varchar(255) DEFAULT NULL COMMENT '评价感谢语';

ALTER TABLE uk_xiaoe_config ADD topicusefulask tinyint DEFAULT 0 COMMENT '评价引导提示未解决点击后弹出原因选择';

ALTER TABLE uk_xiaoe_config ADD quickagent tinyint DEFAULT 0 COMMENT '是否显示快速转人工按钮';

ALTER TABLE uk_xiaoe_config ADD quickagentmsg varchar(255) DEFAULT NULL COMMENT '快速转人工提示消息';

ALTER TABLE uk_xiaoe_config ADD noagentmsg varchar(255) DEFAULT NULL COMMENT '无坐席在线提示消息';