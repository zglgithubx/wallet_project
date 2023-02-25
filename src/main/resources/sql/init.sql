SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for balance_record
-- ----------------------------
DROP TABLE IF EXISTS `balance_record`;
CREATE TABLE `balance_record`  (
                                   `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
                                   `user_id` bigint(0) NOT NULL COMMENT '用户id',
                                   `amount` decimal(20, 2) UNSIGNED NOT NULL COMMENT '金额',
                                   `type` tinyint(1) NOT NULL COMMENT '余额变动类型：1-充值、2-消费、3-退款',
                                   `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of balance_record
-- ----------------------------
INSERT INTO `balance_record` VALUES (1, 1, 1.00, 1, '2023-02-25 21:00:43');
INSERT INTO `balance_record` VALUES (3, 3, 1.00, 2, '2023-02-25 11:50:42');
INSERT INTO `balance_record` VALUES (4, 4, 6.00, 3, '2023-02-25 11:53:34');
INSERT INTO `balance_record` VALUES (5, 1, 3.50, 1, '2023-02-25 12:11:06');
INSERT INTO `balance_record` VALUES (6, 1, 5.56, 1, '2023-02-25 12:11:35');
INSERT INTO `balance_record` VALUES (7, 1, 2.00, 3, '2023-02-25 12:14:20');
INSERT INTO `balance_record` VALUES (8, 1, 2.00, 2, '2023-02-25 12:14:44');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                         `balance` decimal(20, 2) UNSIGNED NOT NULL COMMENT '余额',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 10.06);
INSERT INTO `user` VALUES (2, 2.00);
INSERT INTO `user` VALUES (3, 2.00);
INSERT INTO `user` VALUES (4, 6.00);
INSERT INTO `user` VALUES (5, 0.00);

SET FOREIGN_KEY_CHECKS = 1;
