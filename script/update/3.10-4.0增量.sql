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
  `scope` varchar(32) DEFAULT NULL COMMENT '日期范围类型（单天 one/范围 more/星期week）',
  `apply` varchar(32) DEFAULT NULL COMMENT '适用于（文字客服 word / 排班 sche）',
  `begin` varchar(32) DEFAULT NULL COMMENT '日期开始',
  `end` varchar(32) DEFAULT NULL COMMENT '日期开始',
  `week` text COMMENT '星期',
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

ALTER TABLE uk_chat_message ADD quickagent tinyint DEFAULT 0 COMMENT '错误消息快速转人工坐席';

ALTER TABLE uk_consult_invite ADD enablevoice tinyint DEFAULT 0 COMMENT '是否启用语音消息';

ALTER TABLE uk_consult_invite ADD enablewechatvoice tinyint DEFAULT 0 COMMENT '是否启用微信内置语音消息';

ALTER TABLE uk_consult_invite ADD weichatappkey varchar(255) DEFAULT NULL COMMENT '微信APPKEY';

ALTER TABLE uk_consult_invite ADD weichattoken varchar(255) DEFAULT NULL COMMENT '微信TOKEN';

ALTER TABLE uk_consult_invite ADD defaultskill text DEFAULT NULL COMMENT '默认启用的技能组';

ALTER TABLE uk_consult_invite ADD firstreplytime int DEFAULT 0 COMMENT '首次消息回复时间';


ALTER TABLE uk_agentservice ADD firstreplytime int DEFAULT 0 COMMENT '首次消息回复时间';

ALTER TABLE uk_agentservice ADD memo text DEFAULT NULL COMMENT '备注';

