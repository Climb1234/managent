-- ----------------------------
-- Table structure for demo_excel
-- ----------------------------
DROP TABLE IF EXISTS `demo_excel`;
CREATE TABLE `demo_excel`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `birthday` datetime(0) NULL DEFAULT NULL,
  `double_data` double(10, 4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_excel
-- ----------------------------
INSERT INTO `demo_excel` VALUES (15, 'test', '2010-06-09 11:11:11', 12345.5678);
INSERT INTO `demo_excel` VALUES (23, 'test', '2010-06-09 11:11:11', 12345.5678);
INSERT INTO `demo_excel` VALUES (24, 'test', '2010-06-09 11:11:11', 12345.5678);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `pid` int(20) NULL DEFAULT NULL COMMENT '父级权限id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `type` int(10) NULL DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求路径',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前端资源路径',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `sort` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (3, 0, '测试', 'el-icon-star-off', 0, '/test', 'test/excel-import', '2020-01-02 17:17:46', 1);
INSERT INTO `sys_permission` VALUES (5, 3, '动态Form', 'el-icon-star-off', 1, 'survey-form', 'test/survey-form', '2020-01-02 17:17:46', 2);
INSERT INTO `sys_permission` VALUES (7, 0, '系统设置', 'el-icon-setting', 0, '/sys', 'sys/user', '2020-01-06 10:59:59', 2);
INSERT INTO `sys_permission` VALUES (8, 7, '用户管理', 'el-icon-user', 1, 'user', 'sys/user', '2020-01-06 11:02:49', 1);
INSERT INTO `sys_permission` VALUES (9, 7, '角色管理', 'el-icon-star-on', 1, 'role', 'sys/role', '2020-01-06 13:22:25', 2);
INSERT INTO `sys_permission` VALUES (10, 7, '菜单管理', 'el-icon-menu', 1, 'menu', 'sys/menu', '2020-07-02 22:06:22', 3);
INSERT INTO `sys_permission` VALUES (12, 3, 'Excel导入', 'el-icon-star-off', 0, 'excel-import', 'test/excel-import', NULL, 1);
INSERT INTO `sys_permission` VALUES (23, 3, '表单设计', 'el-icon-star-off', 0, 'form-design', 'test/form-design', NULL, 3);
INSERT INTO `sys_permission` VALUES (28, 0, 'ddddd', NULL, 0, '/ddddddddddddddddddddd', NULL, NULL, 1);
INSERT INTO `sys_permission` VALUES (29, 28, 'ddddddddddd', NULL, 0, 'ssssssssssssss', NULL, NULL, 1);
INSERT INTO `sys_permission` VALUES (30, 28, 'sss', NULL, 0, 'sssss', NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', '超级管理员', '2020-01-02 17:03:13', NULL);
INSERT INTO `sys_role` VALUES (2, '普通用户', '普通用户', '2020-01-07 11:46:00', NULL);
INSERT INTO `sys_role` VALUES (11, 'a', 'a', '2020-07-02 21:28:53', '2020-07-02 21:28:52');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` int(20) NULL DEFAULT NULL COMMENT '角色ID',
  `permission_id` int(20) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色和权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1, 1);
INSERT INTO `sys_role_permission` VALUES (3, 1, 3);
INSERT INTO `sys_role_permission` VALUES (4, 1, 7);
INSERT INTO `sys_role_permission` VALUES (11, 10, 7);
INSERT INTO `sys_role_permission` VALUES (12, 9, 3);
INSERT INTO `sys_role_permission` VALUES (13, 9, 7);
INSERT INTO `sys_role_permission` VALUES (16, 11, 3);
INSERT INTO `sys_role_permission` VALUES (23, 2, 3);
INSERT INTO `sys_role_permission` VALUES (24, 2, 7);
INSERT INTO `sys_role_permission` VALUES (25, 2, 28);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `role_id` int(20) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '用户角色ID(默认普通用户)',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '1', '1', 00000000000000000002, 'JPG.jpg', '2020-01-14 11:53:30');
INSERT INTO `sys_user` VALUES (2, '2', '2', 00000000000000000001, 'JPG.jpg', '2020-01-10 13:53:01');
INSERT INTO `sys_user` VALUES (4, '4', '4', 00000000000000000002, NULL, '2020-01-07 14:19:01');
INSERT INTO `sys_user` VALUES (5, '5', '5', 00000000000000000002, NULL, '2020-01-07 14:19:07');
INSERT INTO `sys_user` VALUES (6, 'admin', 'admin', 00000000000000000001, 'test.png', '2020-01-11 14:57:51');

