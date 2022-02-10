/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : makerone

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 10/02/2022 10:44:13
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
-- Table structure for nft_chain_plat
-- ----------------------------
DROP TABLE IF EXISTS `nft_chain_plat`;
CREATE TABLE `nft_chain_plat`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一标识',
  `chainName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '链名',
  `englishName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '链平台英文名称',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '链平台名称',
  `details` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '平台描述',
  `nodeHost` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '节点链接地址host=ip+port',
  `addressPath` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ak路径(classpath路径下)',
  `privatePath` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '私钥路径(classpath路径下)',
  `passwd` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码,使用SecUtils.encoderByRSAPrivateKey()加密后的数据',
  `publicPath` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '共钥路径(classpath路径下)非必填',
  `contractAccount` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合约账户',
  `contractAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合约地址',
  `browserAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '区块链浏览器地址',
  `currentState` int(0) NOT NULL COMMENT '当前适配状态 0:适配中,1:适配完成',
  `balance` decimal(20, 2) NOT NULL COMMENT 'root账户余额',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `updateTime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `bak1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'bak',
  `bak2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bak3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '链平台' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nft_chain_plat
-- ----------------------------
INSERT INTO `nft_chain_plat` VALUES ('1', 'xuper', 'xuperChain', '百度超级链开放网络', '百度超级链开源技术是百度自主研发创新的产物，拥有链内并行技术、可插拔共识机制、一体化智能合约等业内领先技术支撑，让区块链应用搭建更灵活、性能更高效、安全性更强，全面赋能区块链开发者', '39.156.69.83:37100', 'classpath:keys/xuperchain/root_keys/address', 'classpath:keys/xuperchain/root_keys/private.key', '', 'classpath:keys/xuperchain/root_keys/public.key', '', '', 'https://xuper.baidu.com/n/console#/xuperos/explorer', 1, 0.00, '2021-12-22 11:45:40', '2022-02-08 09:47:16', '', '', '');

-- ----------------------------
-- Table structure for nft_collection
-- ----------------------------
DROP TABLE IF EXISTS `nft_collection`;
CREATE TABLE `nft_collection`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一标识',
  `userId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户标识',
  `logoPath` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'logo图地址',
  `coverPath` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '合集封面地址',
  `bannerPath` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'banner图地址',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '合集名称',
  `link` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自定义链接',
  `details` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '合集简介',
  `type` int(0) NOT NULL COMMENT '合集类别',
  `royalties` decimal(10, 2) NOT NULL COMMENT '合集版税，百分比',
  `chainPlatCert` json NULL COMMENT '上架后,在不同链平台上链,认证后的数据-json',
  `isIn` int(0) NOT NULL COMMENT '是否上架 0:否,1:是',
  `inTime` datetime(0) NULL DEFAULT NULL COMMENT '上架时间',
  `outTime` datetime(0) NULL DEFAULT NULL COMMENT '下架时间',
  `isCert` int(0) NOT NULL COMMENT '是否平台认证,0:否,1是',
  `createTime` datetime(0) NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  `fire` int(0) NULL DEFAULT NULL COMMENT '热度值',
  `sumPrice` decimal(20, 2) NULL DEFAULT NULL COMMENT '交易总额',
  `lowprice` decimal(20, 2) NULL DEFAULT NULL COMMENT '地板价',
  `buyersnum` int(0) NULL DEFAULT NULL COMMENT '拥有者数量',
  `weekchange` double(10, 2) NULL DEFAULT NULL COMMENT '7天涨跌幅',
  `daychange` double(10, 2) NULL DEFAULT NULL COMMENT '1天涨跌幅',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户合集信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nft_collection
-- ----------------------------
INSERT INTO `nft_collection` VALUES ('20220119134138108343727195', 'u_10001', '/attachmentFile/2022-01-19T134057.7240c6eb85e-3778-4903-8b35-84dfc9a02c71.png', '/attachmentFile/2022-01-19T144958.555c5215083-03ce-4209-9655-17533e0e21fa.png', '/attachmentFile/2022-01-19T145002.027510bc6e6-1a48-42de-9de0-7559630aab73.png', '虎年头像', NULL, '%3Cp%20style%3D%22text-align%3Acenter%3B%22%20size%3D%220%22%20_root%3D%22undefined%22%20__ownerid%3D%22undefined%22%20__hash%3D%22undefined%22%20__altered%3D%22false%22%3E%E7%A6%8F%E8%99%8E%E5%90%88%E9%9B%86%3C%2Fp%3E', 1, 1.00, '{}', 0, NULL, NULL, 0, '2022-01-19 13:41:38', '2022-01-19 16:07:08', 13, 0.91, 0.01, 13, 0.00, 0.00);
INSERT INTO `nft_collection` VALUES ('20220207160344942613795446', 'u_10001', '/attachmentFile/2022-02-07T155927.841052b5741-9d0e-4a4d-9bf8-ea9d4d356db5.png', '/attachmentFile/2022-02-07T160327.4568e6ecc92-8e5a-4fc9-a095-0be3698cfcf9.jpg', '/attachmentFile/2022-02-07T160330.5998e9ae1a0-9a30-4aa9-b050-a13ed0c8561c.jpg', '测试数据', NULL, '%3Cp%3E%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%3C%2Fp%3E', 1, 1.00, '{}', 0, NULL, NULL, 0, '2022-02-07 16:03:45', NULL, 2, 0.04, 0.01, 2, 0.00, 0.00);

-- ----------------------------
-- Table structure for nft_order
-- ----------------------------
DROP TABLE IF EXISTS `nft_order`;
CREATE TABLE `nft_order`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一标识',
  `chainPlatCert` json NULL COMMENT '在不同链平台上链,认证后的数据json',
  `gas` decimal(20, 2) NULL DEFAULT NULL COMMENT 'gas总费',
  `fromUser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '卖方',
  `toUser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买方',
  `assetsId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '交易品标识(作品)',
  `num` int(0) NOT NULL COMMENT '交易品数量',
  `price` decimal(20, 2) NOT NULL COMMENT '单价',
  `total` decimal(20, 2) NOT NULL COMMENT '商品总额',
  `commission` decimal(20, 2) NULL DEFAULT NULL COMMENT '佣金(支付给平台的费用)',
  `taxes` decimal(20, 2) NULL DEFAULT NULL COMMENT '税费(二次交易,支付给作者的费用)=(版税*商品总额)',
  `sellerAmount` decimal(20, 2) NULL DEFAULT NULL COMMENT '卖家收到的金额',
  `tradeTotal` decimal(20, 2) NOT NULL COMMENT '交易总额',
  `payPlat` int(0) NULL DEFAULT NULL COMMENT '支付平台(0:微信支付,1支付宝...)',
  `tradeType` int(0) NOT NULL COMMENT '交易类型(0:充值,1:上架合集,2:下架合集,3:上架商品,4:下架商品,5:交易作品,6:转移作品)',
  `txTime` datetime(0) NULL DEFAULT NULL COMMENT '交易时间',
  `transactionId` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付平台支付订单号',
  `payState` int(0) NOT NULL DEFAULT 0 COMMENT '支付状态.0:未支付,1:已支付,2:已取消',
  `codeUrl` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付二维码',
  `prepayId` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预支付id',
  `createTime` datetime(0) NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  `bak1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bak2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bak3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户交易订单列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nft_order
