ALTER TABLE uk_chat_message ADD title varchar(255) COMMENT '标题';
ALTER TABLE uk_chat_message ADD headimgurl varchar(255) COMMENT '缩略图';
ALTER TABLE uk_chat_message ADD qusid varchar(32) COMMENT '问题ID';
ALTER TABLE uk_chat_message ADD code varchar(32) COMMENT '代码';

ALTER TABLE uk_chat_message ADD usetime datetime COMMENT '最后一次评价时间';
ALTER TABLE uk_chat_message ADD score varchar(32) COMMENT '机器人回答置信度';
ALTER TABLE uk_chat_message ADD url varchar(255) COMMENT '图文跳转URL';