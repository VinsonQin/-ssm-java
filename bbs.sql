/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : bbs

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 07/01/2025 20:17:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `cid` int NOT NULL AUTO_INCREMENT,
  `cpid` int NULL DEFAULT NULL,
  `cuid` int NULL DEFAULT NULL,
  `cinfo` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `ctime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 10, 6, '123', NULL);
INSERT INTO `comment` VALUES (2, 10, 6, '哈哈哈哈', NULL);
INSERT INTO `comment` VALUES (3, 10, 6, '笑死我了啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈', NULL);
INSERT INTO `comment` VALUES (4, 10, 6, '哈哈哈哈哈哈哈哈哈哈哈', NULL);
INSERT INTO `comment` VALUES (5, 10, 6, '123456', NULL);
INSERT INTO `comment` VALUES (6, 10, 6, '123', NULL);
INSERT INTO `comment` VALUES (7, 10, 6, '123456', NULL);
INSERT INTO `comment` VALUES (8, 10, 6, '1', NULL);
INSERT INTO `comment` VALUES (9, 10, 6, '2', NULL);
INSERT INTO `comment` VALUES (10, 10, 6, '3', NULL);
INSERT INTO `comment` VALUES (11, 10, 6, '测试', NULL);
INSERT INTO `comment` VALUES (12, 10, 6, '怎么只有我自己？', NULL);
INSERT INTO `comment` VALUES (13, 10, 6, '嗯', NULL);
INSERT INTO `comment` VALUES (14, 10, 6, '确实', NULL);
INSERT INTO `comment` VALUES (15, 10, 6, '哈哈哈', NULL);
INSERT INTO `comment` VALUES (16, 10, 6, '测试', '2024-12-24 22:18:33');
INSERT INTO `comment` VALUES (17, 10, 6, '？', NULL);
INSERT INTO `comment` VALUES (18, 10, 6, '？', NULL);
INSERT INTO `comment` VALUES (19, 10, 6, '？', NULL);
INSERT INTO `comment` VALUES (20, 40, 6, '6666', NULL);
INSERT INTO `comment` VALUES (21, 40, 6, '？？', NULL);
INSERT INTO `comment` VALUES (22, 40, 6, '？\r\n怎么回事\r\n哈哈哈哈', NULL);
INSERT INTO `comment` VALUES (24, 40, 6, '<p>吼吼吼吼吼吼吼吼吼吼吼吼吼吼吼<br/>哈哈哈哈<br/><br/></p>', NULL);
INSERT INTO `comment` VALUES (25, 21, 6, '<p><span>😀</span></p>', NULL);
INSERT INTO `comment` VALUES (26, 40, 6, '<p><span>😀</span></p>', NULL);
INSERT INTO `comment` VALUES (27, 42, 6, '<p>666666666</p>', NULL);
INSERT INTO `comment` VALUES (28, 42, 6, '<p>66666666666666666666666666666666666666666666666</p>', NULL);
INSERT INTO `comment` VALUES (29, 42, 6, '<p>666666666666666666666666666666666666666</p><p>6666666</p>', NULL);
INSERT INTO `comment` VALUES (30, 72, 6, '<p>吆西</p>', NULL);
INSERT INTO `comment` VALUES (31, 75, 9, '<p>你是666</p>', NULL);
INSERT INTO `comment` VALUES (32, 56, 9, '<p><span>😃</span></p>', NULL);
INSERT INTO `comment` VALUES (33, 61, 10, '<p>123<span>😀</span></p>', NULL);
INSERT INTO `comment` VALUES (34, 73, 6, '<p>111</p>', NULL);

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `pid` int NOT NULL AUTO_INCREMENT,
  `puid` int NULL DEFAULT NULL,
  `pname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `ptime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `pinfo` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `pimg` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (50, 6, '竟然......', '2024-12-30 20:05:50', '<p>666</p>', '/uploads/posts/post50/cf185780-4028-4015-9020-40b0f4ae6ef6_c.jpg');
INSERT INTO `post` VALUES (51, 6, '我去！！！', '2024-12-30 20:06:17', '<p>123456哎哎哎</p>', '/uploads/posts/post51/54426943-5628-48fb-b364-c12029b58187_r.jpg');
INSERT INTO `post` VALUES (52, 6, '嗯对啊', '2024-12-30 20:06:46', '<p>999</p>', '/uploads/posts/post52/653a879e-3e7a-4e78-ae5e-939e3858bb82_o.jpg');
INSERT INTO `post` VALUES (53, 6, '青岛大学', '2024-12-30 20:07:36', '<p>青岛大学</p>', '/uploads/posts/post53/7aad55e8-9937-4a1c-b9ab-02f16959a1b3_a.jpg');
INSERT INTO `post` VALUES (54, 6, '我来了', '2024-12-30 20:07:58', '<p>aaa</p>', '/uploads/posts/post54/f416bc2f-d4f5-416e-9e87-59d46cc027d3_u.jpg');
INSERT INTO `post` VALUES (55, 6, '哈哈哈哈', '2024-12-30 20:08:19', '<p>我</p>', '/uploads/posts/post55/41895e89-7a1a-4a97-a785-58dd67b2a7aa_p.jpg');
INSERT INTO `post` VALUES (56, 6, '想问一下', '2024-12-30 20:08:50', '<p>888</p>', '/uploads/posts/post56/a5398f52-a8c7-43e2-ba80-a422b9538199_t.jpg');
INSERT INTO `post` VALUES (57, 6, '还能这样？', '2024-12-30 20:09:08', '<p>哈哈哈哈</p>', '/uploads/posts/post57/06a9a980-a047-4693-9307-f174ce4e8421_v.jpg');
INSERT INTO `post` VALUES (58, 6, '666', '2024-12-30 20:09:39', '<p>----****</p>', '/uploads/posts/post58/6018c1af-a62c-429a-9d62-aaa9916383b4_m.jpg');
INSERT INTO `post` VALUES (59, 6, '难道？！', '2024-12-30 20:10:14', '<p>999</p>', '/uploads/posts/post59/b4e725d7-d789-49f9-90c6-80c62b5e7e5d_s.jpg');
INSERT INTO `post` VALUES (60, 6, '无敌啦', '2024-12-30 20:10:46', '<p>69966943453</p>', '/uploads/posts/post60/09480df5-57f0-4593-a6d7-3b916c74ac9e_n.jpg');
INSERT INTO `post` VALUES (61, 6, '风景大好呀', '2024-12-30 20:11:04', '<p>123</p>', '/uploads/posts/post61/86cf6b43-a3d6-4ce1-b82b-34354d35c46f_e.jpg');
INSERT INTO `post` VALUES (62, 6, '没有再好的了', '2024-12-30 20:11:23', '<p>147</p>', '/uploads/posts/post62/2a69c39e-c6f2-4dc6-999e-be0abc56e031_g.jpg');
INSERT INTO `post` VALUES (63, 6, '吃饭了吗', '2024-12-30 20:11:40', '<p>666</p>', '/uploads/posts/post63/e994b56d-cdd8-417f-a222-63875db007d8_h.jpg');
INSERT INTO `post` VALUES (64, 6, '额~', '2024-12-30 20:12:08', '<p>无敌</p>', '/uploads/posts/post64/be9d1619-f773-4550-afe1-462fcc275f6b_i.jpg');
INSERT INTO `post` VALUES (65, 6, '无敌', '2024-12-30 20:12:44', '<p>666</p>', '/uploads/posts/post65/1ee56911-ad22-4ebc-b6d3-34d68da7ef0c_aaa.jpg');
INSERT INTO `post` VALUES (66, 6, '吃吧', '2024-12-30 20:12:58', '<p>哈哈哈</p>', '/uploads/posts/post66/307a0144-2174-4327-b881-fde3908ebc07_屏幕截图 2023-12-14 103227.png');
INSERT INTO `post` VALUES (67, 6, 'BBS', '2024-12-30 20:13:24', '<p>啦啦啦</p>', '/uploads/posts/post67/38d50734-2e04-444e-8aac-598ef667c352_post11.png');
INSERT INTO `post` VALUES (68, 6, '问一问', '2024-12-30 20:14:14', '<p>嗯</p>', '/uploads/posts/post68/87155b80-5d90-42a0-a4e4-164d749181cd_a.png');
INSERT INTO `post` VALUES (69, 6, '666', '2024-12-30 20:14:25', '<p>6666</p>', '/uploads/posts/post69/f151ecd7-7edc-48dd-a5b5-c74de606772e_屏幕截图 2023-12-12 140209.png');
INSERT INTO `post` VALUES (70, 6, '叮叮当叮叮当.......', '2024-12-30 20:15:27', '<p>6啊</p>', '/uploads/posts/post70/d4ec34ce-881e-43db-a85b-10199de0536e_k.jpg');
INSERT INTO `post` VALUES (71, 6, '啦啦乐', '2024-12-30 20:16:40', '<p>哈哈哈</p>', '/uploads/posts/post71/f283451f-cf55-4d53-9502-75e6e277ce48_屏幕截图 2024-06-02 200614.png');
INSERT INTO `post` VALUES (72, 6, '测试', '2024-12-30 20:17:41', '<p>BBS中国</p><p>校园论坛</p><p><span>😀</span><span>😋</span></p>', '/uploads/posts/post72/09c76d60-afa8-4d7a-baa9-7d81b2dcfd00_a.jpg;/uploads/posts/post72/bd880ff1-e2b4-436f-b37a-5af2a4e7b078_b.jpg;/uploads/posts/post72/6c71fc11-8de2-4def-b4f6-d5a92aeefa9c_c.jpg');
INSERT INTO `post` VALUES (73, 6, 'ce', '2024-12-30 20:29:31', '<p>en</p>', '/uploads/posts/post73/1e2e240f-fbf7-4c58-a26d-66d0b509e011_c.jpg;/uploads/posts/post73/acb571fb-da0a-430f-b9af-21ed97850f97_d.jpg;/uploads/posts/post73/edcac06d-6af0-4abe-8805-b95151fe81c9_e.jpg');
INSERT INTO `post` VALUES (75, 9, '哈哈哈', '2024-12-30 23:09:12', '<p>666</p>', '/uploads/posts/post75/81a133d4-a6e4-4d16-ae8e-f66b1af533d4_屏幕截图 2023-12-04 144614.png;/uploads/posts/post75/bf2536d5-97a0-46a6-a012-202c72cd2354_屏幕截图 2023-12-12 140209.png');
INSERT INTO `post` VALUES (76, 6, '1230', '2024-12-30 23:32:30', '<p>123456</p>', '/uploads/posts/post76/133ad06e-7879-4f88-b846-b3d39e2775ff_屏幕截图 2024-04-25 232321.png;/uploads/posts/post76/b61f55c8-d519-4a9d-864d-3a573f77e54d_屏幕截图 2024-04-25 232442.png');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `upwd` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `uemail` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `uimg` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `uinfo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `ugender` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (6, 'vinson', '123456', 'doooopeisme@qq.com', '/uploads/user/user6/24ea491a-bd0f-4b32-b9f0-550175af4784_post11.png', 'ahhh', '0');
INSERT INTO `user` VALUES (7, '666人机', '1234', 'qinwensheng.qdu@vip.163.com', '/uploads/user/user7/13e3b605-b6e5-4200-9371-4a3f19673eff_post11.png', '无敌是我', '0');
INSERT INTO `user` VALUES (10, '123', '123456', '123@qq.com', '/uploads/user/user10/c001b2d0-2e57-4eb6-a162-b11b253a62ae_post11.png', NULL, '0');

SET FOREIGN_KEY_CHECKS = 1;
