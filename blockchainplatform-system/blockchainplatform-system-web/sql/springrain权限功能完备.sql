/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 16/07/2021 09:39:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ali_payconfig
-- ----------------------------
DROP TABLE IF EXISTS `ali_payconfig`;
CREATE TABLE `ali_payconfig`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `privateKey` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `aliPayPublicKey` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `appId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `serviceUrl` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `charset` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `signType` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `format` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `certPath` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `alipayPublicCertPath` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `rootCertPath` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `encryptType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `aesKey` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付宝的配置信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ali_payconfig
-- ----------------------------

-- ----------------------------
-- Table structure for branch_table
-- ----------------------------
DROP TABLE IF EXISTS `branch_table`;
CREATE TABLE `branch_table`  (
  `branch_id` bigint(0) NOT NULL,
  `xid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `transaction_id` bigint(0) NULL DEFAULT NULL,
  `resource_group_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `resource_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `branch_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` tinyint(0) NULL DEFAULT NULL,
  `client_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `application_data` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`branch_id`) USING BTREE,
  INDEX `idx_xid`(`xid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of branch_table
-- ----------------------------

-- ----------------------------
-- Table structure for global_table
-- ----------------------------
DROP TABLE IF EXISTS `global_table`;
CREATE TABLE `global_table`  (
  `xid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `transaction_id` bigint(0) NULL DEFAULT NULL,
  `status` tinyint(0) NOT NULL,
  `application_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `transaction_service_group` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `transaction_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `timeout` int(0) NULL DEFAULT NULL,
  `begin_time` bigint(0) NULL DEFAULT NULL,
  `application_data` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`xid`) USING BTREE,
  INDEX `idx_gmt_modified_status`(`gmt_modified`, `status`) USING BTREE,
  INDEX `idx_transaction_id`(`transaction_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of global_table
-- ----------------------------

-- ----------------------------
-- Table structure for lock_table
-- ----------------------------
DROP TABLE IF EXISTS `lock_table`;
CREATE TABLE `lock_table`  (
  `row_key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `xid` varchar(96) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `transaction_id` bigint(0) NULL DEFAULT NULL,
  `branch_id` bigint(0) NOT NULL,
  `resource_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `table_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pk` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`row_key`) USING BTREE,
  INDEX `idx_branch_id`(`branch_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lock_table
-- ----------------------------

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `mintitle` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '摘要',
  `keywords` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关键字',
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `lookcount` int(0) NULL DEFAULT NULL COMMENT '打开次数',
  `createPerson` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `createDate` datetime(0) NOT NULL COMMENT '创建时间',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态  0未审核  1审核通过',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '内容表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_article
-- ----------------------------

-- ----------------------------
-- Table structure for t_attachment
-- ----------------------------
DROP TABLE IF EXISTS `t_attachment`;
CREATE TABLE `t_attachment`  (
    `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id主键',
    `orgId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门id',
    `orgTypePathKey` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'kjj' COMMENT 'URL路径中的部门类型,例如 URL路径中的 kjj ',
    `businessId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务ID,用于业务关联查询',
    `attachmentType` int(0) NULL DEFAULT NULL COMMENT '附件类型,1.政策附件.2.企业认证文件3.专家认证文件.4.企业个人认证文件.0.其他文件',
    `fileName` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件名称',
    `fileURL` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路径',
    `suffix` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
    `fileSize` int(0) NOT NULL COMMENT '文件大小,单位K',
    `lastDownTime` datetime(0) NULL DEFAULT NULL COMMENT '最后下载时间',
    `sortno` int(0) NOT NULL DEFAULT 0 COMMENT '排序,查询时倒叙排列',
    `active` int(0) NOT NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
    `createUser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
    `createTime` datetime(0) NOT NULL COMMENT '上传时间',
    `updateUser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
    `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `md5Code` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件的md5值',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '附件表(产业大脑)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_auditlog_history_2019
-- ----------------------------
DROP TABLE IF EXISTS `t_auditlog_history_2019`;
CREATE TABLE `t_auditlog_history_2019`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `operationType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `operatorName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人姓名',
  `preValue` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '旧值',
  `curValue` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '新值',
  `operationTime` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `operationClass` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类',
  `operationClassID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '记录ID',
  `bak1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_auditlog_history_2019
-- ----------------------------

-- ----------------------------
-- Table structure for t_auditlog_history_2020
-- ----------------------------
DROP TABLE IF EXISTS `t_auditlog_history_2020`;
CREATE TABLE `t_auditlog_history_2020`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `operationType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `operatorName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人姓名',
  `preValue` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '旧值',
  `curValue` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '新值',
  `operationTime` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `operationClass` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类',
  `operationClassID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '记录ID',
  `bak1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_auditlog_history_2020
-- ----------------------------

-- ----------------------------
-- Table structure for t_dic_data
-- ----------------------------
DROP TABLE IF EXISTS `t_dic_data`;
CREATE TABLE `t_dic_data`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `code` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `val` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '值',
  `pid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父ID',
  `sortno` int(0) NULL DEFAULT NULL COMMENT '排序',
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `active` int(0) NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `typekey` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型',
  `bak1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公共字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dic_data
-- ----------------------------
INSERT INTO `t_dic_data` VALUES ('16b80bfb-f0ee-47a0-ba94-cc256abaed17', '专科', '', NULL, 'xueli', NULL, '学历-专科', 1, '2021-05-27 10:47:59', 'xueli', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('20210527110527493503158893', '测试', NULL, NULL, 'root', NULL, '测试测试测试测试测试', 1, '2021-05-27 11:05:27', 'test', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('20210527155300778320365969', 'abc', NULL, '111', '20210527110527493503158893', NULL, '12345656', 1, '2021-05-27 15:53:01', 'test', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('20210528155728946195734909', 'dfhghgfdh', NULL, 'dfgsd', '20210527110527493503158893', NULL, 'asfff', 1, '2021-05-28 15:57:29', 'test', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('7ed23330-5538-4943-8678-0c5a2121cf57', '高中', '', NULL, 'xueli', NULL, '学历-高中', 1, '2021-05-27 10:47:59', 'xueli', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('7ed23330-e69f-48d0-9760-b2eae6af039b', '本科', '', NULL, 'xueli', NULL, '学历-本科', 1, '2021-05-27 10:47:59', 'xueli', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('936db407-ae1-45a7-a657-b60580e2a77a', '汉族', '101', NULL, 'minzu', NULL, '民族-汉族', 1, '2021-05-27 10:47:59', 'minzu', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('936db407-ae2-45a7-a657-b60580e2a77a', '回族', '', NULL, 'minzu', NULL, '民族-回族', 1, '2021-05-27 10:47:59', 'minzu', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('936db407-ae3-45a7-a657-b60580e2a77a', '一级', '', NULL, 'grade', NULL, '年级-一级', 1, '2021-05-27 10:47:59', 'grade', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('936db407-ae4-45a7-a657-b60580e2a77a', '二级', '', NULL, 'grade', NULL, '年级-二级', 1, '2021-05-27 10:47:59', 'grade', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('bumenleixing', '部门类型', '', '', 'root', NULL, '部门类型', 1, '2021-05-27 10:47:59', 'bumenleixing', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('grade', '年级', NULL, '', 'root', NULL, '年级', 1, '2021-05-27 10:47:59', 'grade', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('hbc1744b-e69f-48d0-4536-b2esh6fyabcd', '部门', NULL, '100', 'bumenleixing', NULL, '部门类型-部门', 1, '2021-05-27 10:47:59', 'bumenleixing', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('hbc1744b-e69f-48d0-4536-b2esh6fydfgf', '总平台', NULL, '900', 'bumenleixing', NULL, '部门类型-总平台', 1, '2021-05-27 10:47:59', 'bumenleixing', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('hbc1744b-e69f-48d0-4536-b2esh6fytdsd', '门店', NULL, '0', 'bumenleixing', NULL, '部门类型-门店', 1, '2021-05-27 10:47:59', 'bumenleixing', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('hbc1744b-e69f-48d0-4536-b2esh6fytdse', '分公司', NULL, '200', 'bumenleixing', NULL, '部门类型-分公司', 1, '2021-05-27 10:47:59', 'bumenleixing', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('hbc1744b-e69f-48d0-4536-b2esh6fytdsg', '集团公司', NULL, '300', 'bumenleixing', NULL, '部门类型-集团公司', 1, '2021-05-27 10:47:59', 'bumenleixing', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('minzu', '民族', NULL, NULL, 'root', NULL, '民族', 1, '2021-05-27 10:47:59', 'minzu', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('xueli', '学历', NULL, NULL, 'root', NULL, '学历', 1, '2021-05-27 10:47:59', 'xueli', NULL, NULL, NULL, NULL, NULL);

INSERT INTO `t_dic_data`(`id`, `name`, `code`, `val`, `pid`, `sortno`, `remark`, `active`, `createTime`, `typekey`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('20210723135711733685837358', '用户类型', NULL, NULL, 'root', NULL, '用户类型', 1, '2021-07-23 13:57:12', 'userType', 'down', NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data`(`id`, `name`, `code`, `val`, `pid`, `sortno`, `remark`, `active`, `createTime`, `typekey`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('20210723135741551838580253', '会员', '0', '0', '20210723135711733685837358', NULL, '会员', 1, '2021-07-23 13:57:42', 'userType', 'down', NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data`(`id`, `name`, `code`, `val`, `pid`, `sortno`, `remark`, `active`, `createTime`, `typekey`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('20210723140215764144543685', '员工', '1', '1', '20210723135711733685837358', NULL, '员工', 1, '2021-07-23 14:02:16', 'userType', 'down', NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data`(`id`, `name`, `code`, `val`, `pid`, `sortno`, `remark`, `active`, `createTime`, `typekey`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('20210723140244378154672799', '注册企业', '3', '3', '20210723135711733685837358', NULL, '注册企业', 1, '2021-07-23 14:02:44', 'userType', 'down', NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data`(`id`, `name`, `code`, `val`, `pid`, `sortno`, `remark`, `active`, `createTime`, `typekey`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('20210723140256927930774407', '专家', '4', '4', '20210723135711733685837358', NULL, '专家', 1, '2021-07-23 14:02:57', 'userType', 'down', NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data`(`id`, `name`, `code`, `val`, `pid`, `sortno`, `remark`, `active`, `createTime`, `typekey`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('20210723140313983200387116', '市局', '5', '5', '20210723135711733685837358', NULL, '市局', 1, '2021-07-23 14:03:14', 'userType', 'down', NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data`(`id`, `name`, `code`, `val`, `pid`, `sortno`, `remark`, `active`, `createTime`, `typekey`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('20210723140331151004848497', '县局', '6', '6', '20210723135711733685837358', NULL, '县局', 1, '2021-07-23 14:03:31', 'userType', 'down', NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data`(`id`, `name`, `code`, `val`, `pid`, `sortno`, `remark`, `active`, `createTime`, `typekey`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('20210723140345340703977427', '系统管理员', '9', '9', '20210723135711733685837358', NULL, '系统管理员', 1, '2021-07-23 14:03:45', 'userType', 'down', NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data`(`id`, `name`, `code`, `val`, `pid`, `sortno`, `remark`, `active`, `createTime`, `typekey`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('20210723140357014600336647', '访客', '10', '10', '20210723135711733685837358', NULL, '访客', 1, '2021-07-23 14:03:57', 'userType', 'down', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_fwlog_history_2019
-- ----------------------------
DROP TABLE IF EXISTS `t_fwlog_history_2019`;
CREATE TABLE `t_fwlog_history_2019`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `startDate` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  `strDate` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问时间(毫秒)',
  `tomcat` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Tomcat',
  `userCode` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `userName` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sessionId` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sessionId',
  `ip` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP',
  `fwUrl` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问菜单',
  `menuName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `isqx` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否有权限访问',
  `bak1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '访问日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_fwlog_history_2019
-- ----------------------------

-- ----------------------------
-- Table structure for t_fwlog_history_2020
-- ----------------------------
DROP TABLE IF EXISTS `t_fwlog_history_2020`;
CREATE TABLE `t_fwlog_history_2020`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `startDate` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  `strDate` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问时间(毫秒)',
  `tomcat` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Tomcat',
  `userCode` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `userName` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sessionId` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sessionId',
  `ip` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP',
  `fwUrl` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问菜单',
  `menuName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `isqx` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否有权限访问',
  `bak1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '访问日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_fwlog_history_2020
-- ----------------------------

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `comcode` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '代码',
  `title` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'vue使用 meta.title',
  `pid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `remark` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `pageurl` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `code` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限显示key,功能，用于按钮显示判断',
  `menuType` int(0) NOT NULL COMMENT '0.功能按钮,1.导航菜单',
  `path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'vue路由地址',
  `keepAlive` tinyint(0) NULL DEFAULT NULL COMMENT 'vue组件使用',
  `component` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'vue组件使用',
  `permission` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'vue组件使用',
  `redirect` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'vue组件使用',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站外url',
  `target` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '窗口标识',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `createUserId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updateUserId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sortno` int(0) NULL DEFAULT 0 COMMENT '排序,查询时倒叙排列',
  `active` int(0) NOT NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `bak1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('081b3344872545448cf5d1804890ab03', '选择专题页', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,50374413883c45ae9b9f8e8d7c7609bf,081b3344872545448cf5d1804890ab03,', '选择专题页', '50374413883c45ae9b9f8e8d7c7609bf', '', '/s/s_PT/adver/xcx/topic/list', NULL, 0, '/s/s_PT/adver/xcx/topic/list', NULL, NULL, NULL, NULL, '/img/iconImg/default.png', NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 4, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('169815aca9cf41d390e7feb6629d361d', '栏目管理', ',business_manager,169815aca9cf41d390e7feb6629d361d,', '栏目管理', 'business_manager', '', '/api/system/cms/channel/list', NULL, 1, '/api/system/cms/channel/list', NULL, NULL, NULL, NULL, '/img/iconImg/icon15.png', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-18 09:38:16', '', 4, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('202105121015501783845', '角色保存', ',system_manager,t_role_list,202105121015501783845,', '角色保存', 't_role_list', NULL, '/api/system/role/save', 't_role_save', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-12 10:15:50', NULL, '2021-05-12 10:15:50', NULL, 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('202105121506575382721', '角色权限保存', ',system_manager,t_role_list,202105121506575382721,', '角色权限保存', 't_role_list', NULL, '/api/system/role/updateRoleMenus', 't_role_menu_save', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-12 15:06:58', NULL, '2021-05-12 15:06:58', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('202105121606286404716', '角色菜单列表', ',system_manager,t_role_list,202105121606286404716,', '角色菜单列表', 't_role_list', NULL, '/api/system/role/getMenusByRoleId', 't_role_menu_list', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-12 16:06:29', NULL, '2021-05-12 16:06:29', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('202105131018232299435', '批量添加子菜单', ',system_manager,t_menu_list,202105131018232299435,', '批量添加子菜单', 't_menu_list', NULL, '/api/system/menu/batchSave', 't_menu_batchSave', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-13 10:18:23', NULL, '2021-05-13 10:23:23', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('202105141502166346976', '角色列表（是否有效：是）', ',system_manager,t_role_list,202105141502166346976,', '角色列表（是否有效：是）', 't_role_list', NULL, '/api/system/role/list', 't_role_list_active', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-14 15:02:17', NULL, '2021-05-14 15:02:17', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('202105141646058894617', '部门列表数据(分页,条件)', ',system_manager,t_org_list,202105141646058894617,', '部门列表数据(分页,条件)', 't_org_list', NULL, '/api/system/org/pageList', 't_org_page_list', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-14 16:46:06', NULL, '2021-05-14 16:46:13', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('202105151252345580670', '添加用户', ',system_manager,t_user_list,202105151252345580670,', '添加用户', 't_user_list', NULL, '/api/system/user/save', 't_user_save', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-15 12:52:35', NULL, '2021-05-15 12:52:35', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('202105170919073863443', '分配角色', ',system_manager,t_user_list,202105170919073863443,', '分配角色', 't_user_list', NULL, '/api/system/user/updateuserrole', 't_user_distribution_role', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-17 09:19:07', NULL, '2021-05-17 09:19:07', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('202105171018334839880', '部门类型列表（字典表数据）', ',system_manager,t_org_list,202105171018334839880,', '部门类型列表（字典表数据）', 't_org_list', NULL, '/api/system/org/orgTypeList', 't_org_type_list', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-17 10:18:33', NULL, '2021-05-17 10:18:33', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('202105171034080407699', '获取用户角色', ',system_manager,t_user_list,202105171034080407699,', '获取用户角色', 't_user_list', NULL, '/api/system/user/getRolesByUserId', 't_user_get_role', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-17 10:34:08', NULL, '2021-05-17 10:34:08', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('202105171053129521252', '首页', ',202105171053129521252,', '首页', '', NULL, '/home', NULL, 1, NULL, NULL, NULL, NULL, NULL, 'dashboard', NULL, NULL, '2021-05-17 10:53:13', NULL, '2021-05-19 09:20:01', NULL, 100, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('202105171110557380782', '新增保存部门', ',system_manager,t_org_list,202105171110557380782,', '新增保存部门', 't_org_list', NULL, '/api/system/org/save', 't_department_add', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-17 11:10:56', NULL, '2021-05-17 11:10:56', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('202105171517522622275', '查询用户的部门树', ',system_manager,t_user_list,202105171517522622275,', '查询用户的部门树', 't_user_list', NULL, '/api/system/org/treeselect', 't_find_user_org_tree', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-17 15:17:52', NULL, '2021-05-17 15:22:08', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20210519172221273150930534', '导出用户', ',system_manager,t_user_list,20210519172221273150930534,', '导出用户', 't_user_list', NULL, '/api/system/user/export', 't_user_export', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-19 17:22:21', NULL, '2021-05-19 17:41:22', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20210520115456171843954745', '导入用户', ',system_manager,t_user_list,20210520115456171843954745,', '导入用户', 't_user_list', NULL, '/api/system/user/import', 't_user_import', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-20 11:54:56', NULL, '2021-05-20 11:54:56', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20210521170043572189307269', '用户管理角色数据', ',system_manager,t_user_list,20210521170043572189307269,', '用户管理角色数据', 't_user_list', NULL, '/api/system/role/list', 't_user_role_list', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:00:44', NULL, '2021-05-21 17:03:41', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20210521170718181613125891', '角色菜单数据', ',system_manager,t_role_list,20210521170718181613125891,', '角色菜单数据', 't_role_list', NULL, '/api/system/menu/list', 't_role_menu_data', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:07:18', NULL, '2021-05-21 17:07:18', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20210525184737580453255296', '下载用户信息导入模板', ',system_manager,t_user_list,20210520115456171843954745,20210525184737580453255296,', '下载用户信息导入模板', '20210520115456171843954745', NULL, '/api/system/user/importTemplate', 't_user_import_template', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-25 18:47:38', NULL, '2021-05-25 18:47:38', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20210526150953853171269157', '字典列表', ',system_manager,dic_manager,20210526150953853171269157,', '字典列表', 'dic_manager', NULL, '/api/system/dicdata/type/list', 't_dictionaries_list', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:09:54', NULL, '2021-05-27 10:00:27', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20210526151754523530237327', '查询当前字典下的子集', ',system_manager,dic_manager,20210526151754523530237327,', '查询当前字典下的子集', 'dic_manager', NULL, '/api/system/dicdata/data/type', 't_dic_data_list', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:17:55', NULL, '2021-05-26 15:17:55', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20210527095451904604564871', '字典单个删除', ',system_manager,dic_manager,20210527095451904604564871,', '字典单个删除', 'dic_manager', NULL, '/api/system/dicdata/delete', 't_dictionaries_delete_one', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-27 09:54:52', NULL, '2021-05-27 09:54:52', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20210527095537252768804665', '字典多个删除', ',system_manager,dic_manager,20210527095537252768804665,', '字典多个删除', 'dic_manager', NULL, '/api/system/dicdata/type/delete/more', 't_dictionaries_delete_more', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-27 09:55:37', NULL, '2021-05-27 09:56:13', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20210527100553713196107663', '字典新增保存', ',system_manager,dic_manager,20210527100553713196107663,', '字典新增保存', 'dic_manager', NULL, '/api/system/dicdata/type/save', 't_dictionaries_save', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-27 10:05:54', NULL, '2021-05-27 10:05:54', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20210527100626495495029605', '字典修改', ',system_manager,dic_manager,20210527100626495495029605,', '字典修改', 'dic_manager', NULL, '/api/system/dicdata/type/update', 't_dictionaries_update', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-27 10:06:26', NULL, '2021-05-27 10:06:26', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20210527101125018646355183', 'id查询数据', ',system_manager,dic_manager,20210527101125018646355183,', 'id查询数据', 'dic_manager', NULL, '/api/system/dicdata/type/look', 't_dictionaries_look', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-27 10:11:25', NULL, '2021-05-27 10:11:25', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20210527113120304570637037', '字典数据列表(子页面)', ',system_manager,dic_manager,20210527113120304570637037,', '字典数据列表(子页面)', 'dic_manager', NULL, '/api/system/dicdata/data/list', 't_dictionaries_child_list', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-27 11:31:20', NULL, '2021-05-27 11:31:20', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20210527163800020317714432', '字典父级导出', ',system_manager,dic_manager,20210527163800020317714432,', '字典父级导出', 'dic_manager', NULL, '/api/system/dicdata/type/list/export', 't_dictionaries_export', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-27 16:38:00', NULL, '2021-05-27 16:38:00', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20210527163849335432594105', '字典数据导出', ',system_manager,dic_manager,20210527163849335432594105,', '字典数据导出', 'dic_manager', NULL, '/api/system/dicdata/data/list/export', 't_dictionaries_child_export', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-27 16:38:49', NULL, '2021-05-27 16:38:49', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('3330456139a241b1a27a7dcd171d7bf1', '拖拽演示网站', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,', '拖拽演示网站', 'f4d7a1bf7ddf43dc9016e1465cd3d9d8', '', '/undifind', NULL, 1, NULL, NULL, NULL, NULL, NULL, 'drag', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:24:48', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('3501ed1e23da40219b4f0fa5b7b2749a', '菜单列表', ',system_manager,t_menu_list,3501ed1e23da40219b4f0fa5b7b2749a,', '菜单列表', 't_menu_list', '', '/api/system/menu/all/list/json', 't_menu_list', 0, '/api/system/menu/list', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 16:00:01', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('50374413883c45ae9b9f8e8d7c7609bf', '微信首页设置', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,50374413883c45ae9b9f8e8d7c7609bf,', '微信首页设置', 's_PT_854e84ec22284834b9055aaea98e910c', '', '/s/s_PT/dragpage/dragPage', NULL, 1, '/s/s_PT/dragpage/dragPage', NULL, NULL, NULL, NULL, '/img/iconImg/default.png', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:37:34', '', 1, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('5cce870b5880479794c2c00535c55ad8', '后台管理', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,', '后台管理', '3330456139a241b1a27a7dcd171d7bf1', '', '/undifind', NULL, 1, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:24:41', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('78287e4ac70546168b2fa68818710470', '保存首页数据', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,50374413883c45ae9b9f8e8d7c7609bf,78287e4ac70546168b2fa68818710470,', '保存首页数据', '50374413883c45ae9b9f8e8d7c7609bf', '', '/s/s_PT/adver/weChat/saveDragJosn', 'undifiend', 0, '/s/s_PT/adver/weChat/saveDragJosn', NULL, NULL, NULL, NULL, '/img/iconImg/default.png', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:38:12', '', 2, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('7cd0678633d5407dba2bd6a1553cadce', '文件下载', ',system_manager,f5203235547342f094a2c126ad4603bb,7cd0678633d5407dba2bd6a1553cadce,', '文件下载', 'f5203235547342f094a2c126ad4603bb', '', '/api/system/file/downfile', 'undifiend', 0, '/api/system/file/downfile', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:25:29', '', 3, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('8c72a4b5e56643ac9a9ca3aeec753c4e', '启用/禁用', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,9efc46fc51304cae8a35d12c942059c9,8c72a4b5e56643ac9a9ca3aeec753c4e,', '启用/禁用', '9efc46fc51304cae8a35d12c942059c9', '', '/s/s_PT/dragpage/updateActive', 'undifiend', 0, '/s/s_PT/dragpage/updateActive', NULL, NULL, NULL, NULL, '/img/iconImg/default.png', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:38:41', '', 2, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('9bccbc28b32e41438c5ac73a5e61ed58', '专题页设置', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,9bccbc28b32e41438c5ac73a5e61ed58,', '专题页设置', 's_PT_854e84ec22284834b9055aaea98e910c', '', '/s/s_PT/dragpage/specialPage/list', NULL, 1, '/s/s_PT/dragpage/specialPage/list', NULL, NULL, NULL, NULL, '/img/iconImg/default.png', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:37:30', '', 2, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('9efc46fc51304cae8a35d12c942059c9', '首页设置', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,9efc46fc51304cae8a35d12c942059c9,', '首页设置', 's_PT_854e84ec22284834b9055aaea98e910c', '', '/s/s_PT/dragpage/1/list', NULL, 1, '/s/s_PT/dragpage/1/list', NULL, NULL, NULL, NULL, '/img/iconImg/default.png', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:37:38', '', 1, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('af298b90f073443bbde4b9e67113d697', '添加/编辑', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,9efc46fc51304cae8a35d12c942059c9,af298b90f073443bbde4b9e67113d697,', '添加/编辑', '9efc46fc51304cae8a35d12c942059c9', '', '/s/s_PT/dragpage/update', 'undifiend', 0, '/s/s_PT/dragpage/update', NULL, NULL, NULL, NULL, '/img/iconImg/default.png', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:38:48', '', 1, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('aff3dc802af540c298af95cb5608fefe', '拖拽页面', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,9efc46fc51304cae8a35d12c942059c9,aff3dc802af540c298af95cb5608fefe,', '拖拽页面', '9efc46fc51304cae8a35d12c942059c9', '', '/s/s_PT/dragpage/drop', 'undifiend', 0, '/s/s_PT/dragpage/drop', NULL, NULL, NULL, NULL, '/img/iconImg/default.png', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:38:26', '', 4, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('b94392f7b8714f64819c5c0222eb134a', '角色修改-系统', ',system_manager,t_role_list,b94392f7b8714f64819c5c0222eb134a,', '角色修改-系统', 't_role_list', '', '/api/system/role/update/admin', NULL, 0, '/api/system/role/update/admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('b9c4e8ecffe949c0b346e1fd0d6b9977', '内容管理', ',business_manager,b9c4e8ecffe949c0b346e1fd0d6b9977,', '内容管理', 'business_manager', '', '/api/system/cms/content/list', NULL, 1, '/api/system/cms/content/list', NULL, NULL, NULL, NULL, 'form', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-18 10:06:20', '', 5, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('business_manager', '业务管理', ',business_manager,', '业务管理', '', '', '/business/manager', NULL, 1, NULL, NULL, NULL, NULL, NULL, 'solution', NULL, NULL, '2019-07-24 11:34:50', '', '2021-05-13 15:52:45', '', 1, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('ca28235dbd234b7585e133e70cc7999a', '文件上传', ',system_manager,f5203235547342f094a2c126ad4603bb,ca28235dbd234b7585e133e70cc7999a,', '文件上传', 'f5203235547342f094a2c126ad4603bb', '', '/api/system/file/uploadFile', '11111', 0, '/api/system/file/uploadFile', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:25:37', '', 1, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('cafda855318c4560874f7fb14419be4f', '楼层商品选择', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,50374413883c45ae9b9f8e8d7c7609bf,cafda855318c4560874f7fb14419be4f,', '楼层商品选择', '50374413883c45ae9b9f8e8d7c7609bf', '', '/s/s_PT/adver/weChat/addGoods', 'undifiend', 0, '/s/s_PT/adver/weChat/addGoods', NULL, NULL, NULL, NULL, '/img/iconImg/default.png', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:37:58', '', 3, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('d6abe682007849869c3a168215ae40d4', 'WEB-INF文件管理', ',system_manager,d6abe682007849869c3a168215ae40d4,', 'WEB-INF文件管理', 'system_manager', '', '/api/system/file/web/list', NULL, 1, '/api/system/file/web/list', NULL, NULL, NULL, NULL, '/img/iconImg/icon20.png', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:45:34', '', 7, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('d7e44d49421e41ef9c3329be68dff6f7', '获取微信首页', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,50374413883c45ae9b9f8e8d7c7609bf,d7e44d49421e41ef9c3329be68dff6f7,', '获取微信首页', '50374413883c45ae9b9f8e8d7c7609bf', '', '/s/s_PT/adver/weChat/dragPageJosn', 'undifiend', 0, '/s/s_PT/adver/weChat/dragPageJosn', NULL, NULL, NULL, NULL, '/img/iconImg/default.png', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:38:18', '', 1, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('dic_manager', '字典管理', ',system_manager,dic_manager,', '字典管理', 'system_manager', '', '/api/system/dicdata/type/list', NULL, 1, '/dictionaries', NULL, NULL, NULL, NULL, 'tag', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-27 09:47:54', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('e51808e351c24a7e9fb4d47392930a2d', '保存新密码', ',system_manager,t_user_list,e51808e351c24a7e9fb4d47392930a2d,', '保存新密码', 't_user_list', '', '/api/system/user/updatePwd', 't_user_reset_pass', 0, '/api/system/user/modifiypwd/save', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-15 10:56:45', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('e614beb39da04bd79797d7fc6ab91d66', '获取专题页json数据', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,50374413883c45ae9b9f8e8d7c7609bf,e614beb39da04bd79797d7fc6ab91d66,', '获取专题页json数据', '50374413883c45ae9b9f8e8d7c7609bf', '', '/s/s_PT/dragpage/specialPageJson', 'undifiend', 0, '/s/s_PT/dragpage/specialPageJson', NULL, NULL, NULL, NULL, '/img/iconImg/default.png', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:38:05', '', 3, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('f41b9f3b4a0d45f5a3b5842ee40e0e96', '站点管理', ',business_manager,f41b9f3b4a0d45f5a3b5842ee40e0e96,', '站点管理', 'business_manager', '', '/api/system/cms/site/list', NULL, 1, '/api/system/cms/site/list', NULL, NULL, NULL, NULL, 'caret-down', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-18 09:53:19', '', 3, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('f4d7a1bf7ddf43dc9016e1465cd3d9d8', '网站', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,', '网站', '', '', '/system/ssss', NULL, 1, NULL, NULL, NULL, NULL, NULL, 'global', NULL, NULL, '2019-07-24 11:35:11', '', '2021-05-18 09:35:52', '', 3, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('f5203235547342f094a2c126ad4603bb', '文件管理', ',system_manager,f5203235547342f094a2c126ad4603bb,', '文件管理', 'system_manager', '', '/api/system/file/list', NULL, 1, '/api/system/file/list', NULL, NULL, NULL, NULL, '/img/iconImg/icon20.png', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:25:42', '', 6, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('f86962e16c214382bd6a7f57a765693f', '删除', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,9efc46fc51304cae8a35d12c942059c9,f86962e16c214382bd6a7f57a765693f,', '删除', '9efc46fc51304cae8a35d12c942059c9', '', '/s/s_PT/dragpage/delete', 'undifiend', 0, '/s/s_PT/dragpage/delete', NULL, NULL, NULL, NULL, '/img/iconImg/default.png', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:38:35', '', 3, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('system_manager', '系统管理', ',system_manager,', '系统管理', '', '', '/system/manager', NULL, 1, NULL, NULL, NULL, NULL, NULL, 'setting', NULL, NULL, '2019-07-24 11:35:11', '', '2021-05-13 15:50:14', '', 2, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('s_PT_854e84ec22284834b9055aaea98e910c', '拖拽网页', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,', '拖拽网页', '5cce870b5880479794c2c00535c55ad8', '', '/undifind', NULL, 1, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/icon5.png', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:24:35', '', 6, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_auditlog_list', '修改日志', ',system_manager,t_auditlog_list,', '修改日志', 'system_manager', '', '/api/system/auditlog/list', NULL, 1, '/api/system/auditlog/list', NULL, NULL, NULL, NULL, '/img/iconImg/icon42.png', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:39:05', '', 1, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_auditlog_look', '查看修改日志', ',system_manager,t_auditlog_list,t_auditlog_look,', '查看修改日志', 't_auditlog_list', NULL, '/api/system/auditlog/look', 'undifiend', 0, '/api/system/auditlog/look', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-18 09:45:07', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_delete', '删除级别', ',system_manager,dic_manager,t_dic_data_grade_list,t_dic_data_grade_delete,', '删除级别', 't_dic_data_grade_list', NULL, '/api/system/dicdata/grade/delete', 'undifiend', 0, '/api/system/dicdata/grade/delete', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-26 10:59:34', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_deletemore', '批量删除级别', ',system_manager,dic_manager,t_dic_data_grade_list,t_dic_data_grade_deletemore,', '批量删除级别', 't_dic_data_grade_list', NULL, '/api/system/dicdata/grade/delete/more', 'undifiend', 0, '/api/system/dicdata/grade/delete/more', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-26 10:59:10', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_list', '级别管理', ',system_manager,dic_manager,t_dic_data_grade_list,', '级别管理', 'dic_manager', '', '/api/system/dicdata/grade/list', NULL, 1, '/api/system/dicdata/grade/list', NULL, NULL, NULL, NULL, 'bars', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-26 09:19:43', '', 1, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_look', '查看级别', ',system_manager,dic_manager,t_dic_data_grade_list,t_dic_data_grade_look,', '查看级别', 't_dic_data_grade_list', NULL, '/api/system/dicdata/grade/look', 'undifiend', 0, '/api/system/dicdata/grade/look', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-26 10:59:16', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_tree', '级别树形结构', ',system_manager,dic_manager,t_dic_data_grade_list,t_dic_data_grade_tree,', '级别树形结构', 't_dic_data_grade_list', NULL, '/api/system/dicdata/grade/tree', 'undifiend', 0, '/api/system/dicdata/grade/tree', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-26 10:59:22', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_update', '修改级别', ',system_manager,dic_manager,t_dic_data_grade_list,t_dic_data_grade_update,', '修改级别', 't_dic_data_grade_list', NULL, '/api/system/dicdata/grade/update', 'undifiend', 0, '/api/system/dicdata/grade/update', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-26 10:59:28', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_delete', '删除民族', ',system_manager,dic_manager,t_dic_data_minzu_list,t_dic_data_minzu_delete,', '删除民族', 't_dic_data_minzu_list', NULL, '/api/system/dicdata/minzu/delete', 'undifiend', 0, '/api/system/dicdata/minzu/delete', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-26 10:59:42', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_deletemore', '批量删除民族', ',system_manager,dic_manager,t_dic_data_minzu_list,t_dic_data_minzu_deletemore,', '批量删除民族', 't_dic_data_minzu_list', NULL, '/api/system/dicdata/minzu/delete/more', 'undifiend', 0, '/api/system/dicdata/minzu/delete/more', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-26 10:59:48', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_list', '民族管理', ',system_manager,dic_manager,t_dic_data_minzu_list,', '民族管理', 'dic_manager', '', '/api/system/dicdata/minzu/list', NULL, 1, '/api/system/dicdata/minzu/list', NULL, NULL, NULL, NULL, 'bars', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-26 09:19:50', '', 1, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_look', '查看民族', ',system_manager,dic_manager,t_dic_data_minzu_list,t_dic_data_minzu_look,', '查看民族', 't_dic_data_minzu_list', NULL, '/api/system/dicdata/minzu/look', 'undifiend', 0, '/api/system/dicdata/minzu/look', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-26 10:59:52', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_tree', '民族树形结构', ',system_manager,dic_manager,t_dic_data_minzu_list,t_dic_data_minzu_tree,', '民族树形结构', 't_dic_data_minzu_list', NULL, '/api/system/dicdata/minzu/tree', 'undifiend', 0, '/api/system/dicdata/minzu/tree', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-26 10:59:57', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_update', '修改民族', ',system_manager,dic_manager,t_dic_data_minzu_list,t_dic_data_minzu_update,', '修改民族', 't_dic_data_minzu_list', NULL, '/api/system/dicdata/minzu/update', 'undifiend', 0, '/api/system/dicdata/minzu/update', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-26 11:00:07', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_delete', '删除学历', ',system_manager,dic_manager,t_dic_data_xueli_list,t_dic_data_xueli_delete,', '删除学历', 't_dic_data_xueli_list', NULL, '/api/system/dicdata/xueli/delete', 'undifiend', 0, '/api/system/dicdata/xueli/delete', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-26 10:58:29', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_deletemore', '批量删除学历', ',system_manager,dic_manager,t_dic_data_xueli_list,t_dic_data_xueli_deletemore,', '批量删除学历', 't_dic_data_xueli_list', NULL, '/api/system/dicdata/xueli/delete/more', 'undifiend', 0, '/api/system/dicdata/xueli/delete/more', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-26 10:58:45', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_list', '学历管理', ',system_manager,dic_manager,t_dic_data_xueli_list,', '学历管理', 'dic_manager', '', '/api/system/dicdata/xueli/list', NULL, 1, '/menus/menu', NULL, NULL, NULL, NULL, 'bars', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-26 09:21:07', '', 3, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_look', '查看学历', ',system_manager,dic_manager,t_dic_data_xueli_list,t_dic_data_xueli_look,', '查看学历', 't_dic_data_xueli_list', NULL, '/api/system/dicdata/xueli/look', 'undifiend', 0, '/api/system/dicdata/xueli/look', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-26 10:58:51', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_tree', '学历树形结构', ',system_manager,dic_manager,t_dic_data_xueli_list,t_dic_data_xueli_tree,', '学历树形结构', 't_dic_data_xueli_list', NULL, '/api/system/dicdata/xueli/tree', 'undifiend', 0, '/api/system/dicdata/xueli/tree', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-26 10:58:56', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_update', '修改学历', ',system_manager,dic_manager,t_dic_data_xueli_list,t_dic_data_xueli_update,', '修改学历', 't_dic_data_xueli_list', NULL, '/api/system/dicdata/xueli/update', 'undifiend', 0, '/api/system/dicdata/xueli/update', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-26 10:59:01', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_fwlog_list', '访问日志', ',system_manager,t_fwlog_list,', '访问日志', 'system_manager', '', '/api/system/fwlog/list', NULL, 1, '/api/system/fwlog/list', NULL, NULL, NULL, NULL, '/img/iconImg/icon42.png', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:26:21', '', 2, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_fwlog_look', '查看访问日志', ',system_manager,t_fwlog_list,t_fwlog_look,', '查看访问日志', 't_fwlog_list', NULL, '/api/system/fwlog/look', 'undifiend', 0, '/api/system/fwlog/look', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:26:14', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_delete', '删除菜单', ',system_manager,t_menu_list,t_menu_delete,', '删除菜单', 't_menu_list', NULL, '/api/system/menu/delete', 't_menu_delete', 0, '/api/system/menu/delete', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_deletemore', '批量删除菜单', ',system_manager,t_menu_list,t_menu_deletemore,', '批量删除菜单', 't_menu_list', NULL, '/api/system/menu/delete/more', NULL, 0, '/api/system/menu/delete/more', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_list', '菜单管理', ',system_manager,t_menu_list,', '菜单管理', 'system_manager', '', '/api/system/menu/all/list/json', NULL, 1, '/menus', NULL, NULL, NULL, NULL, 'appstore', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-27 18:27:08', '', 3, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_look', '查看菜单', ',system_manager,t_menu_list,t_menu_look,', '查看菜单', 't_menu_list', NULL, '/api/system/menu/look', NULL, 0, '/api/system/menu/look', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_save', '保存菜单', ',system_manager,t_menu_list,t_menu_save,', '保存菜单', 't_menu_list', NULL, '/api/system/menu/save', 't_menu_save', 0, '/api/system/menu/save', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-11 09:59:19', NULL, '2021-05-11 09:59:19', NULL, 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_tree', '菜单树形结构', ',system_manager,t_menu_list,t_menu_tree,', '菜单树形结构', 't_menu_list', NULL, '/api/system/menu/tree', NULL, 0, '/api/system/menu/tree', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_update', '修改菜单', ',system_manager,t_menu_list,t_menu_update,', '修改菜单', 't_menu_list', NULL, '/api/system/menu/update', 't_menu_update', 0, '/api/system/menu/update', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_delete', '删除部门', ',system_manager,t_org_list,t_org_delete,', '删除部门', 't_org_list', NULL, '/api/system/org/delete', 't_org_delete', 0, '/api/system/org/delete', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 14:58:43', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_deletemore', '批量删除部门', ',system_manager,t_org_list,t_org_deletemore,', '批量删除部门', 't_org_list', NULL, '/api/system/org/delete/more', NULL, 0, '/api/system/org/delete/more', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_list', '部门管理', ',system_manager,t_org_list,', '部门管理', 'system_manager', '', '/api/system/org/pageList', NULL, 1, '/department', NULL, NULL, NULL, NULL, 'cluster', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-21 17:36:45', '', 8, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_look', '查看部门', ',system_manager,t_org_list,t_org_look,', '查看部门', 't_org_list', NULL, '/api/system/org/look', NULL, 0, '/api/system/org/look', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_tree', '部门树形结构', ',system_manager,t_org_list,t_org_tree,', '部门树形结构', 't_org_list', NULL, '/api/system/org/tree', NULL, 0, '/api/system/org/tree', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_update', '修改部门', ',system_manager,t_org_list,t_org_update,', '修改部门', 't_org_list', NULL, '/api/system/org/update', 't_org_update', 0, '/api/system/org/update', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 14:48:23', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_delete', '删除角色', ',system_manager,t_role_list,t_role_delete,', '删除角色', 't_role_list', NULL, '/api/system/role/delete', 't_role_delete', 0, '/api/system/role/delete', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_deletemore', '批量删除角色', ',system_manager,t_role_list,t_role_deletemore,', '批量删除角色', 't_role_list', NULL, '/api/system/role/delete/more', NULL, 0, '/api/system/role/delete/more', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_list', '角色管理', ',system_manager,t_role_list,', '角色管理', 'system_manager', '', '/api/system/role/pageList', 't_role_list', 1, '/roles', NULL, NULL, NULL, NULL, 'contacts', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-21 17:36:29', '', 9, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_look', '查看角色', ',system_manager,t_role_list,t_role_look,', '查看角色', 't_role_list', NULL, '/api/system/role/look', 't_role_look', 0, '/api/system/role/look', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_update', '修改角色', ',system_manager,t_role_list,t_role_update,', '修改角色', 't_role_list', NULL, '/api/system/role/update', 't_role_update', 0, '/api/system/role/update', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_delete', '删除用户', ',system_manager,t_user_list,t_user_delete,', '删除用户', 't_user_list', NULL, '/api/system/user/delete', 't_user_delete_one', 0, '/api/system/user/delete', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-21 18:30:47', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_deletemore', '批量删除用户', ',system_manager,t_user_list,t_user_deletemore,', '批量删除用户', 't_user_list', NULL, '/api/system/user/delete/more', 't_user_delete_more', 0, '/api/system/user/delete/more', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-15 12:32:43', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_list', '用户管理', ',system_manager,t_user_list,', '用户管理', 'system_manager', '', '/api/system/user/list', NULL, 1, '/users', NULL, NULL, NULL, NULL, 'team', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-21 17:35:40', '', 10, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_list_export', '导出用户', ',system_manager,t_user_list,t_user_list_export,', '导出用户', 't_user_list', NULL, '/api/system/user/list/export', 't_user_export', 0, '/api/system/user/list/export', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-19 17:35:41', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_look', '查看用户', ',system_manager,t_user_list,t_user_look,', '查看用户', 't_user_list', NULL, '/api/system/user/look', 't_user_look', 0, '/api/system/user/look', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-21 17:09:38', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_update', '修改用户', ',system_manager,t_user_list,t_user_update,', '修改用户', 't_user_list', NULL, '/api/system/user/update', 't_user_update', 0, '/api/system/user/update', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-15 10:30:16', '', 0, 1, NULL, NULL, NULL, NULL, NULL);

INSERT INTO `t_menu`(`id`, `name`, `comcode`, `title`, `pid`, `remark`, `pageurl`, `code`, `menuType`, `path`, `keepAlive`, `component`, `permission`, `redirect`, `icon`, `url`, `target`, `createTime`, `createUserId`, `updateTime`, `updateUserId`, `sortno`, `active`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('20210707170346770919441915', '获取部门信息', ',system_manager,t_role_list,20210707170346770919441915,', '获取部门信息', 't_role_list', NULL, '/api/system/org/treeselect', 't_roles_company', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-07-07 17:03:47', NULL, '2021-07-07 17:03:47', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu`(`id`, `name`, `comcode`, `title`, `pid`, `remark`, `pageurl`, `code`, `menuType`, `path`, `keepAlive`, `component`, `permission`, `redirect`, `icon`, `url`, `target`, `createTime`, `createUserId`, `updateTime`, `updateUserId`, `sortno`, `active`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('20210726140401984820627315', '用户类型', ',system_manager,t_user_list,20210726140401984820627315,', '用户类型', 't_user_list', NULL, '/api/system/user/userTypeList', 't_user_userTypeList', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-07-26 14:04:02', NULL, '2021-07-26 14:04:02', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu`(`id`, `name`, `comcode`, `title`, `pid`, `remark`, `pageurl`, `code`, `menuType`, `path`, `keepAlive`, `component`, `permission`, `redirect`, `icon`, `url`, `target`, `createTime`, `createUserId`, `updateTime`, `updateUserId`, `sortno`, `active`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('20210628172931789866445893', '通用上传', ',20210625113726616908395368,20210628172931789866445893,', '通用上传', '20210625113726616908395368', NULL, '/attachment/upload', 't_attachment_upload', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-06-28 17:29:32', NULL, '2021-06-28 17:29:32', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
-- ----------------------------
-- Table structure for t_org
-- ----------------------------
DROP TABLE IF EXISTS `t_org`;
CREATE TABLE `t_org`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `comcode` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '代码',
  `pid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '上级部门ID',
  `orgType` int(0) NOT NULL COMMENT '0-99门店,100-199部门,200-299,分公司,300-399集团公司,900-999总平台',
  `sortno` int(0) NOT NULL COMMENT '排序,查询时倒叙排列',
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `createUserId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updateUserId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `active` int(0) NOT NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `bak1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_org
-- ----------------------------
INSERT INTO `t_org` VALUES ('202105171503402497159', '盛见网络公司', ',202105171503402497159,', '', 300, 1, NULL, '2021-05-17 15:03:40', 'u_10001', '2021-05-17 15:03:40', 'u_10001', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_org` VALUES ('202105171503547466886', '产品研发中心', ',202105171503402497159,202105171503547466886,', '202105171503402497159', 100, 1, NULL, '2021-05-17 15:03:55', 'u_10001', '2021-05-17 15:03:55', 'u_10001', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_org` VALUES ('202105171504130626640', '123', ',202105171503402497159,202105171504130626640,', '202105171503402497159', 100, 2, NULL, '2021-05-17 15:04:13', 'u_10001', '2021-05-17 15:04:13', 'u_10001', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_org` VALUES ('202105181357082668376', '234', ',202105181357082668376,', '', 100, 3, NULL, '2021-05-18 13:57:08', 'u_10001', '2021-05-18 13:57:08', 'u_10001', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_org` VALUES ('202105181358246447979', '567', ',202105181357082668376,202105181358246447979,', '202105181357082668376', 100, 3, NULL, '2021-05-18 13:58:25', 'u_10001', '2021-05-18 13:58:25', 'u_10001', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_org` VALUES ('20210519110309706064758363', '测试部门', ',202105171503402497159,20210519110309706064758363,', '202105171503402497159', 300, 1, NULL, '2021-05-19 11:03:10', 'u_10001', '2021-05-19 11:03:10', 'u_10001', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_org` VALUES ('20210519145841316747100693', '会员', ',202105171503402497159,20210519110309706064758363,20210519145841316747100693,', '20210519110309706064758363', 0, 1, NULL, '2021-05-19 14:58:41', 'u_10001', '2021-05-19 14:58:41', 'u_10001', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_org` VALUES ('o_10001', '平台', ',o_10001,', '', 900, 1, '', '2019-07-24 11:29:56', '', '2019-07-24 11:29:56', '', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_org` VALUES ('o_10002', '网站', ',o_10001,o_10002,', 'o_10001', 0, 1, '', '2019-07-24 11:30:00', '', '2019-07-24 11:30:00', '', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_org` VALUES ('o_10003', '拖拽演示', ',o_10001,o_10002,o_10003,', 'o_10002', 0, 1, '', '2019-07-24 11:30:02', '', '2019-07-24 11:30:02', '', 0, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_permissions_log
-- ----------------------------
DROP TABLE IF EXISTS `t_permissions_log`;
CREATE TABLE `t_permissions_log`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `siteId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点ID',
  `actionType` int(0) NULL DEFAULT NULL COMMENT '操作类型 创建、编辑、删除、启用、禁用',
  `operatorUserId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人ID',
  `operatorUserName` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人当时名称',
  `operatorUserRoles` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '操作人当时所属角色名称，逗号分割',
  `operatorObjectType` int(0) NULL DEFAULT NULL COMMENT '操作对象类型',
  `operatorObjectId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作对象ID',
  `operatorObjectName` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作对象当时的名称',
  `actionContent` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '操作内容详情',
  `createUserId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '记录创建人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间',
  `bak1` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用字段',
  `bak2` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用字段',
  `bak3` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用字段',
  `bak4` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用字段',
  `bak5` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限变更日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_permissions_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `pid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级角色ID,暂时不实现',
  `privateOrg` int(0) NOT NULL DEFAULT 0 COMMENT '角色的部门是否私有,0否,1是,默认0.当角色私有时,菜单只使用此角色的部门权限,不再扩散到全局角色权限,用于设置特殊的菜单权限.公共权限时部门主管有所管理部门的数据全权限,无论角色是否分配. 私有部门权限时,严格按照配置的数据执行,部门主管可能没有部门权限.',
  `roleOrgType` int(0) NOT NULL DEFAULT 0 COMMENT '0自己的数据,1所在部门,2所在部门及子部门数据,3.自定义部门数据,4.全部数据权限',
  `orgId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色的归属部门,只有归属部门的主管和上级主管才可以管理角色,其他人员只能增加归属到角色的人员.不能选择部门或则其他操作,只能添加人员,不然存在提权风险,例如 员工角色下有1000人, 如果给 角色 设置了部门,那这1000人都起效了.',
  `shareRole` int(0) NOT NULL DEFAULT 0 COMMENT '角色是否共享,0否 1是,默认0,共享的角色可以被下级部门直接使用,但是下级只能添加人员,不能设置其他属性.共享的角色一般只设置roleOrgType,并不设定部门.',
  `createTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `createUserId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updateUserId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sortno` int(0) NOT NULL DEFAULT 10 COMMENT '排序,查询时倒叙排列',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `active` int(0) NOT NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `indexPage` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否有效(0否,1是)',
  `bak1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('202105131008457039760', '测试角色', NULL, NULL, 0, 0, 'o_10001', 1, '2021-05-13 10:08:35', 'u_10001', '2021-05-20 15:22:29', NULL, 5, '测试角色', 1, NULL, NULL, NULL, NULL, NULL,NULL);
INSERT INTO `t_role` VALUES ('e8a4ad9944894908b43ded631094dcbb', '演示站长', 'DEMO_WEBMASTER', NULL, 0, 1, 'o_10001', 0, '2019-07-24 17:29:44', 'u_10001', '2019-07-24 17:29:44', 'u_10001', 2, 'b', 1, NULL, NULL, NULL, NULL, NULL,NULL);
INSERT INTO `t_role` VALUES ('r_10001', '超级管理员', 'SUPER_ADMIN', NULL, 0, 2, '202105171503402497159', 0, '2019-07-24 17:29:45', 'u_10001', '2019-07-24 17:29:45', 'u_10001', 1, 'c', 1, NULL, NULL, NULL, NULL, NULL,NULL);
INSERT INTO `t_role` VALUES ('youke', '游客', 'YOUKE', NULL, 0, 0, '202105171504130626640', 0, '2021-05-18 15:04:26', 'u_10001', '2021-05-18 15:04:26', NULL, 6, '默认角色', 1, NULL, NULL, NULL, NULL, NULL,NULL);

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `roleId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编号',
  `menuId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单编号',
  `bak1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `createUserId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updateUserId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_t_role_menu_roleId_t_role_id`(`roleId`) USING BTREE,
  INDEX `fk_t_role_menu_menuId_t_menu_id`(`menuId`) USING BTREE,
  CONSTRAINT `fk_t_role_menu_menuId_t_menu_id` FOREIGN KEY (`menuId`) REFERENCES `t_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_t_role_menu_roleId_t_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('20210518162407242239971', 'youke', '202105171053129521252', NULL, NULL, NULL, NULL, NULL, '2021-05-18 16:24:07', 'u_10001', '2021-05-18 16:24:07', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283039461908', 'e8a4ad9944894908b43ded631094dcbb', 't_dic_data_grade_update', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283043412989', 'e8a4ad9944894908b43ded631094dcbb', 't_dic_data_grade_tree', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283084342853', 'e8a4ad9944894908b43ded631094dcbb', '202105171110557380782', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283087567520', 'e8a4ad9944894908b43ded631094dcbb', '202105171018334839880', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283091303076', 'e8a4ad9944894908b43ded631094dcbb', 't_dic_data_minzu_delete', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283099166451', 'e8a4ad9944894908b43ded631094dcbb', 't_dic_data_minzu_update', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283100915475', 'e8a4ad9944894908b43ded631094dcbb', '202105171034080407699', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283115218157', 'e8a4ad9944894908b43ded631094dcbb', 't_menu_delete', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283168467121', 'e8a4ad9944894908b43ded631094dcbb', 'b94392f7b8714f64819c5c0222eb134a', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283170462696', 'e8a4ad9944894908b43ded631094dcbb', '202105121015501783845', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283183835369', 'e8a4ad9944894908b43ded631094dcbb', '202105121506575382721', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283198406782', 'e8a4ad9944894908b43ded631094dcbb', 't_menu_list', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283201133386', 'e8a4ad9944894908b43ded631094dcbb', '202105171053129521252', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283264635292', 'e8a4ad9944894908b43ded631094dcbb', '20210520115456171843954745', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283266722554', 'e8a4ad9944894908b43ded631094dcbb', '202105141646058894617', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283276020874', 'e8a4ad9944894908b43ded631094dcbb', 't_dic_data_grade_delete', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283277832281', 'e8a4ad9944894908b43ded631094dcbb', '202105151252345580670', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283302769346', 'e8a4ad9944894908b43ded631094dcbb', 't_menu_update', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283303789928', 'e8a4ad9944894908b43ded631094dcbb', 'e51808e351c24a7e9fb4d47392930a2d', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283339335162', 'e8a4ad9944894908b43ded631094dcbb', 't_role_update', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283362821534', 'e8a4ad9944894908b43ded631094dcbb', 't_dic_data_xueli_list', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283363207123', 'e8a4ad9944894908b43ded631094dcbb', 't_dic_data_minzu_look', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283367777872', 'e8a4ad9944894908b43ded631094dcbb', 't_org_tree', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283373636924', 'e8a4ad9944894908b43ded631094dcbb', 't_dic_data_xueli_tree', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283382828484', 'e8a4ad9944894908b43ded631094dcbb', 't_user_update', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283391779564', 'e8a4ad9944894908b43ded631094dcbb', 't_role_deletemore', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283440156686', 'e8a4ad9944894908b43ded631094dcbb', 't_dic_data_minzu_deletemore', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283474193016', 'e8a4ad9944894908b43ded631094dcbb', '202105141502166346976', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283511347794', 'e8a4ad9944894908b43ded631094dcbb', 't_menu_deletemore', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283531590116', 'e8a4ad9944894908b43ded631094dcbb', 't_org_list', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283541994091', 'e8a4ad9944894908b43ded631094dcbb', 't_dic_data_minzu_list', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283548321376', 'e8a4ad9944894908b43ded631094dcbb', 't_dic_data_grade_deletemore', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283556101246', 'e8a4ad9944894908b43ded631094dcbb', '3501ed1e23da40219b4f0fa5b7b2749a', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283558571473', 'e8a4ad9944894908b43ded631094dcbb', '202105171517522622275', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283564046770', 'e8a4ad9944894908b43ded631094dcbb', '202105170919073863443', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283567102631', 'e8a4ad9944894908b43ded631094dcbb', 't_org_deletemore', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283600360802', 'e8a4ad9944894908b43ded631094dcbb', 't_dic_data_xueli_look', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283602360614', 'e8a4ad9944894908b43ded631094dcbb', 't_org_delete', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283605708609', 'e8a4ad9944894908b43ded631094dcbb', 't_user_list', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283643213325', 'e8a4ad9944894908b43ded631094dcbb', '20210521170043572189307269', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283662073020', 'e8a4ad9944894908b43ded631094dcbb', 't_menu_look', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283664868062', 'e8a4ad9944894908b43ded631094dcbb', '202105121606286404716', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283698937542', 'e8a4ad9944894908b43ded631094dcbb', 't_user_deletemore', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283725625187', 'e8a4ad9944894908b43ded631094dcbb', 't_menu_save', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283732303925', 'e8a4ad9944894908b43ded631094dcbb', 't_role_list', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283749574192', 'e8a4ad9944894908b43ded631094dcbb', 't_role_look', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283753984349', 'e8a4ad9944894908b43ded631094dcbb', 't_dic_data_grade_list', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283779517142', 'e8a4ad9944894908b43ded631094dcbb', '20210519172221273150930534', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283787052081', 'e8a4ad9944894908b43ded631094dcbb', 't_dic_data_xueli_update', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283792284077', 'e8a4ad9944894908b43ded631094dcbb', 'dic_manager', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283794584623', 'e8a4ad9944894908b43ded631094dcbb', '202105131018232299435', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283825555105', 'e8a4ad9944894908b43ded631094dcbb', 't_dic_data_xueli_deletemore', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283852740520', 'e8a4ad9944894908b43ded631094dcbb', 't_menu_tree', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283878020153', 'e8a4ad9944894908b43ded631094dcbb', 't_dic_data_grade_look', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283882313017', 'e8a4ad9944894908b43ded631094dcbb', 't_user_delete', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283884215625', 'e8a4ad9944894908b43ded631094dcbb', 't_dic_data_xueli_delete', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283885427387', 'e8a4ad9944894908b43ded631094dcbb', 'system_manager', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283904577461', 'e8a4ad9944894908b43ded631094dcbb', 't_user_list_export', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283907666940', 'e8a4ad9944894908b43ded631094dcbb', 't_org_look', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283965147854', 'e8a4ad9944894908b43ded631094dcbb', 't_org_update', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283971258381', 'e8a4ad9944894908b43ded631094dcbb', 't_dic_data_minzu_tree', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283984299327', 'e8a4ad9944894908b43ded631094dcbb', 't_user_look', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210521173742283993613511', 'e8a4ad9944894908b43ded631094dcbb', 't_role_delete', NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:37:42', 'u_10001', '2021-05-21 17:37:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041854224405559', 'r_10001', '202105171053129521252', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041854294814056', 'r_10001', 't_org_list', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041854352409859', 'r_10001', 't_role_list', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041854639350587', 'r_10001', 't_user_list', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041854917794141', 'r_10001', 't_menu_list', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855036301479', 'r_10001', 't_role_look', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855041079896', 'r_10001', 't_menu_tree', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855043202227', 'r_10001', 't_role_delete', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855093955469', 'r_10001', 't_role_update', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855218268233', 'r_10001', 't_menu_look', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855250776262', 'r_10001', 'b94392f7b8714f64819c5c0222eb134a', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855287807382', 'r_10001', 'dic_manager', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855334675656', 'r_10001', 't_menu_update', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855348165595', 'r_10001', 't_menu_save', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855352241616', 'r_10001', 't_org_tree', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855352743788', 'r_10001', '3501ed1e23da40219b4f0fa5b7b2749a', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855370216522', 'r_10001', 't_user_delete', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855373219587', 'r_10001', 't_user_list_export', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855387045315', 'r_10001', 'system_manager', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855391025435', 'r_10001', 't_user_update', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855489932854', 'r_10001', 'e51808e351c24a7e9fb4d47392930a2d', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855490506852', 'r_10001', 't_org_deletemore', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855501459339', 'r_10001', 't_user_deletemore', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855529605552', 'r_10001', 't_org_update', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855590679555', 'r_10001', 't_user_look', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855621068506', 'r_10001', '202105121015501783845', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855834942029', 'r_10001', 't_role_deletemore', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855870148410', 'r_10001', 't_menu_delete', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855953769750', 'r_10001', 't_menu_deletemore', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855958836831', 'r_10001', 't_org_delete', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855968973028', 'r_10001', 't_org_look', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041855998702158', 'r_10001', '202105121506575382721', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041856010801548', 'r_10001', '202105151252345580670', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041856016496924', 'r_10001', '202105141502166346976', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041856062493329', 'r_10001', '202105131018232299435', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041856068928170', 'r_10001', '202105121606286404716', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041856148904179', 'r_10001', '20210521170043572189307269', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041856395151220', 'r_10001', '202105171110557380782', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041856416450915', 'r_10001', '202105171018334839880', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041856424891312', 'r_10001', '202105171517522622275', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041856469389368', 'r_10001', '20210519172221273150930534', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041856522551231', 'r_10001', '202105171034080407699', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041856533465085', 'r_10001', '202105141646058894617', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041856634338740', 'r_10001', '202105170919073863443', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041856644674204', 'r_10001', '20210525184737580453255296', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041856712352983', 'r_10001', '20210521170718181613125891', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041856953446736', 'r_10001', '20210520115456171843954745', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151041856995667810', 'r_10001', '20210526150953853171269157', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:10:42', 'u_10001', '2021-05-26 15:10:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210526151754531809542462', 'r_10001', '20210526151754523530237327', NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:17:55', 'u_10001', '2021-05-26 15:17:55', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527095451925748703188', 'r_10001', '20210527095451904604564871', NULL, NULL, NULL, NULL, NULL, '2021-05-27 09:54:52', 'u_10001', '2021-05-27 09:54:52', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527095537256902585789', 'r_10001', '20210527095537252768804665', NULL, NULL, NULL, NULL, NULL, '2021-05-27 09:55:37', 'u_10001', '2021-05-27 09:55:37', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527100553737619771483', 'r_10001', '20210527100553713196107663', NULL, NULL, NULL, NULL, NULL, '2021-05-27 10:05:54', 'u_10001', '2021-05-27 10:05:54', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527100626500827003692', 'r_10001', '20210527100626495495029605', NULL, NULL, NULL, NULL, NULL, '2021-05-27 10:06:27', 'u_10001', '2021-05-27 10:06:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527101125052508982634', 'r_10001', '20210527101125018646355183', NULL, NULL, NULL, NULL, NULL, '2021-05-27 10:11:25', 'u_10001', '2021-05-27 10:11:25', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527113120332827525515', 'r_10001', '20210527113120304570637037', NULL, NULL, NULL, NULL, NULL, '2021-05-27 11:31:20', 'u_10001', '2021-05-27 11:31:20', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542134104018706', '202105131008457039760', '202105171053129521252', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542134251383606', '202105131008457039760', 't_user_list', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542135005657148', '202105131008457039760', 't_org_list', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542135026902681', '202105131008457039760', 't_role_look', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542135193043526', '202105131008457039760', '202105171018334839880', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542135215228344', '202105131008457039760', '202105141646058894617', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542135247872013', '202105131008457039760', 'system_manager', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542135310815667', '202105131008457039760', '202105171034080407699', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542135359296003', '202105131008457039760', 't_org_tree', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542135427219756', '202105131008457039760', 't_menu_tree', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542135464622945', '202105131008457039760', 't_menu_list', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542135472462497', '202105131008457039760', 't_role_list', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542135550399787', '202105131008457039760', 't_org_look', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542135667973706', '202105131008457039760', '202105141502166346976', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542135720081080', '202105131008457039760', '3501ed1e23da40219b4f0fa5b7b2749a', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542135829929694', '202105131008457039760', 't_menu_look', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542135972745573', '202105131008457039760', '20210521170718181613125891', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542136286621419', '202105131008457039760', 'dic_manager', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542136307541182', '202105131008457039760', '202105171517522622275', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542136314452858', '202105131008457039760', '20210527101125018646355183', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542136325359202', '202105131008457039760', '20210527113120304570637037', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542136404332348', '202105131008457039760', '20210526151754523530237327', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542136670388106', '202105131008457039760', '20210526150953853171269157', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527151542136789191927', '202105131008457039760', '202105121606286404716', NULL, NULL, NULL, NULL, NULL, '2021-05-27 15:15:42', 'u_10001', '2021-05-27 15:15:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527163800042568660395', 'r_10001', '20210527163800020317714432', NULL, NULL, NULL, NULL, NULL, '2021-05-27 16:38:00', 'u_10001', '2021-05-27 16:38:00', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20210527163849339792755943', 'r_10001', '20210527163849335432594105', NULL, NULL, NULL, NULL, NULL, '2021-05-27 16:38:49', 'u_10001', '2021-05-27 16:38:49', 'u_10001');

-- ----------------------------
-- Table structure for t_role_org
-- ----------------------------
DROP TABLE IF EXISTS `t_role_org`;
CREATE TABLE `t_role_org`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `orgId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门编号',
  `roleId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编号',
  `children` int(0) NOT NULL DEFAULT 0 COMMENT '0不包含子部门,1包含.用于表示角色和部门的权限关系.用于记录roleOrgType的结果,缓存值',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `createUserId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updateUserId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_t_role_org_orgId_t_org_id`(`orgId`) USING BTREE,
  INDEX `fk_t_role_org_roleId_t_role_id`(`roleId`) USING BTREE,
  CONSTRAINT `fk_t_role_org_orgId_t_org_id` FOREIGN KEY (`orgId`) REFERENCES `t_org` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_t_role_org_roleId_t_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色部门中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_org
-- ----------------------------
INSERT INTO `t_role_org` VALUES ('testid', 'o_10001', 'e8a4ad9944894908b43ded631094dcbb', 1, '2019-07-26 10:00:10', 'u_10001', '2019-07-26 10:00:10', 'u_10001', NULL, NULL, NULL, NULL, NULL);

INSERT INTO `t_role_org`(`id`, `orgId`, `roleId`, `children`, `createTime`, `createUserId`, `updateTime`, `updateUserId`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('20211230173210767620497336', '202105171503402497159', 'r_10001', 1, '2019-07-26 10:00:10', 'u_10001', '2019-07-26 10:00:10', 'u_10001', NULL, NULL, NULL, NULL, NULL);
-- ----------------------------
-- Table structure for t_tableindex
-- ----------------------------
DROP TABLE IF EXISTS `t_tableindex`;
CREATE TABLE `t_tableindex`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名',
  `maxIndex` int(0) NOT NULL DEFAULT 1 COMMENT '表记录最大的行,一直累加',
  `prefix` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '前缀 单个字母加 _',
  `bak1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '记录表最大的行记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_tableindex
-- ----------------------------
INSERT INTO `t_tableindex` VALUES ('cms_channel', 10000, 'h_', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_tableindex` VALUES ('cms_content', 100000, 'c_', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_tableindex` VALUES ('cms_site', 10001, 's_', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_tableindex` VALUES ('t_org', 10003, 'o_', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_tableindex` VALUES ('t_user', 10001, 'u_', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT ' ',
  `userName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '男' COMMENT '性别',
  `mobile` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `openId` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信openId',
  `unionID` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信UnionID',
  `avatar` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `userType` int(0) NOT NULL DEFAULT 1 COMMENT '0会员,1员工,2店长收银,9系统管理员',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `createUserId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updateUserId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `active` int(0) NOT NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `bak1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('20210521175017791641757545', '赵六', 'zl', '202cb962ac59075b964b07152d234b70', NULL, '123', '123', NULL, NULL, '/avatar/default.jpg', 1, '2021-05-21 17:50:18', 'u_10001', '2021-05-21 17:50:18', 'u_10001', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES ('20210521180451455507632824', '小王', 'xw', '202cb962ac59075b964b07152d234b70', '男', '1321', 'a@a.com', NULL, NULL, '/avatar/default.jpg', 1, '2021-05-21 18:04:51', 'u_10001', '2021-05-21 18:04:51', 'u_10001', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES ('20210525185903274101848131', '王五', 'ww', '202cb962ac59075b964b07152d234b70', NULL, '1478223', 'a@a.com', NULL, NULL, '/avatar/default.jpg', 1, '2021-05-21 16:07:13', 'u_10001', '2021-05-25 18:59:03', 'u_10001', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES ('20210527141112401848995513', '张飞', 'zhangfei', '202cb962ac59075b964b07152d234b70', NULL, '18595378756', 'a@a.com', NULL, NULL, '/avatar/default.jpg', 1, '2021-05-21 16:07:13', 'u_10001', '2021-05-27 14:11:12', 'u_10001', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES ('20210527141112441941840098', '刘备', 'liubei', '202cb962ac59075b964b07152d234b70', NULL, '147585263', 'a@a.com', NULL, NULL, '/avatar/default.jpg', 1, '2021-05-21 16:07:13', 'u_10001', '2021-05-27 14:11:12', 'u_10001', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES ('20210527191607488660458173', '小二', 'xiaoer', '202cb962ac59075b964b07152d234b70', NULL, '1.1045896321E10', 'a@b.com', NULL, NULL, '/avatar/default.jpg', 1, '2019-07-24 11:18:22', 'u_10001', '2021-05-27 19:16:07', 'u_10001', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES ('23a2c0c52ed142938c159c9b9004fa35', 'ptAdmin', 'ptAdmin', '202cb962ac59075b964b07152d234b70', '男', '1119', '119@911.com', '', NULL, '/avatar/default.jpg', 2, '2019-07-24 11:18:22', 'u_10001', '2019-07-24 11:18:22', 'u_10001', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES ('u_10001', '超级管理员', 'admin', '21232f297a57a5a743894a0e4a801fc3', '男', '120', '120@qq.com', NULL, NULL, '/avatar/default.jpg', 9, '2019-07-24 11:18:22', 'u_10001', '2019-07-24 11:18:22', 'u_10001', 1, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_user_org
-- ----------------------------
DROP TABLE IF EXISTS `t_user_org`;
CREATE TABLE `t_user_org`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `userId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户编号',
  `orgId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构编号',
  `managerType` int(0) NOT NULL DEFAULT 1 COMMENT '0会员,1员工,2主管',
  `bak1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `createUserId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updateUserId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_t_user_org_userId_t_user_id`(`userId`) USING BTREE,
  INDEX `fk_t_user_org_orgId_t_org_id`(`orgId`) USING BTREE,
  CONSTRAINT `fk_t_user_org_orgId_t_org_id` FOREIGN KEY (`orgId`) REFERENCES `t_org` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_t_user_org_userId_t_user_id` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户部门中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_org
-- ----------------------------
INSERT INTO `t_user_org` VALUES ('1b07f0771b0d40ebb521b89612505005', '20210521180451455507632824', '20210519110309706064758363', 2, NULL, NULL, NULL, NULL, NULL, '2021-05-27 13:50:01', '20210521180451455507632824', '2021-05-27 13:50:01', '20210521180451455507632824');
INSERT INTO `t_user_org` VALUES ('25205fd1840c455a92ee4b7516fb684c', '20210525185903274101848131', '20210519110309706064758363', 1, NULL, NULL, NULL, NULL, NULL, '2021-05-26 19:06:50', 'u_10001', '2021-05-26 19:06:50', 'u_10001');
INSERT INTO `t_user_org` VALUES ('2c22a0a316fe4bc2bd7505709f771c73', 'u_10001', '202105171503402497159', 2, NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:30:43', 'u_10001', '2021-05-26 15:30:43', 'u_10001');
INSERT INTO `t_user_org` VALUES ('421e9d62314e44c09e83c04e922768c6', '20210527141112401848995513', '20210519110309706064758363', 1, NULL, NULL, NULL, NULL, NULL, '2021-05-27 14:11:12', 'u_10001', '2021-05-27 14:11:12', 'u_10001');
INSERT INTO `t_user_org` VALUES ('533dd8085acc470eb248a9c282e7e72e', '20210525185903274101848131', '202105171504130626640', 1, NULL, NULL, NULL, NULL, NULL, '2021-05-26 19:06:50', 'u_10001', '2021-05-26 19:06:50', 'u_10001');
INSERT INTO `t_user_org` VALUES ('62120341b2a54523a1dcbc740d082594', '20210527141112441941840098', '20210519110309706064758363', 1, NULL, NULL, NULL, NULL, NULL, '2021-05-27 14:11:12', 'u_10001', '2021-05-27 14:11:12', 'u_10001');
INSERT INTO `t_user_org` VALUES ('a521953a557f4f30bc8e1a58f4860850', '23a2c0c52ed142938c159c9b9004fa35', '20210519110309706064758363', 2, NULL, NULL, NULL, NULL, NULL, '2021-05-21 15:35:29', 'u_10001', '2021-05-21 15:35:29', 'u_10001');
INSERT INTO `t_user_org` VALUES ('a67fda6e3992450ab6c32f238bd30456', '20210521175017791641757545', '20210519110309706064758363', 2, NULL, NULL, NULL, NULL, NULL, '2021-05-21 17:55:45', 'u_10001', '2021-05-21 17:55:45', 'u_10001');
INSERT INTO `t_user_org` VALUES ('b340bd40ce63455bbb7a1af2c6cd33c1', '20210527191607488660458173', '20210519110309706064758363', 1, NULL, NULL, NULL, NULL, NULL, '2021-05-27 19:16:08', 'u_10001', '2021-05-27 19:16:08', 'u_10001');

-- ----------------------------
-- Table structure for t_user_platform_infos
-- ----------------------------
DROP TABLE IF EXISTS `t_user_platform_infos`;
CREATE TABLE `t_user_platform_infos`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `openId` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公众号openId,企业号userId,小程序openId,APP推送deviceToken',
  `deviceType` int(0) NULL DEFAULT NULL COMMENT '设备/应用类型：1公众号2小程序3企业号4APP IOS消息推送5APP安卓消息推送6web',
  `siteId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属站点ID',
  `userId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 't_user表中ID',
  `bak1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户平台信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_platform_infos
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `userId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户编号',
  `roleId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编号',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `createUserId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updateUserId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_t_user_role_userId_t_user_id`(`userId`) USING BTREE,
  INDEX `fk_t_user_role_roleId_t_role_id`(`roleId`) USING BTREE,
  CONSTRAINT `fk_t_user_role_roleId_t_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_t_user_role_userId_t_user_id` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('08f3b78a397341f082da126854a90f8a', '23a2c0c52ed142938c159c9b9004fa35', 'e8a4ad9944894908b43ded631094dcbb', '2021-05-21 15:35:29', 'u_10001', '2021-05-21 15:35:29', 'u_10001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user_role` VALUES ('0be7fe13b25f46a5a067beb1aa722b9e', 'u_10001', 'r_10001', '2021-05-26 15:30:43', 'u_10001', '2021-05-26 15:30:43', 'u_10001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user_role` VALUES ('20251b487cf04667a3793d7773cc2488', '20210525185903274101848131', 'e8a4ad9944894908b43ded631094dcbb', '2021-05-26 19:06:50', 'u_10001', '2021-05-26 19:06:50', 'u_10001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user_role` VALUES ('333c37e80e6647949ac652d9bb02c788', '20210521175017791641757545', '202105131008457039760', '2021-05-21 17:55:45', 'u_10001', '2021-05-21 17:55:45', 'u_10001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user_role` VALUES ('3fc5d03b2f0e467f93d38ca59996c976', '20210527191607488660458173', '202105131008457039760', '2021-05-27 19:16:08', 'u_10001', '2021-05-27 19:16:08', 'u_10001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user_role` VALUES ('4139640807114411801f642e992c994e', '20210527141112441941840098', '202105131008457039760', '2021-05-27 14:11:12', 'u_10001', '2021-05-27 14:11:12', 'u_10001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user_role` VALUES ('4f607a55f26c4beabc4af57e9cbc7d66', '20210527141112401848995513', '202105131008457039760', '2021-05-27 14:11:12', 'u_10001', '2021-05-27 14:11:12', 'u_10001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user_role` VALUES ('67b4a5c393704d3892eec50e787812e4', '20210525185903274101848131', '202105131008457039760', '2021-05-26 19:06:50', 'u_10001', '2021-05-26 19:06:50', 'u_10001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user_role` VALUES ('af435ce0b4814462be94a4c577d57858', '20210521180451455507632824', '202105131008457039760', '2021-05-27 13:50:01', '20210521180451455507632824', '2021-05-27 13:50:01', '20210521180451455507632824', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'increment id',
  `branch_id` bigint(0) NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int(0) NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(0) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(0) NOT NULL COMMENT 'modify datetime',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'AT transaction mode undo table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

-- ----------------------------
-- Table structure for wx_cpconfig
-- ----------------------------
DROP TABLE IF EXISTS `wx_cpconfig`;
CREATE TABLE `wx_cpconfig`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `orgId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '站点Id',
  `appId` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '开发者Id',
  `secret` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用密钥',
  `active` int(0) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
  `bak1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '微信号需要的配置信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_cpconfig
-- ----------------------------

-- ----------------------------
-- Table structure for wx_menu
-- ----------------------------
DROP TABLE IF EXISTS `wx_menu`;
CREATE TABLE `wx_menu`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单类型',
  `keyword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息内容',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '跳转地址',
  `pid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级菜单id',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `siteId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '站点id',
  `bak1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_menu
-- ----------------------------

-- ----------------------------
-- Table structure for wx_miniappconfig
-- ----------------------------
DROP TABLE IF EXISTS `wx_miniappconfig`;
CREATE TABLE `wx_miniappconfig`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `orgId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '站点Id',
  `appId` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '开发者Id',
  `secret` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用密钥',
  `planId` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '签约模板Id',
  `requestSerial` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '签约请求序列号',
  `active` int(0) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
  `bak1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '小程序配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_miniappconfig
-- ----------------------------
INSERT INTO `wx_miniappconfig` VALUES ('wx95217af982ed4f53', '1', 'wx95217af982ed4f53', '8a4fe0d1b47d46282774d9fe77f6bb19', '1', '1', 1, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for wx_mpconfig
-- ----------------------------
DROP TABLE IF EXISTS `wx_mpconfig`;
CREATE TABLE `wx_mpconfig`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `orgId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '站点Id',
  `appId` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '开发者Id',
  `secret` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用密钥',
  `token` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开发者令牌',
  `aesKey` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息加解密密钥',
  `wxOriginalId` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信原始ID',
  `oauth2` int(0) NULL DEFAULT 1 COMMENT '是否支持微信oauth2.0协议,0是不支持,1是支持',
  `active` int(0) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
  `bak1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '微信号需要的配置信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_mpconfig
-- ----------------------------

-- ----------------------------
-- Table structure for wx_payconfig
-- ----------------------------
DROP TABLE IF EXISTS `wx_payconfig`;
CREATE TABLE `wx_payconfig`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `orgId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '站点Id',
  `appId` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '开发者Id',
  `secret` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用密钥',
  `mchId` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信支付商户号',
  `key` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '交易过程生成签名的密钥，仅保留在商户系统和微信支付后台，不会在网络中传播',
  `certificateFile` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '证书地址',
  `notifyUrl` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通知地址',
  `signType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '加密方式,MD5和HMAC-SHA256',
  `active` int(0) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
  `bak1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bak5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '微信号需要的配置信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_notify
-- ----------------------------
DROP TABLE IF EXISTS `t_notify`;
CREATE TABLE `t_notify`  (
    `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
    `nodifyId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通知者',
    `nodifyTime` datetime(0) NOT NULL COMMENT '通知时间',
    `acceptId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接受者',
    `acceptTime` datetime(0) NULL DEFAULT NULL COMMENT '接受时间(查看时间)',
    `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息标题',
    `content` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息内容',
    `status` tinyint(1) NOT NULL COMMENT '消息状态(0未读,1已读)',
    `type` tinyint(1) NOT NULL COMMENT '消息类型(1系统运行通知,2推广消息)',
    `url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '推广消息跳转的链接',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Records of wx_payconfig
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