CREATE TABLE `uk_session_type` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `NAME` varchar(100) DEFAULT NULL COMMENT '字典名称',
  `CODE` varchar(100) DEFAULT NULL COMMENT '代码',
  `ORGI` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `CTYPE` varchar(32) DEFAULT NULL COMMENT '类型',
  `PARENTID` varchar(32) DEFAULT NULL COMMENT '父级ID',
	`DICID` varchar(32) DEFAULT NULL COMMENT '目录ID',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '描述',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  `ORGAN` varchar(32) DEFAULT NULL COMMENT '部门',
  PRIMARY KEY (`ID`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

ALTER TABLE uk_systemconfig ADD enabledis tinyint DEFAULT 0 COMMENT '启用集群支持功能';
ALTER TABLE uk_systemconfig ADD enablevoice tinyint DEFAULT 0 COMMENT '启用语音';

ALTER TABLE uk_chat_message ADD matchtype varchar(50) DEFAULT NULL COMMENT '匹配状态';


ALTER TABLE uk_chat_message modify expmsg text ;


ALTER TABLE uk_xiaoe_config ADD enablesmartsuggest tinyint DEFAULT 0 COMMENT '启用外部推荐内容';

ALTER TABLE uk_xiaoe_config ADD smartsuggesturl varchar(255) COMMENT '启用外部推荐内容';
ALTER TABLE uk_xiaoe_config ADD smartsuggestssl tinyint DEFAULT 0 COMMENT '外部推荐启用SSL';
ALTER TABLE uk_xiaoe_config ADD smartsuggestlogin tinyint DEFAULT 0 COMMENT '需要登录';

ALTER TABLE uk_xiaoe_config ADD smartsuggestappkey varchar(50) COMMENT '外部推荐APPKey';
ALTER TABLE uk_xiaoe_config ADD smartsuggestappsec varchar(100) COMMENT '推荐AppSec';

ALTER TABLE uk_xiaoe_config ADD smartsuggestparam varchar(255) COMMENT '推荐AppSec';
ALTER TABLE uk_xiaoe_config ADD smartsuggestmethod varchar(50) COMMENT '外部推荐请求方式';
ALTER TABLE uk_xiaoe_config ADD smartsuggesttempletinput varchar(50) COMMENT '外部推荐输入格式化模板';
ALTER TABLE uk_xiaoe_config ADD suggestnum int DEFAULT 0 COMMENT '显示外部推荐条数';


ALTER TABLE uk_chat_message modify expmsg text ;


ALTER TABLE uk_ekm_knowbase ADD kbtype varchar(32) DEFAULT NULL COMMENT '知识库类型（knowledge 知识库 ）/（help 帮助平台）/（ask 问答）';
ALTER TABLE uk_ekm_knowbase ADD kbviewid varchar(32) DEFAULT NULL COMMENT '知识库访问短ID';


ALTER TABLE uk_chat_message ADD plugin varchar(100) DEFAULT NULL COMMENT '插件';
ALTER TABLE uk_ekm_knowbase ADD kbviewid varchar(32) DEFAULT NULL COMMENT '知识库访问短ID';

ALTER TABLE uk_sessionconfig ADD tipagent tinyint DEFAULT 0 COMMENT '在坐席端弹出气泡提示';
ALTER TABLE uk_sessionconfig ADD tipagenticon varchar(255) DEFAULT NULL COMMENT '坐席端气泡提醒的图标';
ALTER TABLE uk_sessionconfig ADD tipagenttitle varchar(100) DEFAULT NULL COMMENT '坐席端气泡提醒的标题';


CREATE TABLE `uk_ekm_helpdesk_times` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `knowid` varchar(32) DEFAULT NULL COMMENT '知识ID',
  `kbid` varchar(32) DEFAULT NULL COMMENT '知识库ID',
  `ktid` varchar(32) DEFAULT NULL COMMENT '知识分类ID',
  `helps` tinyint(4) DEFAULT '0' COMMENT '该知识是否有帮助',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
	`creater` varchar(32) DEFAULT NULL COMMENT '创建人',
	`createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='EKM-知识帮助平台（知识评价表）';

INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('297e74066652bc8a016652f0cdbd0114', 'EKM知识库（问答平台）', 'pub', 'com.dic.ekm.ask.type', NULL, 'data', '0', '', NULL, NULL, NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-08 17:10:04', NULL, '1', '0', NULL, '0', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('297e74066652bc8a016652f07fa20110', 'EKM知识库（帮助平台）', 'pub', 'com.dic.ekm.help.type', NULL, 'data', '0', '', NULL, NULL, NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-08 17:09:44', NULL, '1', '0', NULL, '0', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('297e7406650cce9d01650ce68c37067e', 'EKM知识库知识类型', 'pub', 'com.dic.ekm.knowledge.type', NULL, 'data', '0', '', NULL, NULL, NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-08-06 09:42:40', NULL, '1', '0', NULL, '0', '0', NULL, NULL, NULL, NULL, NULL);




ALTER TABLE uk_chat_message ADD ckind varchar(100) COMMENT '分类';
ALTER TABLE uk_chat_message ADD ckindname varchar(255) COMMENT '分类名称';
ALTER TABLE uk_chat_message ADD clabelname varchar(255) COMMENT '标签名称';
ALTER TABLE uk_chat_message ADD clabelname varchar(255) COMMENT '标签名称';

ALTER TABLE uk_webim_monitor ADD incall int DEFAULT 0 COMMENT '通话中';
ALTER TABLE uk_webim_monitor ADD readyagents int DEFAULT 0 COMMENT '就绪坐席数量';


ALTER TABLE uk_ekm_knowledge_type ADD navshow int(11) DEFAULT '0' COMMENT '导航栏显示（1是/0否）';
ALTER TABLE uk_ekm_knowledge_type ADD deskshow int(11) DEFAULT '0' COMMENT '首页显示（1是/0否）';


CREATE TABLE `uk_ekm_knowbase_config` (
  `id` varchar(32) NOT NULL,
  `knowbaseid` varchar(32) DEFAULT NULL COMMENT '知识库id',
  `basehost` text  COMMENT '站点根网址',
  `webname` text  COMMENT '网站名称',
  `powerby` text  COMMENT '网站版权信息',
  `keywords` text  COMMENT '站点关键字',
  `description` text  COMMENT '站点描述',
  `beian` text  COMMENT '网站备案号',
  `footer` text  COMMENT '页脚',
  `indexlog` text  COMMENT '首页图标',
  `hotwords` text  COMMENT '搜索热词',
  `banner` text  COMMENT '海报图片',
  `creater` varchar(32) DEFAULT NULL,
  `orgi` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='EKM知识库配置表';
