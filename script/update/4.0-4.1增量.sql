CREATE TABLE `uk_qc_mission` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '任务名称（系统分配生成）',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `assuser` varchar(50) DEFAULT NULL COMMENT '分配执行人',
  `asstime` datetime DEFAULT NULL COMMENT '分配时间',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `filtertype` varchar(32) DEFAULT NULL COMMENT '筛选类型（callevent通话筛选/workorders工单筛选/agentservice会话筛选）',
  `dataid` varchar(50) DEFAULT NULL COMMENT '数据ID（通话记录ID/工单记录ID/会话记录ID）',
  `datakey` text DEFAULT NULL COMMENT '数据（通话（主叫号码）/工单记录（工单标题）/会话记录（访客用户名））',
  `datavalue` text DEFAULT NULL COMMENT '数据（通话（被叫号码）/工单记录（处理人）/会话记录（服务坐席））',
  `templateid` varchar(32) DEFAULT NULL COMMENT '质检模板ID',
  `actid` varchar(32) DEFAULT NULL COMMENT '质检活动ID',
  `formfilterid` varchar(32) DEFAULT NULL COMMENT '质检筛选表单ID',
  `filterid` varchar(32) DEFAULT NULL COMMENT '质检筛选记录ID',
  `taskid` varchar(32) DEFAULT NULL COMMENT '质检任务ID',
  `datastatus` tinyint(4) DEFAULT '0' COMMENT '数据状态',
  `qualitystatus` varchar(20) DEFAULT NULL COMMENT '质检状态',
  `qualitydisorgan` varchar(32) DEFAULT NULL COMMENT '分配的质检部门',
  `qualitydisuser` varchar(32) DEFAULT NULL COMMENT '分配的质检用户',
  `qualityorgan` varchar(32) DEFAULT NULL COMMENT '实际质检部门',
  `qualityuser` varchar(32) DEFAULT NULL COMMENT '实际质检人',
  `qualityscore` int(11) DEFAULT '0' COMMENT '质检得分',
  `qualitytime` datetime DEFAULT NULL COMMENT '质检时间',
  `qualitytype` varchar(20) DEFAULT NULL COMMENT '质检类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='QC质检 - 任务主表';

ALTER TABLE uk_qc_template_item ADD type varchar(32) DEFAULT NULL COMMENT '质检项分类（plus评分/minus扣分/taboo禁忌项）';


INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876e8c0b0182', '知识配置', 'pub', 'A14_A09_A02', NULL, 'auth', '402888816686bff701668767c78b010e', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:47:40', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/config/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876e48c8017e', '知识库专家', 'pub', 'A14_A09_A01', NULL, 'auth', '402888816686bff701668767c78b010e', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:47:23', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/experts/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876e20d7017a', '已发布知识', 'pub', 'A14_A08_A08', NULL, 'auth', '402888816686bff701668767976b010b', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:47:13', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/overdue/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876df2030176', '知识回收', 'pub', 'A14_A08_A07', NULL, 'auth', '402888816686bff701668767976b010b', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:47:01', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/recycle/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876dc49c0172', '搜索历史', 'pub', 'A14_A08_A06', NULL, 'auth', '402888816686bff701668767976b010b', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:46:49', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/searchcon/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876d91f9016e', '版本管理', 'pub', 'A14_A08_A05', NULL, 'auth', '402888816686bff701668767976b010b', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:46:36', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/ekmversion/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876d64d6016a', '维度管理', 'pub', 'A14_A08_A04', NULL, 'auth', '402888816686bff701668767976b010b', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:46:25', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/dimension/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876d354c0166', '评论管理', 'pub', 'A14_A08_A03', NULL, 'auth', '402888816686bff701668767976b010b', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:46:13', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/comments/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876cf4550162', '审核管理', 'pub', 'A14_A08_A02', NULL, 'auth', '402888816686bff701668767976b010b', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:45:56', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/ekmallaudit/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876cbf42015e', '知识库管理', 'pub', 'A14_A08_A01', NULL, 'auth', '402888816686bff701668767976b010b', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:45:42', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/knowbase/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876c748d015a', '我收到的评论', 'pub', 'A14_A07_A02', NULL, 'auth', '402888816686bff70166876760180108', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:45:23', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/comments/becomments/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876c08aa0156', '我发出的评论', 'pub', 'A14_A07_A01', NULL, 'auth', '402888816686bff70166876760180108', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:44:56', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/comments/mycomments/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876bceb80152', '待审核的知识', 'pub', 'A14_A06_A02', NULL, 'auth', '402888816686bff7016687672ec30105', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:44:41', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/ekmbeaudit/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876ba547014e', '我审核的知识', 'pub', 'A14_A06_A01', NULL, 'auth', '402888816686bff7016687672ec30105', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:44:30', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/ekmaudit/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876b68e2014a', '被收藏的知识', 'pub', 'A14_A05_A02', NULL, 'auth', '402888816686bff701668766d10b0102', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:44:15', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/ekmbecollect/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876b27500147', '我收藏的知识', 'pub', 'A14_A05_A01', NULL, 'auth', '402888816686bff701668766d10b0102', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:43:58', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/ekmcollect/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876af24a0144', '分享给我的知识', 'pub', 'A14_A04_A02', NULL, 'auth', '402888816686bff701668766827600ff', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:43:44', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/ekmbeshare/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876ab5450140', '我分享的知识', 'pub', 'A14_A04_A01', NULL, 'auth', '402888816686bff701668766827600ff', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:43:29', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/ekmshare/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876a7792013c', '浏览历史', 'pub', 'A14_A03_A02', NULL, 'auth', '402888816686bff70166876640a400fc', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:43:13', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/record/view/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876a43b30138', '访客记录', 'pub', 'A14_A03_A01', NULL, 'auth', '402888816686bff70166876640a400fc', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:43:00', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/record/visitors/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff701668769daff0134', '回收站', 'pub', 'A14_A02_A07', NULL, 'auth', '402888816686bff701668765e45c00f9', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:42:33', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/self/recycle/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff701668769ac410130', '已撤回', 'pub', 'A14_A02_A06', NULL, 'auth', '402888816686bff701668765e45c00f9', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:42:21', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/self/downed/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876977eb012c', '被驳回', 'pub', 'A14_A02_A05', NULL, 'auth', '402888816686bff701668765e45c00f9', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:42:08', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/self/rejected/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876942360128', '审核中', 'pub', 'A14_A02_A04', NULL, 'auth', '402888816686bff701668765e45c00f9', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:41:54', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/self/auditing/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff701668768f97b0124', '已发布', 'pub', 'A14_A02_A03', NULL, 'auth', '402888816686bff701668765e45c00f9', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:41:35', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/self/published/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff701668768b2440120', '全部知识', 'pub', 'A14_A02_A02', NULL, 'auth', '402888816686bff701668765e45c00f9', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:41:17', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/self/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff7016687688d83011c', '我的知识库', 'pub', 'A14_A02_A01', NULL, 'auth', '402888816686bff701668765e45c00f9', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:41:08', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/knowbase/mybase/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876852c90118', '知识管理', 'pub', 'A14_A01_A02', NULL, 'auth', '402888816686bff70166876563ed00f6', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:40:53', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/knowledge/add.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876808a40114', '知识门户', 'pub', 'A14_A01_A01', NULL, 'auth', '402888816686bff70166876563ed00f6', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:40:34', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff701668767c78b010e', '业务配置', 'pub', 'A14_A09', NULL, 'auth', '402888816686bff7016687649b8300f1', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:40:17', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff701668767976b010b', '知识管理', 'pub', 'A14_A08', NULL, 'auth', '402888816686bff7016687649b8300f1', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:40:05', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876760180108', '评论库', 'pub', 'A14_A07', NULL, 'auth', '402888816686bff7016687649b8300f1', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:39:50', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff7016687672ec30105', '审核库', 'pub', 'A14_A06', NULL, 'auth', '402888816686bff7016687649b8300f1', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:39:38', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff701668766d10b0102', '收藏库', 'pub', 'A14_A05', NULL, 'auth', '402888816686bff7016687649b8300f1', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:39:14', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff701668766827600ff', '分享库', 'pub', 'A14_A04', NULL, 'auth', '402888816686bff7016687649b8300f1', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:38:54', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876640a400fc', '相关记录', 'pub', 'A14_A03', NULL, 'auth', '402888816686bff7016687649b8300f1', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:38:37', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff701668765e45c00f9', '我的知识', 'pub', 'A14_A02', NULL, 'auth', '402888816686bff7016687649b8300f1', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:38:13', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876563ed00f6', '知识总览', 'pub', 'A14_A01', NULL, 'auth', '402888816686bff7016687649b8300f1', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:37:40', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff7016687649b8300f1', '知识库', 'pub', 'A14', NULL, 'auth', '402888815d2fe37f015d2fe75cc80002', NULL, NULL, '<i class=\"kfont\" style=\"position: relative;\">&#xe62a;</i>', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:36:49', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/index.html', 'webim', '1', NULL, 'left');
 