-- ----------------------------
INSERT INTO `nft_order` VALUES ('20220119150304044212626354', '{\"xuperChain\": \"{\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"ac5ab9a564beb10330fbc8a404a6e337aa147137eefa9d7803de811fec2ebaa6\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\",\\\"hash\\\":\\\"453b946585bb081f8db27d0153a77f88\\\"}\"}', 0.01, 'MakerOne平台', 'u_10001', '20220119150207930196550433', 1, 0.00, 0.00, NULL, NULL, NULL, 0.01, 0, 3, '2022-01-19 15:03:15', '4200001300202201195648906741', 1, 'weixin://wxpay/bizpayurl?pr=icA0cqgzz', NULL, '2022-01-19 15:03:04', '2022-01-19 15:04:39', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220119150606485265434699', '{\"xuperChain\": \"{\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"12ddcf920be2a5488db15b1ca190fd665222a55eda97b9506e077efe3da24972\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\",\\\"hash\\\":\\\"9d04983c8cb75e02fb431ff17b249638\\\"}\"}', 0.01, 'MakerOne平台', 'u_10001', '20220119135610546896924897', 2022, 0.00, 0.00, NULL, NULL, NULL, 0.01, 0, 3, '2022-01-19 15:06:16', '4200001347202201192681520682', 1, 'weixin://wxpay/bizpayurl?pr=i2NNJu4zz', NULL, '2022-01-19 15:06:07', '2022-01-19 15:06:18', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220119153036317101190935', '{\"xuperChain\": \"{\\\"txId\\\":\\\"1012004913f39233c9b0d0c3dad98c69f9462f9946ff7f3c94efa829346ce4fe\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\"}\"}', 0.06, 'u_10001', '20220110163739394089049276', '20220119135610546896924897', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.07, 0, 5, '2022-01-19 15:30:47', '4200001314202201190415495154', 1, NULL, 'wx1915303661267469b19b8799cc40f20000', '2022-01-19 15:30:37', '2022-01-19 15:30:51', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220119153533120284098188', '{}', 0.06, 'u_10001', '20220113171851406370700861', '20220119135610546896924897', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.07, NULL, 5, NULL, NULL, 2, NULL, 'wx19153533397653335ce4fc49645c3b0000', '2022-01-19 15:35:33', '2022-01-19 15:35:53', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220119153558511043967600', '{}', 0.06, 'u_10001', '20220113171851406370700861', '20220119135610546896924897', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.07, NULL, 5, NULL, NULL, 2, NULL, 'wx19153558806501ee0c460934ea23aa0000', '2022-01-19 15:35:59', '2022-01-19 15:36:12', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220119153615498060029142', '{\"xuperChain\": \"{\\\"txId\\\":\\\"123c64d77e771ed4e34bedba8225de52bd94d05fb140e5e8718c97b8d98c77be\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\"}\"}', 0.06, 'u_10001', '20220113171851406370700861', '20220119135610546896924897', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.07, 0, 5, '2022-01-19 15:36:32', '4200001311202201198530657713', 1, NULL, 'wx19153615790423764ccbb315e6a82d0000', '2022-01-19 15:36:16', '2022-01-19 15:36:36', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220119160449637468807996', '{\"xuperChain\": \"{\\\"txId\\\":\\\"30799e0f4d8c21bda743fad6678549d78b766e715254623b1d8e9f78405a6a36\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\"}\"}', 0.06, 'u_10001', '20220113173245029522827454', '20220119135610546896924897', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.07, 0, 5, '2022-01-19 16:04:56', '4200001332202201199771882210', 1, NULL, 'wx19160449945416b4d65388ea1c34620000', '2022-01-19 16:04:50', '2022-01-19 16:05:00', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220119160751042769908804', '{\"xuperChain\": \"{\\\"txId\\\":\\\"3773857dd4f72b7e479b8c9bc096d436c757c21571accea51db332cb82eaef7f\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\"}\"}', 0.06, 'u_10001', '20220118090607611954267892', '20220119135610546896924897', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.07, 0, 5, '2022-01-19 16:07:58', '4200001311202201194353139612', 1, NULL, 'wx19160751331109db2321316c92f2a90000', '2022-01-19 16:07:51', '2022-01-19 16:08:02', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220119161134023983927314', '{\"xuperChain\": \"{\\\"txId\\\":\\\"f51d7f81876a9878c95a046716dd8bc1084db4844afb9019920734fa809fb98a\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\"}\"}', 0.06, 'u_10001', '20220119160509299117894116', '20220119135610546896924897', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.07, 0, 5, '2022-01-19 16:11:47', '4200001348202201191425483051', 1, NULL, 'wx19161134291880d02b317c39ee3f380000', '2022-01-19 16:11:34', '2022-01-19 16:11:51', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220119173238405759276689', '{}', 0.06, 'u_10001', '20220119164724592016531570', '20220119135610546896924897', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.07, NULL, 5, NULL, NULL, 2, NULL, 'wx19173238705050350b8e5fceeb22030000', '2022-01-19 17:32:39', '2022-01-19 17:32:59', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220119173731112859691102', '{\"xuperChain\": \"{\\\"txId\\\":\\\"54628d3c947970556de6d87b99612cd5d9539970c79416b0863f971d51263c97\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\"}\"}', 0.06, 'u_10001', '20220119164724592016531570', '20220119135610546896924897', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.07, 0, 5, '2022-01-19 17:37:40', '4200001327202201190124468801', 1, NULL, 'wx19173731384721876da8edbace57d90000', '2022-01-19 17:37:31', '2022-01-19 17:37:44', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220120144456120551429898', '{\"xuperChain\": \"{\\\"txId\\\":\\\"fa3ad13300dcdd14bc1e2705f9129cfe89d554ece86c3d4d38b41dc7e8f1b573\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\"}\"}', 0.06, 'u_10001', '20220120144323010894692552', '20220119135610546896924897', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.07, 0, 5, '2022-01-20 14:45:15', '4200001346202201201610343592', 1, NULL, 'wx20144456916598f787cfa04e8f9a130000', '2022-01-20 14:44:57', '2022-01-20 14:45:22', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220120145537853925849530', '{\"xuperChain\": \"{\\\"txId\\\":\\\"a297217e360edeb46d68c2458fa6c4a014d3400005c2b2cafb7ca0acf823aafe\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\"}\"}', 0.06, 'u_10001', '20220120145123675920472814', '20220119135610546896924897', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.07, 0, 5, '2022-01-20 14:55:53', '4200001313202201201007686655', 1, NULL, 'wx201455382273975d05f6a0d1943c7b0000', '2022-01-20 14:55:38', '2022-01-20 14:55:57', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220120145955755578809364', '{\"xuperChain\": \"{\\\"txId\\\":\\\"36941084da083b74e0056e136c5e8e3faa28fb348bfd687a0187d459a7517250\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\"}\"}', 0.06, 'u_10001', '20220120145847550390478452', '20220119135610546896924897', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.07, 0, 5, '2022-01-20 15:00:03', '4200001334202201200139329794', 1, NULL, 'wx201459559917299cd86c1bd27fada40000', '2022-01-20 14:59:56', '2022-01-20 15:00:07', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220120150835948302364039', '{\"xuperChain\": \"{\\\"txId\\\":\\\"c196a890f75a44ef83530d9df88ce5c0f88b836d2fe35d30463fa30939b6d701\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\"}\"}', 0.06, 'u_10001', '20220120150638198426674299', '20220119135610546896924897', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.07, 0, 5, '2022-01-20 15:08:54', '4200001332202201208152053888', 1, NULL, 'wx20150836250740b96c8601ceb8fd960000', '2022-01-20 15:08:36', '2022-01-20 15:08:59', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220121163928592804349878', '{}', 0.06, 'u_10001', '20220113152745730601337038', '20220119135610546896924897', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.07, NULL, 5, NULL, NULL, 2, NULL, 'wx21163928892482138d7ad553efe8d10000', '2022-01-21 16:39:29', '2022-01-21 16:39:38', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220121163946592380660241', '{\"xuperChain\": \"{\\\"txId\\\":\\\"2cbd14c549bc58fd2f10839cc7b86824f91280cbbbae48c4c435af1fd4d43ca5\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\"}\"}', 0.06, 'u_10001', '20220113152745730601337038', '20220119135610546896924897', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.07, 0, 5, '2022-01-21 16:40:34', '4200001347202201211262234827', 1, NULL, 'wx21163946826004c7b872f8c7ede8a80000', '2022-01-21 16:39:47', '2022-01-21 16:40:38', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220201174905107915065285', '{}', 0.06, 'u_10001', '20220201174716816173837811', '20220119135610546896924897', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.07, NULL, 5, NULL, NULL, 2, NULL, 'wx01174905693301ce31110bee726a240000', '2022-02-01 17:49:06', '2022-02-01 18:04:10', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220201175121931230114196', '{\"xuperChain\": \"{\\\"txId\\\":\\\"701f01381f6f91e9de028448a7f5791b482767a185c014593c9403e11600b07e\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\"}\"}', 0.06, 'u_10001', '20220201173425264129665953', '20220119135610546896924897', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.07, 0, 5, '2022-02-01 17:51:45', '4200001328202202018292519523', 1, NULL, 'wx01175122270422ade9f3ef1b9ce98d0000', '2022-02-01 17:51:22', '2022-02-01 17:51:48', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220202112032586969301969', '{\"xuperChain\": \"{\\\"txId\\\":\\\"e080aadd6540643506cc362340e9c22341938c2304bf595be08711dca1b6f595\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\"}\"}', 0.06, 'u_10001', '20220202111906491873236797', '20220119135610546896924897', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.07, 0, 5, '2022-02-02 11:20:52', '4200001349202202021238848920', 1, NULL, 'wx0211203289436647a4a7815e61b02b0000', '2022-02-02 11:20:33', '2022-02-02 11:21:00', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220207160623594020424582', '{\"xuperChain\": \"{\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"2b01bddd64b3bc100b346876d51fa104b111e9b7930f5d2eb9570fad0f875913\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"SJ_Test_ERC_11\\\",\\\"hash\\\":\\\"46a642b7f2a481bbbcf2cc055bcaa779\\\"}\"}', 0.01, 'MakerOne平台', 'u_10001', '20220207160536096088160970', 10, 0.00, 0.00, NULL, NULL, NULL, 0.01, 0, 3, '2022-02-07 16:06:40', '4200001344202202070793258885', 1, 'weixin://wxpay/bizpayurl?pr=OG3fFgtzz', NULL, '2022-02-07 16:06:24', '2022-02-07 16:06:43', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220207162656519510714203', '{\"xuperChain\": \"{\\\"txId\\\":\\\"835bef3ad80a5eb57b35faa6795bbf64470580654b2fcc836a0754b23375b5ef\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"SJ_Test_ERC_11\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\"}\"}', 0.01, 'u_10001', '20220110163739394089049276', '20220207160536096088160970', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.02, 0, 5, '2022-02-07 16:28:15', '4200001314202202072011465389', 1, NULL, 'wx07162656999254e5f84fc9686d01820000', '2022-02-07 16:26:57', '2022-02-07 16:28:20', NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220207171155730976191127', '{}', 0.00, '20220110163739394089049276', '20220113152745730601337038', '20220207160536096088160970', 1, 0.01, 0.00, 0.00, 0.00, 0.00, 0.00, NULL, 6, NULL, NULL, 1, NULL, NULL, '2022-02-07 17:11:56', NULL, NULL, NULL, NULL);
INSERT INTO `nft_order` VALUES ('20220207171445802553671135', '{\"xuperChain\": \"{\\\"txId\\\":\\\"ed4baac286663a734c9f8e4915e7d8c8015a8e8de23ce9929e970daf5a220f77\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"SJ_Test_ERC_11\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\"}\"}', 0.01, 'u_10001', '20220110163739394089049276', '20220207160536096088160970', 1, 0.01, 0.01, 0.00, 0.01, 0.00, 0.02, 0, 5, '2022-02-07 17:14:53', '4200001339202202075674760748', 1, NULL, 'wx07171446761656c46a0f08c5d97ecd0000', '2022-02-07 17:14:47', '2022-02-07 17:14:58', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for nft_type
-- ----------------------------
DROP TABLE IF EXISTS `nft_type`;
CREATE TABLE `nft_type`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一标识',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `value` int(0) NULL DEFAULT NULL COMMENT '值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '作品类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nft_type
-- ----------------------------
INSERT INTO `nft_type` VALUES ('20211224101348044345092190', '艺术品', 1);

-- ----------------------------
-- Table structure for nft_user_assets
-- ----------------------------
DROP TABLE IF EXISTS `nft_user_assets`;
CREATE TABLE `nft_user_assets`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一标识',
  `userId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户标识',
  `assetsId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源标识',
  `type` int(0) NOT NULL COMMENT '资源类型0:合集,1:作品',
  `origin` int(0) NOT NULL COMMENT '资产来源(0:自己创作,1购买其他作者)',
  `sellState` int(0) NOT NULL DEFAULT 0 COMMENT '出售状态,0:不出售,1:售卖中,2:已生成订单,3:已删除',
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  `bak1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bak2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bak3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户资产信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nft_user_assets
-- ----------------------------
INSERT INTO `nft_user_assets` VALUES ('20220119134138123012930220', 'u_10001', '20220119134138108343727195', 0, 0, 0, '2022-01-19 13:41:38', NULL, NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920257316820907', '20220110163739394089049276', '20220119135610546896924897', 1, 1, 0, '2022-01-19 14:49:20', '2022-01-19 15:30:50', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920321214216914', '20220113171851406370700861', '20220119135610546896924897', 1, 1, 0, '2022-01-19 14:49:20', '2022-01-19 15:36:36', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920326782761976', '20220113173245029522827454', '20220119135610546896924897', 1, 1, 0, '2022-01-19 14:49:20', '2022-01-19 16:05:00', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920330965276087', '20220118090607611954267892', '20220119135610546896924897', 1, 1, 0, '2022-01-19 14:49:20', '2022-01-19 16:08:02', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920402157998891', '20220119160509299117894116', '20220119135610546896924897', 1, 1, 0, '2022-01-19 14:49:20', '2022-01-19 16:11:51', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920408839535004', '20220119164724592016531570', '20220119135610546896924897', 1, 1, 0, '2022-01-19 14:49:20', '2022-01-19 17:37:44', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920414790789903', '20220120144323010894692552', '20220119135610546896924897', 1, 1, 0, '2022-01-19 14:49:20', '2022-01-20 14:45:21', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920418237795504', '20220120145123675920472814', '20220119135610546896924897', 1, 1, 0, '2022-01-19 14:49:20', '2022-01-20 14:55:57', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920422808069990', '20220120145847550390478452', '20220119135610546896924897', 1, 1, 0, '2022-01-19 14:49:20', '2022-01-20 15:00:07', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920425183510997', '20220120150638198426674299', '20220119135610546896924897', 1, 1, 0, '2022-01-19 14:49:20', '2022-01-20 15:08:59', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920428219317301', '20220113152745730601337038', '20220119135610546896924897', 1, 1, 0, '2022-01-19 14:49:20', '2022-01-21 16:40:38', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920430696946420', '20220201173425264129665953', '20220119135610546896924897', 1, 1, 0, '2022-01-19 14:49:20', '2022-02-01 17:51:48', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920433876833682', '20220202111906491873236797', '20220119135610546896924897', 1, 1, 0, '2022-01-19 14:49:20', '2022-02-02 11:21:00', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920436706150062', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:20', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920439635153132', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:20', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920442443943356', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:20', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920501710891824', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920505271503981', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920508418019532', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920511500504787', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920514133252446', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920517936975790', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920520208543171', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920523216561081', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920525998557023', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920528941604818', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920531332558468', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920533502975615', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920536282268559', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920538562951934', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920541702732737', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920603168213467', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920608572030169', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920612376208580', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920616188847030', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920619622984965', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920622125095102', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920624991485238', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920627069818757', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920630242683591', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920632805018045', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920635682925828', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920637921700528', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920702642272439', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920706674211326', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920711233283514', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920714319262606', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920718727780056', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920721743395942', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920724236785947', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920727844590666', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920735585153433', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920738085518509', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920803808685747', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920807539744679', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920811399052256', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920814474074263', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920817852889140', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920820798693684', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920822184985513', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920825947262205', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920827066716808', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920829695144039', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920832773084309', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920833351947887', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920835917456339', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920837017092573', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920839188559753', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920841584132062', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920901627074438', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920906923437304', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920909550565042', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920913395400382', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920916152052896', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920919336714827', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920922867688176', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920924096820457', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920926897442307', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920928602954010', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920931383264615', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920933600062110', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920935780623010', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920937988041864', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920939675022383', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920941530680599', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144920943569508350', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921004977357091', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921009376390263', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921012498423628', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921015775031979', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921018877774193', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921020372722142', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921023126525706', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921025083559553', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921028954415057', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921030015510425', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921032580717897', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921034009592552', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921036767583312', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921100542683882', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921104092283629', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921108058478521', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921111607990916', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921114848176540', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921117281584387', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921119022588255', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921121502481109', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921124525509441', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921125233435600', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921127005096728', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921129027821818', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921131215215225', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921132094414630', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921134024636560', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921136620189122', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921137101960805', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921200690071605', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921203962651957', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921206729518957', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921209009288743', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921212774946782', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921214676387099', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921217161071489', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921219782776566', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921221058525386', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921223749613911', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921225395117221', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921226493225193', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921228118249006', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921230970834389', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921231034721491', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921233232928680', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921240791800202', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921301502691507', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921304830780999', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921306226241699', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921308170568259', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921310864689200', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921313396107009', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921315846855477', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921317169035296', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921319438305201', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921321501524140', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921323721280693', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921325260865511', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921326229502475', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921328278586086', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921330031061802', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921331802071040', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921333014020223', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921334551676154', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921336057233119', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921337849331744', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921339480762085', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921340158475088', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921342291922650', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921401847911311', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921405666061372', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921408652944346', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921411164504712', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921413997831419', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921416132559859', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921418545387414', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921420575282564', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921422562619963', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921423790516718', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921425835165810', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:21', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921523145037522', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921525717994323', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921527792868520', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921528362756720', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921530160861409', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921531842763494', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921533983300840', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921535502060468', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921536071077580', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921538633674937', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921539788168812', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921541605294620', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921543029699896', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921544102076119', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921546109492621', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921548080233791', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921549301108480', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921600465544510', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921603054519505', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921605855861805', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921608182581740', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921610966314958', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921612616777934', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921614084474405', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921616321533026', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921618367114919', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921619452803049', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921621878582159', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921623168493512', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921624518944175', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921626781056690', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921628317438880', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921629819687068', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921631273786230', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921632214176310', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921634640821667', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921635467481735', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921700365501199', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921703310580125', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921706539038998', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921709260033262', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921712401306075', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921714422530000', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921716215841260', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921718577116878', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921720378715334', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921727657090680', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921729958825123', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921730522160168', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921732640711874', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921733030703063', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921735649504846', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921737919454797', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921738041874552', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921740038901680', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921741766362096', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921743517418956', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921801009317671', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921803280013452', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921806107541364', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921808445346819', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921810642177545', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921812795792969', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921814381127998', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921816064144597', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921817560585254', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921819390662744', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921820570051079', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921822015840458', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921823062868184', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921824884130637', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921826124045649', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921828340954811', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921829589904431', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921830403646770', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921832202137215', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921833340440334', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921834064458234', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921835243175601', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921837487308948', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921838132837723', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921839270109236', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921841055397117', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921842581312389', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921843252197722', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921900205963041', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921902871366462', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921903619123213', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921905716496710', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921906850424437', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921908094598161', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921909792445664', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921910770733866', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921912933734384', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921913172695989', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921914932095866', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921916104780805', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921917360267061', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921918260828490', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921920880362032', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921921334759609', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921922521213330', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921924810409382', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921925392048251', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921926060628031', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921927977222607', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921929677361457', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921930147582967', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921931016454842', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921932757053099', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921934680620538', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921935823165051', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921936856686979', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921937103168906', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921939431312895', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921940374647723', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921941861366810', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921942872654376', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144921943471293989', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922001377132380', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922002157535692', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922004357323111', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922005517249576', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922006121654503', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922008516952461', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922009207695587', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922010113004926', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922016015356019', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922018193261298', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922019002961493', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922020637496071', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922021864688778', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922023531723439', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922024805805972', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922025325539986', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922026786247734', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922028511492668', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922029412468975', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922030801943368', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922031220550612', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922033491829143', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922034884598416', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922035100681482', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922036649225320', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922037497125549', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922039969329451', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922040927701014', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922041213620596', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922102903389747', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922105095488331', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922108464520158', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922110071146699', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922112535196783', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922114683449794', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922116348785919', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922117567500182', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922119697073015', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922121986530763', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922122959036595', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922123485365754', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922125344645211', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922126089561347', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922127954111579', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922128402035733', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922130067274288', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922131876559876', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922132097847640', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922134286705796', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922135781049322', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922136336684300', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922137798092098', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922139103259607', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922140342704434', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922141199312483', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922142080856889', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922143726639861', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922144843519722', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922145321504206', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922147970495293', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922200151537483', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922201903726727', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922203192396899', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922204859503394', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922206769039810', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922207611733114', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922208346953883', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922209615954001', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922210393870208', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922212378074611', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922213562602726', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922214507149366', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922215341030057', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922216946734038', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922218179740898', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922219446758003', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922220990178536', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922221761742979', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922222501826479', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922224070234177', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922225434723212', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922227122663025', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922228513538234', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922229812999245', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922230980936636', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922231298843447', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922232550854237', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922233164911946', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922234246801133', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922235405214347', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922243566749085', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922244955721377', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922245268297894', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922246198397589', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922301915404908', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922303584762032', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922304457741503', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922306123383530', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922307876394473', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922308219266328', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922310314993587', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922311353847421', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922312867221089', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922313394997437', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922314648479877', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922315445121137', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922316750384097', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922318111417419', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922319246582545', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922320468805943', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922321485032525', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922322533593523', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922323970313002', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922324341524486', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922325352219460', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922326103989670', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922327440215402', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922328975978082', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922329657725643', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922331162623647', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922332678569387', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922333189575214', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922334489593430', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922335813342558', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922336277835358', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922337894796409', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922338263955834', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922339048453412', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922340627518334', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922341886147044', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922342569067562', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922343172391309', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922344696150842', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922345546178453', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922346179546120', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922347074991554', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922348557022703', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922349064373591', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922400269358690', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922401684646261', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922403203736375', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922404912440497', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922405376493602', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922406606656151', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922407941564743', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922409094088880', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922410995664936', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922411690389314', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922412142715857', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922413330414697', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922414494706964', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922415378909203', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922416801853108', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922417064893114', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922418984679971', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922419056976890', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922420967592666', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922421711878862', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922422898631206', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922423089083669', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922425800528580', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922426308949977', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922427634439394', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922428039414809', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922429720373123', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922430099787712', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922431643208997', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922432458465852', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922433808388864', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922433984147218', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922434828536122', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922440713081740', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922441361805142', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922442672746783', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922443905770054', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922444511403546', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922445271053178', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922446512359162', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922447254288568', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922448901952791', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922449480295132', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922450877804696', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:22', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922501286579531', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922503029525172', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922506972982588', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922508138885357', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922509952767442', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922511595273128', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922513289185580', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922514933483721', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922516703092631', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922517680073392', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922519802206018', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922520924741128', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922521167827637', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922523042491535', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922524460430142', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922525950579051', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922526774325212', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922527932562807', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922528381961424', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922530328900108', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922531521121793', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922532679804781', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922534748398273', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922535015293400', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922536962642824', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922537550274458', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922538075698886', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922539957722913', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922540971575934', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922541708217993', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922542499363409', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922544104128642', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922600303035716', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922602785673380', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922604761917240', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922606205982618', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922608340422862', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922609401920482', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922611337843071', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922613468541856', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922615233485886', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922617658862088', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922618331749822', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922620646170426', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922621415252379', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922623282783953', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922624426284737', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922625364730376', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922626338608383', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922627123791086', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922628703602067', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922629401697744', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922631851080182', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922632960121946', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922633094436320', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922634869548748', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922635853715898', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922636445071115', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922637812407986', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922638652514325', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922639727135310', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922640789300355', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922641637751753', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922642276440143', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922643384043898', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922644267482930', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922645319805866', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922646308064674', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922647531528326', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922700042096075', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922709967435376', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922710381227843', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922712732001684', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922714730709798', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922716917361311', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922717184589126', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922719875938633', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922720427909557', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922722682195320', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922723399217509', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922724161510342', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922725219732873', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922727886918849', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922728615342343', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922729647953306', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922730391991080', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922731221248370', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922732055655402', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922733237852493', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922734536302069', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922735406807149', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922736368923694', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922737987182481', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922738566533914', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922739973124925', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922740745630065', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922741475963688', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922742735508674', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922743981549464', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922744738087561', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922745773463451', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922746868102191', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922747107972586', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922748721818544', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922749032112592', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922750160247345', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922800007017567', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922802645724058', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922803670909995', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922804901126359', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922805987980314', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922806757900612', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922807118934697', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922809360674552', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922810897415062', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922811518134596', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922812836092518', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922813934652302', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922814399579840', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922815202618617', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922816939598082', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922817193376200', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922819045985260', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922820122119903', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922821354894765', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922822760974053', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922823991439064', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922824605622706', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922825292480414', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922826985007311', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922827604637898', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922828073430059', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922829934471919', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922830880717012', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922831985907841', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922832350051867', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922833384324625', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922834407322437', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922835844259178', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922836702970052', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922837397459954', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922838232052750', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922839691083188', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922841899924719', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922842965861046', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922843168901965', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922844401627520', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922845574953210', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922846245633237', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922847178095415', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922900131470616', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922908661636596', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922911370211747', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922912886271227', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922914712428243', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922916863036322', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922917433701731', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922919531722554', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922921729597230', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922922188839014', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922924545405221', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922926572221072', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922934488399718', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922937257546616', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922938699377638', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922940391281647', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922942325241580', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922943995489043', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922945406727396', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922946800426234', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144922947651268401', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923001945350240', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923004545559241', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923007451767648', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923009353682575', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923012355162174', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923014839542492', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923016123162092', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923018834191491', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923020417152829', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923022642262554', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923024498994001', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923025885581602', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923027259258864', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923029424602396', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923030864229296', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923032233938536', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923033231256789', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923035807214982', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923036360919888', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923038708154924', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923039878818136', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923040003063401', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923042691011933', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923043200131376', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923100183891073', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923102583997554', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923106148056935', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923109259114127', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923111010996078', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923114727651225', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923116629566415', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923118601146479', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923121602800357', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923123496624099', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923125425237922', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923127818316146', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923128179663735', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923130131774780', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923132151146023', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923134744782330', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923135307983945', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923137049792436', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923139241082128', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923140095771802', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923142266792731', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923143720661480', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923145810860766', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923147924371108', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923148272582991', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923200945845811', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923203452967410', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923206846615055', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923208764384549', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923210709705858', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923212329978293', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923214736049495', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923216320225417', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923218796289256', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923220754456121', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923221454234830', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923223228240271', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923225607919472', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923244797125137', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923247732869788', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923249897006235', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923252023439621', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923254556619099', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923256895394232', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923258334097733', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923259740364833', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923302853899287', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923305223877870', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923307321128701', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923310842970809', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923312777205653', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923314401232183', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923316604776672', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923318883534845', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923320446952853', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923321003906220', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923323158143585', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923324900514540', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923326200165965', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923327039595048', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923329037070678', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923330201320844', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923332123196179', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923333530508809', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923334046295441', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923336151900794', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923337252887252', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923338285618340', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923340696324597', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923341422377044', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923343901448573', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923344664554088', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923345462258339', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923346735559867', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923348367916794', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923349172369572', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923350582640313', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923400614679990', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923403421124655', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923405375612234', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923408668296275', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923410023190093', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923411417110989', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923413052105390', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923414038324680', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923416741950725', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923418027987786', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923420957590700', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923421554157191', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923423374517793', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923424409000960', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923426639790028', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923427368102942', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923429685287809', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923430137751543', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923431038950374', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923432137103811', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923434696596027', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923435888296305', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923437652296024', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923438671237148', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923439076261539', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923441499168798', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923442638875701', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923443618062371', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:23', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923500553999681', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923502388479108', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923505754212866', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923508915495254', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923510100323324', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923512571335364', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923515855629559', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923518711236870', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923521662176724', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923523393259293', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923525046183410', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923527529987680', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923529541100941', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923531788471375', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923541263177449', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923543179255956', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923544716984719', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923545739352486', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923547154721023', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923548230755625', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923550885099012', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923552185832357', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923553336936099', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923555394249788', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923556629329272', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923600183228195', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923603754126201', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923606653162418', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923609826105259', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923611234538923', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923613869222945', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923615679652790', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923617272259667', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923619618794950', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923621317438644', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923623375500117', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923624966742708', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923626112561465', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923627308213087', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923629589969137', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923630206482158', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923632469735668', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923633185728477', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923635405270649', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923636523838184', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923702450939614', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923705199097944', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923707914196621', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923710504094192', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923712503953124', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923715478861138', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923717562479903', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923719092578804', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923721216304364', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923722472008657', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923724287957889', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923725519022798', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923727513119191', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923728490904235', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923730800890003', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923731599801039', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923733668133745', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923734167876118', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923736252801039', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923737800991254', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923738143088099', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923740681174636', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923741001184113', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923742569377389', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923744328238879', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923745786191953', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923746680405734', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923748440734682', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923749667505948', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923750454818880', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923800575541951', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923803193380475', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923806416628086', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923809750539677', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923811165040149', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923813364226655', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923815367944338', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923817920373493', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923819484016386', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923821460586094', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923822825124909', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923824434651701', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923825475048528', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923827693068128', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923828368020756', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923830490922354', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923831181509736', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923833683752043', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923834730236924', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923835813885730', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923836657231357', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923843478787371', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923845442503426', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923846404503318', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923847266387618', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923849726296073', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923850489287394', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923852924621351', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923853863605721', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923900007142277', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923903651447658', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923906349240303', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923909656932400', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923911714702897', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923913765480707', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923916347625985', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923918358953905', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923920994910105', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923921526169979', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923923624195853', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923925896544184', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923926796419514', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923928787122849', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923929614800470', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923931858606679', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923932115289014', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923933564521243', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923934914087681', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923935388962667', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144923936391837425', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924000222183037', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924002175563193', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924004046367194', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924005244457366', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924006051440432', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924008261017741', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924010748816856', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924011286596688', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924012484730695', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924014114764467', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924015572336428', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924016994625407', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924018308398605', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924019022714873', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924021736007243', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924023752188421', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924024825069320', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924026350176309', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924027645749953', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924029571420950', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924030491110748', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924032971071374', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924033818393434', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924034818391229', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924036516218551', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924037390208497', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924038161844672', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924039405091231', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924040843090752', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924041331015732', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924043601886480', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924044055321652', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924101258421336', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924102367902613', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924103529185934', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924104787108698', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924105832131435', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924106882742712', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924107699799694', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924108042256038', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924109366942456', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924110408340652', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924111410471393', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924112894463086', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924113269472298', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924115117009558', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924116817244565', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924117754025879', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924118698607293', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924119788713677', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924120209313515', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924121163337499', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924129697111367', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924130295394194', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924131802551057', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924132346351404', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924133008121857', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924134583998906', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924135284695984', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924136406165752', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924138276953958', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924141878667113', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924142534004201', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924145565049374', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924148674908911', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924150491625939', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924152227978536', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924201668813114', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924203052323601', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924205332889177', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924208214384664', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924210268061247', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924213044615234', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924215353802236', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924216543548055', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924218074430334', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924220315990687', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924222725807226', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924223124354564', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924225367335841', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924226676418519', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924228878185223', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924229510970124', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924231694032020', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924232032523787', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924234519235341', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924235859041139', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924237484564564', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924238834335004', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924240209944176', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924241212867877', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924243843022715', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924244506585811', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924246369943989', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924247870303242', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924300701740700', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924302156626419', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924303126066258', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924305151555561', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924306241588464', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924308512483699', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924310611268666', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924311959429669', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924312577454358', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924314934233931', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924316751936445', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924317649551312', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924319984653277', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924320485837540', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924322283474656', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924324825722336', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924325982769816', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924327872369724', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924328252025996', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924330536899193', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924331300119785', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924333492824043', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924335402956089', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924336228139796', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924337591710262', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924400159977576', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924402116682309', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924403322946124', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924405914615346', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924406019856397', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924408833213698', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924409988778781', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924411929645971', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924412496328022', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924414097493797', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924416355817498', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924417588483683', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924419629384493', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924425950051414', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924426172938348', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924428929657888', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924429769019384', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924431709375164', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924432107192765', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924434031186724', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924435725738407', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924437440870252', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924438429014185', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924440506827995', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924441622016506', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924442853559055', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924444311685908', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:24', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924501409756424', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924502367079845', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924504077175877', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924505408595493', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924507738236834', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924508120211850', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924510891387564', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924511594775941', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924513069052724', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924514921117913', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924516873620310', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924517693429933', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924519072428476', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924520387716152', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924522186395654', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924523159333331', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924524876652135', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924526746136079', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924527369125647', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924528422671364', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924530399777486', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924531925098177', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924533034279744', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924534143824644', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924536881252009', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924537374909479', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924538233982360', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924540410776333', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924541193330979', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924543562273644', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924544861828078', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924546250439864', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924547580058789', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924600818428941', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924602738107490', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924604422151983', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924606320950279', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924607629071611', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924609272565564', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924611819312598', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924612452701358', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924614409763157', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924615758135215', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924617206646188', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924618647756537', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924620957171469', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924621755834626', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924623980687294', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924624025540870', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924626271875537', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924627567611833', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924629570073949', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924630838264224', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924632911358805', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924633353460039', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924634109183246', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924636651544506', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924637070818143', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924638227278655', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924640200301433', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924641299733064', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924642068960157', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924644190533836', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924645336262121', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924646128136247', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924648451112288', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924649627020883', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924650859219691', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924652662339257', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924707318075433', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924709444761869', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924710706226281', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924712044295792', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924713770715898', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924715627854517', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924716402941964', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924718612567413', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924720900781449', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924721277817688', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924723972383474', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924724764810650', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924725477040941', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924727396797811', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924728959841979', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924729304976402', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924730722720392', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924731721776674', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924732538590478', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924733798056707', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924734931870925', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924735470248856', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924736644199427', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924737082552106', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924738796620315', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924739688917200', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924740429895491', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924741623535474', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924742477730165', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924743041583615', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924744378421556', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924745386609915', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924746326063566', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924800622635312', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924801231421651', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924803908796503', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924805436023548', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924807174476131', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924808663182956', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924810505228921', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924812507187553', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924813353787908', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924814726700317', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924816135782373', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924817890372927', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924818236498171', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924820853643553', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924821142455303', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924822122708186', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924823900259622', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924824510753364', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924825482033954', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924826408862797', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924827039510185', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924828007116307', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924829336517529', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924830172585953', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924830810126140', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924831713658477', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924832662049000', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924833561483329', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924834027586183', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924835140933537', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924836666689584', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924837062786187', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924838941204659', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924839739790966', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924840842892771', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924841398767237', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924842657581256', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924843744049822', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924844688309326', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924844851778034', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924845054958125', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924846072289137', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924847817639865', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924848848649383', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924849673890101', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924850673221315', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924900714604258', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924901288497554', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924902417274226', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924903069172043', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924905005343021', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924914427511520', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924915272505124', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924916248305263', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924918867063501', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924919755659551', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924920948071392', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924921803509527', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924922219402515', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924923776212706', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924924992275990', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924925865974820', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924926262679101', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924927242459143', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924928002513308', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924929524753859', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924930133416719', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924931845371611', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924932970527829', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924933175794384', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924934308035553', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924935982718711', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924936710150300', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924937601632171', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924938531953922', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924939098575733', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924939523873754', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924940066003129', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924941380109577', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924942079724368', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924943003560728', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924944962910062', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924945146844663', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924946223232375', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924946302160037', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924947821452535', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924948217372454', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924949106953742', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924950024466179', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924951951410742', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924952114100175', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924953249378091', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924954027189844', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924955198487661', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144924955601339067', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925000452461309', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925001956667994', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925002491994441', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925003406027787', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925004645111529', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925005448065800', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925006235518893', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925007425956752', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925008434609000', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925009961289828', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925010202645508', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925011208439938', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925011620219470', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925012491949554', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925013733205725', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925014867767982', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925015580733254', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925016851007914', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925017501810575', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925018281433028', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925019162238696', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925019193842438', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925020781877553', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925021199694759', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925022421607039', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925023882680576', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925024205089556', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925025002540448', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925026772506877', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925027620156091', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925028397996608', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925028456599919', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925029175545896', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925030865091744', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925031104269195', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925032467868896', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925033580112399', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925034096485636', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925035079098125', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925040889184203', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925041489824143', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925041648984707', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925042461127083', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925043547030998', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925100172894154', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925102162039700', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925104038222393', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925105526975878', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925107254560811', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925108672004115', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925110012416316', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925111757140939', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925113067330486', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925114089437512', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925115607684062', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925116027094794', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925118141702178', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925119371105527', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925120991219388', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925121056661093', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925121772503752', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925122723205726', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925123065391005', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925124408463162', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925125514500502', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925126499480622', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925127615446504', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925128195279746', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925129132071231', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925130124642086', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925131327819675', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925131913571825', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925132118116395', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925133794194176', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925134624100337', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925135317785011', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925136780942769', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925137044123257', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925138540005642', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925139393059751', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925140439960768', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925141066829092', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925141441140460', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925142451006692', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925143226777478', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925144972519967', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925145600985762', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925146364617380', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925146508765317', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925147846211535', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925148044355470', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925149994365750', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925150770259104', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925151121308467', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925151202550416', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925152905215688', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925200532994707', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925201318170733', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925202984132976', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925203591126046', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925204446587953', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925205365734751', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925207932441487', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925208891658210', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925209548446735', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925210776745671', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925211556157708', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925212601726621', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925213628811539', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925214377438069', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925215917599078', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925216551097536', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925217018848531', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925218112646445', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925219496380432', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925220697478075', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925221546387251', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925222352459917', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925223921112942', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925224916468609', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925225076028730', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925226904528884', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925230998067120', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925231553709554', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925232501930823', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925233396459091', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925234436091361', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925235259781852', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925236435265123', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925236711715643', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925237619701592', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925238181252029', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925239308888142', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925240694362759', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925241317168447', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925242918992794', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925243294739482', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925243957210511', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925244851625687', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925245127882417', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925246819464014', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925247363244931', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925248639114642', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925249128300077', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925249651088113', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925250384631681', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925251685747512', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925252481910629', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925253440584562', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925300758164581', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925301203340298', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925302408040657', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925303516590197', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925304884411888', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925305629834170', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925306899845193', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925306903645056', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925308547770134', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925309325191331', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925310112711935', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925310548047431', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925311863275399', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925312100589242', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925313756429105', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925314866835109', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925315699849668', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925316614497982', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925317208512573', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925318386701776', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925319324558445', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925320321309402', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925321724166512', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925322451831048', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925322637014130', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925323667048404', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925324940386272', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925325379355306', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925326331615757', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925327927840917', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925328716729234', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925328825627931', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925329264969909', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925330769370108', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925331406807247', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925332957958802', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925333518233585', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925333574903805', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925334385672283', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925335933805415', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925336457968087', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925337035652597', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925338669327213', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925339542390548', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925339950495746', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925340644601809', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925341515493714', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925342394738568', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925343531839532', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925343718214755', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925344175187514', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925345419370896', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925346393299802', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925347561141459', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925348261928596', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925348274752797', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925353414513821', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925401328116066', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925402768516304', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925403478709780', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925404705744492', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925405980924092', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925406509543637', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925407127633882', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925408939304946', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925409564253250', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925410440424423', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925411912067525', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925412052801354', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925413479657733', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925414135024689', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925415092374303', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925416963078944', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925417102718458', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925418333819530', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925419954748294', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925420327927107', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925421836248393', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925422868497804', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925423838135712', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925424297486886', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925424306535326', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925426350921794', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925427458315851', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925427469469960', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925428267789351', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925429523272961', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925430149544288', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925431352553654', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925432331419909', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925433024851565', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925433486740882', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925434018852549', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925435723690062', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925436586684222', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925437783716972', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925438803466031', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925439157790131', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925440619903942', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925440904505243', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925441220697182', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925443612626141', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925443747459948', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925444962636651', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925445172826210', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925446105332497', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925447344795269', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925447846625583', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925448573255319', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:25', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925501782000537', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925502452226643', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925503970428124', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925505255933028', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925506408000090', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925508378029641', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925509969187388', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925510171253923', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925511985694527', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925513334290331', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925514513403138', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925515667574814', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925516210032340', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925517611869826', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925518483484153', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925519318794576', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925520507007888', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925522293771149', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925523296674099', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925523858332746', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925524921243608', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925525384070950', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925526371619800', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925527517068023', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925528349329792', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925529085974114', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925529482661602', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925530797020876', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925531515800132', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925532989171064', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925533738630010', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925537480764751', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925538489862568', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925539363782302', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925539882010502', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925540302203746', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925541857873469', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925542161351203', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925543870207371', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925544021452853', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925545072123382', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925545429376492', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925546064499639', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925547374168526', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925548947302837', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925549469869988', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925600661112851', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925601494658631', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925604438683699', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925605045585353', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925607513424130', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925609442663211', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925610019838611', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925611176404237', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925613507993975', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925614504649206', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925615445958728', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925616111268138', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925618379875074', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925619659591884', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925620042627162', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925621908229457', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925622027760663', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925623178061843', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925624232058575', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925625444329708', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925625926575058', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925626792175636', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925627194840106', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925628206126176', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925629964825114', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925630951075114', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925631326143467', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925631702141626', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925632097466025', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925633684474873', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925634744930264', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925635128194349', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925636901144222', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925637001395601', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925638829858282', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925639429708674', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925640147341886', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925640248924450', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925641049637348', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925642597841728', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925643545431780', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925644629604036', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925645375997162', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925646036144815', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925646337231395', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925647463086938', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925648756963536', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925649173846326', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925650036751567', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925651235082710', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925651754212440', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925652377559735', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925653323128238', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925654551700047', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925655793685889', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925700333215973', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925701753782142', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925703394432371', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925705139215595', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925707460633136', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925708172069522', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925710968675488', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925711747616926', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925713274815969', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925714872640167', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925716758441942', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925717936210795', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925718265844842', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925724131336281', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925725238306351', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925726781379909', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925728081454152', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925729122783992', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925730247298507', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925731014539579', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925732729016887', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925733798875381', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925734336203469', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925734643331353', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925735608524605', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925736078499347', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925737181029313', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925738057738518', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925739736843287', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925740710586174', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925740924400325', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925741615903271', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925742664433707', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925743425920378', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925744002588565', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925744780046326', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925745930430247', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925746885027058', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925747711202855', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925748511335157', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925749397046796', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925750303431299', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925750472556784', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925751291387639', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925752894737591', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925753112465063', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925800365484088', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925801132065806', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925803113234452', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925804253860710', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925805684850723', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925806293713412', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925808851756631', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925809167267309', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925810289000718', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925812513426251', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925813414283920', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925814767005256', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925815516321034', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925816450359096', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925817327146226', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925818601523603', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925819699633423', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925820059381424', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925821129985661', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925822482860119', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925823554968141', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925824173157543', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925825948864757', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925826949596191', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925827270218783', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925827587505285', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925828879092791', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925829969847566', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925830329773300', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925831409198228', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925831862533573', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925832834763036', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925833650965832', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925834871857581', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925835387371242', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925836862772441', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925836978448069', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925837232645901', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925838825357400', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925839498099613', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925840348891673', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925841636104604', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925842765035257', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925842932163453', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925843738484340', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925844964672870', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925845544513061', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925846807937458', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925847060817502', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925847647286698', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925851230119563', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925852928875143', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925901069806776', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925902183625276', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925902545451701', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925903139011383', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925904822739151', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925905941714661', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925906502253563', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925907199445542', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925908313066454', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925909948800826', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925910404381346', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925911238947578', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925912356934608', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925913595521559', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925914535815378', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925914636296545', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925915979960770', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925916753068012', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925917877531308', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925918262243114', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925919452610019', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925920009667409', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925921182424256', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925921839261958', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925922090456140', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925923900454009', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925924965649427', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925925827508986', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925926499784319', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925926722810980', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925927972195971', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925928255983112', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925929566772211', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925929621485025', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925930610650650', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925931265734848', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925932802637281', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925933551956916', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925933897501837', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925934412696485', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925935493176098', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925936460782246', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925936649272799', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925937175585797', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925938176856445', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925939013822369', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925939409118092', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925940732007417', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925941862232732', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925942902532027', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925943013002685', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925943480558494', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925944779281058', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925945750002818', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925946103484253', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925946737170051', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925947850280034', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925948842644122', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925949097650061', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144925949291304704', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926001395672636', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926003446105824', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926004927827361', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926006963035264', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926007207461945', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926007975584166', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926008841668655', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926009154266299', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926010716854491', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926011553115646', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926012809424863', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926013297762869', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926014376552182', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926015390666529', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926016976535891', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926017392078206', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926018724131680', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926019384470293', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926020266061844', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926021560338050', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926022239874555', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926022432423039', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926027712673443', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926027862945195', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926028965502734', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926029875601712', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926030466073369', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926031960207496', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926032311770506', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926032482921275', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926033019343999', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926034866366000', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926035802271701', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926036517496544', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926036796789023', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926037894709410', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926038781498232', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926039913159907', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926040678480309', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926041066986569', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926041165000724', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926042429066549', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926043805047844', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926044527244779', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926045163538699', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926046886288673', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926047418689499', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926047881490724', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926048650626049', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926049305754712', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926050269208360', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926051928215513', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926052843046938', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926104360392039', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926107376223083', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926108661790302', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926109100119093', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926111770641670', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926112869278273', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926113939976734', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926114902873709', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926116065037869', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926117859403472', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926118873978941', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926119752712483', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926120766946730', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926121031448225', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926122521151290', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926123410822207', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926123669065929', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926124357779183', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926125214325603', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926126270665918', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926127637565275', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926128185997904', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926128322258164', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926129391019585', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926130262721064', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926131016422839', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926131762046087', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926132219737074', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926133981746435', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926134180221990', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926135731537499', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926136183324581', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926136890714874', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926137853966585', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926138390726810', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926139295762876', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926139657436379', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926140545118972', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926141454322986', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926142694510107', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926143418190366', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926143990699549', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926200409748647', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926203731425059', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926205055496690', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926207101296402', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926209481086795', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926210395059785', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926212980174747', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926213037866501', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926214957906047', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926215292972135', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926222728721487', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926223672234110', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926225947271279', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926226714153152', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926228551035945', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926229622856454', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926230829730910', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926231519272550', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926233934670270', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926234989938179', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926235110968173', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926236192295685', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926237084623990', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926239734996849', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926240558553945', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926241307454575', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926242719551769', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926243370536972', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926244145611305', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926246930652963', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926247141308492', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926248104588120', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926249977453107', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926251758239862', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926252890305614', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926253609134535', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926254118382079', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926255244239377', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926256325143871', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926258158212454', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926259259485046', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926301191508247', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926302924679952', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926304616601018', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926306362915012', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926307199406685', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926308160866626', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926310901366284', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926311357504646', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926312077285302', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926314635531731', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926315552694358', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926316034524954', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926318855108376', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926319691829446', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926320923718602', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926322668170914', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926323631235666', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926324369796820', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926325163549712', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926326951972586', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926328555382953', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926329902553164', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926330107296055', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926332964033732', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926333738646340', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926334676734356', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926335734554790', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926336858527703', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926337948754294', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926339497858794', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926340803386120', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926341131714850', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926342763193740', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926344587316727', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926345365678461', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926346799622767', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926347744174030', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926349992406431', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926350112819129', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926351084615756', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926352505948209', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926354757891978', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926355074171996', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926356850637121', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926357872733566', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926358269268754', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926359146137109', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926360423113837', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926362107589969', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926363330890298', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926364194778382', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926365178684182', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926370393583952', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926372845095266', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926373446775158', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926374274401708', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926375506623682', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926377357864206', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926377413306458', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926378088777979', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926379082452529', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926380862429038', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926381232512781', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926382190068224', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926383173533276', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926400997170533', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926402981691214', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926405283313448', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926407128128810', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926408269152960', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926410266697078', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926411926229395', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926413897200655', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926414743313317', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926416700812753', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926417249337357', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926418826874857', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926420357869690', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926421011115095', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926422300432321', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926424409332676', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926425986519103', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926426299012567', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926427048778802', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926428339679902', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926429402908841', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926430594571643', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926431496842682', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926432448890298', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926433381046152', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926434408571863', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926435233819820', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926435314508122', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926436517092340', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926437206058489', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926438141007497', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926439368833183', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926440911414389', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926441080929406', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926442417521842', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926443479448923', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926444822752565', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926445808606910', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926446158689059', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926447807183763', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926448949006922', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926450076645060', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926451490123576', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926452207274711', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:26', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926502299014845', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926504823460623', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926505855129818', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926506006591913', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926507977257421', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926509320183731', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926510069185088', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926511212899779', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926512374313659', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926512630367293', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926513976658590', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926514307983282', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926515132781557', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926516869681151', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926517208002409', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926518425590169', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926519573547508', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926520676628340', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926521202338377', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926522160004547', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926522561690484', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926523677598246', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119144926524597572257', 'u_10001', '20220119135610546896924897', 1, 0, 1, '2022-01-19 14:49:27', '2022-01-19 15:06:17', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220119150207933344463939', 'u_10001', '20220119150207930196550433', 1, 0, 1, '2022-01-19 15:02:08', '2022-01-19 15:04:38', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220207160344951170442269', 'u_10001', '20220207160344942613795446', 0, 0, 0, '2022-02-07 16:03:45', NULL, NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220207160536101170468059', '20220113152745730601337038', '20220207160536096088160970', 1, 1, 0, '2022-02-07 16:05:36', '2022-02-07 17:11:56', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220207160536101242513186', '20220110163739394089049276', '20220207160536096088160970', 1, 1, 0, '2022-02-07 16:05:36', '2022-02-07 17:14:56', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220207160536101454593131', 'u_10001', '20220207160536096088160970', 1, 0, 1, '2022-02-07 16:05:36', '2022-02-07 16:06:41', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220207160536101706160909', 'u_10001', '20220207160536096088160970', 1, 0, 1, '2022-02-07 16:05:36', '2022-02-07 16:06:41', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220207160536101797706387', 'u_10001', '20220207160536096088160970', 1, 0, 1, '2022-02-07 16:05:36', '2022-02-07 16:06:41', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220207160536101898642969', 'u_10001', '20220207160536096088160970', 1, 0, 1, '2022-02-07 16:05:36', '2022-02-07 16:06:41', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220207160536101974300280', 'u_10001', '20220207160536096088160970', 1, 0, 1, '2022-02-07 16:05:36', '2022-02-07 16:06:41', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220207160536101974581684', 'u_10001', '20220207160536096088160970', 1, 0, 1, '2022-02-07 16:05:36', '2022-02-07 16:06:41', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220207160536102583518814', 'u_10001', '20220207160536096088160970', 1, 0, 1, '2022-02-07 16:05:36', '2022-02-07 16:06:41', NULL, NULL, NULL);
INSERT INTO `nft_user_assets` VALUES ('20220207160536102615235514', 'u_10001', '20220207160536096088160970', 1, 0, 1, '2022-02-07 16:05:36', '2022-02-07 16:06:41', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for nft_user_chainplat
-- ----------------------------
DROP TABLE IF EXISTS `nft_user_chainplat`;
CREATE TABLE `nft_user_chainplat`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一标识',
  `userId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户标识',
  `chainPlatName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '链平台英文名称(标识)',
  `privatePath` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '私钥地址',
  `passwd` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码,使用SecUtils.encoderByRSAPrivateKey()加密后的数据',
  `publicPath` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公钥地址',
  `addressPath` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ak地址文件路径',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ak地址',
  `EVMAddress` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'EVMaddress(由address转换得来的Account.xchainAKToEVMAddress())',
  `mnemonic` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '助记词',
  `prevTime` datetime(0) NULL DEFAULT NULL COMMENT '上次链接时间',
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  `bak1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bak2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bak3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户所拥有的链平台账号' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nft_user_chainplat
-- ----------------------------
INSERT INTO `nft_user_chainplat` VALUES ('20220121131242646040693757', 'u_10001', 'xuperChain', 'classpath:keys/xuperchain/root_keys/private.key', '', NULL, 'classpath:keys/xuperchain/root_keys/address', '', '', NULL, NULL, '2022-01-21 13:12:43', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for nft_user_real
-- ----------------------------
DROP TABLE IF EXISTS `nft_user_real`;
CREATE TABLE `nft_user_real`  (
  `userId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户标识',
  `identityNum` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `moble` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `address` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '现居住址',
  `frontPath` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证正面图片',
  `backPath` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证反面图片',
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  `bak1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bak2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bak3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户实名认证信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nft_user_real
-- ----------------------------

-- ----------------------------
-- Table structure for nft_works
-- ----------------------------
DROP TABLE IF EXISTS `nft_works`;
CREATE TABLE `nft_works`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一标识',
  `authorId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者标识=用户标识',
  `collectionId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '合集标识',
  `dataPath` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作品数据地址(图片Base64,上链)',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作品名称',
  `type` int(0) NOT NULL COMMENT '作品类型',
  `link` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自定义链接',
  `details` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作品简介(富文本)',
  `price` decimal(20, 2) NULL DEFAULT NULL COMMENT '作品价格(初始价格)',
  `num` int(0) NOT NULL COMMENT '作者发行数量',
  `buyers` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作品购买者(用户标识,标识之间使用逗号隔开)',
  `inTime` datetime(0) NULL DEFAULT NULL COMMENT '上架时间',
  `outTime` datetime(0) NULL DEFAULT NULL COMMENT '下架时间',
  `waitingTime` datetime(0) NULL DEFAULT NULL COMMENT '发布等待期,日期之后才可购买',
  `chainPlatCert` json NULL COMMENT '上架后,在不同链平台上链,认证后的数据-json',
  `state` int(0) NULL DEFAULT NULL COMMENT '作品状态(0:未上架,1:(已上架)售卖中,2:已售停,3:已下架,4:已删除)',
  `createTime` datetime(0) NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  `bak1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bak2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bak3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户作品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nft_works
-- ----------------------------
INSERT INTO `nft_works` VALUES ('20220119135610546896924897', 'u_10001', '20220119134138108343727195', '/attachmentFile/2022-01-19T135505.838acbad61d-043f-4419-834d-7f7676232eb0.png', '福虎', 1, NULL, '%3Cp%3E%3C%2Fp%3E%3Cdiv%20class%3D%22media-wrap%20image-wrap%22%3E%3Cimg%20class%3D%22media-wrap%20image-wrap%22%20src%3D%22https%3A%2F%2Fmakerone.shengjian.net%2Fnft%2FattachmentFile%2F2022-01-19T135606.583a7a61570-cf4a-4a39-b226-35f0abd16cca.png%22%2F%3E%3C%2Fdiv%3E%3Cp%3E%3C%2Fp%3E', 0.01, 2022, ',20220119164724592016531570,20220110163739394089049276,20220118090607611954267892,20220113171851406370700861,20220120145847550390478452,20220120145123675920472814,20220120144323010894692552,20220201173425264129665953,20220202111906491873236797,20220113173245029522827454,20220119160509299117894116,20220113152745730601337038,20220120150638198426674299', '2022-01-19 15:06:17', '2022-12-31 00:00:00', '2022-01-19 00:00:00', '{\"xuperChain\": \"{\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"12ddcf920be2a5488db15b1ca190fd665222a55eda97b9506e077efe3da24972\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\",\\\"hash\\\":\\\"9d04983c8cb75e02fb431ff17b249638\\\"}\"}', 1, '2022-01-19 13:56:11', '2022-02-02 11:21:00', NULL, NULL, NULL);
INSERT INTO `nft_works` VALUES ('20220119150207930196550433', 'u_10001', '20220119134138108343727195', '/attachmentFile/2022-01-19T150157.699beb2b406-9275-4111-842f-fed8252f0c11.png', 'test', 1, NULL, '%3Cp%3E%3C%2Fp%3E', 0.01, 1, NULL, '2022-01-19 15:04:38', '2022-12-31 00:00:00', '2022-01-19 00:00:00', '{\"xuperChain\": \"{\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"ac5ab9a564beb10330fbc8a404a6e337aa147137eefa9d7803de811fec2ebaa6\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\",\\\"hash\\\":\\\"453b946585bb081f8db27d0153a77f88\\\"}\"}', 4, '2022-01-19 15:02:08', '2022-01-19 15:04:39', NULL, NULL, NULL);
INSERT INTO `nft_works` VALUES ('20220207160536096088160970', 'u_10001', '20220207160344942613795446', '/attachmentFile/2022-02-07T160517.603c6738e5d-049f-403e-b1c1-ab3a7d465e03.png', '测试数据1', 1, NULL, '%3Cp%3E%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE2222%3C%2Fp%3E', 0.01, 10, NULL, '2022-02-07 16:06:41', '2022-03-13 00:00:00', '2022-02-07 00:00:00', '{\"xuperChain\": \"{\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"2b01bddd64b3bc100b346876d51fa104b111e9b7930f5d2eb9570fad0f875913\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"SJ_Test_ERC_11\\\",\\\"hash\\\":\\\"46a642b7f2a481bbbcf2cc055bcaa779\\\"}\"}', 1, '2022-02-07 16:05:36', '2022-02-07 16:06:43', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for nft_works_his
-- ----------------------------
DROP TABLE IF EXISTS `nft_works_his`;
CREATE TABLE `nft_works_his`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一标识',
  `worksId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作品标识',
  `type` int(0) NULL DEFAULT NULL COMMENT '类型(0:售卖,1:转移)',
  `chainPlatCert` json NULL COMMENT '在不同链平台上链,认证后的数据json(使用区块链浏览器查询)',
  `num` int(0) NOT NULL COMMENT '作品数量',
  `price` decimal(20, 2) NOT NULL COMMENT '价格',
  `fromUser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '卖方',
  `toUser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买方',
  `tradeTime` datetime(0) NOT NULL COMMENT '交易时间',
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  `bak1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bak2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bak3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '作品交易历史' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nft_works_his
-- ----------------------------
INSERT INTO `nft_works_his` VALUES ('20220119153050527578943837', '20220119135610546896924897', NULL, '{\"xuperChain\": \"{\\\"gasUsed\\\":\\\"502\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"1012004913f39233c9b0d0c3dad98c69f9462f9946ff7f3c94efa829346ce4fe\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\"}\"}', 1, 0.01, 'u_10001', '20220110163739394089049276', '2022-01-19 15:30:47', '2022-01-19 15:30:51', NULL, NULL, NULL, NULL);
INSERT INTO `nft_works_his` VALUES ('20220119153635974376378237', '20220119135610546896924897', NULL, '{\"xuperChain\": \"{\\\"gasUsed\\\":\\\"502\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"123c64d77e771ed4e34bedba8225de52bd94d05fb140e5e8718c97b8d98c77be\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\"}\"}', 1, 0.01, 'u_10001', '20220113171851406370700861', '2022-01-19 15:36:32', '2022-01-19 15:36:36', NULL, NULL, NULL, NULL);
INSERT INTO `nft_works_his` VALUES ('20220119160459956660421279', '20220119135610546896924897', NULL, '{\"xuperChain\": \"{\\\"gasUsed\\\":\\\"502\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"30799e0f4d8c21bda743fad6678549d78b766e715254623b1d8e9f78405a6a36\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\"}\"}', 1, 0.01, 'u_10001', '20220113173245029522827454', '2022-01-19 16:04:56', '2022-01-19 16:05:00', NULL, NULL, NULL, NULL);
INSERT INTO `nft_works_his` VALUES ('20220119160801784912343295', '20220119135610546896924897', NULL, '{\"xuperChain\": \"{\\\"gasUsed\\\":\\\"502\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"3773857dd4f72b7e479b8c9bc096d436c757c21571accea51db332cb82eaef7f\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\"}\"}', 1, 0.01, 'u_10001', '20220118090607611954267892', '2022-01-19 16:07:58', '2022-01-19 16:08:02', NULL, NULL, NULL, NULL);
INSERT INTO `nft_works_his` VALUES ('20220119161151175667697139', '20220119135610546896924897', NULL, '{\"xuperChain\": \"{\\\"gasUsed\\\":\\\"502\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"f51d7f81876a9878c95a046716dd8bc1084db4844afb9019920734fa809fb98a\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\"}\"}', 1, 0.01, 'u_10001', '20220119160509299117894116', '2022-01-19 16:11:47', '2022-01-19 16:11:51', NULL, NULL, NULL, NULL);
INSERT INTO `nft_works_his` VALUES ('20220119173744227843725304', '20220119135610546896924897', NULL, '{\"xuperChain\": \"{\\\"gasUsed\\\":\\\"502\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"54628d3c947970556de6d87b99612cd5d9539970c79416b0863f971d51263c97\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\"}\"}', 1, 0.01, 'u_10001', '20220119164724592016531570', '2022-01-19 17:37:40', '2022-01-19 17:37:44', NULL, NULL, NULL, NULL);
INSERT INTO `nft_works_his` VALUES ('20220120144521732364572944', '20220119135610546896924897', NULL, '{\"xuperChain\": \"{\\\"gasUsed\\\":\\\"502\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"fa3ad13300dcdd14bc1e2705f9129cfe89d554ece86c3d4d38b41dc7e8f1b573\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\"}\"}', 1, 0.01, 'u_10001', '20220120144323010894692552', '2022-01-20 14:45:15', '2022-01-20 14:45:22', NULL, NULL, NULL, NULL);
INSERT INTO `nft_works_his` VALUES ('20220120145557029123653841', '20220119135610546896924897', NULL, '{\"xuperChain\": \"{\\\"gasUsed\\\":\\\"502\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"a297217e360edeb46d68c2458fa6c4a014d3400005c2b2cafb7ca0acf823aafe\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\"}\"}', 1, 0.01, 'u_10001', '20220120145123675920472814', '2022-01-20 14:55:53', '2022-01-20 14:55:57', NULL, NULL, NULL, NULL);
INSERT INTO `nft_works_his` VALUES ('20220120150007375768801771', '20220119135610546896924897', NULL, '{\"xuperChain\": \"{\\\"gasUsed\\\":\\\"502\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"36941084da083b74e0056e136c5e8e3faa28fb348bfd687a0187d459a7517250\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\"}\"}', 1, 0.01, 'u_10001', '20220120145847550390478452', '2022-01-20 15:00:03', '2022-01-20 15:00:07', NULL, NULL, NULL, NULL);
INSERT INTO `nft_works_his` VALUES ('20220120150858802389793913', '20220119135610546896924897', NULL, '{\"xuperChain\": \"{\\\"gasUsed\\\":\\\"502\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"c196a890f75a44ef83530d9df88ce5c0f88b836d2fe35d30463fa30939b6d701\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\"}\"}', 1, 0.01, 'u_10001', '20220120150638198426674299', '2022-01-20 15:08:54', '2022-01-20 15:08:59', NULL, NULL, NULL, NULL);
INSERT INTO `nft_works_his` VALUES ('20220121164038450064712544', '20220119135610546896924897', NULL, '{\"xuperChain\": \"{\\\"gasUsed\\\":\\\"502\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"2cbd14c549bc58fd2f10839cc7b86824f91280cbbbae48c4c435af1fd4d43ca5\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\"}\"}', 1, 0.01, 'u_10001', '20220113152745730601337038', '2022-01-21 16:40:34', '2022-01-21 16:40:38', NULL, NULL, NULL, NULL);
INSERT INTO `nft_works_his` VALUES ('20220201175148453962393455', '20220119135610546896924897', NULL, '{\"xuperChain\": \"{\\\"gasUsed\\\":\\\"502\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"701f01381f6f91e9de028448a7f5791b482767a185c014593c9403e11600b07e\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\"}\"}', 1, 0.01, 'u_10001', '20220201173425264129665953', '2022-02-01 17:51:45', '2022-02-01 17:51:48', NULL, NULL, NULL, NULL);
INSERT INTO `nft_works_his` VALUES ('20220202112059750117287624', '20220119135610546896924897', NULL, '{\"xuperChain\": \"{\\\"gasUsed\\\":\\\"502\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"e080aadd6540643506cc362340e9c22341938c2304bf595be08711dca1b6f595\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"TestErc\\\"}\"}', 1, 0.01, 'u_10001', '20220202111906491873236797', '2022-02-02 11:20:52', '2022-02-02 11:21:00', NULL, NULL, NULL, NULL);
INSERT INTO `nft_works_his` VALUES ('20220207162819937864254900', '20220207160536096088160970', NULL, '{\"xuperChain\": \"{\\\"gasUsed\\\":\\\"33\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"835bef3ad80a5eb57b35faa6795bbf64470580654b2fcc836a0754b23375b5ef\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"SJ_Test_ERC_11\\\"}\"}', 1, 0.01, 'u_10001', '20220110163739394089049276', '2022-02-07 16:28:15', '2022-02-07 16:28:20', NULL, NULL, NULL, NULL);
INSERT INTO `nft_works_his` VALUES ('20220207171155811390721519', '20220207160536096088160970', 1, '{}', 1, 0.00, '20220110163739394089049276', '20220113152745730601337038', '2022-02-07 17:11:56', '2022-02-07 17:11:56', NULL, NULL, NULL, NULL);
INSERT INTO `nft_works_his` VALUES ('20220207171457724689390192', '20220207160536096088160970', NULL, '{\"xuperChain\": \"{\\\"gasUsed\\\":\\\"33\\\",\\\"browserAddress\\\":\\\"https://xuper.baidu.com/n/console#/xuperos/explorer\\\",\\\"txId\\\":\\\"ed4baac286663a734c9f8e4915e7d8c8015a8e8de23ce9929e970daf5a220f77\\\",\\\"bodyStr\\\":\\\"[]\\\",\\\"contractName\\\":\\\"SJ_Test_ERC_11\\\"}\"}', 1, 0.01, 'u_10001', '20220110163739394089049276', '2022-02-07 17:14:53', '2022-02-07 17:14:58', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for nft_works_price
-- ----------------------------
DROP TABLE IF EXISTS `nft_works_price`;
CREATE TABLE `nft_works_price`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一标识',
  `worksId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作品标识',
  `dateTime` datetime(0) NOT NULL COMMENT '时间',
  `price` decimal(20, 2) NOT NULL COMMENT '价格',
  `ratio` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浮动比例%',
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  `bak1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bak2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bak3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '作品历史价格' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nft_works_price
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
  `orgId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门id',
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
-- Records of t_attachment
-- ----------------------------
INSERT INTO `t_attachment` VALUES ('20211231152538456989113521', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2021-12-31T152538.450758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2021-12-31 15:25:38', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20211231152628491046420216', '202105171503402497159', NULL, NULL, 0, 'al-1.jpg', '/attachmentFile/2021-12-31T152628.488al-1.jpg', '.jpg', 118, NULL, 0, 1, 'u_10001', '2021-12-31 15:26:28', NULL, NULL, '73c19454e90201f9d85a7d67a5aff547');
INSERT INTO `t_attachment` VALUES ('20211231152754592502812136', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2021-12-31T152754.589758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2021-12-31 15:27:55', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20211231152756593886756125', '202105171503402497159', NULL, NULL, 0, '2424.png', '/attachmentFile/2021-12-31T152756.5902424.png', '.png', 1, NULL, 0, 1, 'u_10001', '2021-12-31 15:27:57', NULL, NULL, 'b3d874ce11508d78c64860c78efd85f8');
INSERT INTO `t_attachment` VALUES ('20211231152758924604853785', '202105171503402497159', NULL, NULL, 0, 'al-1.jpg', '/attachmentFile/2021-12-31T152758.921al-1.jpg', '.jpg', 118, NULL, 0, 1, 'u_10001', '2021-12-31 15:27:59', NULL, NULL, '73c19454e90201f9d85a7d67a5aff547');
INSERT INTO `t_attachment` VALUES ('20211231153059389241613750', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2021-12-31T153059.387758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2021-12-31 15:30:59', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20211231153102072331894137', '202105171503402497159', NULL, NULL, 0, 'al-1.jpg', '/attachmentFile/2021-12-31T153102.053al-1.jpg', '.jpg', 118, NULL, 0, 1, 'u_10001', '2021-12-31 15:31:02', NULL, NULL, '73c19454e90201f9d85a7d67a5aff547');
INSERT INTO `t_attachment` VALUES ('20211231153104200237005322', '202105171503402497159', NULL, NULL, 0, 'banner.jpg', '/attachmentFile/2021-12-31T153104.197banner.jpg', '.jpg', 312, NULL, 0, 1, 'u_10001', '2021-12-31 15:31:04', NULL, NULL, 'e6f5ee0fcb1a40cb8260d4973be538ec');
INSERT INTO `t_attachment` VALUES ('20211231163258785868925310', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2021-12-31T163258.778758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2021-12-31 16:32:59', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20211231163401295599828244', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2021-12-31T163401.292758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2021-12-31 16:34:01', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20211231163434813031760972', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2021-12-31T163434.794758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2021-12-31 16:34:35', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20211231163511612548231005', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2021-12-31T163511.610758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2021-12-31 16:35:12', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20211231163518330826099458', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2021-12-31T163518.326758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2021-12-31 16:35:18', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20211231163709520033663410', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2021-12-31T163709.517758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2021-12-31 16:37:10', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20211231163744242010642319', '202105171503402497159', NULL, NULL, 0, 'al-1.jpg', '/attachmentFile/2021-12-31T163744.238al-1.jpg', '.jpg', 118, NULL, 0, 1, 'u_10001', '2021-12-31 16:37:44', NULL, NULL, '73c19454e90201f9d85a7d67a5aff547');
INSERT INTO `t_attachment` VALUES ('20211231163801349954380413', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2021-12-31T163801.346758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2021-12-31 16:38:01', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20211231163831087806919660', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2021-12-31T163831.084758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2021-12-31 16:38:31', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20211231163915445796242487', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2021-12-31T163915.442758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2021-12-31 16:39:15', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20211231164224998709310372', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2021-12-31T164224.995758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2021-12-31 16:42:25', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20211231164250502415411945', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2021-12-31T164250.499758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2021-12-31 16:42:51', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20211231164257593377278784', '202105171503402497159', NULL, NULL, 0, 'al-1.jpg', '/attachmentFile/2021-12-31T164257.590al-1.jpg', '.jpg', 118, NULL, 0, 1, 'u_10001', '2021-12-31 16:42:58', NULL, NULL, '73c19454e90201f9d85a7d67a5aff547');
INSERT INTO `t_attachment` VALUES ('20211231164307317505864991', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2021-12-31T164307.314758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2021-12-31 16:43:07', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20211231164430003162493860', '202105171503402497159', NULL, NULL, 0, 'al-1.jpg', '/attachmentFile/2021-12-31T164429.984al-1.jpg', '.jpg', 118, NULL, 0, 1, 'u_10001', '2021-12-31 16:44:30', NULL, NULL, '73c19454e90201f9d85a7d67a5aff547');
INSERT INTO `t_attachment` VALUES ('20211231164552471170443285', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2021-12-31T164552.468758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2021-12-31 16:45:52', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20211231164738708448294876', '202105171503402497159', NULL, NULL, 0, 'al-1.jpg', '/attachmentFile/2021-12-31T164738.705al-1.jpg', '.jpg', 118, NULL, 0, 1, 'u_10001', '2021-12-31 16:47:39', NULL, NULL, '73c19454e90201f9d85a7d67a5aff547');
INSERT INTO `t_attachment` VALUES ('20211231165007694126854967', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2021-12-31T165007.691758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2021-12-31 16:50:08', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20211231165010226536333094', '202105171503402497159', NULL, NULL, 0, 'al-1.jpg', '/attachmentFile/2021-12-31T165010.224al-1.jpg', '.jpg', 118, NULL, 0, 1, 'u_10001', '2021-12-31 16:50:10', NULL, NULL, '73c19454e90201f9d85a7d67a5aff547');
INSERT INTO `t_attachment` VALUES ('20211231165013444858159247', '202105171503402497159', NULL, NULL, 0, 'banner.png', '/attachmentFile/2021-12-31T165013.442banner.png', '.png', 471, NULL, 0, 1, 'u_10001', '2021-12-31 16:50:13', NULL, NULL, '626188fdd64fa86fcc7a91f8d89ca515');
INSERT INTO `t_attachment` VALUES ('20211231165109762551314613', '202105171503402497159', NULL, NULL, 0, 'al-1.jpg', '/attachmentFile/2021-12-31T165109.758al-1.jpg', '.jpg', 118, NULL, 0, 1, 'u_10001', '2021-12-31 16:51:10', NULL, NULL, '73c19454e90201f9d85a7d67a5aff547');
INSERT INTO `t_attachment` VALUES ('20211231170313036991421441', '202105171503402497159', NULL, NULL, 0, 'logo(4).png', '/attachmentFile/2021-12-31T170313.034logo(4).png', '.png', 8, NULL, 0, 1, 'u_10001', '2021-12-31 17:03:13', NULL, NULL, 'ddc401d7df15c33f408e9312de9ff286');
INSERT INTO `t_attachment` VALUES ('20211231170314959720079487', '202105171503402497159', NULL, NULL, 0, 'logo(4).png', '/attachmentFile/2021-12-31T170314.956logo(4).png', '.png', 8, NULL, 0, 1, 'u_10001', '2021-12-31 17:03:15', NULL, NULL, 'ddc401d7df15c33f408e9312de9ff286');
INSERT INTO `t_attachment` VALUES ('20211231170316945354432886', '202105171503402497159', NULL, NULL, 0, 'logo(4).png', '/attachmentFile/2021-12-31T170316.942logo(4).png', '.png', 8, NULL, 0, 1, 'u_10001', '2021-12-31 17:03:17', NULL, NULL, 'ddc401d7df15c33f408e9312de9ff286');
INSERT INTO `t_attachment` VALUES ('20211231170449439057304657', '202105171503402497159', NULL, NULL, 0, 'al-1.jpg', '/attachmentFile/2021-12-31T170449.427al-1.jpg', '.jpg', 118, NULL, 0, 1, 'u_10001', '2021-12-31 17:04:49', NULL, NULL, '73c19454e90201f9d85a7d67a5aff547');
INSERT INTO `t_attachment` VALUES ('20211231170451014806854735', '202105171503402497159', NULL, NULL, 0, 'logo(4).png', '/attachmentFile/2021-12-31T170450.922logo(4).png', '.png', 8, NULL, 0, 1, 'u_10001', '2021-12-31 17:04:51', NULL, NULL, 'ddc401d7df15c33f408e9312de9ff286');
INSERT INTO `t_attachment` VALUES ('20211231170516788025393275', '202105171503402497159', NULL, NULL, 0, 'default.jpg', '/attachmentFile/2021-12-31T170516.771default.jpg', '.jpg', 14, NULL, 0, 1, 'u_10001', '2021-12-31 17:05:17', NULL, NULL, 'bc9d102fe1ef34f7b9a2d9b8d86f745a');
INSERT INTO `t_attachment` VALUES ('20220104114238294052122943', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2022-01-04T114238.230758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2022-01-04 11:42:38', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20220104114528854352881124', '202105171503402497159', NULL, NULL, 0, 'al-1.jpg', '/attachmentFile/2022-01-04T114528.844al-1.jpg', '.jpg', 118, NULL, 0, 1, 'u_10001', '2022-01-04 11:45:29', NULL, NULL, '73c19454e90201f9d85a7d67a5aff547');
INSERT INTO `t_attachment` VALUES ('20220104161204620935679823', '', NULL, NULL, 0, '1-bg.jpg', '/attachmentFile/2022-01-04T161204.6151-bg.jpg', '.jpg', 956, NULL, 0, 1, 'u_10001', '2022-01-04 16:12:05', NULL, NULL, '49c0d75d3bb7d4782be6e6e6b652603b');
INSERT INTO `t_attachment` VALUES ('20220104161257191205239987', '', NULL, NULL, 0, '1-bg.jpg', '/attachmentFile/2022-01-04T161257.1891-bg.jpg', '.jpg', 956, NULL, 0, 1, 'u_10001', '2022-01-04 16:12:57', NULL, NULL, '49c0d75d3bb7d4782be6e6e6b652603b');
INSERT INTO `t_attachment` VALUES ('20220104161317230856599969', '', NULL, NULL, 0, '1-bg.jpg', '/attachmentFile/2022-01-04T161317.2291-bg.jpg', '.jpg', 956, NULL, 0, 1, 'u_10001', '2022-01-04 16:13:17', NULL, NULL, '49c0d75d3bb7d4782be6e6e6b652603b');
INSERT INTO `t_attachment` VALUES ('20220104161332709258666102', '', NULL, NULL, 0, '1-bg.jpg', '/attachmentFile/2022-01-04T161332.7081-bg.jpg', '.jpg', 956, NULL, 0, 1, 'u_10001', '2022-01-04 16:13:33', NULL, NULL, '49c0d75d3bb7d4782be6e6e6b652603b');
INSERT INTO `t_attachment` VALUES ('20220104161409524671296643', '', NULL, NULL, 0, '1-bg.jpg', '/attachmentFile/2022-01-04T161409.5221-bg.jpg', '.jpg', 956, NULL, 0, 1, 'u_10001', '2022-01-04 16:14:10', NULL, NULL, '49c0d75d3bb7d4782be6e6e6b652603b');
INSERT INTO `t_attachment` VALUES ('20220104161432989340357618', '', NULL, NULL, 0, '1-bg.jpg', '/attachmentFile/2022-01-04T161432.9881-bg.jpg', '.jpg', 956, NULL, 0, 1, 'u_10001', '2022-01-04 16:14:33', NULL, NULL, '49c0d75d3bb7d4782be6e6e6b652603b');
INSERT INTO `t_attachment` VALUES ('20220104161508717026826825', '', NULL, NULL, 0, '2424.png', '/attachmentFile/2022-01-04T161508.7162424.png', '.png', 1, NULL, 0, 1, 'u_10001', '2022-01-04 16:15:09', NULL, NULL, 'b3d874ce11508d78c64860c78efd85f8');
INSERT INTO `t_attachment` VALUES ('20220104161754027981500185', '', NULL, NULL, 0, '2424.png', '/attachmentFile/2022-01-04T161754.0262424.png', '.png', 1, NULL, 0, 1, 'u_10001', '2022-01-04 16:17:54', NULL, NULL, 'b3d874ce11508d78c64860c78efd85f8');
INSERT INTO `t_attachment` VALUES ('20220104161902409394268497', '', NULL, NULL, 0, 'banner.png', '/attachmentFile/2022-01-04T161902.408banner.png', '.png', 471, NULL, 0, 1, 'u_10001', '2022-01-04 16:19:02', NULL, NULL, '626188fdd64fa86fcc7a91f8d89ca515');
INSERT INTO `t_attachment` VALUES ('20220104162013741662740708', '', NULL, NULL, 0, '2022-01-04T161902.408banner.png', '/attachmentFile/2022-01-04T162013.7412022-01-04T161902.408banner.png', '.png', 471, NULL, 0, 1, 'u_10001', '2022-01-04 16:20:14', NULL, NULL, '626188fdd64fa86fcc7a91f8d89ca515');
INSERT INTO `t_attachment` VALUES ('20220104162034538755846579', '', NULL, NULL, 0, 'image.png', '/attachmentFile/2022-01-04T162034.537image.png', '.png', 949, NULL, 0, 1, 'u_10001', '2022-01-04 16:20:35', NULL, NULL, 'e1e0f40ad9d93d46cbca2b9819280465');
INSERT INTO `t_attachment` VALUES ('20220104162054430262798857', '', NULL, NULL, 0, 'image.png', '/attachmentFile/2022-01-04T162054.429image.png', '.png', 949, NULL, 0, 1, 'u_10001', '2022-01-04 16:20:54', NULL, NULL, 'e1e0f40ad9d93d46cbca2b9819280465');
INSERT INTO `t_attachment` VALUES ('20220104162153527342865771', '202105171503402497159', NULL, NULL, 0, 'al-1.jpg', '/attachmentFile/2022-01-04T162153.494al-1.jpg', '.jpg', 118, NULL, 0, 1, 'u_10001', '2022-01-04 16:21:54', NULL, NULL, '73c19454e90201f9d85a7d67a5aff547');
INSERT INTO `t_attachment` VALUES ('20220104162212566283045304', '', NULL, NULL, 0, '1-bg.jpg', '/attachmentFile/2022-01-04T162212.5641-bg.jpg', '.jpg', 956, NULL, 0, 1, 'u_10001', '2022-01-04 16:22:13', NULL, NULL, '49c0d75d3bb7d4782be6e6e6b652603b');
INSERT INTO `t_attachment` VALUES ('20220104162506903538767864', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2022-01-04T162506.870758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2022-01-04 16:25:07', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20220104162519819237584509', '', NULL, NULL, 0, 'banner.png', '/attachmentFile/2022-01-04T162519.818banner.png', '.png', 471, NULL, 0, 1, 'u_10001', '2022-01-04 16:25:20', NULL, NULL, '626188fdd64fa86fcc7a91f8d89ca515');
INSERT INTO `t_attachment` VALUES ('20220104162902817275212802', '202105171503402497159', NULL, NULL, 0, '1-bg.jpg', '/attachmentFile/2022-01-04T162902.8061-bg.jpg', '.jpg', 956, NULL, 0, 1, 'u_10001', '2022-01-04 16:29:03', NULL, NULL, '49c0d75d3bb7d4782be6e6e6b652603b');
INSERT INTO `t_attachment` VALUES ('20220104162920191321907459', '', NULL, NULL, 0, 'banner.png', '/attachmentFile/2022-01-04T162920.190banner.png', '.png', 471, NULL, 0, 1, 'u_10001', '2022-01-04 16:29:20', NULL, NULL, '626188fdd64fa86fcc7a91f8d89ca515');
INSERT INTO `t_attachment` VALUES ('20220104162936938209065457', '202105171503402497159', NULL, NULL, 0, '2424.png', '/attachmentFile/2022-01-04T162936.9352424.png', '.png', 1, NULL, 0, 1, 'u_10001', '2022-01-04 16:29:37', NULL, NULL, 'b3d874ce11508d78c64860c78efd85f8');
INSERT INTO `t_attachment` VALUES ('20220104162944234527137413', '', NULL, NULL, 0, 'al-5.jpg', '/attachmentFile/2022-01-04T162944.233al-5.jpg', '.jpg', 182, NULL, 0, 1, 'u_10001', '2022-01-04 16:29:44', NULL, NULL, 'fecf55a2295a94ad981789144256ab80');
INSERT INTO `t_attachment` VALUES ('20220104172827940762584346', '', NULL, NULL, 0, 'default.jpg', '/attachmentFile/2022-01-04T172827.939default.jpg', '.jpg', 14, NULL, 0, 1, 'u_10001', '2022-01-04 17:28:28', NULL, NULL, 'bc9d102fe1ef34f7b9a2d9b8d86f745a');
INSERT INTO `t_attachment` VALUES ('20220104173721935458285371', '202105171503402497159', NULL, NULL, 0, 'default.jpg', '/attachmentFile/2022-01-04T173721.932default.jpg', '.jpg', 14, NULL, 0, 1, 'u_10001', '2022-01-04 17:37:22', NULL, NULL, 'bc9d102fe1ef34f7b9a2d9b8d86f745a');
INSERT INTO `t_attachment` VALUES ('20220104174041672722439485', '202105171503402497159', NULL, NULL, 0, '2b94b22a8dc6807fda58eb9f47c29f04.jpg', '/attachmentFile/2022-01-04T174041.6582b94b22a8dc6807fda58eb9f47c29f04.jpg', '.jpg', 399, NULL, 0, 1, 'u_10001', '2022-01-04 17:40:42', NULL, NULL, '5b071c7476d8cb31a22ffda1011da8ab');
INSERT INTO `t_attachment` VALUES ('20220104174052836841209502', '202105171503402497159', NULL, NULL, 0, '2b94b22a8dc6807fda58eb9f47c29f04.jpg', '/attachmentFile/2022-01-04T174052.8342b94b22a8dc6807fda58eb9f47c29f04.jpg', '.jpg', 399, NULL, 0, 1, 'u_10001', '2022-01-04 17:40:53', NULL, NULL, '5b071c7476d8cb31a22ffda1011da8ab');
INSERT INTO `t_attachment` VALUES ('20220104174106867999262416', '202105171503402497159', NULL, NULL, 0, '2b94b22a8dc6807fda58eb9f47c29f04.jpg', '/attachmentFile/2022-01-04T174106.8642b94b22a8dc6807fda58eb9f47c29f04.jpg', '.jpg', 399, NULL, 0, 1, 'u_10001', '2022-01-04 17:41:07', NULL, NULL, '5b071c7476d8cb31a22ffda1011da8ab');
INSERT INTO `t_attachment` VALUES ('20220105175258208689633280', '202105171503402497159', NULL, NULL, 0, '2b94b22a8dc6807fda58eb9f47c29f04.jpg', '/attachmentFile/2022-01-05T175258.1782b94b22a8dc6807fda58eb9f47c29f04.jpg', '.jpg', 399, NULL, 0, 1, 'u_10001', '2022-01-05 17:52:58', NULL, NULL, '5b071c7476d8cb31a22ffda1011da8ab');
INSERT INTO `t_attachment` VALUES ('20220105175604235600128666', '202105171503402497159', NULL, NULL, 0, '2b94b22a8dc6807fda58eb9f47c29f04.jpg', '/attachmentFile/2022-01-05T175604.2302b94b22a8dc6807fda58eb9f47c29f04.jpg', '.jpg', 399, NULL, 0, 1, 'u_10001', '2022-01-05 17:56:04', NULL, NULL, '5b071c7476d8cb31a22ffda1011da8ab');
INSERT INTO `t_attachment` VALUES ('20220105180038669146630519', '202105171503402497159', NULL, NULL, 0, '2b94b22a8dc6807fda58eb9f47c29f04.jpg', '/attachmentFile/2022-01-05T180038.6612b94b22a8dc6807fda58eb9f47c29f04.jpg', '.jpg', 399, NULL, 0, 1, 'u_10001', '2022-01-05 18:00:39', NULL, NULL, '5b071c7476d8cb31a22ffda1011da8ab');
INSERT INTO `t_attachment` VALUES ('20220105180040850374487155', '202105171503402497159', NULL, NULL, 0, '250b9b07ec6e5671540cae454c627f51.jpg', '/attachmentFile/2022-01-05T180040.843250b9b07ec6e5671540cae454c627f51.jpg', '.jpg', 1052, NULL, 0, 1, 'u_10001', '2022-01-05 18:00:41', NULL, NULL, '6a28a8686006e19146732de6b889de30');
INSERT INTO `t_attachment` VALUES ('20220105180043723243474078', '202105171503402497159', NULL, NULL, 0, '272470852009cbdc0572364a309e8c0d.jpg', '/attachmentFile/2022-01-05T180043.716272470852009cbdc0572364a309e8c0d.jpg', '.jpg', 1364, NULL, 0, 1, 'u_10001', '2022-01-05 18:00:44', NULL, NULL, '421c2a74ec9b49ee3ceaf1533b12d571');
INSERT INTO `t_attachment` VALUES ('20220106113658774066614556', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2022-01-06T113658.767758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2022-01-06 11:36:59', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20220106113801576942621910', '202105171503402497159', NULL, NULL, 0, 'res添加非空字段.jpg', '/attachmentFile/2022-01-06T113801.571res添加非空字段.jpg', '.jpg', 212, NULL, 0, 1, 'u_10001', '2022-01-06 11:38:02', NULL, NULL, '7f9c24840657a87cf3f5c6aae0e8765e');
INSERT INTO `t_attachment` VALUES ('20220106113958386380547088', '202105171503402497159', NULL, NULL, 0, '盛见.png', '/attachmentFile/2022-01-06T113958.382盛见.png', '.png', 5, NULL, 0, 1, 'u_10001', '2022-01-06 11:39:58', NULL, NULL, '1b5c7c47a3478b2032340e407d47a66f');
INSERT INTO `t_attachment` VALUES ('20220106114005036319838723', '202105171503402497159', NULL, NULL, 0, '盛见.png', '/attachmentFile/2022-01-06T114005.033盛见.png', '.png', 5, NULL, 0, 1, 'u_10001', '2022-01-06 11:40:05', NULL, NULL, '1b5c7c47a3478b2032340e407d47a66f');
INSERT INTO `t_attachment` VALUES ('20220106114009423776312816', '202105171503402497159', NULL, NULL, 0, '盛见.png', '/attachmentFile/2022-01-06T114009.384盛见.png', '.png', 5, NULL, 0, 1, 'u_10001', '2022-01-06 11:40:09', NULL, NULL, '1b5c7c47a3478b2032340e407d47a66f');
INSERT INTO `t_attachment` VALUES ('20220106114443355847609410', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2022-01-06T114443.111758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2022-01-06 11:44:43', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20220106114446444164166950', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2022-01-06T114446.441758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2022-01-06 11:44:46', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20220106114645489059537429', '202105171503402497159', NULL, NULL, 0, '盛见.png', '/attachmentFile/2022-01-06T114645.485盛见.png', '.png', 5, NULL, 0, 1, 'u_10001', '2022-01-06 11:46:45', NULL, NULL, '1b5c7c47a3478b2032340e407d47a66f');
INSERT INTO `t_attachment` VALUES ('20220106114649773970249088', '202105171503402497159', NULL, NULL, 0, '盛见.png', '/attachmentFile/2022-01-06T114649.769盛见.png', '.png', 5, NULL, 0, 1, 'u_10001', '2022-01-06 11:46:50', NULL, NULL, '1b5c7c47a3478b2032340e407d47a66f');
INSERT INTO `t_attachment` VALUES ('20220106114653108687175886', '202105171503402497159', NULL, NULL, 0, '盛见.png', '/attachmentFile/2022-01-06T114653.104盛见.png', '.png', 5, NULL, 0, 1, 'u_10001', '2022-01-06 11:46:53', NULL, NULL, '1b5c7c47a3478b2032340e407d47a66f');
INSERT INTO `t_attachment` VALUES ('20220106115037672539485734', '202105171503402497159', NULL, NULL, 0, '盛见.png', '/attachmentFile/2022-01-06T115037.667盛见.png', '.png', 5, NULL, 0, 1, 'u_10001', '2022-01-06 11:50:38', NULL, NULL, '1b5c7c47a3478b2032340e407d47a66f');
INSERT INTO `t_attachment` VALUES ('20220106115321133107858550', '202105171503402497159', NULL, NULL, 0, '蔡某人.jpeg', '/attachmentFile/2022-01-06T115321.108蔡某人.jpeg', '.jpeg', 14, NULL, 0, 1, 'u_10001', '2022-01-06 11:53:21', NULL, NULL, 'a527a0ae87165e024d0da25be36a69d4');
INSERT INTO `t_attachment` VALUES ('20220106115325026629984888', '202105171503402497159', NULL, NULL, 0, '蔡某人.jpeg', '/attachmentFile/2022-01-06T115325.023蔡某人.jpeg', '.jpeg', 14, NULL, 0, 1, 'u_10001', '2022-01-06 11:53:25', NULL, NULL, 'a527a0ae87165e024d0da25be36a69d4');
INSERT INTO `t_attachment` VALUES ('20220106115328292334651357', '202105171503402497159', NULL, NULL, 0, '蔡某人.jpeg', '/attachmentFile/2022-01-06T115328.280蔡某人.jpeg', '.jpeg', 14, NULL, 0, 1, 'u_10001', '2022-01-06 11:53:28', NULL, NULL, 'a527a0ae87165e024d0da25be36a69d4');
INSERT INTO `t_attachment` VALUES ('20220106132745030995121048', '202105171503402497159', NULL, NULL, 0, '蔡某人.jpeg', '/attachmentFile/2022-01-06T132745.024蔡某人.jpeg', '.jpeg', 14, NULL, 0, 1, 'u_10001', '2022-01-06 13:27:45', NULL, NULL, 'a527a0ae87165e024d0da25be36a69d4');
INSERT INTO `t_attachment` VALUES ('20220106133248611937120669', '202105171503402497159', NULL, NULL, 0, '蔡某人.jpeg', '/attachmentFile/2022-01-06T133203.579蔡某人.jpeg', '.jpeg', 14, NULL, 0, 1, 'u_10001', '2022-01-06 13:32:49', NULL, NULL, 'a527a0ae87165e024d0da25be36a69d4');
INSERT INTO `t_attachment` VALUES ('20220106134019505610241965', '202105171503402497159', NULL, NULL, 0, '蔡某人.jpeg', '/attachmentFile/2022-01-06T134019.498蔡某人.jpeg', '.jpeg', 14, NULL, 0, 1, 'u_10001', '2022-01-06 13:40:20', NULL, NULL, 'a527a0ae87165e024d0da25be36a69d4');
INSERT INTO `t_attachment` VALUES ('20220106140401970895144013', '202105171503402497159', NULL, NULL, 0, '蔡某人.jpeg', '/attachmentFile/2022-01-06T140129.587蔡某人.jpeg', '.jpeg', 14, NULL, 0, 1, 'u_10001', '2022-01-06 14:04:02', NULL, NULL, 'a527a0ae87165e024d0da25be36a69d4');
INSERT INTO `t_attachment` VALUES ('20220106141305953479985349', '202105171503402497159', NULL, NULL, 0, '蔡某人.jpeg', '/attachmentFile/2022-01-06T141305.947蔡某人.jpeg', '.jpeg', 14, NULL, 0, 1, 'u_10001', '2022-01-06 14:13:06', NULL, NULL, 'a527a0ae87165e024d0da25be36a69d4');
INSERT INTO `t_attachment` VALUES ('20220106141457649317082581', '202105171503402497159', NULL, NULL, 0, '蔡某人.jpeg', '/attachmentFile/2022-01-06T141457.644蔡某人.jpeg', '.jpeg', 14, NULL, 0, 1, 'u_10001', '2022-01-06 14:14:58', NULL, NULL, 'a527a0ae87165e024d0da25be36a69d4');
INSERT INTO `t_attachment` VALUES ('20220106143146569656120642', '202105171503402497159', NULL, NULL, 0, 'b.jpeg', '/attachmentFile/2022-01-06T143146.562b.jpeg', '.jpeg', 14, NULL, 0, 1, 'u_10001', '2022-01-06 14:31:47', NULL, NULL, 'a527a0ae87165e024d0da25be36a69d4');
INSERT INTO `t_attachment` VALUES ('20220106143359053783816010', '202105171503402497159', NULL, NULL, 0, 'b.jpeg', '/attachmentFile/2022-01-06T143359.050b.jpeg', '.jpeg', 14, NULL, 0, 1, 'u_10001', '2022-01-06 14:33:59', NULL, NULL, 'a527a0ae87165e024d0da25be36a69d4');
INSERT INTO `t_attachment` VALUES ('20220106143402566686457770', '202105171503402497159', NULL, NULL, 0, 'b.jpeg', '/attachmentFile/2022-01-06T143402.563b.jpeg', '.jpeg', 14, NULL, 0, 1, 'u_10001', '2022-01-06 14:34:03', NULL, NULL, 'a527a0ae87165e024d0da25be36a69d4');
INSERT INTO `t_attachment` VALUES ('20220106143406223399146639', '202105171503402497159', NULL, NULL, 0, 'b.jpeg', '/attachmentFile/2022-01-06T143406.220b.jpeg', '.jpeg', 14, NULL, 0, 1, 'u_10001', '2022-01-06 14:34:06', NULL, NULL, 'a527a0ae87165e024d0da25be36a69d4');
INSERT INTO `t_attachment` VALUES ('20220106143613411564685290', '202105171503402497159', NULL, NULL, 0, 'b.jpeg', '/attachmentFile/2022-01-06T143613.401b.jpeg', '.jpeg', 14, NULL, 0, 1, 'u_10001', '2022-01-06 14:36:13', NULL, NULL, 'a527a0ae87165e024d0da25be36a69d4');
INSERT INTO `t_attachment` VALUES ('20220106164618978180345988', '202105171503402497159', NULL, NULL, 0, '2b94b22a8dc6807fda58eb9f47c29f04.jpg', '/attachmentFile/2022-01-06T164618.9682b94b22a8dc6807fda58eb9f47c29f04.jpg', '.jpg', 399, NULL, 0, 1, 'u_10001', '2022-01-06 16:46:19', NULL, NULL, '5b071c7476d8cb31a22ffda1011da8ab');
INSERT INTO `t_attachment` VALUES ('20220106164621888145147686', '202105171503402497159', NULL, NULL, 0, '250b9b07ec6e5671540cae454c627f51.jpg', '/attachmentFile/2022-01-06T164621.884250b9b07ec6e5671540cae454c627f51.jpg', '.jpg', 1052, NULL, 0, 1, 'u_10001', '2022-01-06 16:46:22', NULL, NULL, '6a28a8686006e19146732de6b889de30');
INSERT INTO `t_attachment` VALUES ('20220106164623986953659089', '202105171503402497159', NULL, NULL, 0, '272470852009cbdc0572364a309e8c0d.jpg', '/attachmentFile/2022-01-06T164623.981272470852009cbdc0572364a309e8c0d.jpg', '.jpg', 1364, NULL, 0, 1, 'u_10001', '2022-01-06 16:46:24', NULL, NULL, '421c2a74ec9b49ee3ceaf1533b12d571');
INSERT INTO `t_attachment` VALUES ('20220106181240629166545413', '202105171503402497159', NULL, NULL, 0, 'banner.png', '/attachmentFile/2022-01-06T181240.612banner.png', '.png', 471, NULL, 0, 1, 'u_10001', '2022-01-06 18:12:41', NULL, NULL, '626188fdd64fa86fcc7a91f8d89ca515');
INSERT INTO `t_attachment` VALUES ('20220106181359291885110983', '202105171503402497159', NULL, NULL, 0, '7cde8b422ead2ef1fda4998bab9665b8.jpg', '/attachmentFile/2022-01-06T181359.2887cde8b422ead2ef1fda4998bab9665b8.jpg', '.jpg', 370, NULL, 0, 1, 'u_10001', '2022-01-06 18:13:59', NULL, NULL, 'f78815d454540eacdfa5a2ed55ec5292');
INSERT INTO `t_attachment` VALUES ('20220106181405497172292197', '202105171503402497159', NULL, NULL, 0, 'b7cc6444a2940d06b3961030b7c6826e.jpg', '/attachmentFile/2022-01-06T181405.483b7cc6444a2940d06b3961030b7c6826e.jpg', '.jpg', 566, NULL, 0, 1, 'u_10001', '2022-01-06 18:14:05', NULL, NULL, '6bf5398ec35f1f1dd7018fe43dbbdde5');
INSERT INTO `t_attachment` VALUES ('20220106181407941912331265', '202105171503402497159', NULL, NULL, 0, 'd45d6c9a3ea7d40a5984682727e8cb3c.jpg', '/attachmentFile/2022-01-06T181407.938d45d6c9a3ea7d40a5984682727e8cb3c.jpg', '.jpg', 307, NULL, 0, 1, 'u_10001', '2022-01-06 18:14:08', NULL, NULL, '331fb430b0cf8d920f60e4b440fd32d1');
INSERT INTO `t_attachment` VALUES ('20220106181411774273569219', '202105171503402497159', NULL, NULL, 0, '250b9b07ec6e5671540cae454c627f51.jpg', '/attachmentFile/2022-01-06T181411.770250b9b07ec6e5671540cae454c627f51.jpg', '.jpg', 1052, NULL, 0, 1, 'u_10001', '2022-01-06 18:14:12', NULL, NULL, '6a28a8686006e19146732de6b889de30');
INSERT INTO `t_attachment` VALUES ('20220106181428448613507963', '202105171503402497159', NULL, NULL, 0, '7cde8b422ead2ef1fda4998bab9665b8.jpg', '/attachmentFile/2022-01-06T181428.4447cde8b422ead2ef1fda4998bab9665b8.jpg', '.jpg', 370, NULL, 0, 1, 'u_10001', '2022-01-06 18:14:28', NULL, NULL, 'f78815d454540eacdfa5a2ed55ec5292');
INSERT INTO `t_attachment` VALUES ('20220106181809134742373071', '202105171503402497159', NULL, NULL, 0, 'banner.png', '/attachmentFile/2022-01-06T181809.126banner.png', '.png', 471, NULL, 0, 1, 'u_10001', '2022-01-06 18:18:09', NULL, NULL, '626188fdd64fa86fcc7a91f8d89ca515');
INSERT INTO `t_attachment` VALUES ('20220106181829578453871347', '202105171503402497159', NULL, NULL, 0, 'banner.png', '/attachmentFile/2022-01-06T181829.575banner.png', '.png', 471, NULL, 0, 1, 'u_10001', '2022-01-06 18:18:30', NULL, NULL, '626188fdd64fa86fcc7a91f8d89ca515');
INSERT INTO `t_attachment` VALUES ('20220106181949476454152747', '202105171503402497159', NULL, NULL, 0, '7cde8b422ead2ef1fda4998bab9665b8.jpg', '/attachmentFile/2022-01-06T181949.4587cde8b422ead2ef1fda4998bab9665b8.jpg', '.jpg', 370, NULL, 0, 1, 'u_10001', '2022-01-06 18:19:49', NULL, NULL, 'f78815d454540eacdfa5a2ed55ec5292');
INSERT INTO `t_attachment` VALUES ('20220106181954445344012195', '202105171503402497159', NULL, NULL, 0, '9d8e2cb262fdecc2b9a685241d386841.jpg', '/attachmentFile/2022-01-06T181954.4409d8e2cb262fdecc2b9a685241d386841.jpg', '.jpg', 1683, NULL, 0, 1, 'u_10001', '2022-01-06 18:19:54', NULL, NULL, 'ae2ca0e41a4dfd0f3f4c1d0b436e8113');
INSERT INTO `t_attachment` VALUES ('20220106181958332746753412', '202105171503402497159', NULL, NULL, 0, '250b9b07ec6e5671540cae454c627f51.jpg', '/attachmentFile/2022-01-06T181958.327250b9b07ec6e5671540cae454c627f51.jpg', '.jpg', 1052, NULL, 0, 1, 'u_10001', '2022-01-06 18:19:58', NULL, NULL, '6a28a8686006e19146732de6b889de30');
INSERT INTO `t_attachment` VALUES ('20220106182532833772912967', '202105171503402497159', NULL, NULL, 0, 'b.jpg', '/attachmentFile/2022-01-06T182532.830b.jpg', '.jpg', 399, NULL, 0, 1, 'u_10001', '2022-01-06 18:25:33', NULL, NULL, '5b071c7476d8cb31a22ffda1011da8ab');
INSERT INTO `t_attachment` VALUES ('20220106182553258176976570', '202105171503402497159', NULL, NULL, 0, 'b.jpg', '/attachmentFile/2022-01-06T182553.255b.jpg', '.jpg', 399, NULL, 0, 1, 'u_10001', '2022-01-06 18:25:53', NULL, NULL, '5b071c7476d8cb31a22ffda1011da8ab');
INSERT INTO `t_attachment` VALUES ('20220106182654684430486548', '202105171503402497159', NULL, NULL, 0, '未标题-1.jpg', '/attachmentFile/2022-01-06T182654.680未标题-1.jpg', '.jpg', 60, NULL, 0, 1, 'u_10001', '2022-01-06 18:26:55', NULL, NULL, '56434fa2746893009fa0470271e9cdce');
INSERT INTO `t_attachment` VALUES ('20220106182712651220066345', '202105171503402497159', NULL, NULL, 0, 'asd.jpg', '/attachmentFile/2022-01-06T182712.648asd.jpg', '.jpg', 60, NULL, 0, 1, 'u_10001', '2022-01-06 18:27:13', NULL, NULL, '56434fa2746893009fa0470271e9cdce');
INSERT INTO `t_attachment` VALUES ('20220106182934610489918222', '202105171503402497159', NULL, NULL, 0, 'ad637f228114616847ce4e8d94b24489.jpg', '/attachmentFile/2022-01-06T182934.606ad637f228114616847ce4e8d94b24489.jpg', '.jpg', 1032, NULL, 0, 1, 'u_10001', '2022-01-06 18:29:35', NULL, NULL, 'e88ad187e6f1891098d6b461a30a315a');
INSERT INTO `t_attachment` VALUES ('20220106183001331691088719', '202105171503402497159', NULL, NULL, 0, '144c7eaa5f7cec5e3631409e076913ed.jpg', '/attachmentFile/2022-01-06T183001.327144c7eaa5f7cec5e3631409e076913ed.jpg', '.jpg', 1610, NULL, 0, 1, 'u_10001', '2022-01-06 18:30:01', NULL, NULL, 'ac0a714ec33f569eb9fed6ba01590bba');
INSERT INTO `t_attachment` VALUES ('20220106183604348027788969', '202105171503402497159', NULL, NULL, 0, 'ad637f228114616847ce4e8d94b24489.jpg', '/attachmentFile/2022-01-06T183604.328ad637f228114616847ce4e8d94b24489.jpg', '.jpg', 1032, NULL, 0, 1, 'u_10001', '2022-01-06 18:36:04', NULL, NULL, 'e88ad187e6f1891098d6b461a30a315a');
INSERT INTO `t_attachment` VALUES ('20220106183608440826047055', '202105171503402497159', NULL, NULL, 0, '7cde8b422ead2ef1fda4998bab9665b8.jpg', '/attachmentFile/2022-01-06T183608.4367cde8b422ead2ef1fda4998bab9665b8.jpg', '.jpg', 370, NULL, 0, 1, 'u_10001', '2022-01-06 18:36:08', NULL, NULL, 'f78815d454540eacdfa5a2ed55ec5292');
INSERT INTO `t_attachment` VALUES ('20220106183755070633167699', '202105171503402497159', NULL, NULL, 0, 'ad637f228114616847ce4e8d94b24489.jpg', '/attachmentFile/2022-01-06T183755.065ad637f228114616847ce4e8d94b24489.jpg', '.jpg', 1032, NULL, 0, 1, 'u_10001', '2022-01-06 18:37:55', NULL, NULL, 'e88ad187e6f1891098d6b461a30a315a');
INSERT INTO `t_attachment` VALUES ('20220106185506025308080132', '202105171503402497159', NULL, NULL, 0, 'ad637f228114616847ce4e8d94b24489.jpg', '/attachmentFile/2022-01-06T185506.019ad637f228114616847ce4e8d94b24489.jpg', '.jpg', 1032, NULL, 0, 1, 'u_10001', '2022-01-06 18:55:06', NULL, NULL, 'e88ad187e6f1891098d6b461a30a315a');
INSERT INTO `t_attachment` VALUES ('20220106190844746867735166', '202105171503402497159', NULL, NULL, 0, '图层 4.png', '/attachmentFile/2022-01-06T190844.715图层 4.png', '.png', 95, NULL, 0, 1, 'u_10001', '2022-01-06 19:08:45', NULL, NULL, '91390c8b59b34e0c48e8b852e6551d07');
INSERT INTO `t_attachment` VALUES ('20220106190927007980754891', '202105171503402497159', NULL, NULL, 0, 'agr5h6ytjyk.png', '/attachmentFile/2022-01-06T190927.004agr5h6ytjyk.png', '.png', 102, NULL, 0, 1, 'u_10001', '2022-01-06 19:09:27', NULL, NULL, '512ad4386f8a1bd2e5f218d910fc29ed');
INSERT INTO `t_attachment` VALUES ('20220107092230823027204015', '202105171503402497159', NULL, NULL, 0, '1-bg.jpg', '/attachmentFile/2022-01-07T092230.8071-bg.jpg', '.jpg', 956, NULL, 0, 1, 'u_10001', '2022-01-07 09:22:31', NULL, NULL, '49c0d75d3bb7d4782be6e6e6b652603b');
INSERT INTO `t_attachment` VALUES ('20220107093341752155660996', '202105171503402497159', NULL, NULL, 0, 'lQLPDhsHxlMGAELNAfLNAfKwzA2LoE71D4IB3WlJMoCcAA_498_498.png', '/attachmentFile/2022-01-07T093341.723lQLPDhsHxlMGAELNAfLNAfKwzA2LoE71D4IB3WlJMoCcAA_498_498.png', '.png', 155, NULL, 0, 1, 'u_10001', '2022-01-07 09:33:42', NULL, NULL, '57c38d8507ed40161482b5c30da3f3fb');
INSERT INTO `t_attachment` VALUES ('20220107093349359360998992', '202105171503402497159', NULL, NULL, 0, '272470852009cbdc0572364a309e8c0d.jpg', '/attachmentFile/2022-01-07T093348.830272470852009cbdc0572364a309e8c0d.jpg', '.jpg', 1364, NULL, 0, 1, 'u_10001', '2022-01-07 09:33:49', NULL, NULL, '421c2a74ec9b49ee3ceaf1533b12d571');
INSERT INTO `t_attachment` VALUES ('20220107093357015367694653', '202105171503402497159', NULL, NULL, 0, 'adgjyt.png', '/attachmentFile/2022-01-07T093357.012adgjyt.png', '.png', 104, NULL, 0, 1, 'u_10001', '2022-01-07 09:33:57', NULL, NULL, 'e44e7660424f46b87601f02e1210d514');
INSERT INTO `t_attachment` VALUES ('20220107093426894309643082', '202105171503402497159', NULL, NULL, 0, 'lQLPDhsHxlMGAELNAfLNAfKwzA2LoE71D4IB3WlJMoCcAA_498_498.png', '/attachmentFile/2022-01-07T093426.890lQLPDhsHxlMGAELNAfLNAfKwzA2LoE71D4IB3WlJMoCcAA_498_498.png', '.png', 155, NULL, 0, 1, 'u_10001', '2022-01-07 09:34:27', NULL, NULL, '57c38d8507ed40161482b5c30da3f3fb');
INSERT INTO `t_attachment` VALUES ('20220107093456550687860195', '202105171503402497159', NULL, NULL, 0, 'lQLPDhsHxlCjpqzNAfLNAfKwMYd1IAA6vt8B3WlFygCcAA_498_498.png', '/attachmentFile/2022-01-07T093456.546lQLPDhsHxlCjpqzNAfLNAfKwMYd1IAA6vt8B3WlFygCcAA_498_498.png', '.png', 181, NULL, 0, 1, 'u_10001', '2022-01-07 09:34:57', NULL, NULL, 'a2bcbd1fb362a4309357385dabbe3afd');
INSERT INTO `t_attachment` VALUES ('20220107095231706494734297', '202105171503402497159', NULL, NULL, 0, 'al-1.jpg', '/attachmentFile/2022-01-07T095231.699al-1.jpg', '.jpg', 118, NULL, 0, 1, 'u_10001', '2022-01-07 09:52:32', NULL, NULL, '73c19454e90201f9d85a7d67a5aff547');
INSERT INTO `t_attachment` VALUES ('20220107095937514035985055', '', NULL, NULL, 0, '37f5a9e32e13720428600a403dcfdff8.jpg', '/attachmentFile/2022-01-07T095937.48537f5a9e32e13720428600a403dcfdff8.jpg', '.jpg', 336, NULL, 0, 1, 'u_10001', '2022-01-07 09:59:38', NULL, NULL, 'dc382d45ab8880e01519f6dc4cc6a7a7');
INSERT INTO `t_attachment` VALUES ('20220107100113267611640354', '202105171503402497159', NULL, NULL, 0, 'lQLPDhsHxlCjpqzNAfLNAfKwMYd1IAA6vt8B3WlFygCcAA_498_498.png', '/attachmentFile/2022-01-07T100113.220lQLPDhsHxlCjpqzNAfLNAfKwMYd1IAA6vt8B3WlFygCcAA_498_498.png', '.png', 181, NULL, 0, 1, 'u_10001', '2022-01-07 10:01:13', NULL, NULL, 'a2bcbd1fb362a4309357385dabbe3afd');
INSERT INTO `t_attachment` VALUES ('20220107110549956897217750', '202105171503402497159', NULL, NULL, 0, 'lQLPDhsHxlE8PPTNAfLNAfKwTbeMov10HggB3WlFvgAFAA_498_498.png', '/attachmentFile/2022-01-07T110549.935lQLPDhsHxlE8PPTNAfLNAfKwTbeMov10HggB3WlFvgAFAA_498_498.png', '.png', 152, NULL, 0, 1, 'u_10001', '2022-01-07 11:05:50', NULL, NULL, '4026092bf828fb1eeb5373ddb03f5532');
INSERT INTO `t_attachment` VALUES ('20220107110825717264202597', '202105171503402497159', NULL, NULL, 0, 'lQLPDhsHxlE8PSfNAfLNAfKwE79QuGucTEgB3WlF1gC0AA_498_498.png', '/attachmentFile/2022-01-07T110825.713lQLPDhsHxlE8PSfNAfLNAfKwE79QuGucTEgB3WlF1gC0AA_498_498.png', '.png', 145, NULL, 0, 1, 'u_10001', '2022-01-07 11:08:26', NULL, NULL, 'bcf656db7a459da2b876550513843326');
INSERT INTO `t_attachment` VALUES ('20220107111119619219149762', '20210519110309706064758363', NULL, NULL, 0, '37f5a9e32e13720428600a403dcfdff8.jpg', '/attachmentFile/2022-01-07T111119.59437f5a9e32e13720428600a403dcfdff8.jpg', '.jpg', 336, NULL, 0, 1, '20210521175017791641757545', '2022-01-07 11:11:20', NULL, NULL, 'dc382d45ab8880e01519f6dc4cc6a7a7');
INSERT INTO `t_attachment` VALUES ('20220107111126557971712245', '20210519110309706064758363', NULL, NULL, 0, 'd45d6c9a3ea7d40a5984682727e8cb3c.jpg', '/attachmentFile/2022-01-07T111126.552d45d6c9a3ea7d40a5984682727e8cb3c.jpg', '.jpg', 307, NULL, 0, 1, '20210521175017791641757545', '2022-01-07 11:11:27', NULL, NULL, '331fb430b0cf8d920f60e4b440fd32d1');
INSERT INTO `t_attachment` VALUES ('20220107111326378138767750', '20210519110309706064758363', NULL, NULL, 0, '2021112611360167ea9d28a86c4d96b4d82844bc402736.jpg', '/attachmentFile/2022-01-07T111326.3752021112611360167ea9d28a86c4d96b4d82844bc402736.jpg', '.jpg', 7, NULL, 0, 1, '20210521175017791641757545', '2022-01-07 11:13:26', NULL, NULL, '72c2bfd665ecdcb9417a85f4ba7dd701');
INSERT INTO `t_attachment` VALUES ('20220107111329591350075439', '20210519110309706064758363', NULL, NULL, 0, '202111261157541ff8d0e867f04f56890eed8fdffa67c1.jpg', '/attachmentFile/2022-01-07T111329.587202111261157541ff8d0e867f04f56890eed8fdffa67c1.jpg', '.jpg', 7, NULL, 0, 1, '20210521175017791641757545', '2022-01-07 11:13:30', NULL, NULL, 'a8fd827f6421882622d672109f78d4be');
INSERT INTO `t_attachment` VALUES ('20220107111414635691537076', '20210519110309706064758363', NULL, NULL, 0, 'adgjyt.png', '/attachmentFile/2022-01-07T111414.633adgjyt.png', '.png', 104, NULL, 0, 1, '20210521175017791641757545', '2022-01-07 11:14:15', NULL, NULL, 'e44e7660424f46b87601f02e1210d514');
INSERT INTO `t_attachment` VALUES ('20220107111552001681846296', '20210519110309706064758363', NULL, NULL, 0, '202111261137580f5759e201554589990d2cfae5a7c93e.jpg', '/attachmentFile/2022-01-07T111551.998202111261137580f5759e201554589990d2cfae5a7c93e.jpg', '.jpg', 11, NULL, 0, 1, '20210521175017791641757545', '2022-01-07 11:15:52', NULL, NULL, '7d80999aaff567d6e02720702e7eec6e');
INSERT INTO `t_attachment` VALUES ('20220107111641098287323675', '20210519110309706064758363', NULL, NULL, 0, '20211126113049b4a5e847ed6248e98a7b602d5cd032bb.jpg', '/attachmentFile/2022-01-07T111641.09420211126113049b4a5e847ed6248e98a7b602d5cd032bb.jpg', '.jpg', 19, NULL, 0, 1, '20210521175017791641757545', '2022-01-07 11:16:41', NULL, NULL, 'b763750655f65e1e42cd79f02376344e');
INSERT INTO `t_attachment` VALUES ('20220107112820828212908305', '20210519110309706064758363', NULL, NULL, 0, '2021112611360167ea9d28a86c4d96b4d82844bc402736.jpg', '/attachmentFile/2022-01-07T112820.8232021112611360167ea9d28a86c4d96b4d82844bc402736.jpg', '.jpg', 7, NULL, 0, 1, '20210521175017791641757545', '2022-01-07 11:28:21', NULL, NULL, '72c2bfd665ecdcb9417a85f4ba7dd701');
INSERT INTO `t_attachment` VALUES ('20220107135155362422676807', '202105171503402497159', NULL, NULL, 0, '7cde8b422ead2ef1fda4998bab9665b8.jpg', '/attachmentFile/2022-01-07T135155.3497cde8b422ead2ef1fda4998bab9665b8.jpg', '.jpg', 370, NULL, 0, 1, 'u_10001', '2022-01-07 13:51:55', NULL, NULL, 'f78815d454540eacdfa5a2ed55ec5292');
INSERT INTO `t_attachment` VALUES ('20220107135203152966988720', '202105171503402497159', NULL, NULL, 0, '4ef14658fe7b752c2302cfb4a94d00b8.jpg', '/attachmentFile/2022-01-07T135203.1494ef14658fe7b752c2302cfb4a94d00b8.jpg', '.jpg', 270, NULL, 0, 1, 'u_10001', '2022-01-07 13:52:03', NULL, NULL, '07c19909da80f0bd44a47ece778a07ed');
INSERT INTO `t_attachment` VALUES ('20220107135217245850165041', '202105171503402497159', NULL, NULL, 0, 'agr5h6ytjyk.png', '/attachmentFile/2022-01-07T135217.241agr5h6ytjyk.png', '.png', 102, NULL, 0, 1, 'u_10001', '2022-01-07 13:52:17', NULL, NULL, '512ad4386f8a1bd2e5f218d910fc29ed');
INSERT INTO `t_attachment` VALUES ('20220107135234998654079909', '202105171503402497159', NULL, NULL, 0, '2b94b22a8dc6807fda58eb9f47c29f04.jpg', '/attachmentFile/2022-01-07T135234.9952b94b22a8dc6807fda58eb9f47c29f04.jpg', '.jpg', 399, NULL, 0, 1, 'u_10001', '2022-01-07 13:52:35', NULL, NULL, '5b071c7476d8cb31a22ffda1011da8ab');
INSERT INTO `t_attachment` VALUES ('20220107135237227846895832', '202105171503402497159', NULL, NULL, 0, '2b94b22a8dc6807fda58eb9f47c29f04.jpg', '/attachmentFile/2022-01-07T135237.2242b94b22a8dc6807fda58eb9f47c29f04.jpg', '.jpg', 399, NULL, 0, 1, 'u_10001', '2022-01-07 13:52:37', NULL, NULL, '5b071c7476d8cb31a22ffda1011da8ab');
INSERT INTO `t_attachment` VALUES ('20220107135243649603578056', '202105171503402497159', NULL, NULL, 0, 'asd.jpg', '/attachmentFile/2022-01-07T135243.647asd.jpg', '.jpg', 60, NULL, 0, 1, 'u_10001', '2022-01-07 13:52:44', NULL, NULL, '56434fa2746893009fa0470271e9cdce');
INSERT INTO `t_attachment` VALUES ('20220107135306529910639553', '202105171503402497159', NULL, NULL, 0, 'lQLPDhsHxlMGAHrNAfLNAfKwbDESQwz_5OsB3WlJLoAFAA_498_498.png', '/attachmentFile/2022-01-07T135306.525lQLPDhsHxlMGAHrNAfLNAfKwbDESQwz_5OsB3WlJLoAFAA_498_498.png', '.png', 150, NULL, 0, 1, 'u_10001', '2022-01-07 13:53:07', NULL, NULL, '43af3ecf1faeace6439f965bba86475a');
INSERT INTO `t_attachment` VALUES ('20220107135340702372935245', '202105171503402497159', NULL, NULL, 0, 'd45d6c9a3ea7d40a5984682727e8cb3c.jpg', '/attachmentFile/2022-01-07T135340.681d45d6c9a3ea7d40a5984682727e8cb3c.jpg', '.jpg', 307, NULL, 0, 1, 'u_10001', '2022-01-07 13:53:41', NULL, NULL, '331fb430b0cf8d920f60e4b440fd32d1');
INSERT INTO `t_attachment` VALUES ('20220107135346215747355991', '202105171503402497159', NULL, NULL, 0, 'selfBanner.png', '/attachmentFile/2022-01-07T135346.210selfBanner.png', '.png', 186, NULL, 0, 1, 'u_10001', '2022-01-07 13:53:46', NULL, NULL, 'a5265d4384312ad679682b826bbd8895');
INSERT INTO `t_attachment` VALUES ('20220107140413380292324787', '202105171503402497159', NULL, NULL, 0, 'lQLPDhsHxlMGAELNAfLNAfKwzA2LoE71D4IB3WlJMoCcAA_498_498.png', '/attachmentFile/2022-01-07T140413.377lQLPDhsHxlMGAELNAfLNAfKwzA2LoE71D4IB3WlJMoCcAA_498_498.png', '.png', 155, NULL, 0, 1, 'u_10001', '2022-01-07 14:04:13', NULL, NULL, '57c38d8507ed40161482b5c30da3f3fb');
INSERT INTO `t_attachment` VALUES ('20220107140416973319953448', '202105171503402497159', NULL, NULL, 0, '272470852009cbdc0572364a309e8c0d.jpg', '/attachmentFile/2022-01-07T140416.966272470852009cbdc0572364a309e8c0d.jpg', '.jpg', 1364, NULL, 0, 1, 'u_10001', '2022-01-07 14:04:17', NULL, NULL, '421c2a74ec9b49ee3ceaf1533b12d571');
INSERT INTO `t_attachment` VALUES ('20220107140421313408886290', '202105171503402497159', NULL, NULL, 0, 'adgjyt.png', '/attachmentFile/2022-01-07T140421.309adgjyt.png', '.png', 104, NULL, 0, 1, 'u_10001', '2022-01-07 14:04:21', NULL, NULL, 'e44e7660424f46b87601f02e1210d514');
INSERT INTO `t_attachment` VALUES ('20220107140555766500664793', '202105171503402497159', NULL, NULL, 0, '250b9b07ec6e5671540cae454c627f51.jpg', '/attachmentFile/2022-01-07T140555.759250b9b07ec6e5671540cae454c627f51.jpg', '.jpg', 1052, NULL, 0, 1, 'u_10001', '2022-01-07 14:05:56', NULL, NULL, '6a28a8686006e19146732de6b889de30');
INSERT INTO `t_attachment` VALUES ('20220107140628683216069288', '202105171503402497159', NULL, NULL, 0, 'lQLPDhsHxlCjpqzNAfLNAfKwMYd1IAA6vt8B3WlFygCcAA_498_498.png', '/attachmentFile/2022-01-07T140628.679lQLPDhsHxlCjpqzNAfLNAfKwMYd1IAA6vt8B3WlFygCcAA_498_498.png', '.png', 181, NULL, 0, 1, 'u_10001', '2022-01-07 14:06:29', NULL, NULL, 'a2bcbd1fb362a4309357385dabbe3afd');
INSERT INTO `t_attachment` VALUES ('20220107140907581708400718', '202105171503402497159', NULL, NULL, 0, 'lQLPDhsHxlMGAHrNAfLNAfKwbDESQwz_5OsB3WlJLoAFAA_498_498.png', '/attachmentFile/2022-01-07T140907.573lQLPDhsHxlMGAHrNAfLNAfKwbDESQwz_5OsB3WlJLoAFAA_498_498.png', '.png', 150, NULL, 0, 1, 'u_10001', '2022-01-07 14:09:08', NULL, NULL, '43af3ecf1faeace6439f965bba86475a');
INSERT INTO `t_attachment` VALUES ('20220107140924324523709518', '202105171503402497159', NULL, NULL, 0, 'download.png', '/attachmentFile/2022-01-07T140924.298download.png', '.png', 150, NULL, 0, 1, 'u_10001', '2022-01-07 14:09:24', NULL, NULL, '43af3ecf1faeace6439f965bba86475a');
INSERT INTO `t_attachment` VALUES ('20220107141005152495043321', '202105171503402497159', NULL, NULL, 0, 'lQLPDhsHxlE8PPTNAfLNAfKwTbeMov10HggB3WlFvgAFAA_498_498.png', '/attachmentFile/2022-01-07T141005.132lQLPDhsHxlE8PPTNAfLNAfKwTbeMov10HggB3WlFvgAFAA_498_498.png', '.png', 152, NULL, 0, 1, 'u_10001', '2022-01-07 14:10:05', NULL, NULL, '4026092bf828fb1eeb5373ddb03f5532');
INSERT INTO `t_attachment` VALUES ('20220107141705941334558775', '20210519110309706064758363', NULL, NULL, 0, '202111261137580f5759e201554589990d2cfae5a7c93e.jpg', '/attachmentFile/2022-01-07T141705.924202111261137580f5759e201554589990d2cfae5a7c93e.jpg', '.jpg', 11, NULL, 0, 1, '20210521175017791641757545', '2022-01-07 14:17:06', NULL, NULL, '7d80999aaff567d6e02720702e7eec6e');
INSERT INTO `t_attachment` VALUES ('20220107141709174535707614', '20210519110309706064758363', NULL, NULL, 0, 'd24bed2a125e7395760a1e8e54d749f1.jpg', '/attachmentFile/2022-01-07T141709.169d24bed2a125e7395760a1e8e54d749f1.jpg', '.jpg', 1635, NULL, 0, 1, '20210521175017791641757545', '2022-01-07 14:17:09', NULL, NULL, '8fdf3c05a02ca0390f42e5ffcdb51b6a');
INSERT INTO `t_attachment` VALUES ('20220107141726586397898977', '20210519110309706064758363', NULL, NULL, 0, 'selfBanner.png', '/attachmentFile/2022-01-07T141726.572selfBanner.png', '.png', 186, NULL, 0, 1, '20210521175017791641757545', '2022-01-07 14:17:27', NULL, NULL, 'a5265d4384312ad679682b826bbd8895');
INSERT INTO `t_attachment` VALUES ('20220107142034109346439768', '20210519110309706064758363', NULL, NULL, 0, '20211126113049b4a5e847ed6248e98a7b602d5cd032bb.jpg', '/attachmentFile/2022-01-07T142034.10520211126113049b4a5e847ed6248e98a7b602d5cd032bb.jpg', '.jpg', 19, NULL, 0, 1, '20210521175017791641757545', '2022-01-07 14:20:34', NULL, NULL, 'b763750655f65e1e42cd79f02376344e');
INSERT INTO `t_attachment` VALUES ('20220107142216622024567818', '20210519110309706064758363', NULL, NULL, 0, '20211126113049b4a5e847ed6248e98a7b602d5cd032bb.jpg', '/attachmentFile/2022-01-07T142216.61820211126113049b4a5e847ed6248e98a7b602d5cd032bb.jpg', '.jpg', 19, NULL, 0, 1, '20210521175017791641757545', '2022-01-07 14:22:17', NULL, NULL, 'b763750655f65e1e42cd79f02376344e');
INSERT INTO `t_attachment` VALUES ('20220107142349187099094002', '20210519110309706064758363', NULL, NULL, 0, 'agr5h6ytjyk.png', '/attachmentFile/2022-01-07T142349.184agr5h6ytjyk.png', '.png', 102, NULL, 0, 1, '20210521175017791641757545', '2022-01-07 14:23:49', NULL, NULL, '512ad4386f8a1bd2e5f218d910fc29ed');
INSERT INTO `t_attachment` VALUES ('20220107142411485393071809', '20210519110309706064758363', NULL, NULL, 0, '2021112611360167ea9d28a86c4d96b4d82844bc402736.jpg', '/attachmentFile/2022-01-07T142411.4642021112611360167ea9d28a86c4d96b4d82844bc402736.jpg', '.jpg', 7, NULL, 0, 1, '20210521175017791641757545', '2022-01-07 14:24:11', NULL, NULL, '72c2bfd665ecdcb9417a85f4ba7dd701');
INSERT INTO `t_attachment` VALUES ('20220107161352293552406680', '202105171503402497159', NULL, NULL, 0, 'lQLPDhsHxlMGAHrNAfLNAfKwbDESQwz_5OsB3WlJLoAFAA_498_498.png', '/attachmentFile/2022-01-07T161352.289lQLPDhsHxlMGAHrNAfLNAfKwbDESQwz_5OsB3WlJLoAFAA_498_498.png', '.png', 150, NULL, 0, 1, 'u_10001', '2022-01-07 16:13:52', NULL, NULL, '43af3ecf1faeace6439f965bba86475a');
INSERT INTO `t_attachment` VALUES ('20220107161506916959812081', '202105171503402497159', NULL, NULL, 0, 'lQLPDhsHxlMGAELNAfLNAfKwzA2LoE71D4IB3WlJMoCcAA_498_498.png', '/attachmentFile/2022-01-07T161506.911lQLPDhsHxlMGAELNAfLNAfKwzA2LoE71D4IB3WlJMoCcAA_498_498.png', '.png', 155, NULL, 0, 1, 'u_10001', '2022-01-07 16:15:07', NULL, NULL, '57c38d8507ed40161482b5c30da3f3fb');
INSERT INTO `t_attachment` VALUES ('20220110143900315214791913', '202105171503402497159', NULL, NULL, 0, '490911444781668186.png', '/attachmentFile/2022-01-10T143900.221490911444781668186.png', '.png', 10, NULL, 0, 1, 'u_10001', '2022-01-10 14:39:00', NULL, NULL, 'e23fa412b38baf40fe2fe8c66920d3f0');
INSERT INTO `t_attachment` VALUES ('20220110143903832124235511', '202105171503402497159', NULL, NULL, 0, '490911444781668186.png', '/attachmentFile/2022-01-10T143903.828490911444781668186.png', '.png', 10, NULL, 0, 1, 'u_10001', '2022-01-10 14:39:04', NULL, NULL, 'e23fa412b38baf40fe2fe8c66920d3f0');
INSERT INTO `t_attachment` VALUES ('20220110144113311043172006', '202105171503402497159', NULL, NULL, 0, 'asfdsg.jpg', '/attachmentFile/2022-01-10T144113.285asfdsg.jpg', '.jpg', 142, NULL, 0, 1, 'u_10001', '2022-01-10 14:41:13', NULL, NULL, '753a00d3d0a419411a3ef6c06dfe0592');
INSERT INTO `t_attachment` VALUES ('20220110144120761648345932', '202105171503402497159', NULL, NULL, 0, 'asfdsg.jpg', '/attachmentFile/2022-01-10T144120.756asfdsg.jpg', '.jpg', 142, NULL, 0, 1, 'u_10001', '2022-01-10 14:41:21', NULL, NULL, '753a00d3d0a419411a3ef6c06dfe0592');
INSERT INTO `t_attachment` VALUES ('20220110144159237020925968', '202105171503402497159', NULL, NULL, 0, 'asfdgafd.jpg', '/attachmentFile/2022-01-10T144159.221asfdgafd.jpg', '.jpg', 36, NULL, 0, 1, 'u_10001', '2022-01-10 14:41:59', NULL, NULL, 'b3701c9b22018ec3568665425a37f12e');
INSERT INTO `t_attachment` VALUES ('20220110144400427659248459', '202105171503402497159', NULL, NULL, 0, '937098428700335159.png', '/attachmentFile/2022-01-10T144400.423937098428700335159.png', '.png', 15, NULL, 0, 1, 'u_10001', '2022-01-10 14:44:00', NULL, NULL, '29db5ef026ddd427f89c0599b4ac7117');
INSERT INTO `t_attachment` VALUES ('20220110144753404794983203', '202105171503402497159', NULL, NULL, 0, '937098428700335159.png', '/attachmentFile/2022-01-10T144753.400937098428700335159.png', '.png', 15, NULL, 0, 1, 'u_10001', '2022-01-10 14:47:53', NULL, NULL, '29db5ef026ddd427f89c0599b4ac7117');
INSERT INTO `t_attachment` VALUES ('20220110144924245367680333', '202105171503402497159', NULL, NULL, 0, '1750284933933999859.png', '/attachmentFile/2022-01-10T144924.2291750284933933999859.png', '.png', 14, NULL, 0, 1, 'u_10001', '2022-01-10 14:49:24', NULL, NULL, '453b946585bb081f8db27d0153a77f88');
INSERT INTO `t_attachment` VALUES ('20220110145025265571738076', '202105171503402497159', NULL, NULL, 0, '3454604398887363063.png', '/attachmentFile/2022-01-10T145025.2613454604398887363063.png', '.png', 12, NULL, 0, 1, 'u_10001', '2022-01-10 14:50:25', NULL, NULL, '42af805b56747b16837f99720ea4c4ad');
INSERT INTO `t_attachment` VALUES ('20220110145139757558574376', '202105171503402497159', NULL, NULL, 0, '8987762840678959407.png', '/attachmentFile/2022-01-10T145139.7558987762840678959407.png', '.png', 12, NULL, 0, 1, 'u_10001', '2022-01-10 14:51:40', NULL, NULL, '46a642b7f2a481bbbcf2cc055bcaa779');
INSERT INTO `t_attachment` VALUES ('20220110145300137104652146', '202105171503402497159', NULL, NULL, 0, 'agfhhgh.jpg', '/attachmentFile/2022-01-10T145300.123agfhhgh.jpg', '.jpg', 36, NULL, 0, 1, 'u_10001', '2022-01-10 14:53:00', NULL, NULL, '18f261952b7afcd410c8387876dd2f0f');
INSERT INTO `t_attachment` VALUES ('20220110145408557008948507', '20210519110309706064758363', NULL, NULL, 0, 'aghfjhfjhn.jpg', '/attachmentFile/2022-01-10T145408.554aghfjhfjhn.jpg', '.jpg', 404, NULL, 0, 1, '20210521175017791641757545', '2022-01-10 14:54:09', NULL, NULL, '807dd44d69e359a6ddc4044f4269e597');
INSERT INTO `t_attachment` VALUES ('20220110150751355110863197', '202105171503402497159', NULL, NULL, 0, 'mmexport1641374859695.jpg', '/attachmentFile/2022-01-10T150751.296mmexport1641374859695.jpg', '.jpg', 148, NULL, 0, 1, '20211231094738022333952742', '2022-01-10 15:07:51', NULL, NULL, '615a0eb534f7111d87f3f2062a43b1f5');
INSERT INTO `t_attachment` VALUES ('20220110153118003184432970', '20210519110309706064758363', NULL, NULL, 0, '下载.png', '/attachmentFile/2022-01-10T153117.927下载.png', '.png', 0, NULL, 0, 1, '20210521175017791641757545', '2022-01-10 15:31:18', NULL, NULL, '6604b68c06d6ac57377ed124468983c5');
INSERT INTO `t_attachment` VALUES ('20220110153136490932331938', '20210519110309706064758363', NULL, NULL, 0, '下载.jpg', '/attachmentFile/2022-01-10T153136.479下载.jpg', '.jpg', 151, NULL, 0, 1, '20210521175017791641757545', '2022-01-10 15:31:36', NULL, NULL, '581153d48ad6e9d9aee2b70cc644399f');
INSERT INTO `t_attachment` VALUES ('20220110153234395150848674', '20210519110309706064758363', NULL, NULL, 0, 'sdfdagf.jpg', '/attachmentFile/2022-01-10T153234.382sdfdagf.jpg', '.jpg', 141, NULL, 0, 1, '20210521175017791641757545', '2022-01-10 15:32:34', NULL, NULL, 'a6a54074ef78cf4ba4262606f652059c');
INSERT INTO `t_attachment` VALUES ('20220110153241326059390533', '20210519110309706064758363', NULL, NULL, 0, 'asfdgfh.jpg', '/attachmentFile/2022-01-10T153241.322asfdgfh.jpg', '.jpg', 151, NULL, 0, 1, '20210521175017791641757545', '2022-01-10 15:32:41', NULL, NULL, '581153d48ad6e9d9aee2b70cc644399f');
INSERT INTO `t_attachment` VALUES ('20220110153353768575884070', '20210519110309706064758363', NULL, NULL, 0, '1863263384755765895.jpg', '/attachmentFile/2022-01-10T153353.7571863263384755765895.jpg', '.jpg', 15, NULL, 0, 1, '20210521175017791641757545', '2022-01-10 15:33:54', NULL, NULL, 'b1ccbe52f074099d8ca46101dac8b2f1');
INSERT INTO `t_attachment` VALUES ('20220110153746868374677388', '20210519110309706064758363', NULL, NULL, 0, '460167712113713905.jpg', '/attachmentFile/2022-01-10T153746.857460167712113713905.jpg', '.jpg', 13, NULL, 0, 1, '20210521175017791641757545', '2022-01-10 15:37:47', NULL, NULL, '33cf5e25629d9f57d9d74276f1e6594e');
INSERT INTO `t_attachment` VALUES ('20220110160052334038885788', '20210519110309706064758363', NULL, NULL, 0, 'asdgafgh.png', '/attachmentFile/2022-01-10T160052.326asdgafgh.png', '.png', 10, NULL, 0, 1, '20210521180451455507632824', '2022-01-10 16:00:52', NULL, NULL, '47919b52597b3dc58a4a91c108a9085c');
INSERT INTO `t_attachment` VALUES ('20220110160114692686980236', '20210519110309706064758363', NULL, NULL, 0, '522192dd-8420-4f4e-a2fa-b3437ad376f2.png', '/attachmentFile/2022-01-10T160114.679522192dd-8420-4f4e-a2fa-b3437ad376f2.png', '.png', 473, NULL, 0, 1, '20210521180451455507632824', '2022-01-10 16:01:15', NULL, NULL, 'd3c76ec8eb0e1d606eb2232dd7de4793');
INSERT INTO `t_attachment` VALUES ('20220110160117272775506798', '20210519110309706064758363', NULL, NULL, 0, '522192dd-8420-4f4e-a2fa-b3437ad376f2.png', '/attachmentFile/2022-01-10T160117.267522192dd-8420-4f4e-a2fa-b3437ad376f2.png', '.png', 473, NULL, 0, 1, '20210521180451455507632824', '2022-01-10 16:01:17', NULL, NULL, 'd3c76ec8eb0e1d606eb2232dd7de4793');
INSERT INTO `t_attachment` VALUES ('20220110160306273198962193', '20210519110309706064758363', NULL, NULL, 0, '522192dd-8420-4f4e-a2fa-b3437ad376f2.png', '/attachmentFile/2022-01-10T160306.258522192dd-8420-4f4e-a2fa-b3437ad376f2.png', '.png', 454, NULL, 0, 1, '20210521180451455507632824', '2022-01-10 16:03:06', NULL, NULL, 'bce080bcae98ce7bcaaf640b50dd0366');
INSERT INTO `t_attachment` VALUES ('20220110160345894889755675', '20210519110309706064758363', NULL, NULL, 0, '2998389497849250011.png', '/attachmentFile/2022-01-10T160345.8792998389497849250011.png', '.png', 31, NULL, 0, 1, '20210521180451455507632824', '2022-01-10 16:03:46', NULL, NULL, 'f8052a159ac8cf199a27817c4ace8263');
INSERT INTO `t_attachment` VALUES ('20220110160454807972783922', '20210519110309706064758363', NULL, NULL, 0, '6361646422558284324.png', '/attachmentFile/2022-01-10T160454.7966361646422558284324.png', '.png', 41, NULL, 0, 1, '20210521180451455507632824', '2022-01-10 16:04:55', NULL, NULL, '0f7f4a9dae56ff9089175d77a92d37a3');
INSERT INTO `t_attachment` VALUES ('20220110164037743406707483', '202105171503402497159', NULL, NULL, 0, 'jinji.png', '/attachmentFile/2022-01-10T164037.730jinji.png', '.png', 192, NULL, 0, 1, '20211231094738022333952742', '2022-01-10 16:40:38', NULL, NULL, '42037cb8a6eb8c70d677e64012a8a044');
INSERT INTO `t_attachment` VALUES ('20220110164247660993973712', '202105171503402497159', NULL, NULL, 0, 'else.png', '/attachmentFile/2022-01-10T164247.620else.png', '.png', 247, NULL, 0, 1, '20211231094738022333952742', '2022-01-10 16:42:48', NULL, NULL, '4d439ec2214251be7b74c2d75b338530');
INSERT INTO `t_attachment` VALUES ('20220110164420413891993202', '202105171503402497159', NULL, NULL, 0, 'mmexport1641374859695.jpg', '/attachmentFile/2022-01-10T164420.398mmexport1641374859695.jpg', '.jpg', 148, NULL, 0, 1, '20211231094738022333952742', '2022-01-10 16:44:20', NULL, NULL, '615a0eb534f7111d87f3f2062a43b1f5');
INSERT INTO `t_attachment` VALUES ('20220110164617834276293163', '202105171503402497159', NULL, NULL, 0, 'mmexport1641374859695.jpg', '/attachmentFile/2022-01-10T164617.831mmexport1641374859695.jpg', '.jpg', 148, NULL, 0, 1, '20211231094738022333952742', '2022-01-10 16:46:18', NULL, NULL, '615a0eb534f7111d87f3f2062a43b1f5');
INSERT INTO `t_attachment` VALUES ('20220110165105507399185384', NULL, NULL, NULL, 0, 'C5C78BD4-20D5-4842-A56B-136760BF9CF9.jpeg', '/attachmentFile/2022-01-10T165105.504C5C78BD4-20D5-4842-A56B-136760BF9CF9.jpeg', '.jpeg', 137, NULL, 0, 1, '20220110163739394089049276', '2022-01-10 16:51:06', NULL, NULL, '8d270576da411710a61e2deed2ddbc45');
INSERT INTO `t_attachment` VALUES ('20220110173224045850163496', NULL, NULL, NULL, 0, 'D16D6DAF-04A2-4779-BD5C-013D2C779112.jpeg', '/attachmentFile/2022-01-10T173224.030D16D6DAF-04A2-4779-BD5C-013D2C779112.jpeg', '.jpeg', 137, NULL, 0, 1, '20220110163739394089049276', '2022-01-10 17:32:24', NULL, NULL, '8d270576da411710a61e2deed2ddbc45');
INSERT INTO `t_attachment` VALUES ('20220110174857437568804182', NULL, NULL, NULL, 0, 'mmexport1641374859695.jpg', '/attachmentFile/2022-01-10T174857.422mmexport1641374859695.jpg', '.jpg', 148, NULL, 0, 1, '20220110164853203582598442', '2022-01-10 17:48:57', NULL, NULL, '615a0eb534f7111d87f3f2062a43b1f5');
INSERT INTO `t_attachment` VALUES ('20220110175209527062334912', NULL, NULL, NULL, 0, 'mmexport1641374859695.jpg', '/attachmentFile/2022-01-10T175209.523mmexport1641374859695.jpg', '.jpg', 148, NULL, 0, 1, '20220110164853203582598442', '2022-01-10 17:52:10', NULL, NULL, '615a0eb534f7111d87f3f2062a43b1f5');
INSERT INTO `t_attachment` VALUES ('20220110175847252670788905', NULL, NULL, NULL, 0, 'mmexport1641374859695.jpg', '/attachmentFile/2022-01-10T175847.210mmexport1641374859695.jpg', '.jpg', 148, NULL, 0, 1, '20220110175431589407731047', '2022-01-10 17:58:47', NULL, NULL, '615a0eb534f7111d87f3f2062a43b1f5');
INSERT INTO `t_attachment` VALUES ('20220112154625949121825830', NULL, NULL, NULL, 0, 'asdgafgh.png', '/attachmentFile/2022-01-12T154625.945asdgafgh.png', '.png', 10, NULL, 0, 1, '20220110163739394089049276', '2022-01-12 15:46:26', NULL, NULL, '47919b52597b3dc58a4a91c108a9085c');
INSERT INTO `t_attachment` VALUES ('20220112154631850003277162', NULL, NULL, NULL, 0, 'sdfdagf.jpg', '/attachmentFile/2022-01-12T154631.847sdfdagf.jpg', '.jpg', 141, NULL, 0, 1, '20220110163739394089049276', '2022-01-12 15:46:32', NULL, NULL, 'a6a54074ef78cf4ba4262606f652059c');
INSERT INTO `t_attachment` VALUES ('20220112154636407929113758', NULL, NULL, NULL, 0, '522192dd-8420-4f4e-a2fa-b3437ad376f2.png', '/attachmentFile/2022-01-12T154636.403522192dd-8420-4f4e-a2fa-b3437ad376f2.png', '.png', 473, NULL, 0, 1, '20220110163739394089049276', '2022-01-12 15:46:36', NULL, NULL, 'd3c76ec8eb0e1d606eb2232dd7de4793');
INSERT INTO `t_attachment` VALUES ('20220112154852639544481813', NULL, NULL, NULL, 0, '2998389497849250011.png', '/attachmentFile/2022-01-12T154852.6362998389497849250011.png', '.png', 31, NULL, 0, 1, '20220110163739394089049276', '2022-01-12 15:48:53', NULL, NULL, 'f8052a159ac8cf199a27817c4ace8263');
INSERT INTO `t_attachment` VALUES ('20220112185558894361251841', '202105171503402497159', NULL, NULL, 0, '490911444781668186.png', '/attachmentFile/2022-01-12T185558.888490911444781668186.png', '.png', 10, NULL, 0, 1, 'u_10001', '2022-01-12 18:55:59', NULL, NULL, 'e23fa412b38baf40fe2fe8c66920d3f0');
INSERT INTO `t_attachment` VALUES ('20220112185607014192006633', '202105171503402497159', NULL, NULL, 0, 'asfdsg.jpg', '/attachmentFile/2022-01-12T185607.010asfdsg.jpg', '.jpg', 142, NULL, 0, 1, 'u_10001', '2022-01-12 18:56:07', NULL, NULL, '753a00d3d0a419411a3ef6c06dfe0592');
INSERT INTO `t_attachment` VALUES ('20220112185614080405316363', '202105171503402497159', NULL, NULL, 0, 'asfdsg.jpg', '/attachmentFile/2022-01-12T185614.076asfdsg.jpg', '.jpg', 142, NULL, 0, 1, 'u_10001', '2022-01-12 18:56:14', NULL, NULL, '753a00d3d0a419411a3ef6c06dfe0592');
INSERT INTO `t_attachment` VALUES ('20220112185621600797192203', '202105171503402497159', NULL, NULL, 0, 'asfdsg.jpg', '/attachmentFile/2022-01-12T185621.597asfdsg.jpg', '.jpg', 142, NULL, 0, 1, 'u_10001', '2022-01-12 18:56:22', NULL, NULL, '753a00d3d0a419411a3ef6c06dfe0592');
INSERT INTO `t_attachment` VALUES ('20220112185632521431166408', '202105171503402497159', NULL, NULL, 0, 'agfhhgh.jpg', '/attachmentFile/2022-01-12T185632.518agfhhgh.jpg', '.jpg', 36, NULL, 0, 1, 'u_10001', '2022-01-12 18:56:33', NULL, NULL, '18f261952b7afcd410c8387876dd2f0f');
INSERT INTO `t_attachment` VALUES ('20220112185737873558876481', '202105171503402497159', NULL, NULL, 0, '3454604398887363063.png', '/attachmentFile/2022-01-12T185737.8703454604398887363063.png', '.png', 12, NULL, 0, 1, 'u_10001', '2022-01-12 18:57:38', NULL, NULL, '42af805b56747b16837f99720ea4c4ad');
INSERT INTO `t_attachment` VALUES ('20220113093551320991506690', NULL, NULL, NULL, 0, 'FDF99793-F7D0-4AAF-A36D-1F32067A6081.jpeg', '/attachmentFile/2022-01-13T093551.317FDF99793-F7D0-4AAF-A36D-1F32067A6081.jpeg', '.jpeg', 137, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 09:35:51', NULL, NULL, '8d270576da411710a61e2deed2ddbc45');
INSERT INTO `t_attachment` VALUES ('20220113101804654164110440', NULL, NULL, NULL, 0, '下载.png', '/attachmentFile/2022-01-13T101804.647下载.png', '.png', 0, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 10:18:05', NULL, NULL, '6604b68c06d6ac57377ed124468983c5');
INSERT INTO `t_attachment` VALUES ('20220113101815224639712900', NULL, NULL, NULL, 0, 'sdfdagf.jpg', '/attachmentFile/2022-01-13T101815.218sdfdagf.jpg', '.jpg', 141, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 10:18:15', NULL, NULL, 'a6a54074ef78cf4ba4262606f652059c');
INSERT INTO `t_attachment` VALUES ('20220113101818470304787850', NULL, NULL, NULL, 0, 'asfdgfh.jpg', '/attachmentFile/2022-01-13T101818.466asfdgfh.jpg', '.jpg', 151, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 10:18:18', NULL, NULL, '581153d48ad6e9d9aee2b70cc644399f');
INSERT INTO `t_attachment` VALUES ('20220113101937588442954826', NULL, NULL, NULL, 0, '460167712113713905.jpg', '/attachmentFile/2022-01-13T101937.581460167712113713905.jpg', '.jpg', 13, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 10:19:38', NULL, NULL, '33cf5e25629d9f57d9d74276f1e6594e');
INSERT INTO `t_attachment` VALUES ('20220113101944939125813629', NULL, NULL, NULL, 0, '460167712113713905.jpg', '/attachmentFile/2022-01-13T101944.935460167712113713905.jpg', '.jpg', 13, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 10:19:45', NULL, NULL, '33cf5e25629d9f57d9d74276f1e6594e');
INSERT INTO `t_attachment` VALUES ('20220113102025996627468714', NULL, NULL, NULL, 0, '1863263384755765895.jpg', '/attachmentFile/2022-01-13T102025.9901863263384755765895.jpg', '.jpg', 15, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 10:20:26', NULL, NULL, 'b1ccbe52f074099d8ca46101dac8b2f1');
INSERT INTO `t_attachment` VALUES ('20220113102121928937482558', NULL, NULL, NULL, 0, 'fadgadg.png', '/attachmentFile/2022-01-13T102121.924fadgadg.png', '.png', 0, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 10:21:22', NULL, NULL, '6604b68c06d6ac57377ed124468983c5');
INSERT INTO `t_attachment` VALUES ('20220113103024230559314563', NULL, NULL, NULL, 0, 'mmexport1641374859695.jpg', '/attachmentFile/2022-01-13T103024.225mmexport1641374859695.jpg', '.jpg', 148, NULL, 0, 1, '20220110175431589407731047', '2022-01-13 10:30:24', NULL, NULL, '615a0eb534f7111d87f3f2062a43b1f5');
INSERT INTO `t_attachment` VALUES ('20220113105040863497427305', '202105171503402497159', NULL, NULL, 0, '937098428700335159.png', '/attachmentFile/2022-01-13T105040.836937098428700335159.png', '.png', 15, NULL, 0, 1, 'u_10001', '2022-01-13 10:50:41', NULL, NULL, '29db5ef026ddd427f89c0599b4ac7117');
INSERT INTO `t_attachment` VALUES ('20220113105354475016176068', '202105171503402497159', NULL, NULL, 0, 'asdgafgh.png', '/attachmentFile/2022-01-13T105354.472asdgafgh.png', '.png', 10, NULL, 0, 1, 'u_10001', '2022-01-13 10:53:54', NULL, NULL, '47919b52597b3dc58a4a91c108a9085c');
INSERT INTO `t_attachment` VALUES ('20220113105359529178324056', '202105171503402497159', NULL, NULL, 0, 'aghfjhfjhn.jpg', '/attachmentFile/2022-01-13T105359.524aghfjhfjhn.jpg', '.jpg', 404, NULL, 0, 1, 'u_10001', '2022-01-13 10:54:00', NULL, NULL, '807dd44d69e359a6ddc4044f4269e597');
INSERT INTO `t_attachment` VALUES ('20220113105402803119662957', '202105171503402497159', NULL, NULL, 0, '522192dd-8420-4f4e-a2fa-b3437ad376f2.png', '/attachmentFile/2022-01-13T105402.801522192dd-8420-4f4e-a2fa-b3437ad376f2.png', '.png', 473, NULL, 0, 1, 'u_10001', '2022-01-13 10:54:03', NULL, NULL, 'd3c76ec8eb0e1d606eb2232dd7de4793');
INSERT INTO `t_attachment` VALUES ('20220113105448919743235180', '202105171503402497159', NULL, NULL, 0, '6361646422558284324.png', '/attachmentFile/2022-01-13T105448.9176361646422558284324.png', '.png', 41, NULL, 0, 1, 'u_10001', '2022-01-13 10:54:49', NULL, NULL, '0f7f4a9dae56ff9089175d77a92d37a3');
INSERT INTO `t_attachment` VALUES ('20220113114449500241038291', NULL, NULL, NULL, 0, '4928585161386869689.jpg', '/attachmentFile/2022-01-13T114449.4944928585161386869689.jpg', '.jpg', 14, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 11:44:50', NULL, NULL, '3c91548bd77c49781e65a7492226ba3c');
INSERT INTO `t_attachment` VALUES ('20220113114904336736415029', NULL, NULL, NULL, 0, '4928585161386869689.jpg', '/attachmentFile/2022-01-13T114904.3324928585161386869689.jpg', '.jpg', 14, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 11:49:04', NULL, NULL, '3c91548bd77c49781e65a7492226ba3c');
INSERT INTO `t_attachment` VALUES ('20220113130406821892882495', '202105171503402497159', NULL, NULL, 0, '2998389497849250011.png', '/attachmentFile/2022-01-13T130406.7942998389497849250011.png', '.png', 31, NULL, 0, 1, 'u_10001', '2022-01-13 13:04:07', NULL, NULL, 'f8052a159ac8cf199a27817c4ace8263');
INSERT INTO `t_attachment` VALUES ('20220113142444066930198879', '202105171503402497159', NULL, NULL, 0, '758_P_1477333565272.jpg', '/attachmentFile/2022-01-13T142444.037758_P_1477333565272.jpg', '.jpg', 93, NULL, 0, 1, 'u_10001', '2022-01-13 14:24:44', NULL, NULL, '06a05dbebf9f5082c9dcc9dbac8ebeaf');
INSERT INTO `t_attachment` VALUES ('20220113145355028176270979', NULL, NULL, NULL, 0, 'F720016B-8A88-4158-847D-3F2C9F3D1D25.jpeg', '/attachmentFile/2022-01-13T145355.015F720016B-8A88-4158-847D-3F2C9F3D1D25.jpeg', '.jpeg', 137, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 14:53:55', NULL, NULL, '8d270576da411710a61e2deed2ddbc45');
INSERT INTO `t_attachment` VALUES ('20220113151419189164187024', '202105171503402497159', NULL, NULL, 0, '490911444781668186.png', '/attachmentFile/2022-01-13T151419.186490911444781668186.png', '.png', 10, NULL, 0, 1, 'u_10001', '2022-01-13 15:14:19', NULL, NULL, 'e23fa412b38baf40fe2fe8c66920d3f0');
INSERT INTO `t_attachment` VALUES ('20220113151427191839907059', '202105171503402497159', NULL, NULL, 0, 'asfdgafd.jpg', '/attachmentFile/2022-01-13T151427.189asfdgafd.jpg', '.jpg', 36, NULL, 0, 1, 'u_10001', '2022-01-13 15:14:27', NULL, NULL, 'b3701c9b22018ec3568665425a37f12e');
INSERT INTO `t_attachment` VALUES ('20220113151434799061793833', '202105171503402497159', NULL, NULL, 0, 'asfdsg.jpg', '/attachmentFile/2022-01-13T151434.797asfdsg.jpg', '.jpg', 142, NULL, 0, 1, 'u_10001', '2022-01-13 15:14:35', NULL, NULL, '753a00d3d0a419411a3ef6c06dfe0592');
INSERT INTO `t_attachment` VALUES ('20220113151546185400808404', '202105171503402497159', NULL, NULL, 0, '490911444781668186.png', '/attachmentFile/2022-01-13T151546.183490911444781668186.png', '.png', 10, NULL, 0, 1, 'u_10001', '2022-01-13 15:15:46', NULL, NULL, 'e23fa412b38baf40fe2fe8c66920d3f0');
INSERT INTO `t_attachment` VALUES ('20220113151551507901085484', '202105171503402497159', NULL, NULL, 0, 'asfdgafd.jpg', '/attachmentFile/2022-01-13T151551.505asfdgafd.jpg', '.jpg', 36, NULL, 0, 1, 'u_10001', '2022-01-13 15:15:52', NULL, NULL, 'b3701c9b22018ec3568665425a37f12e');
INSERT INTO `t_attachment` VALUES ('20220113151558202456859482', '202105171503402497159', NULL, NULL, 0, 'asfdgafd.jpg', '/attachmentFile/2022-01-13T151558.198asfdgafd.jpg', '.jpg', 36, NULL, 0, 1, 'u_10001', '2022-01-13 15:15:58', NULL, NULL, 'b3701c9b22018ec3568665425a37f12e');
INSERT INTO `t_attachment` VALUES ('20220113151725605613560262', '202105171503402497159', NULL, NULL, 0, 'asfdsg.jpg', '/attachmentFile/2022-01-13T151725.603asfdsg.jpg', '.jpg', 142, NULL, 0, 1, 'u_10001', '2022-01-13 15:17:26', NULL, NULL, '753a00d3d0a419411a3ef6c06dfe0592');
INSERT INTO `t_attachment` VALUES ('20220113151827061114009627', '202105171503402497159', NULL, NULL, 0, '1750284933933999859.png', '/attachmentFile/2022-01-13T151827.0591750284933933999859.png', '.png', 14, NULL, 0, 1, 'u_10001', '2022-01-13 15:18:27', NULL, NULL, '453b946585bb081f8db27d0153a77f88');
INSERT INTO `t_attachment` VALUES ('20220113152805595253697149', NULL, NULL, NULL, 0, 'mmexport1641374859695.jpg', '/attachmentFile/2022-01-13T152805.592mmexport1641374859695.jpg', '.jpg', 148, NULL, 0, 1, '20220113152745730601337038', '2022-01-13 15:28:06', NULL, NULL, '615a0eb534f7111d87f3f2062a43b1f5');
INSERT INTO `t_attachment` VALUES ('20220113154435536734080509', NULL, NULL, NULL, 0, '781F3897-74FA-42DE-B081-99A873061866.jpeg', '/attachmentFile/2022-01-13T154435.527781F3897-74FA-42DE-B081-99A873061866.jpeg', '.jpeg', 137, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 15:44:36', NULL, NULL, '8d270576da411710a61e2deed2ddbc45');
INSERT INTO `t_attachment` VALUES ('20220113154721786010519497', '202105171503402497159', NULL, NULL, 0, '3454604398887363063.png', '/attachmentFile/2022-01-13T154721.7833454604398887363063.png', '.png', 12, NULL, 0, 1, 'u_10001', '2022-01-13 15:47:22', NULL, NULL, '42af805b56747b16837f99720ea4c4ad');
INSERT INTO `t_attachment` VALUES ('20220113154957288371824559', '202105171503402497159', NULL, NULL, 0, '3454604398887363063.png', '/attachmentFile/2022-01-13T154957.2833454604398887363063.png', '.png', 12, NULL, 0, 1, 'u_10001', '2022-01-13 15:49:57', NULL, NULL, '42af805b56747b16837f99720ea4c4ad');
INSERT INTO `t_attachment` VALUES ('20220113155202953269385548', '202105171503402497159', NULL, NULL, 0, '3454604398887363063.png', '/attachmentFile/2022-01-13T155202.9513454604398887363063.png', '.png', 12, NULL, 0, 1, 'u_10001', '2022-01-13 15:52:03', NULL, NULL, '42af805b56747b16837f99720ea4c4ad');
INSERT INTO `t_attachment` VALUES ('20220113161310860231367636', '202105171503402497159', NULL, NULL, 0, '937098428700335159.png', '/attachmentFile/2022-01-13T161310.857937098428700335159.png', '.png', 15, NULL, 0, 1, 'u_10001', '2022-01-13 16:13:11', NULL, NULL, '29db5ef026ddd427f89c0599b4ac7117');
INSERT INTO `t_attachment` VALUES ('20220113161518272171843173', '202105171503402497159', NULL, NULL, 0, '8987762840678959407.png', '/attachmentFile/2022-01-13T161518.2698987762840678959407.png', '.png', 12, NULL, 0, 1, 'u_10001', '2022-01-13 16:15:18', NULL, NULL, '46a642b7f2a481bbbcf2cc055bcaa779');
INSERT INTO `t_attachment` VALUES ('20220113161531071795810615', '202105171503402497159', NULL, NULL, 0, 'al-1.jpg', '/attachmentFile/2022-01-13T161531.069al-1.jpg', '.jpg', 118, NULL, 0, 1, 'u_10001', '2022-01-13 16:15:31', NULL, NULL, '73c19454e90201f9d85a7d67a5aff547');
INSERT INTO `t_attachment` VALUES ('20220113161635192621801952', '202105171503402497159', NULL, NULL, 0, 'al-5.jpg', '/attachmentFile/2022-01-13T161635.190al-5.jpg', '.jpg', 182, NULL, 0, 1, 'u_10001', '2022-01-13 16:16:35', NULL, NULL, 'fecf55a2295a94ad981789144256ab80');
INSERT INTO `t_attachment` VALUES ('20220113161745149099471023', '202105171503402497159', NULL, NULL, 0, 'al-5.jpg', '/attachmentFile/2022-01-13T161745.146al-5.jpg', '.jpg', 182, NULL, 0, 1, 'u_10001', '2022-01-13 16:17:45', NULL, NULL, 'fecf55a2295a94ad981789144256ab80');
INSERT INTO `t_attachment` VALUES ('20220113161929861080952603', NULL, NULL, NULL, 0, 'asdgafgh.png', '/attachmentFile/2022-01-13T161929.858asdgafgh.png', '.png', 10, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 16:19:30', NULL, NULL, '47919b52597b3dc58a4a91c108a9085c');
INSERT INTO `t_attachment` VALUES ('20220113161934484164042893', NULL, NULL, NULL, 0, '522192dd-8420-4f4e-a2fa-b3437ad376f2.png', '/attachmentFile/2022-01-13T161934.481522192dd-8420-4f4e-a2fa-b3437ad376f2.png', '.png', 473, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 16:19:34', NULL, NULL, 'd3c76ec8eb0e1d606eb2232dd7de4793');
INSERT INTO `t_attachment` VALUES ('20220113161948328600484820', NULL, NULL, NULL, 0, 'aghfjhfjhn.jpg', '/attachmentFile/2022-01-13T161948.323aghfjhfjhn.jpg', '.jpg', 404, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 16:19:48', NULL, NULL, '807dd44d69e359a6ddc4044f4269e597');
INSERT INTO `t_attachment` VALUES ('20220113161950708602831683', NULL, NULL, NULL, 0, '522192dd-8420-4f4e-a2fa-b3437ad376f2.png', '/attachmentFile/2022-01-13T161950.705522192dd-8420-4f4e-a2fa-b3437ad376f2.png', '.png', 473, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 16:19:51', NULL, NULL, 'd3c76ec8eb0e1d606eb2232dd7de4793');
INSERT INTO `t_attachment` VALUES ('20220113161951567174596374', '202105171503402497159', NULL, NULL, 0, 'al-5-1.png', '/attachmentFile/2022-01-13T161951.564al-5-1.png', '.png', 630, NULL, 0, 1, 'u_10001', '2022-01-13 16:19:52', NULL, NULL, '19d05f8cced04396e91e3698810ef11b');
INSERT INTO `t_attachment` VALUES ('20220113162038059827110500', NULL, NULL, NULL, 0, '6361646422558284324.png', '/attachmentFile/2022-01-13T162038.0566361646422558284324.png', '.png', 41, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 16:20:38', NULL, NULL, '0f7f4a9dae56ff9089175d77a92d37a3');
INSERT INTO `t_attachment` VALUES ('20220113162052759155885645', '202105171503402497159', NULL, NULL, 0, 'al-5-1.png', '/attachmentFile/2022-01-13T162052.756al-5-1.png', '.png', 630, NULL, 0, 1, 'u_10001', '2022-01-13 16:20:53', NULL, NULL, '19d05f8cced04396e91e3698810ef11b');
INSERT INTO `t_attachment` VALUES ('20220113162119375731415579', '202105171503402497159', NULL, NULL, 0, 'al-5-1.png', '/attachmentFile/2022-01-13T162119.304al-5-1.png', '.png', 630, NULL, 0, 1, 'u_10001', '2022-01-13 16:21:19', NULL, NULL, '19d05f8cced04396e91e3698810ef11b');
INSERT INTO `t_attachment` VALUES ('20220113162212317363302108', '202105171503402497159', NULL, NULL, 0, 'al-5-1.png', '/attachmentFile/2022-01-13T162212.314al-5-1.png', '.png', 630, NULL, 0, 1, 'u_10001', '2022-01-13 16:22:12', NULL, NULL, '19d05f8cced04396e91e3698810ef11b');
INSERT INTO `t_attachment` VALUES ('20220113162253744757467398', '202105171503402497159', NULL, NULL, 0, 'al-5.jpg', '/attachmentFile/2022-01-13T162253.742al-5.jpg', '.jpg', 182, NULL, 0, 1, 'u_10001', '2022-01-13 16:22:54', NULL, NULL, 'fecf55a2295a94ad981789144256ab80');
INSERT INTO `t_attachment` VALUES ('20220113162337922735379876', '202105171503402497159', NULL, NULL, 0, 'banner.jpg', '/attachmentFile/2022-01-13T162337.919banner.jpg', '.jpg', 312, NULL, 0, 1, 'u_10001', '2022-01-13 16:23:38', NULL, NULL, 'e6f5ee0fcb1a40cb8260d4973be538ec');
INSERT INTO `t_attachment` VALUES ('20220113162418476396549435', '202105171503402497159', NULL, NULL, 0, '1-bg.jpg', '/attachmentFile/2022-01-13T162418.4731-bg.jpg', '.jpg', 956, NULL, 0, 1, 'u_10001', '2022-01-13 16:24:18', NULL, NULL, '49c0d75d3bb7d4782be6e6e6b652603b');
INSERT INTO `t_attachment` VALUES ('20220113162453189509369745', '202105171503402497159', NULL, NULL, 0, 'banner.jpg', '/attachmentFile/2022-01-13T162453.187banner.jpg', '.jpg', 312, NULL, 0, 1, 'u_10001', '2022-01-13 16:24:53', NULL, NULL, 'e6f5ee0fcb1a40cb8260d4973be538ec');
INSERT INTO `t_attachment` VALUES ('20220113162533596027561240', '202105171503402497159', NULL, NULL, 0, 'bao.jpg', '/attachmentFile/2022-01-13T162533.594bao.jpg', '.jpg', 13, NULL, 0, 1, 'u_10001', '2022-01-13 16:25:34', NULL, NULL, '2840513b655f6499fe465a2624d324f3');
INSERT INTO `t_attachment` VALUES ('20220113162549498210358017', '202105171503402497159', NULL, NULL, 0, 'cpjs4.png', '/attachmentFile/2022-01-13T162549.496cpjs4.png', '.png', 3, NULL, 0, 1, 'u_10001', '2022-01-13 16:25:49', NULL, NULL, 'f5478f4a0ff3959bbe4eb840bac1035e');
INSERT INTO `t_attachment` VALUES ('20220113162632692187806675', '202105171503402497159', NULL, NULL, 0, 'cpjs1.png', '/attachmentFile/2022-01-13T162632.690cpjs1.png', '.png', 1, NULL, 0, 1, 'u_10001', '2022-01-13 16:26:33', NULL, NULL, '8ea3571133a456b904a5394e29bd8236');
INSERT INTO `t_attachment` VALUES ('20220113162709950478103738', '202105171503402497159', NULL, NULL, 0, 'cpjs3.png', '/attachmentFile/2022-01-13T162709.949cpjs3.png', '.png', 1, NULL, 0, 1, 'u_10001', '2022-01-13 16:27:10', NULL, NULL, '19e847c2610e481551b7afcdb5685ee5');
INSERT INTO `t_attachment` VALUES ('20220113162740513157322072', '202105171503402497159', NULL, NULL, 0, 'cpjs1.png', '/attachmentFile/2022-01-13T162740.511cpjs1.png', '.png', 1, NULL, 0, 1, 'u_10001', '2022-01-13 16:27:41', NULL, NULL, '8ea3571133a456b904a5394e29bd8236');
INSERT INTO `t_attachment` VALUES ('20220113162825409376793214', '202105171503402497159', NULL, NULL, 0, 'cpjs2.png', '/attachmentFile/2022-01-13T162825.406cpjs2.png', '.png', 2, NULL, 0, 1, 'u_10001', '2022-01-13 16:28:25', NULL, NULL, '36a2976c8bde427d9beae608b3ea3a9f');
INSERT INTO `t_attachment` VALUES ('20220113162837027306644104', NULL, NULL, NULL, 0, '6361646422558284324.png', '/attachmentFile/2022-01-13T162837.0246361646422558284324.png', '.png', 41, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 16:28:37', NULL, NULL, '0f7f4a9dae56ff9089175d77a92d37a3');
INSERT INTO `t_attachment` VALUES ('20220113162845911343858765', '202105171503402497159', NULL, NULL, 0, 'cpjs3.png', '/attachmentFile/2022-01-13T162845.909cpjs3.png', '.png', 1, NULL, 0, 1, 'u_10001', '2022-01-13 16:28:46', NULL, NULL, '19e847c2610e481551b7afcdb5685ee5');
INSERT INTO `t_attachment` VALUES ('20220113162922278774674285', '202105171503402497159', NULL, NULL, 0, 'cpjs2.png', '/attachmentFile/2022-01-13T162922.276cpjs2.png', '.png', 2, NULL, 0, 1, 'u_10001', '2022-01-13 16:29:22', NULL, NULL, '36a2976c8bde427d9beae608b3ea3a9f');
INSERT INTO `t_attachment` VALUES ('20220113162945973881930865', '202105171503402497159', NULL, NULL, 0, 'cpjs2.png', '/attachmentFile/2022-01-13T162945.971cpjs2.png', '.png', 2, NULL, 0, 1, 'u_10001', '2022-01-13 16:29:46', NULL, NULL, '36a2976c8bde427d9beae608b3ea3a9f');
INSERT INTO `t_attachment` VALUES ('20220113163014199469201151', '202105171503402497159', NULL, NULL, 0, 'cpjs4.png', '/attachmentFile/2022-01-13T163014.196cpjs4.png', '.png', 3, NULL, 0, 1, 'u_10001', '2022-01-13 16:30:14', NULL, NULL, 'f5478f4a0ff3959bbe4eb840bac1035e');
INSERT INTO `t_attachment` VALUES ('20220113163035050784027974', '202105171503402497159', NULL, NULL, 0, 'cpjs2.png', '/attachmentFile/2022-01-13T163035.048cpjs2.png', '.png', 2, NULL, 0, 1, 'u_10001', '2022-01-13 16:30:35', NULL, NULL, '36a2976c8bde427d9beae608b3ea3a9f');
INSERT INTO `t_attachment` VALUES ('20220113163122050750768987', '202105171503402497159', NULL, NULL, 0, 'cpjs3.png', '/attachmentFile/2022-01-13T163122.047cpjs3.png', '.png', 1, NULL, 0, 1, 'u_10001', '2022-01-13 16:31:22', NULL, NULL, '19e847c2610e481551b7afcdb5685ee5');
INSERT INTO `t_attachment` VALUES ('20220113163142980577858743', '202105171503402497159', NULL, NULL, 0, 'cpjs3.png', '/attachmentFile/2022-01-13T163142.977cpjs3.png', '.png', 1, NULL, 0, 1, 'u_10001', '2022-01-13 16:31:43', NULL, NULL, '19e847c2610e481551b7afcdb5685ee5');
INSERT INTO `t_attachment` VALUES ('20220113164045808152391880', '202105171503402497159', NULL, NULL, 0, 'cpjs3.png', '/attachmentFile/2022-01-13T164045.805cpjs3.png', '.png', 1, NULL, 0, 1, 'u_10001', '2022-01-13 16:40:46', NULL, NULL, '19e847c2610e481551b7afcdb5685ee5');
INSERT INTO `t_attachment` VALUES ('20220113164236462553466918', NULL, NULL, NULL, 0, '中文.png', '/attachmentFile/2022-01-13T164236.452fab150af-8693-4d6e-8af5-c4513642b90a.png', '.png', 0, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 16:42:36', NULL, NULL, '6604b68c06d6ac57377ed124468983c5');
INSERT INTO `t_attachment` VALUES ('20220113164312171648615147', NULL, NULL, NULL, 0, 'asdgafgh.png', '/attachmentFile/2022-01-13T164312.168a0d6a596-446b-4046-8e06-920ce713aa66.png', '.png', 10, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 16:43:12', NULL, NULL, '47919b52597b3dc58a4a91c108a9085c');
INSERT INTO `t_attachment` VALUES ('20220113165122614351794818', NULL, NULL, NULL, 0, '6361646422558284324.png', '/attachmentFile/2022-01-13T165122.611d88a1a7b-6e8b-47b0-84e1-d7aea4ef0f5b.png', '.png', 41, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 16:51:23', NULL, NULL, '0f7f4a9dae56ff9089175d77a92d37a3');
INSERT INTO `t_attachment` VALUES ('20220113170041090909045099', NULL, NULL, NULL, 0, '6361646422558284324.png', '/attachmentFile/2022-01-13T170041.087b351ab6e-9745-490d-9b9d-0e868fadff47.png', '.png', 41, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 17:00:41', NULL, NULL, '0f7f4a9dae56ff9089175d77a92d37a3');
INSERT INTO `t_attachment` VALUES ('20220113173420603420501336', NULL, NULL, NULL, 0, 'Screenshot_20220111_145231_com.Sixman.jpg', '/attachmentFile/2022-01-13T173420.59933129675-05b8-48fd-a241-92d568c498f6.jpg', '.jpg', 615, NULL, 0, 1, '20220113173245029522827454', '2022-01-13 17:34:21', NULL, NULL, '5453c11a8e8671b2aa9b0bf6dcd92d0a');
INSERT INTO `t_attachment` VALUES ('20220113180341064622524754', NULL, NULL, NULL, 0, '24350D7F-6EC4-413D-AFA4-297AAEEFDA52.jpeg', '/attachmentFile/2022-01-13T180341.01815d2a2ba-d3de-4d6f-bcf3-6a4d2b272710.jpeg', '.jpeg', 137, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 18:03:41', NULL, NULL, '8d270576da411710a61e2deed2ddbc45');
INSERT INTO `t_attachment` VALUES ('20220113181710876362325636', '202105171503402497159', NULL, NULL, 0, '490911444781668186.png', '/attachmentFile/2022-01-13T181710.8715c324e74-86f5-47c0-bd1c-8270827d5901.png', '.png', 10, NULL, 0, 1, 'u_10001', '2022-01-13 18:17:11', NULL, NULL, 'e23fa412b38baf40fe2fe8c66920d3f0');
INSERT INTO `t_attachment` VALUES ('20220113181715203476629102', '202105171503402497159', NULL, NULL, 0, 'asfdgafd.jpg', '/attachmentFile/2022-01-13T181715.200304f36ab-06a6-4a64-bab7-6cd8fe7e9884.jpg', '.jpg', 36, NULL, 0, 1, 'u_10001', '2022-01-13 18:17:15', NULL, NULL, 'b3701c9b22018ec3568665425a37f12e');
INSERT INTO `t_attachment` VALUES ('20220113181720000529899307', '202105171503402497159', NULL, NULL, 0, 'asfdsg.jpg', '/attachmentFile/2022-01-13T181719.9965bef480c-80e2-4adf-add9-4feac8a2e745.jpg', '.jpg', 142, NULL, 0, 1, 'u_10001', '2022-01-13 18:17:20', NULL, NULL, '753a00d3d0a419411a3ef6c06dfe0592');
INSERT INTO `t_attachment` VALUES ('20220113181907823094862944', '202105171503402497159', NULL, NULL, 0, '490911444781668186.png', '/attachmentFile/2022-01-13T181907.82091d8cb5a-9484-4598-adac-77103a6a0260.png', '.png', 10, NULL, 0, 1, 'u_10001', '2022-01-13 18:19:08', NULL, NULL, 'e23fa412b38baf40fe2fe8c66920d3f0');
INSERT INTO `t_attachment` VALUES ('20220113181924202540161561', '202105171503402497159', NULL, NULL, 0, 'agfhhgh.jpg', '/attachmentFile/2022-01-13T181924.199c023acca-4587-49a0-89ee-4133bca6119d.jpg', '.jpg', 36, NULL, 0, 1, 'u_10001', '2022-01-13 18:19:24', NULL, NULL, '18f261952b7afcd410c8387876dd2f0f');
INSERT INTO `t_attachment` VALUES ('20220113181939384027209847', '202105171503402497159', NULL, NULL, 0, 'asfdsg.jpg', '/attachmentFile/2022-01-13T181939.3828ada476b-c758-4b54-92dc-133f5e38d9c3.jpg', '.jpg', 142, NULL, 0, 1, 'u_10001', '2022-01-13 18:19:39', NULL, NULL, '753a00d3d0a419411a3ef6c06dfe0592');
INSERT INTO `t_attachment` VALUES ('20220113182022381262292957', '202105171503402497159', NULL, NULL, 0, '1750284933933999859.png', '/attachmentFile/2022-01-13T182022.379921aafc6-9d43-43f8-962b-bed1b64e404b.png', '.png', 14, NULL, 0, 1, 'u_10001', '2022-01-13 18:20:22', NULL, NULL, '453b946585bb081f8db27d0153a77f88');
INSERT INTO `t_attachment` VALUES ('20220113182718084838277343', '202105171503402497159', NULL, NULL, 0, '8987762840678959407.png', '/attachmentFile/2022-01-13T182718.0815f965210-9853-47fa-9187-70282c52b55d.png', '.png', 12, NULL, 0, 1, 'u_10001', '2022-01-13 18:27:18', NULL, NULL, '46a642b7f2a481bbbcf2cc055bcaa779');
INSERT INTO `t_attachment` VALUES ('20220113182811227729751397', '202105171503402497159', NULL, NULL, 0, '937098428700335159.png', '/attachmentFile/2022-01-13T182811.2232896555d-714c-40d3-aa10-30d03df93ea5.png', '.png', 15, NULL, 0, 1, 'u_10001', '2022-01-13 18:28:11', NULL, NULL, '29db5ef026ddd427f89c0599b4ac7117');
INSERT INTO `t_attachment` VALUES ('20220113182901701003893374', NULL, NULL, NULL, 0, 'asdgafgh.png', '/attachmentFile/2022-01-13T182901.699b3a1255a-076a-4be4-b4cf-f55bde62c383.png', '.png', 10, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 18:29:02', NULL, NULL, '47919b52597b3dc58a4a91c108a9085c');
INSERT INTO `t_attachment` VALUES ('20220113182908324914734071', NULL, NULL, NULL, 0, 'agfhhgh.jpg', '/attachmentFile/2022-01-13T182908.32115c12150-3fcf-4648-b15f-bb5636e5e89f.jpg', '.jpg', 36, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 18:29:08', NULL, NULL, '18f261952b7afcd410c8387876dd2f0f');
INSERT INTO `t_attachment` VALUES ('20220113182911538654876313', NULL, NULL, NULL, 0, '522192dd-8420-4f4e-a2fa-b3437ad376f2.png', '/attachmentFile/2022-01-13T182911.535db5b9b12-4ab2-4d0a-8624-c7b36360e9aa.png', '.png', 473, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 18:29:12', NULL, NULL, 'd3c76ec8eb0e1d606eb2232dd7de4793');
INSERT INTO `t_attachment` VALUES ('20220113183000085122639400', NULL, NULL, NULL, 0, '2998389497849250011.png', '/attachmentFile/2022-01-13T183000.0820d477b7f-3403-451b-812d-3a51a99d42ed.png', '.png', 31, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 18:30:00', NULL, NULL, 'f8052a159ac8cf199a27817c4ace8263');
INSERT INTO `t_attachment` VALUES ('20220113183103227605931665', NULL, NULL, NULL, 0, '6361646422558284324.png', '/attachmentFile/2022-01-13T183103.224b39701ff-0f29-4ec3-b534-1d605dc227d5.png', '.png', 41, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 18:31:03', NULL, NULL, '0f7f4a9dae56ff9089175d77a92d37a3');
INSERT INTO `t_attachment` VALUES ('20220113183127880764233560', NULL, NULL, NULL, 0, 'aghfjhfjhn.jpg', '/attachmentFile/2022-01-13T183127.8772656274d-de4e-4d9c-8f94-a164c64617d4.jpg', '.jpg', 404, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 18:31:28', NULL, NULL, '807dd44d69e359a6ddc4044f4269e597');
INSERT INTO `t_attachment` VALUES ('20220113183200101756205850', NULL, NULL, NULL, 0, '6361646422558284324.png', '/attachmentFile/2022-01-13T183200.0999788303f-52f1-4db1-9168-2d7b8ade2e66.png', '.png', 41, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 18:32:00', NULL, NULL, '0f7f4a9dae56ff9089175d77a92d37a3');
INSERT INTO `t_attachment` VALUES ('20220113183312418450684993', NULL, NULL, NULL, 0, '5AA5C0D4-14A2-4FA5-8632-94A04BCE467E.jpeg', '/attachmentFile/2022-01-13T183312.4160873ef8e-2c82-4199-8839-129954611c3e.jpeg', '.jpeg', 137, NULL, 0, 1, '20220110163739394089049276', '2022-01-13 18:33:12', NULL, NULL, '8d270576da411710a61e2deed2ddbc45');
INSERT INTO `t_attachment` VALUES ('20220113183949465429794533', NULL, NULL, NULL, 0, 'editimage_1641995512829.jpg', '/attachmentFile/2022-01-13T183949.461f36b2b29-23ac-4117-9808-cb53635c33ce.jpg', '.jpg', 441, NULL, 0, 1, '20220113183913551915648542', '2022-01-13 18:39:49', NULL, NULL, 'd42ba9a25f68d79ac31515dc4d425402');
INSERT INTO `t_attachment` VALUES ('20220113190459799697508356', NULL, NULL, NULL, 0, 'Screenshot_20220111_145231_com.Sixman.jpg', '/attachmentFile/2022-01-13T190459.7944c9e008b-0a2d-4688-b8b9-3c6c01ba7775.jpg', '.jpg', 615, NULL, 0, 1, '20220113173245029522827454', '2022-01-13 19:05:00', NULL, NULL, '5453c11a8e8671b2aa9b0bf6dcd92d0a');
INSERT INTO `t_attachment` VALUES ('20220114101834690776234374', NULL, NULL, NULL, 0, '1E508BAE-7F0B-4EE6-B462-945FAC8E705E.jpeg', '/attachmentFile/2022-01-14T101834.660ba3a0903-6c21-4ad4-a872-a370cc590103.jpeg', '.jpeg', 137, NULL, 0, 1, '20220110163739394089049276', '2022-01-14 10:18:35', NULL, NULL, '8d270576da411710a61e2deed2ddbc45');
INSERT INTO `t_attachment` VALUES ('20220114151610535691855218', '202105171503402497159', NULL, NULL, 0, '37f5a9e32e13720428600a403dcfdff8.jpg', '/attachmentFile/2022-01-14T151610.532aaef92a9-1889-4e0c-b1d5-4d6928edb6f5.jpg', '.jpg', 336, NULL, 0, 1, 'u_10001', '2022-01-14 15:16:11', NULL, NULL, 'dc382d45ab8880e01519f6dc4cc6a7a7');
INSERT INTO `t_attachment` VALUES ('20220114151614714384422170', '202105171503402497159', NULL, NULL, 0, '1750284933933999859.png', '/attachmentFile/2022-01-14T151614.711bbc80ea4-c101-4157-8f25-b2bb84719319.png', '.png', 14, NULL, 0, 1, 'u_10001', '2022-01-14 15:16:15', NULL, NULL, '453b946585bb081f8db27d0153a77f88');
INSERT INTO `t_attachment` VALUES ('20220114153117137678660509', '202105171503402497159', NULL, NULL, 0, 'dtqw3.png', '/attachmentFile/2022-01-14T153117.135ec93d3d0-2368-49f9-ae27-3bcae7d3f7f9.png', '.png', 18, NULL, 0, 1, 'u_10001', '2022-01-14 15:31:17', NULL, NULL, '7e63fdcc7709cf77dab7a476ab384f7f');
INSERT INTO `t_attachment` VALUES ('20220114153349263319498571', '202105171503402497159', NULL, NULL, 0, 'dtqw2.png', '/attachmentFile/2022-01-14T153348.99421d7a023-7b28-4f68-b5ae-608b774402cd.png', '.png', 18, NULL, 0, 1, 'u_10001', '2022-01-14 15:33:49', NULL, NULL, '2c922a5ccfb95f9dca4560d9c5e459ed');
INSERT INTO `t_attachment` VALUES ('20220114153751942473856739', '202105171503402497159', NULL, NULL, 0, 'cpjs5.png', '/attachmentFile/2022-01-14T153751.9373214ddb1-fa94-4ed8-866f-8223d83c12ad.png', '.png', 2, NULL, 0, 1, 'u_10001', '2022-01-14 15:37:52', NULL, NULL, '97af6f67c7b2b20cebe96fda86e48fcb');
INSERT INTO `t_attachment` VALUES ('20220114154007905825156323', '202105171503402497159', NULL, NULL, 0, 'gmjs-1.png', '/attachmentFile/2022-01-14T154007.90164c8dc58-c133-4ec8-a096-cb97e92d1e86.png', '.png', 2, NULL, 0, 1, 'u_10001', '2022-01-14 15:40:08', NULL, NULL, 'cb0e01b1439550775d877c255f85f8ff');
INSERT INTO `t_attachment` VALUES ('20220114154513808451213426', '202105171503402497159', NULL, NULL, 0, 'hdlq-7.png', '/attachmentFile/2022-01-14T154513.8033abc708a-447e-4f2c-b5b9-951a70707414.png', '.png', 3, NULL, 0, 1, 'u_10001', '2022-01-14 15:45:14', NULL, NULL, '6d7055b9d8d44a025b34ecda22de6eca');
INSERT INTO `t_attachment` VALUES ('20220114155445183402712302', '202105171503402497159', NULL, NULL, 0, 'al-5-1.png', '/attachmentFile/2022-01-14T155445.165fef7382f-8d39-462d-9a0f-c7ec316d6593.png', '.png', 630, NULL, 0, 1, 'u_10001', '2022-01-14 15:54:45', NULL, NULL, '19d05f8cced04396e91e3698810ef11b');
INSERT INTO `t_attachment` VALUES ('20220114155956271198368707', '202105171503402497159', NULL, NULL, 0, 'al-1.jpg', '/attachmentFile/2022-01-14T155956.26559de1ba3-b8d8-4810-856e-10cbc3afbc2c.jpg', '.jpg', 118, NULL, 0, 1, 'u_10001', '2022-01-14 15:59:56', NULL, NULL, '73c19454e90201f9d85a7d67a5aff547');
INSERT INTO `t_attachment` VALUES ('20220118100411463216168635', '202105171503402497159', NULL, NULL, 0, '3454604398887363063.png', '/attachmentFile/2022-01-18T100411.3886f1e9433-4a26-440a-b46b-3bc8ce09edb0.png', '.png', 12, NULL, 0, 1, 'u_10001', '2022-01-18 10:04:11', NULL, NULL, '42af805b56747b16837f99720ea4c4ad');
INSERT INTO `t_attachment` VALUES ('20220118135423770420594632', '202105171503402497159', NULL, NULL, 0, '3454604398887363063.png', '/attachmentFile/2022-01-18T135423.764e72d114e-9bec-48ad-837c-60a3e80cc813.png', '.png', 12, NULL, 0, 1, 'u_10001', '2022-01-18 13:54:24', NULL, NULL, '42af805b56747b16837f99720ea4c4ad');
INSERT INTO `t_attachment` VALUES ('20220118172501005947630614', '202105171503402497159', NULL, NULL, 0, '490911444781668186.png', '/attachmentFile/2022-01-18T172501a1deb41b-5d4f-4263-a559-3a56447b48ef.png', '.png', 10, NULL, 0, 1, 'u_10001', '2022-01-18 17:25:01', NULL, NULL, 'e23fa412b38baf40fe2fe8c66920d3f0');
INSERT INTO `t_attachment` VALUES ('20220118172511053918962421', '202105171503402497159', NULL, NULL, 0, 'asfdgafd.jpg', '/attachmentFile/2022-01-18T172511.050ea391929-157d-44f3-b47c-16517341648b.jpg', '.jpg', 36, NULL, 0, 1, 'u_10001', '2022-01-18 17:25:11', NULL, NULL, 'b3701c9b22018ec3568665425a37f12e');
INSERT INTO `t_attachment` VALUES ('20220118172514522181982438', '202105171503402497159', NULL, NULL, 0, 'asfdsg.jpg', '/attachmentFile/2022-01-18T172514.519dbdc4843-0230-4d4a-8484-72f5f90aa176.jpg', '.jpg', 142, NULL, 0, 1, 'u_10001', '2022-01-18 17:25:15', NULL, NULL, '753a00d3d0a419411a3ef6c06dfe0592');
INSERT INTO `t_attachment` VALUES ('20220118173139421001024305', '202105171503402497159', NULL, NULL, 0, '8987762840678959407.png', '/attachmentFile/2022-01-18T173139.417ae4c95c9-17ff-4f6d-a407-876f429eeca9.png', '.png', 12, NULL, 0, 1, 'u_10001', '2022-01-18 17:31:39', NULL, NULL, '46a642b7f2a481bbbcf2cc055bcaa779');
INSERT INTO `t_attachment` VALUES ('20220118180747634102197086', '202105171503402497159', NULL, NULL, 0, '3454604398887363063.png', '/attachmentFile/2022-01-18T180747.624cdaf036a-8ddf-49bd-a3a8-badbe81fd745.png', '.png', 12, NULL, 0, 1, 'u_10001', '2022-01-18 18:07:48', NULL, NULL, '42af805b56747b16837f99720ea4c4ad');
INSERT INTO `t_attachment` VALUES ('20220118184216924501651299', '202105171503402497159', NULL, NULL, 0, '1750284933933999859.png', '/attachmentFile/2022-01-18T184216.91816392895-8f05-41e4-91ca-9507593cff5c.png', '.png', 14, NULL, 0, 1, 'u_10001', '2022-01-18 18:42:17', NULL, NULL, '453b946585bb081f8db27d0153a77f88');
INSERT INTO `t_attachment` VALUES ('20220119112554102187239819', '202105171503402497159', NULL, NULL, 0, '937098428700335159.png', '/attachmentFile/2022-01-19T112554.095fd2e575d-9171-45b3-b48a-84164c7fc8cf.png', '.png', 15, NULL, 0, 1, 'u_10001', '2022-01-19 11:25:54', NULL, NULL, '29db5ef026ddd427f89c0599b4ac7117');
INSERT INTO `t_attachment` VALUES ('20220119134057744983376185', '202105171503402497159', NULL, NULL, 0, '虎年头像-03.png', '/attachmentFile/2022-01-19T134057.7240c6eb85e-3778-4903-8b35-84dfc9a02c71.png', '.png', 37, NULL, 0, 1, 'u_10001', '2022-01-19 13:40:58', NULL, NULL, '9d04983c8cb75e02fb431ff17b249638');
INSERT INTO `t_attachment` VALUES ('20220119134101268289474170', '202105171503402497159', NULL, NULL, 0, '虎年头像-03.png', '/attachmentFile/2022-01-19T134101.2625726680e-9ca0-43d9-87b5-a5905346d444.png', '.png', 37, NULL, 0, 1, 'u_10001', '2022-01-19 13:41:01', NULL, NULL, '9d04983c8cb75e02fb431ff17b249638');
INSERT INTO `t_attachment` VALUES ('20220119134105810594568452', '202105171503402497159', NULL, NULL, 0, '虎年头像-03.png', '/attachmentFile/2022-01-19T134105.805247fdc03-6615-48e3-b85d-7e18a9bdeb5e.png', '.png', 37, NULL, 0, 1, 'u_10001', '2022-01-19 13:41:06', NULL, NULL, '9d04983c8cb75e02fb431ff17b249638');
INSERT INTO `t_attachment` VALUES ('20220119134133049844055255', '', NULL, NULL, 0, 'collectiondetails.png', '/attachmentFile/2022-01-19T134133.04832c7b249-8551-48c9-983c-366077ecdfc0.png', '.png', 50, NULL, 0, 1, 'u_10001', '2022-01-19 13:41:33', NULL, NULL, '16306c57472bb9e125f51f1f7e8f6d71');
INSERT INTO `t_attachment` VALUES ('20220119135327720168399476', '202105171503402497159', NULL, NULL, 0, 'fengmian.png', '/attachmentFile/2022-01-19T135327.710d3c891d2-f607-4a77-8205-7cb76e879692.png', '.png', 15, NULL, 0, 1, 'u_10001', '2022-01-19 13:53:28', NULL, NULL, '8244091f8a4a630cdf46ed06bc4e212e');
INSERT INTO `t_attachment` VALUES ('20220119135333639331726017', '202105171503402497159', NULL, NULL, 0, 'bannner.png', '/attachmentFile/2022-01-19T135333.6358fc62e34-e4e1-439e-8c6f-d17cefe72b2b.png', '.png', 37, NULL, 0, 1, 'u_10001', '2022-01-19 13:53:34', NULL, NULL, 'd2f9db235ed425fd9fa322372067b857');
INSERT INTO `t_attachment` VALUES ('20220119135505841683500104', '202105171503402497159', NULL, NULL, 0, '虎年头像-03.png', '/attachmentFile/2022-01-19T135505.838acbad61d-043f-4419-834d-7f7676232eb0.png', '.png', 37, NULL, 0, 1, 'u_10001', '2022-01-19 13:55:06', NULL, NULL, '9d04983c8cb75e02fb431ff17b249638');
INSERT INTO `t_attachment` VALUES ('20220119135606584930467487', '', NULL, NULL, 0, 'collectiondetails.png', '/attachmentFile/2022-01-19T135606.583a7a61570-cf4a-4a39-b226-35f0abd16cca.png', '.png', 50, NULL, 0, 1, 'u_10001', '2022-01-19 13:56:07', NULL, NULL, '16306c57472bb9e125f51f1f7e8f6d71');
INSERT INTO `t_attachment` VALUES ('20220119144958565274994114', '202105171503402497159', NULL, NULL, 0, '虎年头像-04.png', '/attachmentFile/2022-01-19T144958.555c5215083-03ce-4209-9655-17533e0e21fa.png', '.png', 7, NULL, 0, 1, 'u_10001', '2022-01-19 14:49:59', NULL, NULL, '6e4f90fb20fa0f01f81ab914668407d8');
INSERT INTO `t_attachment` VALUES ('20220119145002030186464199', '202105171503402497159', NULL, NULL, 0, '虎年头像-05.png', '/attachmentFile/2022-01-19T145002.027510bc6e6-1a48-42de-9de0-7559630aab73.png', '.png', 14, NULL, 0, 1, 'u_10001', '2022-01-19 14:50:02', NULL, NULL, '64209972abb52dfd1e401d87fc313d2e');
INSERT INTO `t_attachment` VALUES ('20220119150157703513329293', '202105171503402497159', NULL, NULL, 0, '1750284933933999859.png', '/attachmentFile/2022-01-19T150157.699beb2b406-9275-4111-842f-fed8252f0c11.png', '.png', 14, NULL, 0, 1, 'u_10001', '2022-01-19 15:01:58', NULL, NULL, '453b946585bb081f8db27d0153a77f88');
INSERT INTO `t_attachment` VALUES ('20220207155927844645034759', '202105171503402497159', NULL, NULL, 0, '中文.png', '/attachmentFile/2022-02-07T155927.841052b5741-9d0e-4a4d-9bf8-ea9d4d356db5.png', '.png', 0, NULL, 0, 1, 'u_10001', '2022-02-07 15:59:28', NULL, NULL, '6604b68c06d6ac57377ed124468983c5');
INSERT INTO `t_attachment` VALUES ('20220207160327458340069273', '202105171503402497159', NULL, NULL, 0, 'asfdgafd.jpg', '/attachmentFile/2022-02-07T160327.4568e6ecc92-8e5a-4fc9-a095-0be3698cfcf9.jpg', '.jpg', 36, NULL, 0, 1, 'u_10001', '2022-02-07 16:03:27', NULL, NULL, 'b3701c9b22018ec3568665425a37f12e');
INSERT INTO `t_attachment` VALUES ('20220207160330602823747747', '202105171503402497159', NULL, NULL, 0, 'asfdsg.jpg', '/attachmentFile/2022-02-07T160330.5998e9ae1a0-9a30-4aa9-b050-a13ed0c8561c.jpg', '.jpg', 142, NULL, 0, 1, 'u_10001', '2022-02-07 16:03:31', NULL, NULL, '753a00d3d0a419411a3ef6c06dfe0592');
INSERT INTO `t_attachment` VALUES ('20220207160517605007735735', '202105171503402497159', NULL, NULL, 0, '8987762840678959407.png', '/attachmentFile/2022-02-07T160517.603c6738e5d-049f-403e-b1c1-ab3a7d465e03.png', '.png', 12, NULL, 0, 1, 'u_10001', '2022-02-07 16:05:18', NULL, NULL, '46a642b7f2a481bbbcf2cc055bcaa779');

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
INSERT INTO `t_dic_data` VALUES ('20210723135711733685837358', '用户类型', NULL, NULL, 'root', NULL, '用户类型', 1, '2021-07-23 13:57:12', 'userType', 'down', NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('20210723135741551838580253', '会员', '0', '0', '20210723135711733685837358', NULL, '会员', 1, '2021-07-23 13:57:42', 'userType', 'down', NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('20210723140215764144543685', '员工', '1', '1', '20210723135711733685837358', NULL, '员工', 1, '2021-07-23 14:02:16', 'userType', 'down', NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('20210723140244378154672799', '注册企业', '3', '3', '20210723135711733685837358', NULL, '注册企业', 1, '2021-07-23 14:02:44', 'userType', 'down', NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('20210723140256927930774407', '专家', '4', '4', '20210723135711733685837358', NULL, '专家', 1, '2021-07-23 14:02:57', 'userType', 'down', NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('20210723140313983200387116', '市局', '5', '5', '20210723135711733685837358', NULL, '市局', 1, '2021-07-23 14:03:14', 'userType', 'down', NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('20210723140331151004848497', '县局', '6', '6', '20210723135711733685837358', NULL, '县局', 1, '2021-07-23 14:03:31', 'userType', 'down', NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('20210723140345340703977427', '系统管理员', '9', '9', '20210723135711733685837358', NULL, '系统管理员', 1, '2021-07-23 14:03:45', 'userType', 'down', NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('20210723140357014600336647', '访客', '10', '10', '20210723135711733685837358', NULL, '访客', 1, '2021-07-23 14:03:57', 'userType', 'down', NULL, NULL, NULL, NULL);
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
INSERT INTO `t_menu` VALUES ('20210628172931789866445893', '通用上传', ',system_manager,20210628172931789866445893,', '通用上传', 'system_manager', NULL, '/attachment/upload', 't_attachment_upload', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-06-28 17:29:32', NULL, '2021-12-31 14:39:14', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20210707170346770919441915', '获取部门信息', ',system_manager,t_role_list,20210707170346770919441915,', '获取部门信息', 't_role_list', NULL, '/api/system/org/treeselect', 't_roles_company', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-07-07 17:03:47', NULL, '2021-07-07 17:03:47', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20210726140401984820627315', '用户类型', ',system_manager,t_user_list,20210726140401984820627315,', '用户类型', 't_user_list', NULL, '/api/system/user/userTypeList', 't_user_userTypeList', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-07-26 14:04:02', NULL, '2021-07-26 14:04:02', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20211231093524473606372261', '创建', ',20220104142542123788173684,20211231093524473606372261,', '创建', '20220104142542123788173684', NULL, '/api', NULL, 1, NULL, NULL, NULL, NULL, NULL, 'bars', NULL, NULL, '2021-12-31 09:35:24', NULL, '2022-01-04 14:25:51', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20211231093550886104679185', '创建NFT', ',20220104142542123788173684,20211231093524473606372261,20211231093550886104679185,', '创建NFT', '20211231093524473606372261', NULL, '/api', NULL, 1, '/nft_creatNFT', NULL, NULL, NULL, NULL, 'bars', NULL, NULL, '2021-12-31 09:35:51', NULL, '2021-12-31 09:35:51', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20211231093619595541365083', '创建合集', ',20220104142542123788173684,20211231093524473606372261,20211231093619595541365083,', '创建合集', '20211231093524473606372261', NULL, '/api', NULL, 1, '/nft_creatCollection', NULL, NULL, NULL, NULL, 'bars', NULL, NULL, '2021-12-31 09:36:20', NULL, '2021-12-31 09:36:20', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20211231093706836672865868', '获取合集类别', ',20220104142542123788173684,20211231093524473606372261,20211231093706836672865868,', '获取合集类别', '20211231093524473606372261', NULL, '/api/nft/collection/labelValuePair', 'nft_collection_labelValuePair', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-12-31 09:37:07', NULL, '2021-12-31 09:37:07', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20211231093814920083731393', '创建合集', ',20220104142542123788173684,20211231093524473606372261,20211231093619595541365083,20211231093814920083731393,', '创建合集', '20211231093619595541365083', NULL, '/api/nft/collection/create', 'nft_collection_create', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-12-31 09:38:15', NULL, '2022-01-06 16:28:00', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20211231153025601390727594', '类别数据', ',20220104142542123788173684,20211231093524473606372261,20211231153025601390727594,', '类别数据', '20211231093524473606372261', NULL, '/api/nft/type/list', 'nft_type_list', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-12-31 15:30:26', NULL, '2021-12-31 15:30:26', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20211231164719144298912294', '创建NFT', ',20220104142542123788173684,20211231093524473606372261,20211231093550886104679185,20211231164719144298912294,', '创建NFT', '20211231093550886104679185', NULL, '/api/nft/works/createWorks', 'nft_works_createWorks', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-12-31 16:47:19', NULL, '2022-01-06 16:28:51', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220104142542123788173684', 'web_nft', ',20220104142542123788173684,', 'web_nft', '', NULL, '/api', NULL, 1, NULL, NULL, NULL, NULL, NULL, 'bars', NULL, NULL, '2022-01-04 14:25:42', NULL, '2022-01-04 14:25:42', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220104142602091834741487', 'mobile_nft', ',20220104142602091834741487,', 'mobile_nft', '', NULL, '/api', NULL, 1, NULL, NULL, NULL, NULL, NULL, 'bars', NULL, NULL, '2022-01-04 14:26:02', NULL, '2022-01-04 14:26:02', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220104142622475939156297', '首页', ',20220104142602091834741487,20220104142622475939156297,', '首页', '20220104142602091834741487', NULL, '/api', NULL, 1, '/nft_mobile_home', NULL, NULL, NULL, NULL, 'bars', NULL, NULL, '2022-01-04 14:26:22', NULL, '2022-01-04 14:26:55', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220104142645108136051030', '排行榜', ',20220104142602091834741487,20220104142645108136051030,', '排行榜', '20220104142602091834741487', NULL, '/api', NULL, 1, '/nft_mobile_list', NULL, NULL, NULL, NULL, 'bars', NULL, NULL, '2022-01-04 14:26:45', NULL, '2022-01-04 14:26:45', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220104142718128398296568', '搜索', ',20220104142602091834741487,20220104142718128398296568,', '搜索', '20220104142602091834741487', NULL, '/api', NULL, 1, '/nft_mobile_search', NULL, NULL, NULL, NULL, 'bars', NULL, NULL, '2022-01-04 14:27:18', NULL, '2022-01-04 14:27:18', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220104142756029411135150', '创建', ',20220104142602091834741487,20220104142756029411135150,', '创建', '20220104142602091834741487', NULL, '/api', NULL, 1, '/nft_mobile_create', NULL, NULL, NULL, NULL, 'bars', NULL, NULL, '2022-01-04 14:27:56', NULL, '2022-01-04 14:27:56', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220104142818741480966982', '我的', ',20220104142602091834741487,20220104142818741480966982,', '我的', '20220104142602091834741487', NULL, '/api', NULL, 1, '/nft_mobile_myself', NULL, NULL, NULL, NULL, 'bars', NULL, NULL, '2022-01-04 14:28:19', NULL, '2022-01-04 14:28:19', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220104142911225215272748', 'NFT合集列表(市场)', ',20220104142602091834741487,20220104142622475939156297,20220104142911225215272748,', 'NFT合集列表(市场)', '20220104142622475939156297', NULL, '/api/nft/collection/findNftMarketList', 'nft_mobile_collection_findNftMarketList', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-04 14:29:11', NULL, '2022-01-04 14:44:09', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220104144352748677258417', 'NFT列表', ',20220104142602091834741487,20220104142622475939156297,20220104144352748677258417,', 'NFT列表', '20220104142622475939156297', NULL, '/api/nft/collection/colletionDetail', 'nft_mobile_collection_colletionDetail', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-04 14:43:53', NULL, '2022-01-04 14:43:53', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220104170136788977360618', 'NFT详情头像数据', ',20220104142602091834741487,20220104142622475939156297,20220104170136788977360618,', 'NFT详情头像数据', '20220104142622475939156297', NULL, '/api/nft/works/buyWorksInfo', 'nft_mobile_works_buyWorksInfo', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-04 17:01:37', NULL, '2022-01-04 17:01:37', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220104170258373927022228', 'NFT详情页持有者数据', ',20220104142602091834741487,20220104142622475939156297,20220104170258373927022228,', 'NFT详情页持有者数据', '20220104142622475939156297', NULL, '/api/nft/works/buyWorks', 'nft_mobile_works_buyWorks', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-04 17:02:58', NULL, '2022-01-04 17:02:58', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220105114733959419830633', '排行榜列表', ',20220104142602091834741487,20220104142645108136051030,20220105114733959419830633,', '排行榜列表', '20220104142645108136051030', NULL, '/api/nft/order/rankingFindAll', 'nft_mobile_order_rankingFindAll', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-05 11:47:34', NULL, '2022-01-05 11:47:34', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220105114853009770929731', '收藏品列表', ',20220104142602091834741487,20220104142818741480966982,20220105114853009770929731,', '收藏品列表', '20220104142818741480966982', NULL, '/api/nft/userassets/findUserCollectionWorks', 'nft_mobile_userassets_findUserCollectionWorks', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-05 11:48:53', NULL, '2022-01-05 11:48:53', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220105114952898199008485', '创建作品列表', ',20220104142602091834741487,20220104142818741480966982,20220105114952898199008485,', '创建作品列表', '20220104142818741480966982', NULL, '/api/nft/userassets/findMyCreateWorks', 'nft_mobile_userassets_findMyCreateWorks', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-05 11:49:53', NULL, '2022-01-05 11:49:53', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220105115110443747972919', '合集列表', ',20220104142602091834741487,20220104142818741480966982,20220105115110443747972919,', '合集列表', '20220104142818741480966982', NULL, '/api/nft/userassets/findUserCollection', 'nft_mobile_userassets_findUserCollection', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-05 11:51:10', NULL, '2022-01-05 11:51:10', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220105115312420625797138', '交易历史列表', ',20220104142602091834741487,20220104142818741480966982,20220105115312420625797138,', '交易历史列表', '20220104142818741480966982', NULL, '/api/nft/order/findTransHis', 'nft_mobile_order_findTransHis', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-05 11:53:12', NULL, '2022-01-05 11:53:12', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220105161744748372947124', '上架作品', ',20220104142602091834741487,20220104142622475939156297,20220105161744748372947124,', '上架作品', '20220104142622475939156297', NULL, '/api/nft/works/updateWorksIn', 'nft_mobile_works_updateWorksIn', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-05 16:17:45', NULL, '2022-01-05 16:17:45', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220105173218539504996827', '热门合集列表', ',20220104142602091834741487,20220104142622475939156297,20220105173218539504996827,', '热门合集列表', '20220104142622475939156297', NULL, '/api/nft/collection/findFairCollectionList', 'nft_mobile_collection_findFairCollectionList', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-05 17:32:19', NULL, '2022-01-05 17:32:19', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220106094233475775871829', '获取用户个人信息', ',20220104142602091834741487,20220104142818741480966982,20220106094233475775871829,', '获取用户个人信息', '20220104142818741480966982', NULL, '/api/nft/userassets/findUserDetails', 'nft_mobile_userassets_findUserDetails', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-06 09:42:33', NULL, '2022-01-06 09:42:33', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220106180309277908298683', '修改', ',20220104142542123788173684,20211231093524473606372261,20211231093619595541365083,20220106180309277908298683,', '修改', '20211231093619595541365083', NULL, '/api/nft/collection/update', 'nft_collection_update', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-06 18:03:09', NULL, '2022-01-06 18:03:09', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220106180420743517167135', '修改', ',20220104142542123788173684,20211231093524473606372261,20211231093550886104679185,20220106180420743517167135,', '修改', '20211231093550886104679185', NULL, '/api/nft/works/updateWorks', 'nft_works_updateWorks', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-06 18:04:21', NULL, '2022-01-06 18:04:21', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220106185654436934618789', '查询一个作品', ',20220104142542123788173684,20211231093524473606372261,20211231093550886104679185,20220106185654436934618789,', '查询一个作品', '20211231093550886104679185', NULL, '/api/nft/works/findWorks', 'nft_works_findWorks', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-06 18:56:54', NULL, '2022-01-06 18:56:54', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220106191700202973162766', '购买下订单', ',20220104142602091834741487,20220104142818741480966982,20220106191700202973162766,', '购买下订单', '20220104142818741480966982', NULL, '/api/nft/order/generateOrderByWorksId', 'nft_mobile_order_generateOrderByWorksId', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-06 19:17:00', NULL, '2022-01-06 19:17:00', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220107101830268276912144', '市场', ',20220104142542123788173684,20220107101830268276912144,', '市场', '20220104142542123788173684', NULL, '/api', NULL, 1, '/nft_market', NULL, NULL, NULL, NULL, 'bars', NULL, NULL, '2022-01-07 10:18:30', NULL, '2022-01-07 10:18:30', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220107101854226108278453', '删除作品', ',20220104142542123788173684,20220107101830268276912144,20220107101854226108278453,', '删除作品', '20220107101830268276912144', NULL, '/api/nft/works/logicDel', 'nft_works_logicDel', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-07 10:18:54', NULL, '2022-01-07 10:19:55', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220107111138315200698219', '模拟支付成功', ',20220104142602091834741487,20220104142622475939156297,20220107111138315200698219,', '模拟支付成功', '20220104142622475939156297', NULL, '/api/nft/order/payAfter', 'nft_mobile_order_payAfter', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-07 11:11:38', NULL, '2022-01-07 11:11:38', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220107154808756068707537', '搜索', ',20220104142602091834741487,20220104142718128398296568,20220107154808756068707537,', '搜索', '20220104142718128398296568', NULL, '/api/nft/works/searchWorks', 'nft_mobile_works_searchWorks', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-07 15:48:09', NULL, '2022-01-07 15:48:09', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220110131512673482416844', '判断用户是否需要完善信息', ',20220104142602091834741487,20220104142622475939156297,20220110131512673482416844,', '判断用户是否需要完善信息', '20220104142622475939156297', NULL, '/api/nft/login/haveCurrentUserInfo', 'nft_mobile_login_haveCurrentUserInfo', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-10 13:15:13', NULL, '2022-01-10 13:15:13', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220110131925693399966287', '用户初始化信息', ',20220104142602091834741487,20220104142818741480966982,20220110131925693399966287,', '用户初始化信息', '20220104142818741480966982', NULL, '/api/nft/login/userInfoInit', 'nft_mobile_login_userInfoInit', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-10 13:19:26', NULL, '2022-01-10 13:19:26', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220112144206800011731606', '支付', ',20220104142602091834741487,20220104142622475939156297,20220112144206800011731606,', '支付', '20220104142622475939156297', NULL, '/api/nft/order/getPayRequestParam', 'nft_mobile_order_getPayRequestParam', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-12 14:42:07', NULL, '2022-01-12 14:42:07', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220112151835601336960516', '检查订单状态', ',20220104142602091834741487,20220104142622475939156297,20220112151835601336960516,', '检查订单状态', '20220104142622475939156297', NULL, '/api/nft/order/checkOrderState', 'nft_mobile_order_checkOrderState', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-12 15:18:36', NULL, '2022-01-12 15:18:36', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220112171351090662647367', '链平台信息', ',20220104142602091834741487,20220104142818741480966982,20220112171351090662647367,', '链平台信息', '20220104142818741480966982', NULL, '/api/nft/userchainplat/findUserChainplat', 'nft_mobile_userchainplat_findUserChainplat', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-12 17:13:51', NULL, '2022-01-12 17:13:51', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220112173845599282286168', '下载公钥私钥', ',20220104142602091834741487,20220104142818741480966982,20220112173845599282286168,', '下载公钥私钥', '20220104142818741480966982', NULL, '/api/nft/userchainplat/userDownPrivateAndPublic', 'nft_mobile_userchainplat_userDownPrivateAndPublic', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-12 17:38:46', NULL, '2022-01-12 17:38:46', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220112173912752301041531', '删除公钥私钥', ',20220104142602091834741487,20220104142818741480966982,20220112173912752301041531,', '删除公钥私钥', '20220104142818741480966982', NULL, '/api/nft/userchainplat/userDelPrivateAndPublic', 'nft_mobile_userchainplat_userDelPrivateAndPublic', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-12 17:39:13', NULL, '2022-01-12 17:39:13', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220114150751701792713417', '上架', ',20220104142542123788173684,20220114150751701792713417,', '上架', '20220104142542123788173684', NULL, '/api/nft/order/generateOrderWorksIn', 'nft_order_generateOrderWorksIn', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-14 15:07:52', NULL, '2022-01-14 15:07:52', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220114161325850832595643', '检查是否有未支付订单', ',20220104142602091834741487,20220104142622475939156297,20220114161325850832595643,', '检查是否有未支付订单', '20220104142622475939156297', NULL, '/api/nft/order/checkUnpaidOrders', 'nft_mobile_order_checkUnpaidOrders', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-14 16:13:26', NULL, '2022-01-14 16:13:26', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220114161353204713704595', '取消支付', ',20220104142602091834741487,20220104142622475939156297,20220114161353204713704595,', '取消支付', '20220104142622475939156297', NULL, '/api/nft/order/cancelOrder', 'nft_mobile_order_cancelOrder', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-14 16:13:53', NULL, '2022-01-14 16:13:53', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220117093816549773011922', '购买详情页作品拥有者', ',20220104142602091834741487,20220104142818741480966982,20220117093816549773011922,', '购买详情页作品拥有者', '20220104142818741480966982', NULL, '/api/nft/works/findWorkBuyers', 'nft_mobile_works_findWorkBuyers', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-17 09:38:17', NULL, '2022-01-17 09:38:17', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220117094007268192631981', '合集详情页作品列表', ',20220104142602091834741487,20220104142622475939156297,20220117094007268192631981,', '合集详情页作品列表', '20220104142622475939156297', NULL, '/api/nft/collection/findWorksInCollection', 'nft_mobile_collection_findWorksInCollection', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-17 09:40:07', NULL, '2022-01-17 09:40:07', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220117180144838084823717', '綁定百度超级链开放网络', ',20220104142602091834741487,20220104142818741480966982,20220117180144838084823717,', '綁定百度超级链开放网络', '20220104142818741480966982', NULL, '/api/nft/userchainplat/bindXuperChainOpenNet', 'nft_mobile_userchainplat_bindXuperChainOpenNet', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-17 18:01:45', NULL, '2022-01-17 18:01:45', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220117180227567594447150', '检查是否绑定开放网络', ',20220104142602091834741487,20220104142818741480966982,20220117180227567594447150,', '检查是否绑定开放网络', '20220104142818741480966982', NULL, '/api/nft/userchainplat/checkBindChainOpenNet', 'nft_mobile_userchainplat_checkBindChainOpenNet', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-17 18:02:28', NULL, '2022-01-17 18:02:28', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220121105427523928713981', '完善信息', ',20220104142542123788173684,20220121105427523928713981,', '完善信息', '20220104142542123788173684', NULL, '/api', NULL, 1, '/nft_assetsKey', NULL, NULL, NULL, NULL, 'bars', NULL, NULL, '2022-01-21 10:54:28', NULL, '2022-01-21 10:54:28', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220121105459982711047451', '绑定百度超级链address', ',20220104142542123788173684,20220121105427523928713981,20220121105459982711047451,', '绑定百度超级链address', '20220121105427523928713981', NULL, '/api/nft/userchainplat/bindXuperChainOpenNetPrivate', 'nft_userchainplat_bindXuperChainOpenNetPrivate', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-21 10:55:00', NULL, '2022-01-21 10:55:00', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220121105556227507499314', '检查是否绑定', ',20220104142542123788173684,20220121105427523928713981,20220121105556227507499314,', '检查是否绑定', '20220121105427523928713981', NULL, '/api/nft/userchainplat/checkBindXuperChainOpenNetPrivate', 'nft_userchainplat_checkBindXuperChainOpenNetPrivate', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-21 10:55:56', NULL, '2022-01-21 10:55:56', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220122103652309369137209', '回显', ',20220104142542123788173684,20220121105427523928713981,20220122103652309369137209,', '回显', '20220121105427523928713981', NULL, '/api/nft/userchainplat/reviewBind', 'nft_userchainplat_reviewBind', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-22 10:36:52', NULL, '2022-01-22 10:36:52', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220122172844353167846312', '冷却期', ',20220104142602091834741487,20220104142622475939156297,20220122172844353167846312,', '冷却期', '20220104142622475939156297', NULL, '/api/nft/works/getCoolingPeriod', 'nft_works_getCoolingPeriod', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-01-22 17:28:44', NULL, '2022-01-22 17:28:44', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220207102823996688018018', '个人中心隐私绑定私钥', ',20220104142602091834741487,20220104142818741480966982,20220207102823996688018018,', '个人中心隐私绑定私钥', '20220104142818741480966982', NULL, '/api/nft/userchainplat/checkBindPrivateAndPasswd', 'nft_mobile_userchainplat_checkBindPrivateAndPasswd', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-02-07 10:28:24', NULL, '2022-02-07 14:21:43', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220207102908259671003209', '收藏品检查是否绑定', ',20220104142602091834741487,20220104142818741480966982,20220207102908259671003209,', '收藏品检查是否绑定', '20220104142818741480966982', NULL, '/api/nft/userchainplat/checkBindChainOpenNet', 'nft_mobile_userchainplat_checkBindChainOpenNet', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-02-07 10:29:08', NULL, '2022-02-07 10:29:08', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('20220207103002096245592159', '收藏品转移', ',20220104142602091834741487,20220104142818741480966982,20220207103002096245592159,', '收藏品转移', '20220104142818741480966982', NULL, '/api/nft/userassets/transferAssets', 'nft_mobile_userassets_transferAssets', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-02-07 10:30:02', NULL, '2022-02-07 10:30:02', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('3330456139a241b1a27a7dcd171d7bf1', '拖拽演示网站', ',f4d7a1bf7ddf43dc9016e1465cd3d9d8,3330456139a241b1a27a7dcd171d7bf1,', '拖拽演示网站', 'f4d7a1bf7ddf43dc9016e1465cd3d9d8', '', '/undifind', NULL, 1, NULL, NULL, NULL, NULL, NULL, 'drag', NULL, NULL, '2019-07-24 11:33:44', '', '2021-05-17 10:24:48', '', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('3501ed1e23da40219b4f0fa5b7b2749a', '菜单列表', ',system_manager,t_menu_list,3501ed1e23da40219b4f0fa5b7b2749a,', '菜单列表', 't_menu_list', '', '/api/system/menu/all/list/json', 't_menu_list', 0, '/api/system/menu/list', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-24 11:33:44', '', '2021-12-30 17:49:30', '', 0, 1, NULL, NULL, NULL, NULL, NULL);
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
INSERT INTO `t_menu` VALUES ('d6abe682007849869c3a168215ae40d4', 'WEB-INF文件管理', ',system_manager,d6abe682007849869c3a168215ae40d4,', 'WEB-INF文件管理', 'system_manager', '', '/api/system/file/web/list', NULL, 1, '/api/system/file/web/list', NULL, NULL, NULL, NULL, '/img/iconImg/icon20.png', NULL, NULL, '2019-07-24 11:33:44', '', '2021-12-31 14:39:03', '', 7, 0, NULL, NULL, NULL, NULL, NULL);
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
-- Records of t_notify
-- ----------------------------

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
INSERT INTO `t_role` VALUES ('202105131008457039760', '测试角色', NULL, NULL, 0, 0, 'o_10001', 1, '2021-05-13 10:08:35', 'u_10001', '2021-05-20 15:22:29', NULL, 5, '测试角色', 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role` VALUES ('e8a4ad9944894908b43ded631094dcbb', '演示站长', 'DEMO_WEBMASTER', NULL, 0, 1, 'o_10001', 0, '2019-07-24 17:29:44', 'u_10001', '2019-07-24 17:29:44', 'u_10001', 2, 'b', 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role` VALUES ('nft_role', 'NFT用户角色', NULL, NULL, 0, 0, '202105171503402497159', 0, '2021-12-30 17:32:11', 'u_10001', '2022-01-06 19:35:03', 'u_10001', 7, 'NFT用户角色', 1, '/nft_mobile_home', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role` VALUES ('r_10001', '超级管理员', 'SUPER_ADMIN', NULL, 0, 2, '202105171503402497159', 0, '2019-07-24 17:29:45', 'u_10001', '2022-01-06 19:47:34', 'u_10001', 1, 'c', 1, '/nft_home', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role` VALUES ('youke', '游客', 'YOUKE', NULL, 0, 0, '202105171504130626640', 0, '2021-05-18 15:04:26', 'u_10001', '2021-05-18 15:04:26', NULL, 6, '默认角色', 1, NULL, NULL, NULL, NULL, NULL, NULL);

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
INSERT INTO `t_role_menu` VALUES ('20211231143921644728308887', 'r_10001', '202105171053129521252', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921645012654703', 'r_10001', 'e51808e351c24a7e9fb4d47392930a2d', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921645075505710', 'r_10001', 't_menu_delete', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921645120824872', 'r_10001', '3501ed1e23da40219b4f0fa5b7b2749a', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921645157166967', 'r_10001', '202105121015501783845', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921645162551591', 'r_10001', 't_menu_update', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921645184619915', 'r_10001', 't_menu_tree', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921645186690413', 'r_10001', 'system_manager', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921645286185707', 'r_10001', 't_org_list', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921645319184397', 'r_10001', 't_user_list', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921645443587165', 'r_10001', 't_org_deletemore', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921645578506274', 'r_10001', 't_menu_look', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921645617526459', 'r_10001', 't_menu_deletemore', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921645797332283', 'r_10001', 't_role_list', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921645878515077', 'r_10001', 'b94392f7b8714f64819c5c0222eb134a', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921645912721424', 'r_10001', 'dic_manager', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921645937108567', 'r_10001', 't_menu_save', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921645989748497', 'r_10001', 't_menu_list', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646021900089', 'r_10001', 't_org_delete', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646062095863', 'r_10001', '20211231093619595541365083', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646131579039', 'r_10001', '20211231093814920083731393', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646160078525', 'r_10001', 't_role_deletemore', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646226058072', 'r_10001', '202105121606286404716', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646270110392', 'r_10001', '202105151252345580670', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646272140472', 'r_10001', 't_user_look', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646332088892', 'r_10001', 't_org_look', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646393321736', 'r_10001', '202105121506575382721', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646394022947', 'r_10001', 't_user_deletemore', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646456582661', 'r_10001', '202105141502166346976', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646524139768', 'r_10001', '202105131018232299435', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646609881325', 'r_10001', 't_role_look', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646654990580', 'r_10001', 't_org_update', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646694355162', 'r_10001', 't_org_tree', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646733926557', 'r_10001', '202105141646058894617', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646734679460', 'r_10001', '20210525184737580453255296', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646748337686', 'r_10001', 't_user_delete', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646790020861', 'r_10001', 't_role_update', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646818394548', 'r_10001', 't_user_list_export', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646908491726', 'r_10001', 't_role_delete', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921646951690938', 'r_10001', 't_user_update', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647136814225', 'r_10001', '202105171018334839880', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647272946376', 'r_10001', '20211231093550886104679185', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647282403514', 'r_10001', '20210526150953853171269157', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647313578343', 'r_10001', '20210527100553713196107663', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647338289615', 'r_10001', '20211231093524473606372261', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647360939813', 'r_10001', '20210527113120304570637037', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647446091025', 'r_10001', '20210527095451904604564871', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647520160550', 'r_10001', '20210519172221273150930534', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647545566431', 'r_10001', '20210520115456171843954745', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647583672386', 'r_10001', '20210527095537252768804665', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647590665179', 'r_10001', '20210526151754523530237327', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647593490829', 'r_10001', '202105171110557380782', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647634743366', 'r_10001', '20210527163800020317714432', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647639254228', 'r_10001', '20211231093706836672865868', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647655109323', 'r_10001', '20210521170043572189307269', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647705405538', 'r_10001', '20210527100626495495029605', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647731107870', 'r_10001', '20210527101125018646355183', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647756679598', 'r_10001', '20210707170346770919441915', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647820574254', 'r_10001', '20210521170718181613125891', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647859095185', 'r_10001', '20210527163849335432594105', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647866649112', 'r_10001', '202105171034080407699', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647956555839', 'r_10001', '20210726140401984820627315', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647982713681', 'r_10001', '202105170919073863443', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921647996977579', 'r_10001', '202105171517522622275', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231143921648184258525', 'r_10001', '20210628172931789866445893', NULL, NULL, NULL, NULL, NULL, '2021-12-31 14:39:22', 'u_10001', '2021-12-31 14:39:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231153025607935076962', 'r_10001', '20211231153025601390727594', NULL, NULL, NULL, NULL, NULL, '2021-12-31 15:30:26', 'u_10001', '2021-12-31 15:30:26', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20211231164719148942131390', 'r_10001', '20211231164719144298912294', NULL, NULL, NULL, NULL, NULL, '2021-12-31 16:47:19', 'u_10001', '2021-12-31 16:47:19', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220104142542168084097610', 'r_10001', '20220104142542123788173684', NULL, NULL, NULL, NULL, NULL, '2022-01-04 14:25:42', 'u_10001', '2022-01-04 14:25:42', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220104142602095854160673', 'r_10001', '20220104142602091834741487', NULL, NULL, NULL, NULL, NULL, '2022-01-04 14:26:02', 'u_10001', '2022-01-04 14:26:02', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220104142622481409566059', 'r_10001', '20220104142622475939156297', NULL, NULL, NULL, NULL, NULL, '2022-01-04 14:26:22', 'u_10001', '2022-01-04 14:26:22', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220104142645112755434333', 'r_10001', '20220104142645108136051030', NULL, NULL, NULL, NULL, NULL, '2022-01-04 14:26:45', 'u_10001', '2022-01-04 14:26:45', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220104142718132500284759', 'r_10001', '20220104142718128398296568', NULL, NULL, NULL, NULL, NULL, '2022-01-04 14:27:18', 'u_10001', '2022-01-04 14:27:18', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220104142756032969889952', 'r_10001', '20220104142756029411135150', NULL, NULL, NULL, NULL, NULL, '2022-01-04 14:27:56', 'u_10001', '2022-01-04 14:27:56', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220104142818744948442981', 'r_10001', '20220104142818741480966982', NULL, NULL, NULL, NULL, NULL, '2022-01-04 14:28:19', 'u_10001', '2022-01-04 14:28:19', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220104142911228777500150', 'r_10001', '20220104142911225215272748', NULL, NULL, NULL, NULL, NULL, '2022-01-04 14:29:11', 'u_10001', '2022-01-04 14:29:11', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220104144352761345210397', 'r_10001', '20220104144352748677258417', NULL, NULL, NULL, NULL, NULL, '2022-01-04 14:43:53', 'u_10001', '2022-01-04 14:43:53', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220104170136792356545581', 'r_10001', '20220104170136788977360618', NULL, NULL, NULL, NULL, NULL, '2022-01-04 17:01:37', 'u_10001', '2022-01-04 17:01:37', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220104170258377018730061', 'r_10001', '20220104170258373927022228', NULL, NULL, NULL, NULL, NULL, '2022-01-04 17:02:58', 'u_10001', '2022-01-04 17:02:58', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220105114733981005381142', 'r_10001', '20220105114733959419830633', NULL, NULL, NULL, NULL, NULL, '2022-01-05 11:47:34', 'u_10001', '2022-01-05 11:47:34', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220105114853016567031185', 'r_10001', '20220105114853009770929731', NULL, NULL, NULL, NULL, NULL, '2022-01-05 11:48:53', 'u_10001', '2022-01-05 11:48:53', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220105114952905926001383', 'r_10001', '20220105114952898199008485', NULL, NULL, NULL, NULL, NULL, '2022-01-05 11:49:53', 'u_10001', '2022-01-05 11:49:53', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220105115110451869010608', 'r_10001', '20220105115110443747972919', NULL, NULL, NULL, NULL, NULL, '2022-01-05 11:51:10', 'u_10001', '2022-01-05 11:51:10', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220105115312429276124126', 'r_10001', '20220105115312420625797138', NULL, NULL, NULL, NULL, NULL, '2022-01-05 11:53:12', 'u_10001', '2022-01-05 11:53:12', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220105161744770326040757', 'r_10001', '20220105161744748372947124', NULL, NULL, NULL, NULL, NULL, '2022-01-05 16:17:45', 'u_10001', '2022-01-05 16:17:45', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220105173218555623250410', 'r_10001', '20220105173218539504996827', NULL, NULL, NULL, NULL, NULL, '2022-01-05 17:32:19', 'u_10001', '2022-01-05 17:32:19', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220106094233485116656169', 'r_10001', '20220106094233475775871829', NULL, NULL, NULL, NULL, NULL, '2022-01-06 09:42:33', 'u_10001', '2022-01-06 09:42:33', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220106180309324599090466', 'r_10001', '20220106180309277908298683', NULL, NULL, NULL, NULL, NULL, '2022-01-06 18:03:09', 'u_10001', '2022-01-06 18:03:09', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220106180420749213880349', 'r_10001', '20220106180420743517167135', NULL, NULL, NULL, NULL, NULL, '2022-01-06 18:04:21', 'u_10001', '2022-01-06 18:04:21', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220106185654441001142915', 'r_10001', '20220106185654436934618789', NULL, NULL, NULL, NULL, NULL, '2022-01-06 18:56:54', 'u_10001', '2022-01-06 18:56:54', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220106191700207749361601', 'r_10001', '20220106191700202973162766', NULL, NULL, NULL, NULL, NULL, '2022-01-06 19:17:00', 'u_10001', '2022-01-06 19:17:00', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220107101830273039759088', 'r_10001', '20220107101830268276912144', NULL, NULL, NULL, NULL, NULL, '2022-01-07 10:18:30', 'u_10001', '2022-01-07 10:18:30', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220107101854230197313512', 'r_10001', '20220107101854226108278453', NULL, NULL, NULL, NULL, NULL, '2022-01-07 10:18:54', 'u_10001', '2022-01-07 10:18:54', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220107111138318171112171', 'r_10001', '20220107111138315200698219', NULL, NULL, NULL, NULL, NULL, '2022-01-07 11:11:38', 'u_10001', '2022-01-07 11:11:38', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220107154809275115924155', 'r_10001', '20220107154808756068707537', NULL, NULL, NULL, NULL, NULL, '2022-01-07 15:48:09', 'u_10001', '2022-01-07 15:48:09', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220110131512687804681341', 'r_10001', '20220110131512673482416844', NULL, NULL, NULL, NULL, NULL, '2022-01-10 13:15:13', 'u_10001', '2022-01-10 13:15:13', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220110131925698305339582', 'r_10001', '20220110131925693399966287', NULL, NULL, NULL, NULL, NULL, '2022-01-10 13:19:26', 'u_10001', '2022-01-10 13:19:26', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220112144206807943234217', 'r_10001', '20220112144206800011731606', NULL, NULL, NULL, NULL, NULL, '2022-01-12 14:42:07', 'u_10001', '2022-01-12 14:42:07', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220112151835605674517112', 'r_10001', '20220112151835601336960516', NULL, NULL, NULL, NULL, NULL, '2022-01-12 15:18:36', 'u_10001', '2022-01-12 15:18:36', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220112171351094430130853', 'r_10001', '20220112171351090662647367', NULL, NULL, NULL, NULL, NULL, '2022-01-12 17:13:51', 'u_10001', '2022-01-12 17:13:51', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220112173845610634433954', 'r_10001', '20220112173845599282286168', NULL, NULL, NULL, NULL, NULL, '2022-01-12 17:38:46', 'u_10001', '2022-01-12 17:38:46', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220112173912756804244476', 'r_10001', '20220112173912752301041531', NULL, NULL, NULL, NULL, NULL, '2022-01-12 17:39:13', 'u_10001', '2022-01-12 17:39:13', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220114150751715196635442', 'r_10001', '20220114150751701792713417', NULL, NULL, NULL, NULL, NULL, '2022-01-14 15:07:52', 'u_10001', '2022-01-14 15:07:52', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220114161325911912359487', 'r_10001', '20220114161325850832595643', NULL, NULL, NULL, NULL, NULL, '2022-01-14 16:13:26', 'u_10001', '2022-01-14 16:13:26', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220114161353211770075635', 'r_10001', '20220114161353204713704595', NULL, NULL, NULL, NULL, NULL, '2022-01-14 16:13:53', 'u_10001', '2022-01-14 16:13:53', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220117093816563592655529', 'r_10001', '20220117093816549773011922', NULL, NULL, NULL, NULL, NULL, '2022-01-17 09:38:17', 'u_10001', '2022-01-17 09:38:17', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220117094007282001418306', 'r_10001', '20220117094007268192631981', NULL, NULL, NULL, NULL, NULL, '2022-01-17 09:40:07', 'u_10001', '2022-01-17 09:40:07', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220117180144895035633791', 'r_10001', '20220117180144838084823717', NULL, NULL, NULL, NULL, NULL, '2022-01-17 18:01:45', 'u_10001', '2022-01-17 18:01:45', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220117180227572631564908', 'r_10001', '20220117180227567594447150', NULL, NULL, NULL, NULL, NULL, '2022-01-17 18:02:28', 'u_10001', '2022-01-17 18:02:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220121105427551516018291', 'r_10001', '20220121105427523928713981', NULL, NULL, NULL, NULL, NULL, '2022-01-21 10:54:28', 'u_10001', '2022-01-21 10:54:28', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220121105500019065074820', 'r_10001', '20220121105459982711047451', NULL, NULL, NULL, NULL, NULL, '2022-01-21 10:55:00', 'u_10001', '2022-01-21 10:55:00', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220121105556233926080730', 'r_10001', '20220121105556227507499314', NULL, NULL, NULL, NULL, NULL, '2022-01-21 10:55:56', 'u_10001', '2022-01-21 10:55:56', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403942062298085', 'nft_role', '20211231093550886104679185', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403942212875797', 'nft_role', '20220106185654436934618789', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403942248263517', 'nft_role', '20210628172931789866445893', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403942250688953', 'nft_role', '20220106180309277908298683', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403942298342865', 'nft_role', '20220104142542123788173684', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403942314552205', 'nft_role', '20211231093814920083731393', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403942364836857', 'nft_role', '20211231093619595541365083', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403942692922597', 'nft_role', '20211231153025601390727594', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403942759701026', 'nft_role', '20211231164719144298912294', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403942768492426', 'nft_role', '20220106180420743517167135', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403942904725974', 'nft_role', '202105171053129521252', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403942972044430', 'nft_role', '20211231093706836672865868', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403942987407936', 'nft_role', '20211231093524473606372261', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943092838070', 'nft_role', '20220112151835601336960516', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943144970949', 'nft_role', '20220104142911225215272748', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943160367170', 'nft_role', '20220107101830268276912144', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943192271267', 'nft_role', '20220105161744748372947124', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943210561762', 'nft_role', '20220114161353204713704595', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943326197085', 'nft_role', '20220122103652309369137209', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943411878931', 'nft_role', '20220114161325850832595643', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943445559382', 'nft_role', '20220122172844353167846312', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943472277472', 'nft_role', '20220121105459982711047451', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943584927826', 'nft_role', '20220110131512673482416844', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943623001713', 'nft_role', '20220104170136788977360618', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943682155030', 'nft_role', '20220104142602091834741487', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943719776547', 'nft_role', '20220107101854226108278453', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943722281400', 'nft_role', '20220107111138315200698219', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943730426835', 'nft_role', '20220121105556227507499314', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943731855042', 'nft_role', '20220104142622475939156297', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943759477204', 'nft_role', '20220105173218539504996827', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943769606236', 'nft_role', '20220121105427523928713981', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943791388105', 'nft_role', '20220104144352748677258417', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943808813249', 'nft_role', '20220117094007268192631981', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943936386418', 'nft_role', '20220114150751701792713417', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943946045030', 'nft_role', '20220104170258373927022228', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403943965400387', 'nft_role', '20220112144206800011731606', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944141513857', 'nft_role', '20220117093816549773011922', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944189089634', 'nft_role', '20220104142645108136051030', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944225958396', 'nft_role', '20220105114952898199008485', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944289372590', 'nft_role', '20220207103002096245592159', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944356203711', 'nft_role', '20220105115312420625797138', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944384053604', 'nft_role', '20220117180227567594447150', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944387421940', 'nft_role', '20220207102908259671003209', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944444490072', 'nft_role', '20220106191700202973162766', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944488230405', 'nft_role', '20220106094233475775871829', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944516675164', 'nft_role', '20220117180144838084823717', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944526671024', 'nft_role', '20220207102823996688018018', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944700891784', 'nft_role', '20220112173845599282286168', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944730022572', 'nft_role', '20220104142718128398296568', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944745725451', 'nft_role', '20220105114853009770929731', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944752696238', 'nft_role', '20220107154808756068707537', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944780445962', 'nft_role', '20220105114733959419830633', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944794110523', 'nft_role', '20220110131925693399966287', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944803286229', 'nft_role', '20220105115110443747972919', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944813980685', 'nft_role', '20220112171351090662647367', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944819018460', 'nft_role', '20220104142818741480966982', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944858288431', 'nft_role', '20220112173912752301041531', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');
INSERT INTO `t_role_menu` VALUES ('20220207162403944982833956', 'nft_role', '20220104142756029411135150', NULL, NULL, NULL, NULL, NULL, '2022-02-07 16:24:04', 'u_10001', '2022-02-07 16:24:04', 'u_10001');

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
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码,默认密码123',
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
INSERT INTO `t_user` VALUES ('u_10001', 'MakerOne', 'admin', '202cb962ac59075b964b07152d234b70', '男', '', '', '', NULL, '/avatar/default.jpg', 9, '2019-07-24 11:18:22', 'u_10001', '2022-01-21 11:36:56', 'u_10001', 1, NULL, NULL, NULL, NULL, NULL);

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
INSERT INTO `t_user_org` VALUES ('2c22a0a316fe4bc2bd7505709f771c73', 'u_10001', '202105171503402497159', 2, NULL, NULL, NULL, NULL, NULL, '2021-05-26 15:30:43', 'u_10001', '2021-05-26 15:30:43', 'u_10001');

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
INSERT INTO `t_user_role` VALUES ('0be7fe13b25f46a5a067beb1aa722b9e', 'u_10001', 'r_10001', '2021-05-26 15:30:43', 'u_10001', '2021-05-26 15:30:43', 'u_10001', NULL, NULL, NULL, NULL, NULL);

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
INSERT INTO `wx_cpconfig` VALUES ('1', '1', '', '', 1, NULL, NULL, NULL, NULL, NULL);

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
INSERT INTO `wx_mpconfig` VALUES ('1', '1', '', '', '', '', '', 1, 1, NULL, NULL, NULL, NULL, NULL);

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
  `apiV3Key` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'V3秘钥',
  `certificateFile` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '证书地址,绝对路径',
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
-- Records of wx_payconfig
-- ----------------------------
INSERT INTO `wx_payconfig` VALUES ('1', '1', '', '', '', '', '', '/static/1618616598_20220105_cert/', 'https://makerone.shengjian.net/nft/api/nft/wechat/pay/notify', 'MD5', 1, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
