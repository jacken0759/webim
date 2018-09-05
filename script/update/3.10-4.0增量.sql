ALTER TABLE uk_chat_message ADD title varchar(255) COMMENT '标题';
ALTER TABLE uk_chat_message ADD headimgurl varchar(255) COMMENT '缩略图';
ALTER TABLE uk_chat_message ADD qusid varchar(32) COMMENT '问题ID';
ALTER TABLE uk_chat_message ADD code varchar(32) COMMENT '代码';

ALTER TABLE uk_chat_message ADD usetime datetime COMMENT '最后一次评价时间';
ALTER TABLE uk_chat_message ADD score varchar(32) COMMENT '机器人回答置信度';
ALTER TABLE uk_chat_message ADD url varchar(255) COMMENT '图文跳转URL';
ALTER TABLE uk_chat_message ADD bustype varchar(32) COMMENT '扩展消息类型';


ALTER TABLE uk_callcenter_event ADD qualitystatus varchar(20) COMMENT '质检状态';
ALTER TABLE uk_callcenter_event ADD qualitydisorgan varchar(32) COMMENT '分配的质检部门';
ALTER TABLE uk_callcenter_event ADD qualitydisuser varchar(32) COMMENT '分配的质检用户';

ALTER TABLE uk_callcenter_event ADD qualityorgan varchar(32) COMMENT '实际质检部门';
ALTER TABLE uk_callcenter_event ADD qualityuser varchar(32) COMMENT '实际质检人';

ALTER TABLE uk_callcenter_event ADD qualityscore int DEFAULT 0 COMMENT '质检得分';
ALTER TABLE uk_callcenter_event ADD qualitytime datetime COMMENT '质检时间';
ALTER TABLE uk_callcenter_event ADD qualitytype varchar(20) COMMENT '质检类型';