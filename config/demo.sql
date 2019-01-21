/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50532
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 50532
 File Encoding         : 65001

 Date: 21/01/2019 09:50:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pets
-- ----------------------------
DROP TABLE IF EXISTS `pets`;
CREATE TABLE `pets`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `birth` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `createdAt` bigint(20) NOT NULL,
  `updatedAt` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pets
-- ----------------------------
INSERT INTO `pets` VALUES ('d-1521623016554', 'Odie1', 0, '2008-08-08', 1521623016554, 1521623016554, 0);

-- ----------------------------
-- Table structure for stu_score
-- ----------------------------
DROP TABLE IF EXISTS `stu_score`;
CREATE TABLE `stu_score`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `course` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `score` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of stu_score
-- ----------------------------
INSERT INTO `stu_score` VALUES (1, '李四', '语文', 30);
INSERT INTO `stu_score` VALUES (2, '李四', '数学', 30);
INSERT INTO `stu_score` VALUES (3, '李四', '英语', 100);
INSERT INTO `stu_score` VALUES (4, '王五', '语文', 30);
INSERT INTO `stu_score` VALUES (5, '王五', '数学', 40);
INSERT INTO `stu_score` VALUES (6, '赵六', '语文', 50);
INSERT INTO `stu_score` VALUES (8, '赵六', '数学', 70);
INSERT INTO `stu_score` VALUES (9, '王五', '英语', 70);
INSERT INTO `stu_score` VALUES (10, '赵六', '英语', 60);

-- ----------------------------
-- Table structure for stu_score_avg
-- ----------------------------
DROP TABLE IF EXISTS `stu_score_avg`;
CREATE TABLE `stu_score_avg`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `avg` decimal(5, 0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of stu_score_avg
-- ----------------------------
INSERT INTO `stu_score_avg` VALUES (1, '李四', 53);
INSERT INTO `stu_score_avg` VALUES (2, '王五', 47);
INSERT INTO `stu_score_avg` VALUES (3, '赵六', 60);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `age` int(4) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'hyy-1', 26);
INSERT INTO `user` VALUES (3, 'redis-1', 88);
INSERT INTO `user` VALUES (4, 'sun', 23);
INSERT INTO `user` VALUES (5, 'sunss', 23);
INSERT INTO `user` VALUES (6, 'sun3s', 23);

-- ----------------------------
-- Procedure structure for getScore
-- ----------------------------
DROP PROCEDURE IF EXISTS `getScore`;
delimiter ;;
CREATE PROCEDURE `getScore`(avg_score int, o int)
begin 
	if o = '1' then 
		SELECT * from stu_score_avg WHERE avg > avg_score;
	else 
		SELECT * from stu_score_avg WHERE avg <= avg_score;
	end if;
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table stu_score
-- ----------------------------
DROP TRIGGER IF EXISTS `updateAvgScore`;
delimiter ;;
CREATE TRIGGER `updateAvgScore` AFTER UPDATE ON `stu_score` FOR EACH ROW BEGIN
	UPDATE stu_score_avg set avg = (SELECT avg(score) from stu_score WHERE name = old.name) WHERE name = old.name;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
