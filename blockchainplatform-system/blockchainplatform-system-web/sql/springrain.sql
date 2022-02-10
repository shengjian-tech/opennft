

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- https://github.com/seata/seata/tree/develop/script

-- for AT mode you must to init this sql for you business database. the seata server not need it.
CREATE TABLE IF NOT EXISTS `undo_log`
(
    `id`            BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT 'increment id',
    `branch_id`     BIGINT(20)   NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(100) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME     NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME     NOT NULL COMMENT 'modify datetime',
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4  COMMENT ='AT transaction mode undo table';


-- -------------------------------- The script used when storeMode is 'db' --------------------------------
-- the table to store GlobalSession data
CREATE TABLE IF NOT EXISTS `global_table`
(
    `xid`                       VARCHAR(128) NOT NULL,
    `transaction_id`            BIGINT,
    `status`                    TINYINT      NOT NULL,
    `application_id`            VARCHAR(32),
    `transaction_service_group` VARCHAR(32),
    `transaction_name`          VARCHAR(128),
    `timeout`                   INT,
    `begin_time`                BIGINT,
    `application_data`          VARCHAR(2000),
    `gmt_create`                DATETIME,
    `gmt_modified`              DATETIME,
    PRIMARY KEY (`xid`),
    KEY `idx_gmt_modified_status` (`gmt_modified`, `status`),
    KEY `idx_transaction_id` (`transaction_id`)
)  ENGINE = InnoDB CHARACTER SET = utf8mb4 ;

-- the table to store BranchSession data
CREATE TABLE IF NOT EXISTS `branch_table`
(
    `branch_id`         BIGINT       NOT NULL,
    `xid`               VARCHAR(128) NOT NULL,
    `transaction_id`    BIGINT,
    `resource_group_id` VARCHAR(32),
    `resource_id`       VARCHAR(256),
    `branch_type`       VARCHAR(8),
    `status`            TINYINT,
    `client_id`         VARCHAR(64),
    `application_data`  VARCHAR(2000),
    `gmt_create`        DATETIME,
    `gmt_modified`      DATETIME,
    PRIMARY KEY (`branch_id`),
    KEY `idx_xid` (`xid`)
)  ENGINE = InnoDB CHARACTER SET = utf8mb4 ;

-- the table to store lock data
CREATE TABLE IF NOT EXISTS `lock_table`
(
    `row_key`        VARCHAR(128) NOT NULL,
    `xid`            VARCHAR(96),
    `transaction_id` BIGINT,
    `branch_id`      BIGINT       NOT NULL,
    `resource_id`    VARCHAR(256),
    `table_name`     VARCHAR(32),
    `pk`             VARCHAR(36),
    `gmt_create`     DATETIME,
    `gmt_modified`   DATETIME,
    PRIMARY KEY (`row_key`),
    KEY `idx_branch_id` (`branch_id`)
)  ENGINE = InnoDB CHARACTER SET = utf8mb4 ;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article`  (
  `id` varchar(50)  NOT NULL COMMENT 'ID',
  `mintitle` varchar(200)  NULL DEFAULT NULL COMMENT '标题',
  `summary` text  NULL COMMENT '摘要',
  `keywords` varchar(1000)  NULL DEFAULT NULL COMMENT '关键字',
  `description` varchar(1000)  NULL DEFAULT NULL COMMENT '描述',
  `lookcount` int(11) NULL DEFAULT NULL COMMENT '打开次数',
  `createPerson` varchar(50)  NULL DEFAULT NULL COMMENT '创建人',
  `createDate` datetime(0) NOT NULL COMMENT '创建时间',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态  0未审核  1审核通过',
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '内容表' ;

-- ----------------------------
-- Table structure for t_auditlog_history_2019
-- ----------------------------
DROP TABLE IF EXISTS `t_auditlog_history_2019`;
CREATE TABLE `t_auditlog_history_2019`  (
  `id` varchar(50)  NOT NULL COMMENT 'ID',
  `operationType` varchar(50)  NULL DEFAULT NULL COMMENT '操作类型',
  `operatorName` varchar(50)  NULL DEFAULT NULL COMMENT '操作人姓名',
  `preValue` text  NULL COMMENT '旧值',
  `curValue` text  NULL COMMENT '新值',
  `operationTime` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `operationClass` varchar(500)  NULL DEFAULT NULL COMMENT '操作类',
  `operationClassID` varchar(50)  NULL DEFAULT NULL COMMENT '记录ID',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '操作记录' ;

-- ----------------------------
-- Table structure for t_auditlog_history_2020
-- ----------------------------
DROP TABLE IF EXISTS `t_auditlog_history_2020`;
CREATE TABLE `t_auditlog_history_2020`  (
  `id` varchar(50)  NOT NULL COMMENT 'ID',
  `operationType` varchar(50)  NULL DEFAULT NULL COMMENT '操作类型',
  `operatorName` varchar(50)  NULL DEFAULT NULL COMMENT '操作人姓名',
  `preValue` text  NULL COMMENT '旧值',
  `curValue` text  NULL COMMENT '新值',
  `operationTime` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `operationClass` varchar(500)  NULL DEFAULT NULL COMMENT '操作类',
  `operationClassID` varchar(50)  NULL DEFAULT NULL COMMENT '记录ID',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '操作记录' ;

-- ----------------------------
-- Table structure for t_dic_data
-- ----------------------------
DROP TABLE IF EXISTS `t_dic_data`;
CREATE TABLE `t_dic_data`  (
  `id` varchar(50)  NOT NULL,
  `name` varchar(60)  NOT NULL COMMENT '名称',
  `code` varchar(60)  NULL DEFAULT NULL COMMENT '编码',
  `val` varchar(1000)  NULL DEFAULT NULL COMMENT '值',
  `pid` varchar(50)  NULL DEFAULT NULL COMMENT '父ID',
  `sortno` int(11) NULL DEFAULT NULL COMMENT '排序',
  `remark` varchar(2000)  NULL DEFAULT NULL COMMENT '描述',
  `active` int(11) NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `typekey` varchar(20)  NULL DEFAULT NULL COMMENT '类型',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '公共字典' ;

-- ----------------------------
-- Records of t_dic_data
-- ----------------------------
INSERT INTO `t_dic_data` VALUES ('16b80bfb-f0ee-47a0-ba94-cc256abaed17', '专科', '', NULL, NULL, NULL, '', 1, 'xueli', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('7ed23330-5538-4943-8678-0c5a2121cf57', '高中', '', NULL, NULL, NULL, '', 1, 'xueli', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('936db407-ae1-45a7-a657-b60580e2a77a', '汉族', '101', NULL, NULL, NULL, '', 1, 'minzu', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('936db407-ae2-45a7-a657-b60580e2a77a', '回族', '', NULL, NULL, NULL, '', 1, 'minzu', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('936db407-ae3-45a7-a657-b60580e2a77a', '一级', '', NULL, NULL, NULL, '', 1, 'grade', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('936db407-ae4-45a7-a657-b60580e2a77a', '二级', '', NULL, NULL, NULL, '', 1, 'grade', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('d7d1744b-e69f-48d0-9760-b2eae6af039b', '本科', '', NULL, NULL, NULL, '', 1, 'xueli', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_fwlog_history_2019
-- ----------------------------
DROP TABLE IF EXISTS `t_fwlog_history_2019`;
CREATE TABLE `t_fwlog_history_2019`  (
  `id` varchar(100)  NOT NULL COMMENT 'ID',
  `startDate` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  `strDate` varchar(100)  NULL DEFAULT NULL COMMENT '访问时间(毫秒)',
  `tomcat` varchar(50)  NULL DEFAULT NULL COMMENT 'Tomcat',
  `userCode` varchar(300)  NULL DEFAULT NULL COMMENT '登录账号',
  `userName` varchar(200)  NULL DEFAULT NULL COMMENT '姓名',
  `sessionId` varchar(300)  NULL DEFAULT NULL COMMENT 'sessionId',
  `ip` varchar(200)  NULL DEFAULT NULL COMMENT 'IP',
  `fwUrl` varchar(3000)  NULL DEFAULT NULL COMMENT '访问菜单',
  `menuName` varchar(100)  NULL DEFAULT NULL COMMENT '菜单名称',
  `isqx` varchar(2)  NULL DEFAULT NULL COMMENT '是否有权限访问',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '访问日志' ;

-- ----------------------------
-- Table structure for t_fwlog_history_2020
-- ----------------------------
DROP TABLE IF EXISTS `t_fwlog_history_2020`;
CREATE TABLE `t_fwlog_history_2020`  (
  `id` varchar(100)  NOT NULL COMMENT 'ID',
  `startDate` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  `strDate` varchar(100)  NULL DEFAULT NULL COMMENT '访问时间(毫秒)',
  `tomcat` varchar(50)  NULL DEFAULT NULL COMMENT 'Tomcat',
  `userCode` varchar(300)  NULL DEFAULT NULL COMMENT '登录账号',
  `userName` varchar(200)  NULL DEFAULT NULL COMMENT '姓名',
  `sessionId` varchar(300)  NULL DEFAULT NULL COMMENT 'sessionId',
  `ip` varchar(200)  NULL DEFAULT NULL COMMENT 'IP',
  `fwUrl` varchar(3000)  NULL DEFAULT NULL COMMENT '访问菜单',
  `menuName` varchar(100)  NULL DEFAULT NULL COMMENT '菜单名称',
  `isqx` varchar(2)  NULL DEFAULT NULL COMMENT '是否有权限访问',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '访问日志' ;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` varchar(50)  NOT NULL,
  `name` varchar(500)  NOT NULL COMMENT '菜单名称',
  `comcode` varchar(1000)  NOT NULL COMMENT '代码',
  `title` varchar(500)  NULL DEFAULT NULL COMMENT 'vue使用 meta.title',
  `pid` varchar(50)  NOT NULL,
  `remark` varchar(1000)  NULL DEFAULT NULL COMMENT '备注',
  `pageurl` varchar(3000)  NULL DEFAULT NULL,
  `menuType` int(11) NOT NULL COMMENT '0.功能按钮,1.导航菜单',
  `path` varchar(500)  NULL DEFAULT NULL COMMENT 'vue路由地址',
  `keepAlive` tinyint(4) NULL DEFAULT NULL COMMENT 'vue组件使用',
  `component` varchar(500)  NULL DEFAULT NULL COMMENT 'vue组件使用',
  `permission` varchar(500)  NULL DEFAULT NULL COMMENT 'vue组件使用',
  `redirect` varchar(500)  NULL DEFAULT NULL COMMENT 'vue组件使用',
  `icon` varchar(100)  NULL DEFAULT NULL,
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createUserId` varchar(50)  NULL DEFAULT NULL,
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateUserId` varchar(50)  NULL DEFAULT NULL,
  `sortno` int(11) NOT NULL DEFAULT 0 COMMENT '排序,查询时倒叙排列',
  `active` int(11) NOT NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '菜单' ;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('081b3344872545448cf5d1804890ab03', '选择专题页', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,50374413883c45ae9b9f8e8d7c7609bf,081b3344872545448cf5d1804890ab03,', '选择专题页', '50374413883c45ae9b9f8e8d7c7609bf', '', '/s/s_PT/adver/xcx/topic/list', 0, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/default.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 4, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('169815aca9cf41d390e7feb6629d361d', '栏目管理', ',business_manager,169815aca9cf41d390e7feb6629d361d,', '栏目管理', 'business_manager', '', '/api/system/cms/channel/list', 1, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/icon15.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 4, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('3330456139a241b1a27a7dcd171d7bf1', '拖拽演示网站', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,', '拖拽演示网站', 'f4d7a1bf7ddf43dc9016e1465cd3d9d8', '', NULL, 1, NULL, NULL, NULL, NULL, NULL, '', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('3501ed1e23da40219b4f0fa5b7b2749a', '菜单列表', ',system_manager,t_menu_list,3501ed1e23da40219b4f0fa5b7b2749a,', '菜单列表', 't_menu_list', '', '/api/system/menu/list', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('36ab9175f7b7423eadda974ba046be05', '修改密码', ',business_manager,t_user_list,36ab9175f7b7423eadda974ba046be05,', '修改密码', 't_user_list', '', '/api/system/user/modifiypwd/pre', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('4adc1e3e3e244c0991d9dab66c63badf', '目录创建', ',system_manager,f5203235547342f094a2c126ad4603bb,4adc1e3e3e244c0991d9dab66c63badf,', '目录创建', 'f5203235547342f094a2c126ad4603bb', '', '/api/system/file/uploadDic', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 2, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('50374413883c45ae9b9f8e8d7c7609bf', '微信首页设置', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,50374413883c45ae9b9f8e8d7c7609bf,', '微信首页设置', 's_PT_854e84ec22284834b9055aaea98e910c', '', '/s/s_PT/dragpage/dragPage', 0, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/default.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 1, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('5cce870b5880479794c2c00535c55ad8', '后台管理', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,', '后台管理', '3330456139a241b1a27a7dcd171d7bf1', '', NULL, 1, NULL, NULL, NULL, NULL, NULL, '', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('78287e4ac70546168b2fa68818710470', '保存首页数据', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,50374413883c45ae9b9f8e8d7c7609bf,78287e4ac70546168b2fa68818710470,', '保存首页数据', '50374413883c45ae9b9f8e8d7c7609bf', '', '/s/s_PT/adver/weChat/saveDragJosn', 0, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/default.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 2, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('7cd0678633d5407dba2bd6a1553cadce', '文件下载', ',system_manager,f5203235547342f094a2c126ad4603bb,7cd0678633d5407dba2bd6a1553cadce,', '文件下载', 'f5203235547342f094a2c126ad4603bb', '', '/api/system/file/downfile', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 3, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('8c72a4b5e56643ac9a9ca3aeec753c4e', '启用/禁用', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,9efc46fc51304cae8a35d12c942059c9,8c72a4b5e56643ac9a9ca3aeec753c4e,', '启用/禁用', '9efc46fc51304cae8a35d12c942059c9', '', '/s/s_PT/dragpage/updateActive', 0, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/default.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 2, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('91779a0d304f4b91932b63dec87a8536', '角色管理-系统', ',system_manager,91779a0d304f4b91932b63dec87a8536,', '角色管理-系统', 'system_manager', '', '/api/system/role/list/all', 1, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/icon23.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('9bccbc28b32e41438c5ac73a5e61ed58', '专题页设置', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,9bccbc28b32e41438c5ac73a5e61ed58,', '专题页设置', 's_PT_854e84ec22284834b9055aaea98e910c', '', '/s/s_PT/dragpage/specialPage/list', 1, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/default.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 2, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('9efc46fc51304cae8a35d12c942059c9', '首页设置', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,9efc46fc51304cae8a35d12c942059c9,', '首页设置', 's_PT_854e84ec22284834b9055aaea98e910c', '', '/s/s_PT/dragpage/1/list', 1, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/default.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 1, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('af298b90f073443bbde4b9e67113d697', '添加/编辑', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,9efc46fc51304cae8a35d12c942059c9,af298b90f073443bbde4b9e67113d697,', '添加/编辑', '9efc46fc51304cae8a35d12c942059c9', '', '/s/s_PT/dragpage/update', 0, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/default.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 1, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('aff3dc802af540c298af95cb5608fefe', '拖拽页面', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,9efc46fc51304cae8a35d12c942059c9,aff3dc802af540c298af95cb5608fefe,', '拖拽页面', '9efc46fc51304cae8a35d12c942059c9', '', '/s/s_PT/dragpage/drop', 0, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/default.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 4, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('b94392f7b8714f64819c5c0222eb134a', '角色修改-系统', ',system_manager,t_role_list,b94392f7b8714f64819c5c0222eb134a,', '角色修改-系统', 't_role_list', '', '/api/system/role/update/admin', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('b9c4e8ecffe949c0b346e1fd0d6b9977', '内容管理', ',business_manager,b9c4e8ecffe949c0b346e1fd0d6b9977,', '内容管理', 'business_manager', '', '/api/system/cms/content/list', 1, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/icon9.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 5, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('business_manager', '业务管理', ',business_manager,', '业务管理', '', '', NULL, 1, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/icon10.png', '2019-07-24 11:34:50', '', '2019-07-24 11:34:50', '', 1, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('ca152df1a7b44d4f81162f34b808934a', '验证老密码', ',business_manager,t_user_list,36ab9175f7b7423eadda974ba046be05,ca152df1a7b44d4f81162f34b808934a,', '验证老密码', '36ab9175f7b7423eadda974ba046be05', '', '/api/system/user/modifiypwd/ispwd', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('ca28235dbd234b7585e133e70cc7999a', '文件上传', ',system_manager,f5203235547342f094a2c126ad4603bb,ca28235dbd234b7585e133e70cc7999a,', '文件上传', 'f5203235547342f094a2c126ad4603bb', '', '/api/system/file/uploadFile', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 1, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('cafda855318c4560874f7fb14419be4f', '楼层商品选择', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,50374413883c45ae9b9f8e8d7c7609bf,cafda855318c4560874f7fb14419be4f,', '楼层商品选择', '50374413883c45ae9b9f8e8d7c7609bf', '', '/s/s_PT/adver/weChat/addGoods', 0, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/default.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 3, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('d6abe682007849869c3a168215ae40d4', 'WEB-INF文件管理', ',system_manager,d6abe682007849869c3a168215ae40d4,', 'WEB-INF文件管理', 'system_manager', '', '/api/system/file/web/list', 1, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/icon20.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 7, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('d7e44d49421e41ef9c3329be68dff6f7', '获取微信首页', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,50374413883c45ae9b9f8e8d7c7609bf,d7e44d49421e41ef9c3329be68dff6f7,', '获取微信首页', '50374413883c45ae9b9f8e8d7c7609bf', '', '/s/s_PT/adver/weChat/dragPageJosn', 0, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/default.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 1, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('dic_manager', '字典管理', ',system_manager,dic_manager,', '字典管理', 'system_manager', '', NULL, 1, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/icon30.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('e51808e351c24a7e9fb4d47392930a2d', '保存新密码', ',business_manager,t_user_list,36ab9175f7b7423eadda974ba046be05,e51808e351c24a7e9fb4d47392930a2d,', '保存新密码', '36ab9175f7b7423eadda974ba046be05', '', '/api/system/user/modifiypwd/save', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('e614beb39da04bd79797d7fc6ab91d66', '获取专题页json数据', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,50374413883c45ae9b9f8e8d7c7609bf,e614beb39da04bd79797d7fc6ab91d66,', '获取专题页json数据', '50374413883c45ae9b9f8e8d7c7609bf', '', '/s/s_PT/dragpage/specialPageJson', 0, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/default.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 3, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('f41b9f3b4a0d45f5a3b5842ee40e0e96', '站点管理', ',business_manager,f41b9f3b4a0d45f5a3b5842ee40e0e96,', '站点管理', 'business_manager', '', '/api/system/cms/site/list', 1, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/icon4.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 3, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('f4d7a1bf7ddf43dc9016e1465cd3d9d8', '网站', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,', '网站', '', '', NULL, 1, NULL, NULL, NULL, NULL, NULL, '', '2019-07-24 11:35:11', '', '2019-07-24 11:35:11', '', 3, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('f5203235547342f094a2c126ad4603bb', '文件管理', ',system_manager,f5203235547342f094a2c126ad4603bb,', '文件管理', 'system_manager', '', '/api/system/file/list', 1, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/icon20.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 6, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('f86962e16c214382bd6a7f57a765693f', '删除', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,9efc46fc51304cae8a35d12c942059c9,f86962e16c214382bd6a7f57a765693f,', '删除', '9efc46fc51304cae8a35d12c942059c9', '', '/s/s_PT/dragpage/delete', 0, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/default.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 3, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('s_PT_854e84ec22284834b9055aaea98e910c', '拖拽网页', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,5cce870b5880479794c2c00535c55ad8,s_PT_854e84ec22284834b9055aaea98e910c,', '拖拽网页', '5cce870b5880479794c2c00535c55ad8', '', NULL, 1, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/icon5.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 6, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('system_manager', '系统管理', ',system_manager,', '系统管理', '', '', NULL, 1, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/icon13.png', '2019-07-24 11:35:11', '', '2019-07-24 11:35:11', '', 2, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_auditlog_list', '修改日志', ',system_manager,t_auditlog_list,', '修改日志', 'system_manager', '', '/api/system/auditlog/list', 1, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/icon42.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 1, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_auditlog_look', '查看修改日志', ',system_manager,t_auditlog_list,t_auditlog_look,', '查看修改日志', 't_auditlog_list', NULL, '/api/system/auditlog/look', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_delete', '删除级别', ',system_manager,dic_manager,t_dic_data_grade_list,t_dic_data_grade_delete,', '删除级别', 't_dic_data_grade_list', NULL, '/api/system/dicdata/grade/delete', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_deletemore', '批量删除级别', ',system_manager,dic_manager,t_dic_data_grade_list,t_dic_data_grade_deletemore,', '批量删除级别', 't_dic_data_grade_list', NULL, '/api/system/dicdata/grade/delete/more', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_list', '级别管理', ',system_manager,dic_manager,t_dic_data_grade_list,', '级别管理', 'dic_manager', '', '/api/system/dicdata/grade/list', 1, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 1, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_look', '查看级别', ',system_manager,dic_manager,t_dic_data_grade_list,t_dic_data_grade_look,', '查看级别', 't_dic_data_grade_list', NULL, '/api/system/dicdata/grade/look', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_tree', '级别树形结构', ',system_manager,dic_manager,t_dic_data_grade_list,t_dic_data_grade_tree,', '级别树形结构', 't_dic_data_grade_list', NULL, '/api/system/dicdata/grade/tree', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_update', '修改级别', ',system_manager,dic_manager,t_dic_data_grade_list,t_dic_data_grade_update,', '修改级别', 't_dic_data_grade_list', NULL, '/api/system/dicdata/grade/update', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_delete', '删除民族', ',system_manager,dic_manager,t_dic_data_minzu_list,t_dic_data_minzu_delete,', '删除民族', 't_dic_data_minzu_list', NULL, '/api/system/dicdata/minzu/delete', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_deletemore', '批量删除民族', ',system_manager,dic_manager,t_dic_data_minzu_list,t_dic_data_minzu_deletemore,', '批量删除民族', 't_dic_data_minzu_list', NULL, '/api/system/dicdata/minzu/delete/more', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_list', '民族管理', ',system_manager,dic_manager,t_dic_data_minzu_list,', '民族管理', 'dic_manager', '', '/api/system/dicdata/minzu/list', 1, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 1, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_look', '查看民族', ',system_manager,dic_manager,t_dic_data_minzu_list,t_dic_data_minzu_look,', '查看民族', 't_dic_data_minzu_list', NULL, '/api/system/dicdata/minzu/look', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_tree', '民族树形结构', ',system_manager,dic_manager,t_dic_data_minzu_list,t_dic_data_minzu_tree,', '民族树形结构', 't_dic_data_minzu_list', NULL, '/api/system/dicdata/minzu/tree', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_update', '修改民族', ',system_manager,dic_manager,t_dic_data_minzu_list,t_dic_data_minzu_update,', '修改民族', 't_dic_data_minzu_list', NULL, '/api/system/dicdata/minzu/update', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_delete', '删除学历', ',system_manager,dic_manager,t_dic_data_xueli_list,t_dic_data_xueli_delete,', '删除学历', 't_dic_data_xueli_list', NULL, '/api/system/dicdata/xueli/delete', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_deletemore', '批量删除学历', ',system_manager,dic_manager,t_dic_data_xueli_list,t_dic_data_xueli_deletemore,', '批量删除学历', 't_dic_data_xueli_list', NULL, '/api/system/dicdata/xueli/delete/more', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_list', '学历管理', ',system_manager,dic_manager,t_dic_data_xueli_list,', '学历管理', 'dic_manager', '', '/api/system/dicdata/xueli/list', 1, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 3, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_look', '查看学历', ',system_manager,dic_manager,t_dic_data_xueli_list,t_dic_data_xueli_look,', '查看学历', 't_dic_data_xueli_list', NULL, '/api/system/dicdata/xueli/look', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_tree', '学历树形结构', ',system_manager,dic_manager,t_dic_data_xueli_list,t_dic_data_xueli_tree,', '学历树形结构', 't_dic_data_xueli_list', NULL, '/api/system/dicdata/xueli/tree', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_update', '修改学历', ',system_manager,dic_manager,t_dic_data_xueli_list,t_dic_data_xueli_update,', '修改学历', 't_dic_data_xueli_list', NULL, '/api/system/dicdata/xueli/update', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_fwlog_list', '访问日志', ',system_manager,t_fwlog_list,', '访问日志', 'system_manager', '', '/api/system/fwlog/list', 1, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/icon42.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 2, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_fwlog_look', '查看访问日志', ',system_manager,t_fwlog_list,t_fwlog_look,', '查看访问日志', 't_fwlog_list', NULL, '/api/system/fwlog/look', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_api_user', '菜单管理', ',system_manager,t_menu_api_user,', NULL, 'system_manager', NULL, '/api/user/menu', 1, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-25 00:00:00', '', '2019-07-25 00:00:00', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_delete', '删除菜单', ',system_manager,t_menu_list,t_menu_delete,', '删除菜单', 't_menu_list', NULL, '/api/system/menu/delete', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_deletemore', '批量删除菜单', ',system_manager,t_menu_list,t_menu_deletemore,', '批量删除菜单', 't_menu_list', NULL, '/api/system/menu/delete/more', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_list', '菜单管理', ',system_manager,t_menu_list,', '菜单管理', 'system_manager', '', '/api/system/menu/list/all', 1, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/icon11.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 3, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_look', '查看菜单', ',system_manager,t_menu_list,t_menu_look,', '查看菜单', 't_menu_list', NULL, '/api/system/menu/look', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_tree', '菜单树形结构', ',system_manager,t_menu_list,t_menu_tree,', '菜单树形结构', 't_menu_list', NULL, '/api/system/menu/tree', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_update', '修改菜单', ',system_manager,t_menu_list,t_menu_update,', '修改菜单', 't_menu_list', NULL, '/api/system/menu/update', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_delete', '删除部门', ',business_manager,t_org_list,t_org_delete,', '删除部门', 't_org_list', NULL, '/api/system/org/delete', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_deletemore', '批量删除部门', ',business_manager,t_org_list,t_org_deletemore,', '批量删除部门', 't_org_list', NULL, '/api/system/org/delete/more', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_list', '部门管理', ',business_manager,t_org_list,', '部门管理', 'business_manager', '', '/api/system/org/list', 1, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/icon29.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 1, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_look', '查看部门', ',business_manager,t_org_list,t_org_look,', '查看部门', 't_org_list', NULL, '/api/system/org/look', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_tree', '部门树形结构', ',business_manager,t_org_list,t_org_tree,', '部门树形结构', 't_org_list', NULL, '/api/system/org/tree', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_update', '修改部门', ',business_manager,t_org_list,t_org_update,', '修改部门', 't_org_list', NULL, '/api/system/org/update', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_delete', '删除角色', ',system_manager,t_role_list,t_role_delete,', '删除角色', 't_role_list', NULL, '/api/system/role/delete', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_deletemore', '批量删除角色', ',system_manager,t_role_list,t_role_deletemore,', '批量删除角色', 't_role_list', NULL, '/api/system/role/delete/more', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_list', '角色管理', ',system_manager,t_role_list,', '角色管理', 'system_manager', '', '/api/system/role/list', 1, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/icon23.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 4, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_look', '查看角色', ',system_manager,t_role_list,t_role_look,', '查看角色', 't_role_list', NULL, '/api/system/role/look', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_update', '修改角色', ',system_manager,t_role_list,t_role_update,', '修改角色', 't_role_list', NULL, '/api/system/role/update', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_delete', '删除用户', ',business_manager,t_user_list,t_user_delete,', '删除用户', 't_user_list', NULL, '/api/system/user/delete', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_deletemore', '批量删除用户', ',business_manager,t_user_list,t_user_deletemore,', '批量删除用户', 't_user_list', NULL, '/api/system/user/delete/more', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_list', '用户管理', ',business_manager,t_user_list,', '用户管理', 'business_manager', '', '/api/system/user/list', 1, NULL, NULL, NULL, NULL, NULL, '/img/iconImg/icon24.png', '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 2, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_list_export', '导出用户', ',business_manager,t_user_list,t_user_list_export,', '导出用户', 't_user_list', NULL, '/api/system/user/list/export', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_look', '查看用户', ',business_manager,t_user_list,t_user_look,', '查看用户', 't_user_list', NULL, '/api/system/user/look', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_update', '修改用户', ',business_manager,t_user_list,t_user_update,', '修改用户', 't_user_list', NULL, '/api/system/user/update', 0, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2019-07-24 11:33:44', '', 0, 1, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_org
-- ----------------------------
DROP TABLE IF EXISTS `t_org`;
CREATE TABLE `t_org`  (
  `id` varchar(50)  NOT NULL COMMENT '编号',
  `name` varchar(60)  NOT NULL COMMENT '名称',
  `comcode` varchar(1000)  NOT NULL COMMENT '代码',
  `pid` varchar(50)  NOT NULL COMMENT '上级部门ID',
  `orgType` int(11) NOT NULL COMMENT '0-99门店,100-199部门,200-299,分公司,300-399集团公司,900-999总平台',
  `sortno` int(11) NOT NULL COMMENT '排序,查询时倒叙排列',
  `remark` varchar(2000)  NULL DEFAULT NULL COMMENT '备注',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createUserId` varchar(50)  NULL DEFAULT NULL,
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateUserId` varchar(50)  NULL DEFAULT NULL,
  `active` int(11) NOT NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '部门' ;

-- ----------------------------
-- Records of t_org
-- ----------------------------
INSERT INTO `t_org` VALUES ('o_10001', '平台', ',o_10001,', '', 900, 1, '', '2019-07-24 11:29:56', '', '2019-07-24 11:29:56', '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_org` VALUES ('o_10002', '网站', ',o_10001,o_10002,', 'o_10001', 0, 1, '', '2019-07-24 11:30:00', '', '2019-07-24 11:30:00', '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_org` VALUES ('o_10003', '拖拽演示', ',o_10001,o_10002,o_10003,', 'o_10002', 0, 1, '', '2019-07-24 11:30:02', '', '2019-07-24 11:30:02', '', 1, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_permissions_log
-- ----------------------------
DROP TABLE IF EXISTS `t_permissions_log`;
CREATE TABLE `t_permissions_log`  (
  `id` varchar(50)  NOT NULL COMMENT '主键',
  `siteId` varchar(50)  NULL DEFAULT NULL COMMENT '站点ID',
  `actionType` int(2) NULL DEFAULT NULL COMMENT '操作类型 创建、编辑、删除、启用、禁用',
  `operatorUserId` varchar(50)  NULL DEFAULT NULL COMMENT '操作人ID',
  `operatorUserName` varchar(200)  NULL DEFAULT NULL COMMENT '操作人当时名称',
  `operatorUserRoles` text  NULL COMMENT '操作人当时所属角色名称，逗号分割',
  `operatorObjectType` int(2) NULL DEFAULT NULL COMMENT '操作对象类型',
  `operatorObjectId` varchar(50)  NULL DEFAULT NULL COMMENT '操作对象ID',
  `operatorObjectName` varchar(200)  NULL DEFAULT NULL COMMENT '操作对象当时的名称',
  `actionContent` longtext  NULL COMMENT '操作内容详情',
  `createUserId` varchar(50)  NULL DEFAULT NULL COMMENT '记录创建人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间',
  `bak1` varchar(200)  NULL DEFAULT NULL COMMENT '备用字段',
  `bak2` varchar(200)  NULL DEFAULT NULL COMMENT '备用字段',
  `bak3` varchar(200)  NULL DEFAULT NULL COMMENT '备用字段',
  `bak4` varchar(200)  NULL DEFAULT NULL COMMENT '备用字段',
  `bak5` varchar(200)  NULL DEFAULT NULL COMMENT '备用字段',
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '权限变更日志' ;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` varchar(50)  NOT NULL COMMENT '角色ID',
  `name` varchar(60)  NOT NULL COMMENT '角色名称',
  `code` varchar(255)  NULL DEFAULT NULL COMMENT '权限编码',
  `pid` varchar(50)  NULL DEFAULT NULL COMMENT '上级角色ID,暂时不实现',
  `privateOrg` int(11) NOT NULL DEFAULT 0 COMMENT '角色的部门是否私有,0否,1是,默认0.当角色私有时,菜单只使用此角色的部门权限,不再扩散到全局角色权限,用于设置特殊的菜单权限.公共权限时部门主管有所管理部门的数据全权限,无论角色是否分配. 私有部门权限时,严格按照配置的数据执行,部门主管可能没有部门权限.',
  `roleOrgType` int(11) NOT NULL DEFAULT 0 COMMENT '0自己的数据,1所在部门,2所在部门及子部门数据,3.自定义部门数据.',
  `orgId` varchar(50)  NOT NULL COMMENT '角色的归属部门,只有归属部门的主管和上级主管才可以管理角色,其他人员只能增加归属到角色的人员.不能选择部门或则其他操作,只能添加人员,不然存在提权风险,例如 员工角色下有1000人, 如果给 角色 设置了部门,那这1000人都起效了.',
  `shareRole` int(11) NOT NULL DEFAULT 0 COMMENT '角色是否共享,0否 1是,默认0,共享的角色可以被下级部门直接使用,但是下级只能添加人员,不能设置其他属性.共享的角色一般只设置roleOrgType,并不设定部门.',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createUserId` varchar(50)  NULL DEFAULT NULL,
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateUserId` varchar(50)  NULL DEFAULT NULL,
  `sortno` int(11) NOT NULL DEFAULT 0 COMMENT '排序,查询时倒叙排列',
  `remark` varchar(255)  NULL DEFAULT NULL COMMENT '备注',
  `active` int(11) NOT NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '角色' ;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('e8a4ad9944894908b43ded631094dcbb', '演示站长', NULL, NULL, 0, 1, 'o_10001', 0, '2019-07-24 17:29:44', 'u_10001', '2019-07-24 17:29:44', 'u_10001', 0, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role` VALUES ('r_10001', '超级管理员', NULL, NULL, 0, 2, 'o_10001', 0, '2019-07-24 17:29:45', 'u_10001', '2019-07-24 17:29:45', 'u_10001', 0, '', 1, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `id` varchar(50)  NOT NULL COMMENT '编号',
  `roleId` varchar(50)  NOT NULL COMMENT '角色编号',
  `menuId` varchar(50)  NOT NULL COMMENT '菜单编号',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createUserId` varchar(50)  NULL DEFAULT NULL,
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateUserId` varchar(50)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) ,
  INDEX `fk_t_role_menu_roleId_t_role_id`(`roleId`) ,
  INDEX `fk_t_role_menu_menuId_t_menu_id`(`menuId`) ,
  CONSTRAINT `fk_t_role_menu_menuId_t_menu_id` FOREIGN KEY (`menuId`) REFERENCES `t_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_t_role_menu_roleId_t_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '角色菜单中间表' ;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('09e61f268d174d3082da1c3a35aa1bea', 'r_10001', 't_menu_list', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('125a6986a3ac4d67a61cb056d44768f0', 'r_10001', 't_dic_data_grade_look', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('1318aa56fe9347e9b762394502552513', 'r_10001', 't_org_update', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:28', 'u_10001', '2019-08-08 17:47:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('15da1b7456fc412ca26dd6b0bc41d214', 'r_10001', 't_user_deletemore', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:28', 'u_10001', '2019-08-08 17:47:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('1c496c7e50a446b79dbacb3b0b889071', 'r_10001', 't_user_list', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:28', 'u_10001', '2019-08-08 17:47:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('1e1dd8ce596d4ee69b957dd243f1c947', 'r_10001', 't_org_tree', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:28', 'u_10001', '2019-08-08 17:47:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('1f4df68b4a944e9e8d2cd30f2fa8b6ea', 'r_10001', 't_dic_data_xueli_deletemore', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('21fbbdaf279649af91a477f7694531cc', 'r_10001', 'aff3dc802af540c298af95cb5608fefe', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('24879970948149e3b8f6a04cb87803ff', 'e8a4ad9944894908b43ded631094dcbb', '5cce870b5880479794c2c00535c55ad8', NULL, NULL, NULL, NULL, NULL, '2019-07-25 00:00:00', '', '2019-07-25 00:00:00', '');
INSERT INTO `t_role_menu` VALUES ('248a0b14eb4047de867417187a4c2bf6', 'r_10001', 'f41b9f3b4a0d45f5a3b5842ee40e0e96', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:28', 'u_10001', '2019-08-08 17:47:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('25a7265b025f42098a4e512e37752cee', 'r_10001', 't_user_list_export', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:28', 'u_10001', '2019-08-08 17:47:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('26f09a97370a4915842dbb545c998558', 'r_10001', 't_menu_api_user', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('29919758203944788be230aedb8c29c3', 'r_10001', 'd7e44d49421e41ef9c3329be68dff6f7', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('2cf3c184b81a4bf396efe952a6f0fe23', 'r_10001', 'f5203235547342f094a2c126ad4603bb', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('2e3d8cbb5a5d49b5808ac6b252e97678', 'r_10001', 't_role_delete', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('2e95f583c3b041679605bfae8f80b9ea', 'r_10001', 't_org_deletemore', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:28', 'u_10001', '2019-08-08 17:47:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('354b58e4a22141d1a725c13fa7f1d6ac', 'r_10001', '50374413883c45ae9b9f8e8d7c7609bf', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('35ff1c326ef443278942e7fe77e90b05', 'r_10001', 't_user_look', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:28', 'u_10001', '2019-08-08 17:47:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('3c44fa090de943bdb7e9b40fbed2f06a', 'r_10001', 'b9c4e8ecffe949c0b346e1fd0d6b9977', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:28', 'u_10001', '2019-08-08 17:47:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('420fe7bcc3a24fa0b83155e50c4025c9', 'r_10001', 't_user_delete', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:28', 'u_10001', '2019-08-08 17:47:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('431f83d8d4e945a08d515e280bd92f83', 'r_10001', 't_dic_data_xueli_tree', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('47cafe9a1a6e4cc284fd8cce8bcac751', 'e8a4ad9944894908b43ded631094dcbb', '3330456139a241b1a27a7dcd171d7bf1', NULL, NULL, NULL, NULL, NULL, '2019-07-25 00:00:00', '', '2019-07-25 00:00:00', '');
INSERT INTO `t_role_menu` VALUES ('47fba1306a7a49e39e2d0150ba726610', 'r_10001', 't_dic_data_grade_update', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('4a68595006aa4bb0a053aaac96afb1b1', 'r_10001', 'business_manager', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:28', 'u_10001', '2019-08-08 17:47:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('5f77b7ac7fbd4142801c25926d91ba48', 'e8a4ad9944894908b43ded631094dcbb', '9efc46fc51304cae8a35d12c942059c9', NULL, NULL, NULL, NULL, NULL, '2019-07-25 00:00:00', '', '2019-07-25 00:00:00', '');
INSERT INTO `t_role_menu` VALUES ('60e32bb5513e46bb90358e9bc0d78f9a', 'r_10001', 't_user_update', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:28', 'u_10001', '2019-08-08 17:47:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('62f65c63d7264c1fbac3c27fce94ab1f', 'r_10001', 't_role_deletemore', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('679a21c6850f4f97b59e688b18a1b47c', 'r_10001', '78287e4ac70546168b2fa68818710470', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('692e1968df804c8b85f152129b00e1c4', 'r_10001', 't_dic_data_minzu_deletemore', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('6a61060a48124d729ec745f9119c6ff0', 'r_10001', 't_dic_data_grade_tree', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('6c4814ebbb2b41edb9d086f15c7f67c6', 'r_10001', 'e51808e351c24a7e9fb4d47392930a2d', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:28', 'u_10001', '2019-08-08 17:47:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('6d5817c094634086af2dc44beedaa9cf', 'r_10001', '7cd0678633d5407dba2bd6a1553cadce', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('6f168dcfae3a410683063a0183317c8f', 'r_10001', '081b3344872545448cf5d1804890ab03', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('71c8e8babbca478dad43a2993a9dce6d', 'r_10001', '8c72a4b5e56643ac9a9ca3aeec753c4e', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('73cd2ff78f214e1583c7f480bb80c4bb', 'e8a4ad9944894908b43ded631094dcbb', '50374413883c45ae9b9f8e8d7c7609bf', NULL, NULL, NULL, NULL, NULL, '2019-07-25 00:00:00', '', '2019-07-25 00:00:00', '');
INSERT INTO `t_role_menu` VALUES ('74370cdb3b254ce3b8abda7d1c95d851', 'r_10001', 't_dic_data_grade_delete', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('75a982f1395845adab21ddae3fe3eb39', 'r_10001', 't_menu_update', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('7643bc2a359e430d93b5bb3d69f3d1cc', 'r_10001', 's_PT_854e84ec22284834b9055aaea98e910c', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('77c4df50f57b4278a1f477bd5eef9867', 'r_10001', 't_role_list', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('78c18878be3f49d1a3aa374fd0fd9536', 'r_10001', '36ab9175f7b7423eadda974ba046be05', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:28', 'u_10001', '2019-08-08 17:47:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('7ae10a61dd7947318c84e2878472566e', 'r_10001', 't_dic_data_xueli_delete', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('7b8728b1f9a34b908d258aff18522524', 'r_10001', '9bccbc28b32e41438c5ac73a5e61ed58', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('7edd515f870a4a5aaea7a99a2e8c14d0', 'r_10001', 't_dic_data_xueli_list', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('809ac6144c8e4747af61a55f2f676ee9', 'r_10001', '4adc1e3e3e244c0991d9dab66c63badf', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('828015573b8d47e28dae6d73f16beec7', 'r_10001', 't_org_look', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:28', 'u_10001', '2019-08-08 17:47:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('82ab4035cabc4c9e8485f28e56786595', 'r_10001', 't_dic_data_minzu_tree', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('82c62743f46e4457acdeec79c996c99c', 'r_10001', 't_dic_data_grade_deletemore', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('88746ace469b4f8ab5cbabfe7b588da6', 'r_10001', 't_auditlog_look', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('8961288dad9c4652adbaba4a4ef05ebc', 'e8a4ad9944894908b43ded631094dcbb', '78287e4ac70546168b2fa68818710470', NULL, NULL, NULL, NULL, NULL, '2019-07-25 00:00:00', '', '2019-07-25 00:00:00', '');
INSERT INTO `t_role_menu` VALUES ('8c6fe3b06c3e4a6bb74a657237302596', 'r_10001', 't_role_update', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('8e3404f4db164d38b0214b598bcd2c0e', 'r_10001', 'e614beb39da04bd79797d7fc6ab91d66', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('9270014557ed48d6a2f4f9f5999407b8', 'r_10001', 't_fwlog_look', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('99d56ef1afcb44fba0135c983f26dbe2', 'r_10001', 't_dic_data_grade_list', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('9dbe11d9c138409891c966debb4b2ffb', 'r_10001', 'af298b90f073443bbde4b9e67113d697', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('9f64456c81e448569b244e50cb069e1a', 'r_10001', 't_role_look', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('9fdd5af5b78b44b885ad2726d76cb8a9', 'r_10001', 'ca28235dbd234b7585e133e70cc7999a', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('a11b0d88d62e4795b95ae4f53c293bcc', 'r_10001', 't_dic_data_xueli_update', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('a45d49a55a1c4275a010b6755835d2e8', 'r_10001', 'system_manager', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('ab5db59a708d4689afa2cb320a9592d2', 'e8a4ad9944894908b43ded631094dcbb', 'd7e44d49421e41ef9c3329be68dff6f7', NULL, NULL, NULL, NULL, NULL, '2019-07-25 00:00:00', '', '2019-07-25 00:00:00', '');
INSERT INTO `t_role_menu` VALUES ('af1e133042bd4515b2e71bb02d6cfb77', 'r_10001', 't_menu_deletemore', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('b4b392ff4f00447fbac701aa99ebb9f3', 'r_10001', 'd6abe682007849869c3a168215ae40d4', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('b672da5e0dbe4963b3ac7b05630ad08d', 'r_10001', 't_dic_data_minzu_delete', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('b9cf34c274f84de2804c466e7ed29169', 'r_10001', 'f86962e16c214382bd6a7f57a765693f', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('bad1e832a2ca41839ae15acc53070d4d', 'e8a4ad9944894908b43ded631094dcbb', 's_PT_854e84ec22284834b9055aaea98e910c', NULL, NULL, NULL, NULL, NULL, '2019-07-25 00:00:00', '', '2019-07-25 00:00:00', '');
INSERT INTO `t_role_menu` VALUES ('bbe182b425134d3aaedfda16e4477a85', 'r_10001', 't_auditlog_list', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('bf5c90fd1b244733b5e05ba01f0ee3b1', 'r_10001', 't_menu_delete', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('c834df4888dc421aa0e648220d12e561', 'r_10001', '9efc46fc51304cae8a35d12c942059c9', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('c85e5b69d5af47e6a999afb88b7812be', 'r_10001', '91779a0d304f4b91932b63dec87a8536', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('ccb4a2141a0e4d4b981e2a467693b964', 'r_10001', 'dic_manager', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('d0cfddc792c44fa5a2633060e9c61e51', 'r_10001', 't_dic_data_minzu_look', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('d2e97f48d4044506aca7011e3616dce9', 'e8a4ad9944894908b43ded631094dcbb', '081b3344872545448cf5d1804890ab03', NULL, NULL, NULL, NULL, NULL, '2019-07-25 00:00:00', '', '2019-07-25 00:00:00', '');
INSERT INTO `t_role_menu` VALUES ('d8576f6d04e249858029ed4e20249be7', 'e8a4ad9944894908b43ded631094dcbb', 'af298b90f073443bbde4b9e67113d697', NULL, NULL, NULL, NULL, NULL, '2019-07-25 00:00:00', '', '2019-07-25 00:00:00', '');
INSERT INTO `t_role_menu` VALUES ('dbc8d445a3134919b0424a59db6061b3', 'r_10001', 't_org_list', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:28', 'u_10001', '2019-08-08 17:47:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('dc9fd8388c69470eabd513b303d4ac65', 'r_10001', '3501ed1e23da40219b4f0fa5b7b2749a', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('e51470df0018461c9e231287d6b9f88c', 'e8a4ad9944894908b43ded631094dcbb', 'f4d7a1bf7ddf43dc9016e1465cd3d9d8', NULL, NULL, NULL, NULL, NULL, '2019-07-25 00:00:00', '', '2019-07-25 00:00:00', '');
INSERT INTO `t_role_menu` VALUES ('e58fa584904b4631826ae82081d1b2ed', 'r_10001', 't_org_delete', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:28', 'u_10001', '2019-08-08 17:47:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('e7018424c25a47399e8d0101ebe2d2d4', 'r_10001', 't_menu_tree', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('e73919f166d34839b7624d5c8cb81e27', 'r_10001', 't_dic_data_xueli_look', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('eb1f7cde31e6458c8bd88e0a224942be', 'e8a4ad9944894908b43ded631094dcbb', 'aff3dc802af540c298af95cb5608fefe', NULL, NULL, NULL, NULL, NULL, '2019-07-25 00:00:00', '', '2019-07-25 00:00:00', '');
INSERT INTO `t_role_menu` VALUES ('ecd1c2711d25426fb729fbfa3f224fae', 'r_10001', 'b94392f7b8714f64819c5c0222eb134a', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('f008b79b272a42318bf4f095b65cebb4', 'r_10001', 't_menu_look', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('f0faaa9c51064b779d14edaea2487d8a', 'e8a4ad9944894908b43ded631094dcbb', 'f86962e16c214382bd6a7f57a765693f', NULL, NULL, NULL, NULL, NULL, '2019-07-25 00:00:00', '', '2019-07-25 00:00:00', '');
INSERT INTO `t_role_menu` VALUES ('f135644958554ad69b1e789cdb307e55', 'r_10001', 'ca152df1a7b44d4f81162f34b808934a', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:28', 'u_10001', '2019-08-08 17:47:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('f4cd2fe5a1934fcea797695f4e2c7bb1', 'r_10001', 't_dic_data_minzu_list', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('f6544c19ddea45ae862be6792343c2a1', 'e8a4ad9944894908b43ded631094dcbb', 'e614beb39da04bd79797d7fc6ab91d66', NULL, NULL, NULL, NULL, NULL, '2019-07-25 00:00:00', '', '2019-07-25 00:00:00', '');
INSERT INTO `t_role_menu` VALUES ('f8554632a6d942d39ab95344a4f9bfc2', 'e8a4ad9944894908b43ded631094dcbb', '8c72a4b5e56643ac9a9ca3aeec753c4e', NULL, NULL, NULL, NULL, NULL, '2019-07-25 00:00:00', '', '2019-07-25 00:00:00', '');
INSERT INTO `t_role_menu` VALUES ('f8a9f3863fa8471d933cd76738682b8f', 'r_10001', 't_dic_data_minzu_update', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('f9b4699bd83f4066bd99808ab835b526', 'r_10001', 'cafda855318c4560874f7fb14419be4f', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('fa21e7d6caad4c2fbd97aa026a396cdf', 'e8a4ad9944894908b43ded631094dcbb', 'cafda855318c4560874f7fb14419be4f', NULL, NULL, NULL, NULL, NULL, '2019-07-25 00:00:00', '', '2019-07-25 00:00:00', '');
INSERT INTO `t_role_menu` VALUES ('fcf813cc353d46f4a90d0ff93518f857', 'r_10001', 't_fwlog_list', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:27', 'u_10001', '2019-08-08 17:47:27', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('fdf35a97408f4cf1ba7b6145b9a13705', 'r_10001', '169815aca9cf41d390e7feb6629d361d', NULL, NULL, NULL, NULL, NULL, '2019-08-08 17:47:28', 'u_10001', '2019-08-08 17:47:28', 'u_10001');

-- ----------------------------
-- Table structure for t_role_org
-- ----------------------------
DROP TABLE IF EXISTS `t_role_org`;
CREATE TABLE `t_role_org`  (
  `id` varchar(50)  NOT NULL COMMENT '编号',
  `orgId` varchar(50)  NOT NULL COMMENT '部门编号',
  `roleId` varchar(50)  NOT NULL COMMENT '角色编号',
  `children` int(11) NOT NULL DEFAULT 0 COMMENT '0不包含子部门,1包含.用于表示角色和部门的权限关系.用于记录roleOrgType的结果,缓存值',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createUserId` varchar(50)  NULL DEFAULT NULL,
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateUserId` varchar(50)  NULL DEFAULT NULL,
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) ,
  INDEX `fk_t_role_org_orgId_t_org_id`(`orgId`) ,
  INDEX `fk_t_role_org_roleId_t_role_id`(`roleId`) ,
  CONSTRAINT `fk_t_role_org_orgId_t_org_id` FOREIGN KEY (`orgId`) REFERENCES `t_org` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_t_role_org_roleId_t_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '角色部门中间表' ;

-- ----------------------------
-- Records of t_role_org
-- ----------------------------
INSERT INTO `t_role_org` VALUES ('testid', 'o_10001', 'e8a4ad9944894908b43ded631094dcbb', 1, '2019-07-26 10:00:10', 'u_10001', '2019-07-26 10:00:10', 'u_10001', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_tableindex
-- ----------------------------
DROP TABLE IF EXISTS `t_tableindex`;
CREATE TABLE `t_tableindex`  (
  `id` varchar(50)  NOT NULL COMMENT '表名',
  `maxIndex` int(11) NOT NULL DEFAULT 1 COMMENT '表记录最大的行,一直累加',
  `prefix` varchar(50)  NOT NULL COMMENT '前缀 单个字母加 _',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '记录表最大的行记录' ;

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
  `id` varchar(50)  NOT NULL COMMENT ' ',
  `userName` varchar(30)  NULL DEFAULT NULL COMMENT '姓名',
  `account` varchar(50)  NOT NULL COMMENT '账号',
  `password` varchar(50)  NOT NULL COMMENT '密码',
  `sex` varchar(2)  NULL DEFAULT '男' COMMENT '性别',
  `mobile` varchar(16)  NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(60)  NULL DEFAULT NULL COMMENT '邮箱',
  `openId` varchar(200)  NULL DEFAULT NULL COMMENT '微信openId',
  `unionID` varchar(200)  NULL DEFAULT NULL COMMENT '微信UnionID',
  `avatar` varchar(2000)  NULL DEFAULT NULL COMMENT '头像地址',
  `userType` int(11) NOT NULL COMMENT '0会员,1员工,2店长收银,9系统管理员',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createUserId` varchar(50)  NULL DEFAULT NULL,
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateUserId` varchar(50)  NULL DEFAULT NULL,
  `active` int(11) NOT NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '用户' ;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('23a2c0c52ed142938c159c9b9004fa35', 'ptAdmin', 'ptAdmin', '21232f297a57a5a743894a0e4a801fc3', '男', '', '', '', NULL, NULL, 2, '2019-07-24 11:18:22', 'u_10001', '2019-07-24 11:18:22', 'u_10001', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES ('u_10001', '超级管理员', 'admin', '21232f297a57a5a743894a0e4a801fc3', '男', NULL, NULL, NULL, NULL, NULL, 0, '2019-07-24 11:18:22', 'u_10001', '2019-07-24 11:18:22', 'u_10001', 1, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_user_org
-- ----------------------------
DROP TABLE IF EXISTS `t_user_org`;
CREATE TABLE `t_user_org`  (
  `id` varchar(50)  NOT NULL COMMENT '编号',
  `userId` varchar(50)  NOT NULL COMMENT '用户编号',
  `orgId` varchar(50)  NOT NULL COMMENT '机构编号',
  `managerType` int(11) NOT NULL DEFAULT 1 COMMENT '0会员,1员工,2主管',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createUserId` varchar(50)  NULL DEFAULT NULL,
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateUserId` varchar(50)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) ,
  INDEX `fk_t_user_org_userId_t_user_id`(`userId`) ,
  INDEX `fk_t_user_org_orgId_t_org_id`(`orgId`) ,
  CONSTRAINT `fk_t_user_org_orgId_t_org_id` FOREIGN KEY (`orgId`) REFERENCES `t_org` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_t_user_org_userId_t_user_id` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '用户部门中间表' ;

-- ----------------------------
-- Records of t_user_org
-- ----------------------------
INSERT INTO `t_user_org` VALUES ('1', 'u_10001', 'o_10001', 2, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:18:54', 'u_10001', '2019-07-24 11:18:54', 'u_10001');
INSERT INTO `t_user_org` VALUES ('e6e6ed8fce534c6d9b66feb77c817413', '23a2c0c52ed142938c159c9b9004fa35', 'o_10003', 2, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:18:54', 'u_10001', '2019-07-24 11:18:54', 'u_10001');

-- ----------------------------
-- Table structure for t_user_platform_infos
-- ----------------------------
DROP TABLE IF EXISTS `t_user_platform_infos`;
CREATE TABLE `t_user_platform_infos`  (
  `id` varchar(50)  NOT NULL COMMENT '主键id',
  `openId` varchar(100)  NULL DEFAULT NULL COMMENT '公众号openId,企业号userId,小程序openId,APP推送deviceToken',
  `deviceType` int(11) NULL DEFAULT NULL COMMENT '设备/应用类型：1公众号2小程序3企业号4APP IOS消息推送5APP安卓消息推送6web',
  `siteId` varchar(50)  NULL DEFAULT NULL COMMENT '所属站点ID',
  `userId` varchar(50)  NULL DEFAULT NULL COMMENT 't_user表中ID',
  `bak1` varchar(255)  NULL DEFAULT NULL,
  `bak2` varchar(255)  NULL DEFAULT NULL,
  `bak3` varchar(255)  NULL DEFAULT NULL,
  `bak4` varchar(255)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '用户平台信息表' ;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` varchar(50)  NOT NULL COMMENT '编号',
  `userId` varchar(50)  NOT NULL COMMENT '用户编号',
  `roleId` varchar(50)  NOT NULL COMMENT '角色编号',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createUserId` varchar(50)  NULL DEFAULT NULL,
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateUserId` varchar(50)  NULL DEFAULT NULL,
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) ,
  INDEX `fk_t_user_role_userId_t_user_id`(`userId`) ,
  INDEX `fk_t_user_role_roleId_t_role_id`(`roleId`) ,
  CONSTRAINT `fk_t_user_role_roleId_t_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_t_user_role_userId_t_user_id` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '用户角色中间表' ;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', 'u_10001', 'r_10001', '2019-07-24 11:19:27', 'u_10001', '2019-07-24 11:19:27', 'u_10001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user_role` VALUES ('8a7f31289845414583f230839b98e98d', '23a2c0c52ed142938c159c9b9004fa35', 'e8a4ad9944894908b43ded631094dcbb', '2019-07-24 11:19:27', 'u_10001', '2019-07-24 11:19:27', 'u_10001', NULL, NULL, NULL, NULL, NULL);


-- ----------------------------
-- Table structure for wx_cpconfig
-- ----------------------------
DROP TABLE IF EXISTS `wx_cpconfig`;
CREATE TABLE `wx_cpconfig`  (
  `id` varchar(50)  NOT NULL,
  `orgId` varchar(50)  NOT NULL COMMENT '站点Id',
  `appId` varchar(500)  NOT NULL COMMENT '开发者Id',
  `secret` varchar(500)  NOT NULL COMMENT '应用密钥',
  `active` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '微信号需要的配置信息' ;

-- ----------------------------
-- Table structure for wx_menu
-- ----------------------------
DROP TABLE IF EXISTS `wx_menu`;
CREATE TABLE `wx_menu`  (
  `id` varchar(50)  NOT NULL COMMENT 'id',
  `name` varchar(100)  NULL DEFAULT NULL COMMENT '菜单名称',
  `type` varchar(50)  NULL DEFAULT NULL COMMENT '菜单类型',
  `keyword` varchar(255)  NULL DEFAULT NULL COMMENT '消息内容',
  `url` varchar(255)  NULL DEFAULT NULL COMMENT '跳转地址',
  `pid` varchar(50)  NULL DEFAULT NULL COMMENT '上级菜单id',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `siteId` varchar(50)  NULL DEFAULT NULL COMMENT '站点id',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4 ;

-- ----------------------------
-- Table structure for wx_miniappconfig
-- ----------------------------
DROP TABLE IF EXISTS `wx_miniappconfig`;
CREATE TABLE `wx_miniappconfig`  (
  `id` varchar(50)  NOT NULL COMMENT '主键id',
  `orgId` varchar(50)  NOT NULL COMMENT '站点Id',
  `appId` varchar(500)  NOT NULL COMMENT '开发者Id',
  `secret` varchar(500)  NOT NULL COMMENT '应用密钥',
  `planId` varchar(500)  NULL DEFAULT NULL COMMENT '签约模板Id',
  `requestSerial` varchar(5000)  NULL DEFAULT NULL COMMENT '签约请求序列号',
  `active` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '小程序配置表' ;



INSERT INTO `wx_miniappconfig` (`id`,`orgId`,`appId`,`secret`,`planId`,`requestSerial`,`active`,`bak1`,`bak2`,`bak3`,`bak4`,`bak5`) VALUES ('wx95217af982ed4f53','1','wx95217af982ed4f53','8a4fe0d1b47d46282774d9fe77f6bb19','1','1',1,NULL,NULL,NULL,NULL,NULL);

-- ----------------------------
-- Table structure for wx_mpconfig
-- ----------------------------
DROP TABLE IF EXISTS `wx_mpconfig`;
CREATE TABLE `wx_mpconfig`  (
  `id` varchar(50)  NOT NULL,
  `orgId` varchar(50)  NOT NULL COMMENT '站点Id',
  `appId` varchar(500)  NOT NULL COMMENT '开发者Id',
  `secret` varchar(500)  NOT NULL COMMENT '应用密钥',
  `token` varchar(500)  NULL DEFAULT NULL COMMENT '开发者令牌',
  `aesKey` varchar(500)  NULL DEFAULT NULL COMMENT '消息加解密密钥',
  `wxOriginalId` varchar(500)  NULL DEFAULT NULL COMMENT '微信原始ID',
  `oauth2` int(11) NULL DEFAULT 1 COMMENT '是否支持微信oauth2.0协议,0是不支持,1是支持',
  `active` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '微信号需要的配置信息' ;

-- ----------------------------
-- Table structure for wx_payconfig
-- ----------------------------
DROP TABLE IF EXISTS `wx_payconfig`;
CREATE TABLE `wx_payconfig`  (
  `id` varchar(50)  NOT NULL,
  `orgId` varchar(50)  NOT NULL COMMENT '站点Id',
  `appId` varchar(500)  NOT NULL COMMENT '开发者Id',
  `secret` varchar(500)  NOT NULL COMMENT '应用密钥',
  `mchId` varchar(500)  NOT NULL COMMENT '微信支付商户号',
  `key` varchar(500)  NOT NULL COMMENT '交易过程生成签名的密钥，仅保留在商户系统和微信支付后台，不会在网络中传播',
  `certificateFile` varchar(500)  NOT NULL COMMENT '证书地址',
  `notifyUrl` varchar(1000)  NULL DEFAULT NULL COMMENT '通知地址',
  `signType` varchar(255)  NULL DEFAULT NULL COMMENT '加密方式,MD5和HMAC-SHA256',
  `active` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '微信号需要的配置信息' ;

DROP TABLE IF EXISTS `ali_payconfig`;
CREATE TABLE `ali_payconfig`  (
  `id` varchar(50)    NOT NULL,
  `privateKey` varchar(2000)    NULL DEFAULT NULL,
  `aliPayPublicKey` varchar(1000)    NULL DEFAULT NULL,
  `appId` varchar(50)    NULL DEFAULT NULL,
  `serviceUrl` varchar(200)    NOT NULL,
  `charset` varchar(20)    NOT NULL,
  `signType` varchar(10)    NOT NULL,
  `format` varchar(10)   NOT NULL,
  `certPath` varchar(200)    NULL DEFAULT NULL,
  `alipayPublicCertPath` varchar(200)    NULL DEFAULT NULL,
  `rootCertPath` varchar(200)    NULL DEFAULT NULL,
  `encryptType` varchar(50)   NULL DEFAULT NULL,
  `aesKey` varchar(50)    NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '支付宝的配置信息' ;




SET FOREIGN_KEY_CHECKS = 1;
