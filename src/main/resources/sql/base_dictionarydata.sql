/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : 127.0.0.1:3306
 Source Schema         : gms_center

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 14/12/2021 17:51:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_dictionarydata
-- ----------------------------
DROP TABLE IF EXISTS `base_dictionarydata`;
CREATE TABLE `base_dictionarydata`
(
    `F_Id`               varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '自然主键',
    `F_ParentId`         varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级',
    `F_FullName`         varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
    `F_EnCode`           varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
    `F_SimpleSpelling`   longtext CHARACTER SET utf8 COLLATE utf8_general_ci    NULL COMMENT '拼音',
    `F_IsDefault`        int                                                    NULL DEFAULT NULL COMMENT '默认',
    `F_Description`      longtext CHARACTER SET utf8 COLLATE utf8_general_ci    NULL COMMENT '描述',
    `F_SortCode`         bigint                                                 NULL DEFAULT NULL COMMENT '排序',
    `F_EnabledMark`      int                                                    NULL DEFAULT NULL COMMENT '有效标志',
    `F_CreatorTime`      datetime                                               NULL DEFAULT NULL COMMENT '创建时间',
    `F_CreatorUserId`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
    `F_LastModifyTime`   datetime                                               NULL DEFAULT NULL COMMENT '修改时间',
    `F_LastModifyUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改用户',
    `F_DeleteMark`       int                                                    NULL DEFAULT NULL COMMENT '删除标志',
    `F_DeleteTime`       datetime                                               NULL DEFAULT NULL COMMENT '删除时间',
    `F_DeleteUserId`     varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除用户',
    `F_DictionaryTypeId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别主键',
    `F_TenantId`         varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`F_Id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '字典数据'
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
