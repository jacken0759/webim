ALTER TABLE uk_chat_message ADD title varchar(255) COMMENT '标题';
ALTER TABLE uk_chat_message ADD headimgurl varchar(255) COMMENT '缩略图';
ALTER TABLE uk_chat_message ADD qusid varchar(32) COMMENT '问题ID';
ALTER TABLE uk_chat_message ADD code varchar(32) COMMENT '代码';