alter table uk_agentservice modify session varchar(50);
alter table uk_agentuser modify sessionid varchar(50);

alter table uk_blacklist modify sessionid varchar(50);

alter table uk_kbs_topic modify sessionid varchar(50);
alter table uk_userevent modify sessionid varchar(50);
alter table uk_workorders modify sessionid varchar(50);
alter table uk_xiaoe_scene modify sessionid varchar(50);
alter table uk_xiaoe_topic modify sessionid varchar(50);
 
 ALTER TABLE uk_qc_mission ADD agentdata text DEFAULT NULL COMMENT '会话质检（访客用户名）';
 
 CREATE TABLE `uk_qc_activity` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `NAME` varchar(50) DEFAULT NULL COMMENT '名称',
  `CODE` varchar(50) DEFAULT NULL COMMENT '代码',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  `ORGI` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `USERNAME` varchar(50) DEFAULT NULL COMMENT '创建人',
  `STATUS` varchar(50) DEFAULT NULL COMMENT '任务状态',
  `PARENTID` varchar(32) DEFAULT NULL COMMENT '上级ID',
  `ACTID` varchar(32) DEFAULT NULL COMMENT '活动ID',
  `INX` int(11) DEFAULT '0' COMMENT '分类排序序号',
  `NAMENUM` int(11) DEFAULT '0' COMMENT '批次包含的名单总数',
  `VALIDNUM` int(11) DEFAULT '0' COMMENT '批次包含的有效名单总数',
  `INVALIDNUM` int(11) DEFAULT '0' COMMENT '批次包含的无效名单总数',
  `ASSIGNED` int(11) DEFAULT '0' COMMENT '已分配名单总数',
  `NOTASSIGNED` int(11) DEFAULT '0' COMMENT '未分配名单总数',
  `ENABLE` tinyint(4) NOT NULL DEFAULT '0' COMMENT '分类状态',
  `DATASTATUS` tinyint(4) DEFAULT '0' COMMENT '数据状态',
  `AREA` text COMMENT '分类描述',
  `imptype` varchar(50) DEFAULT NULL COMMENT '导入类型',
  `batchtype` varchar(32) DEFAULT NULL COMMENT '批次类型',
  `ORGAN` varchar(32) DEFAULT NULL COMMENT '部门',
  `impurl` text COMMENT '导入URL',
  `filetype` varchar(50) DEFAULT NULL COMMENT '文件类型',
  `dbtype` varchar(50) DEFAULT NULL COMMENT '数据库类型',
  `jdbcurl` text COMMENT '数据库URL',
  `driverclazz` varchar(255) DEFAULT NULL COMMENT '数据库驱动',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `DESCRIPTION` text COMMENT '描述信息',
  `execnum` int(11) DEFAULT '0' COMMENT '导入次数',
  `SOURCE` varchar(255) DEFAULT NULL COMMENT '来源',
  `CLAZZ` varchar(255) DEFAULT NULL COMMENT '执行的Resource类',
  `TASKFIRETIME` datetime DEFAULT NULL COMMENT '启动时间',
  `CRAWLTASKID` varchar(255) DEFAULT NULL COMMENT '任务ID',
  `EMAIL` varchar(255) DEFAULT NULL COMMENT '邮件地址',
  `NICKNAME` varchar(255) DEFAULT NULL COMMENT '昵称',
  `USERID` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `TASKTYPE` varchar(255) DEFAULT NULL COMMENT '任务类型',
  `TASKID` varchar(255) DEFAULT NULL COMMENT '任务ID',
  `FETCHER` smallint(6) NOT NULL COMMENT '当前状态',
  `PAUSE` smallint(6) NOT NULL COMMENT '暂停',
  `PLANTASK` smallint(6) NOT NULL COMMENT '计划任务',
  `SECURE_ID` varchar(32) DEFAULT NULL COMMENT '安全规则ID',
  `CONFIGURE_ID` varchar(32) DEFAULT NULL COMMENT '配置项ID',
  `TAKSPLAN_ID` varchar(32) DEFAULT NULL COMMENT '计划任务ID',
  `CRAWLTASK` varchar(32) DEFAULT NULL COMMENT '当前任务状态',
  `TARGETTASK` varchar(32) DEFAULT NULL COMMENT '目标任务',
  `STARTINDEX` int(11) DEFAULT NULL COMMENT '开始位置',
  `LASTDATE` datetime DEFAULT NULL COMMENT '最后更新时间',
  `CREATETABLE` tinyint(4) DEFAULT NULL COMMENT '是否创建数据表',
  `MEMO` text COMMENT '备注',
  `NEXTFIRETIME` datetime DEFAULT NULL COMMENT '下次启动时间',
  `CRONEXP` varchar(255) DEFAULT NULL COMMENT 'CRON表达式',
  `TASKSTATUS` varchar(32) DEFAULT NULL COMMENT '任务执行状态',
  `usearea` varchar(255) DEFAULT NULL COMMENT '用户数据',
  `areafield` varchar(255) DEFAULT NULL COMMENT '用户字段',
  `areafieldtype` varchar(32) DEFAULT NULL COMMENT '用户数据类型',
  `arearule` varchar(255) DEFAULT NULL COMMENT '用户数据值',
  `minareavalue` varchar(255) DEFAULT NULL COMMENT '最小值',
  `maxareavalue` varchar(255) DEFAULT NULL COMMENT '最大值',
  `formatstr` varchar(255) DEFAULT NULL COMMENT '格式化字符串',
  `DATAID` varchar(1000) DEFAULT NULL COMMENT '报表id字符串',
  `DICID` varchar(1000) DEFAULT NULL COMMENT '目录id字符串',
  `taskinfo` longtext COMMENT 'taskinfo信息',
  `FILTERID` varchar(32) DEFAULT NULL COMMENT '筛选表单ID',
  `FETCH_SIZE` int(11) DEFAULT NULL COMMENT '采集数据窗口大小',
  `LASTINDEX` int(11) DEFAULT NULL COMMENT '结束位置',
  `PAGES` int(11) DEFAULT NULL COMMENT '页面数量',
  `plantaskreadtorun` tinyint(4) DEFAULT NULL COMMENT '计划任务',
  `priority` int(11) DEFAULT NULL COMMENT '策略',
  `runserver` varchar(100) DEFAULT NULL COMMENT '运行服务器',
  `actype` varchar(50) DEFAULT NULL COMMENT '活动类型',
  `distype` varchar(32) DEFAULT NULL COMMENT '分配类型',
  `distpolicy` varchar(50) DEFAULT NULL COMMENT '分配策略',
  `policynum` int(11) DEFAULT NULL COMMENT '分配数量',
  `busstype` varchar(32) DEFAULT NULL COMMENT '业务类型',
  `disnum` varchar(32) DEFAULT NULL COMMENT '默认分配数量',
  `execmd` varchar(32) DEFAULT NULL COMMENT '执行次数',
  `exectarget` varchar(50) DEFAULT NULL COMMENT '执行分配目标',
  `exectype` varchar(32) DEFAULT NULL COMMENT '执行类型',
  `execto` varchar(32) DEFAULT NULL COMMENT '回收数据位置',
  `threads` int(11) DEFAULT '0' COMMENT '启动任务的线程数量',
  `siptrunk` varchar(32) DEFAULT NULL COMMENT '线路信息',
  `province` varchar(32) DEFAULT NULL COMMENT '线路所在省份',
  `city` varchar(32) DEFAULT NULL COMMENT '线路所在城市',
  `prefix` tinyint(4) DEFAULT '0' COMMENT '线路资源拨号前缀',
  `reportid` varchar(32) DEFAULT NULL COMMENT '数据表ID',
  `mapping` tinyint(4) DEFAULT '0' COMMENT '默认映射结构',
  `organid` varchar(32) DEFAULT NULL COMMENT '获取远程批次时的部门ID',
  `localserver` varchar(255) DEFAULT NULL COMMENT '获取远程的本地服务URL',
  PRIMARY KEY (`ID`,`ENABLE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='作业调度任务/活动/批次表';
 
 CREATE TABLE `uk_qc_activity_task` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `NAME` varchar(50) DEFAULT NULL COMMENT '任务名称',
  `CODE` varchar(50) DEFAULT NULL COMMENT '任务代码',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  `ORGI` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `USERNAME` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `STATUS` varchar(50) DEFAULT NULL COMMENT '状态',
  `PARENTID` varchar(32) DEFAULT NULL COMMENT '上级ID',
  `ACTID` varchar(32) DEFAULT NULL COMMENT '活动ID',
  `INX` int(11) DEFAULT '0' COMMENT '分类排序序号',
  `NAMENUM` int(11) DEFAULT '0' COMMENT '批次包含的名单总数',
  `VALIDNUM` int(11) DEFAULT '0' COMMENT '批次包含的有效名单总数',
  `INVALIDNUM` int(11) DEFAULT '0' COMMENT '批次包含的无效名单总数',
  `ASSIGNED` int(11) DEFAULT '0' COMMENT '已分配名单总数',
  `NOTASSIGNED` int(11) DEFAULT '0' COMMENT '未分配名单总数',
  `ENABLE` tinyint(4) DEFAULT '0' COMMENT '分类状态',
  `DATASTATUS` tinyint(4) DEFAULT '0' COMMENT '数据状态',
  `ORGAN` varchar(32) DEFAULT NULL COMMENT '部门',
  `DESCRIPTION` text COMMENT '备注信息',
  `execnum` int(11) DEFAULT '0' COMMENT '导入次数',
  `SOURCE` varchar(255) DEFAULT NULL COMMENT '来源信息',
  `BATID` varchar(32) DEFAULT NULL COMMENT '批次ID',
  `FILTERID` varchar(32) DEFAULT NULL COMMENT '筛选ID',
  `ASSIGNEDORGAN` int(11) DEFAULT '0' COMMENT '分配给部门',
  `exectype` varchar(32) DEFAULT NULL COMMENT '执行类型',
  `renum` int(11) DEFAULT '0' COMMENT '分配数量',
  `reorgannum` int(11) DEFAULT '0' COMMENT '分配到部门数量',
  `assignedai` int(11) DEFAULT '0' COMMENT '分配到AI的名单数量',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='质检活动 - 任务表';
 
CREATE TABLE `uk_qc_formfilter` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '筛选表单名称',
  `code` varchar(50) DEFAULT NULL COMMENT '筛选表单代码',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `username` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `filtertype` varchar(32) DEFAULT NULL COMMENT '筛选类型（callevent通话筛选/workorders工单筛选/agentservice会话筛选）',
  `tablename` varchar(50) DEFAULT NULL COMMENT '对应的数据表名',
  `datastatus` tinyint(4) DEFAULT '0' COMMENT '数据状态',
  `organ` varchar(32) DEFAULT NULL COMMENT '部门',
  `description` text COMMENT '备注信息',
  `filternum` int(11) DEFAULT '0' COMMENT '筛选次数',
  `conditional` int(11) DEFAULT '0' COMMENT '条件个数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='QC质检 - 筛选表单';

CREATE TABLE `uk_qc_formfilter_item` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `qcformfilterid` varchar(32) DEFAULT NULL COMMENT '筛选器ID',
  `field` varchar(32) DEFAULT NULL COMMENT '字段',
  `cond` varchar(32) DEFAULT NULL COMMENT '条件',
  `value` varchar(32) DEFAULT NULL COMMENT '取值',
  `contype` varchar(32) DEFAULT NULL COMMENT '条件类型',
  `itemtype` varchar(32) DEFAULT NULL COMMENT '类型',
  `comp` varchar(50) DEFAULT NULL COMMENT '逻辑条件',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='QC质检 - 筛选项';

CREATE TABLE `uk_qc_filter_his` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `NAME` varchar(50) DEFAULT NULL COMMENT '筛选名称',
  `CODE` varchar(50) DEFAULT NULL COMMENT '筛选代码',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  `ORGI` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `USERNAME` varchar(50) DEFAULT NULL COMMENT '用户名',
  `STATUS` varchar(50) DEFAULT NULL COMMENT '状态',
  `PARENTID` varchar(32) DEFAULT NULL COMMENT '上级ID',
  `ACTID` varchar(32) DEFAULT NULL COMMENT '活动ID',
  `INX` int(11) DEFAULT '0' COMMENT '分类排序序号',
  `NAMENUM` int(11) DEFAULT '0' COMMENT '批次包含的名单总数',
  `VALIDNUM` int(11) DEFAULT '0' COMMENT '批次包含的有效名单总数',
  `INVALIDNUM` int(11) DEFAULT '0' COMMENT '批次包含的无效名单总数',
  `ASSIGNED` int(11) DEFAULT '0' COMMENT '已分配名单总数',
  `NOTASSIGNED` int(11) DEFAULT '0' COMMENT '未分配名单总数',
  `ENABLE` tinyint(4) DEFAULT '0' COMMENT '分类状态',
  `DATASTATUS` tinyint(4) DEFAULT '0' COMMENT '数据状态',
  `ORGAN` varchar(32) DEFAULT NULL COMMENT '部门',
  `DESCRIPTION` text COMMENT '备注',
  `execnum` int(11) DEFAULT '0' COMMENT '导入次数',
  `SOURCE` varchar(255) DEFAULT NULL COMMENT '来源',
  `BATID` varchar(32) DEFAULT NULL COMMENT '批次ID',
  `FILTERID` varchar(32) DEFAULT NULL COMMENT '筛选表单ID',
  `ASSIGNEDORGAN` int(11) DEFAULT '0' COMMENT '分配部门',
  `exectype` varchar(32) DEFAULT NULL COMMENT '执行类型',
  `renum` int(11) DEFAULT '0' COMMENT '分配数量',
  `reorgannum` int(11) DEFAULT '0' COMMENT '部门分配数量',
  `assignedai` int(11) DEFAULT '0' COMMENT '分配到AI的名单数量',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='筛选记录表';

ALTER TABLE uk_ekm_knowledge ADD attr text DEFAULT NULL COMMENT '知识分类id,维度分类id';
ALTER TABLE uk_ekm_knowledge_master ADD attr text DEFAULT NULL COMMENT '知识分类id,维度分类id';
ALTER TABLE uk_ekm_knowledge_publish ADD attr text DEFAULT NULL COMMENT '知识分类id,维度分类id';
ALTER TABLE uk_ekm_knowledge_verison ADD attr text DEFAULT NULL COMMENT '知识分类id,维度分类id';

CREATE TABLE `uk_spt_salespatter` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL COMMENT '话术名称',
  `type` varchar(32) DEFAULT NULL COMMENT '话术类型',
  `scene` varchar(32) DEFAULT NULL COMMENT '话术适用场景',
  `welword` varchar(255) DEFAULT NULL COMMENT '话术欢迎语（文字）',
  `welvoice` varchar(255) DEFAULT NULL COMMENT '话术欢迎语ID（语音）',
  `weltype` varchar(32) DEFAULT NULL COMMENT '话术欢迎语类型',
  `endword` varchar(255) DEFAULT NULL COMMENT '话术结束语（文字）',
  `endvoice` varchar(255) DEFAULT NULL COMMENT '话术结束语ID（语音）',
  `endtype` varchar(32) DEFAULT NULL COMMENT '话术结束语类型',
  `totalscore` varchar(32) DEFAULT NULL COMMENT '参考评分值',
  `score` varchar(32) DEFAULT '0' COMMENT '是否评分（0否1是）',
  `memo` text COMMENT '备注',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `prostatus` varchar(32) DEFAULT '0' COMMENT '话术状态（0未发布1发布）',
  `sumscore` varchar(32) DEFAULT NULL COMMENT '总评分值',
  `description` text COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='话术调查表';

ALTER TABLE uk_callcenter_event ADD qualitydistime datetime DEFAULT NULL COMMENT '质检分配的时间';
ALTER TABLE uk_workorders ADD qualitydistime datetime DEFAULT NULL COMMENT '质检分配的时间';
ALTER TABLE uk_agentservice ADD qualitydistime datetime DEFAULT NULL COMMENT '质检分配的时间';

ALTER TABLE uk_qc_formfilter ADD execnum int DEFAULT '0' COMMENT '执行次数';

CREATE TABLE `uk_qc_result` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `remarks` text COMMENT '质检备注',
  `adcom` text COMMENT '优点评语',
  `qacom` text COMMENT 'QA评语',
  `imcom` text COMMENT '改进评语',
  `score` int(11) DEFAULT '0' COMMENT '实际得分',
  `totalscore` int(11) DEFAULT '0' COMMENT '总分',
  `passscore` int(11) DEFAULT '0' COMMENT '合格分',
  `arithmetic` varchar(32) DEFAULT NULL COMMENT '算分机制(plus评分/minus扣分)',
  `missionid` varchar(32) DEFAULT NULL COMMENT '质检任务id',
  `dataid` varchar(32) DEFAULT NULL COMMENT '数据ID（通话记录ID/工单记录ID/会话记录ID）',
  `qualityuser` varchar(32) DEFAULT NULL COMMENT '实际质检人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='QC质检 - 结果主表';

CREATE TABLE `uk_qc_result_item` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `maxscore` int(32) DEFAULT NULL COMMENT '最高分数',
  `minscore` int(32) DEFAULT NULL COMMENT '最低分数',
  `score` int(32) DEFAULT NULL COMMENT '评分',
  `scheme` text COMMENT '评分方案',
  `remarks` text COMMENT '质检备注',
  `parentid` varchar(32) DEFAULT NULL COMMENT '质检项父级id',
  `type` varchar(32) DEFAULT NULL COMMENT '质检项分类（plus评分/minus扣分/taboo禁忌项）',
  `resultid` varchar(32) DEFAULT NULL COMMENT '质检结果id',
  `itemid` varchar(32) DEFAULT NULL COMMENT '质检项id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='QC质检 - 结果子表';


 ALTER TABLE uk_jobdetail ADD filtertype varchar(50) DEFAULT NULL COMMENT '活动筛选类型';
 
ALTER TABLE uk_workorders ADD assuser varchar(50) DEFAULT NULL COMMENT '分配执行人';
ALTER TABLE uk_callcenter_event ADD assuser varchar(50) DEFAULT NULL COMMENT '分配执行人';
ALTER TABLE uk_agentservice ADD assuser varchar(50) DEFAULT NULL COMMENT '分配执行人';

ALTER TABLE uk_workorders ADD templateid varchar(50) DEFAULT NULL COMMENT '质检模板id';
ALTER TABLE uk_callcenter_event ADD templateid varchar(50) DEFAULT NULL COMMENT '质检模板id';
ALTER TABLE uk_agentservice ADD templateid varchar(50) DEFAULT NULL COMMENT '质检模板id';

ALTER TABLE uk_qc_result ADD status varchar(32) DEFAULT NULL COMMENT '状态（已质检done、已归档archive、复检中recheck）';
ALTER TABLE uk_qc_result ADD archivedate datetime DEFAULT NULL COMMENT '归档日期';

ALTER TABLE uk_qc_result ADD qualitytype varchar(32) DEFAULT NULL COMMENT '质检类型（callevent通话/workorders工单/agentservice会话）';

CREATE TABLE `uk_qc_appeal` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `remarks` text COMMENT '申诉备注',
  `dataid` varchar(32) DEFAULT NULL COMMENT '数据ID（通话记录ID/工单记录ID/会话记录ID）',
  `resultid` varchar(32) DEFAULT NULL COMMENT '质检结果id',
  `appealuser` varchar(32) DEFAULT NULL COMMENT '申诉人',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='QC质检 - 质检申诉表';

 ALTER TABLE uk_jobdetail ADD templateid varchar(50) DEFAULT NULL COMMENT '质检模板ID';
 
 CREATE TABLE `uk_qc_mission_his` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `dataid` varchar(50) DEFAULT NULL COMMENT '数据ID（uk_callcenter_event 通话/uk_workorders 工单/uk_agentservice 在线客服）',
  `templateid` varchar(50) DEFAULT NULL COMMENT '质检模板ID',
  `actid` varchar(50) DEFAULT NULL COMMENT '质检活动ID',
  `formfilterid` varchar(32) DEFAULT NULL COMMENT '质检筛选表单ID',
  `filterid` varchar(32) DEFAULT NULL COMMENT '质检筛选记录ID',
  `taskid` varchar(32) DEFAULT NULL COMMENT '质检任务ID',
  `datastatus` tinyint(4) DEFAULT '0' COMMENT '数据状态',
  `qualitystatus` varchar(20) DEFAULT NULL COMMENT '质检状态',
  `qualitydisorgan` varchar(32) DEFAULT NULL COMMENT '分配的质检部门',
  `qualitydisuser` varchar(32) DEFAULT NULL COMMENT '分配的质检用户',
  `qualityorgan` varchar(32) DEFAULT NULL COMMENT '实际质检部门',
  `qualityuser` varchar(32) DEFAULT NULL COMMENT '实际质检人',
  `qualityscore` int(11) DEFAULT '0' COMMENT '质检得分',
  `qualitytime` datetime DEFAULT NULL COMMENT '质检时间',
  `qualitytype` varchar(20) DEFAULT NULL COMMENT '质检类型',
  `agentdata` text COMMENT '会话质检（访客用户名）',
  `organ` varchar(50) DEFAULT NULL COMMENT '部门',
  `orgi` varchar(50) DEFAULT NULL COMMENT '租户ID',
  `assuser` varchar(50) DEFAULT NULL COMMENT '分配执行人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='QC质检 - 任务历史表';
