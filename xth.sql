
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `ID` int(19) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CREATOR_ID` int(19) DEFAULT NULL COMMENT '创建人id',
  `CREATOR` varchar(255) DEFAULT NULL COMMENT '创建人',
  `UPDATER_ID` int(19) DEFAULT NULL COMMENT '更新人id',
  `UPDATER` varchar(255) DEFAULT NULL COMMENT '更新人',
  `LOCK_VERSION` int(19) DEFAULT NULL COMMENT '乐观锁',
  `TITLE` varchar(255) DEFAULT NULL COMMENT '内容标题',
  `SLUG` varchar(255) DEFAULT NULL COMMENT '内容缩略名',
  `AUTHOR_ID` varchar(255) DEFAULT NULL COMMENT '作者id',
  `IMG_URL` varchar(255) DEFAULT NULL COMMENT '图片url',
  `IS_HOT` varchar(255) DEFAULT NULL COMMENT '是否热门推荐,0:否，1：是',
  `STATUS` varchar(255) DEFAULT NULL COMMENT '文章状态',
  `HITS` int(19) DEFAULT NULL COMMENT '点击次数',
  `CONTENT` text COMMENT '内容',
  `AUTHOR` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='@Date: 2018/10/4 0010 下午 22:10';

-- ----------------------------
-- Table structure for t_city
-- ----------------------------
DROP TABLE IF EXISTS `t_city`;
CREATE TABLE `t_city` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `short_name` varchar(50) NOT NULL,
  `CREATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATOR_ID` int(19) DEFAULT NULL,
  `CREATOR` varchar(255) DEFAULT NULL,
  `UPDATER_ID` int(19) DEFAULT NULL,
  `UPDATER` varchar(255) DEFAULT NULL,
  `LOCK_VERSION` int(19) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_client
-- ----------------------------
DROP TABLE IF EXISTS `t_client`;
CREATE TABLE `t_client` (
  `ID` int(19) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CREATOR_ID` int(19) DEFAULT NULL COMMENT '创建人id',
  `CREATOR` varchar(255) DEFAULT NULL COMMENT '创建人',
  `UPDATER_ID` int(19) DEFAULT NULL COMMENT '更新人id',
  `UPDATER` varchar(255) DEFAULT NULL COMMENT '更新人',
  `LOCK_VERSION` int(19) DEFAULT NULL COMMENT '乐观锁',
  `NICKNAME` varchar(255) DEFAULT NULL COMMENT '昵称',
  `USERNAME` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `GENDER` varchar(255) DEFAULT NULL COMMENT '性别',
  `ID_NUMBER` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `COUPON_IDS` varchar(255) DEFAULT NULL COMMENT '已领优惠券的ID集合,已|分割',
  `CLIENT_LEVEL_ID` int(19) DEFAULT NULL COMMENT '客户会员等级id',
  `LOGIN_NUM` int(19) DEFAULT NULL COMMENT '累积登录次数',
  `LAST_LOGIN_TIME` timestamp NULL DEFAULT NULL COMMENT '最后一次登录时间',
  `OPEN_ID` varchar(255) DEFAULT NULL,
  `MOBILE` varchar(255) DEFAULT NULL COMMENT '手机号',
  `LAST_LONGITUDE` varchar(255) DEFAULT NULL COMMENT '最近登录经度',
  `LAST_LATITUDE` varchar(255) DEFAULT NULL COMMENT '最近登录纬度',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='@Date: 2018/9/18 0010 下午 22:12';

-- ----------------------------
-- Table structure for t_client_coupon
-- ----------------------------
DROP TABLE IF EXISTS `t_client_coupon`;
CREATE TABLE `t_client_coupon` (
  `ID` int(19) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CREATOR_ID` int(19) DEFAULT NULL COMMENT '创建人id',
  `CREATOR` varchar(255) DEFAULT NULL COMMENT '创建人',
  `UPDATER_ID` int(19) DEFAULT NULL COMMENT '更新人id',
  `UPDATER` varchar(255) DEFAULT NULL COMMENT '更新人',
  `LOCK_VERSION` int(19) DEFAULT NULL COMMENT '乐观锁',
  `CLIENT_ID` int(19) DEFAULT NULL COMMENT '客户id',
  `COUPON_ID` int(19) DEFAULT NULL COMMENT '优惠券id',
  `IS_USED` varchar(255) DEFAULT NULL COMMENT '是否使用过',
  `AVAILABLE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COMMENT='@Date: 2018/9/25 0010 下午 22:10';

-- ----------------------------
-- Table structure for t_coach
-- ----------------------------
DROP TABLE IF EXISTS `t_coach`;
CREATE TABLE `t_coach` (
  `ID` int(19) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CREATOR_ID` int(19) DEFAULT NULL COMMENT '创建人id',
  `CREATOR` varchar(255) DEFAULT NULL COMMENT '创建人',
  `UPDATER_ID` int(19) DEFAULT NULL COMMENT '更新人id',
  `UPDATER` varchar(255) DEFAULT NULL COMMENT '更新人',
  `LOCK_VERSION` int(19) DEFAULT NULL COMMENT '乐观锁',
  `NAME` varchar(255) DEFAULT NULL COMMENT '教练姓名',
  `GENDER` varchar(255) DEFAULT NULL COMMENT '性别',
  `BIRTH_YEAR` int(19) DEFAULT NULL COMMENT '出生年份（到年）',
  `ID_NUMBER` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `PHONE` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `STORE_ID` int(19) DEFAULT NULL COMMENT '所属场馆id',
  `SPECIALITY` varchar(255) DEFAULT NULL COMMENT '特长描述',
  `IMG_URL` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `STAR_LEVEL` int(19) DEFAULT NULL COMMENT '星级',
  `IS_GOLD` varchar(255) DEFAULT NULL COMMENT '是否是金牌教练',
  `GOLD_LEVEL` varchar(255) DEFAULT NULL COMMENT '0代表非金牌教练，非0是金牌教练，数字越大越优先显示',
  `SORT_INDEX` varchar(255) DEFAULT NULL COMMENT '排序',
  `TEACH_AGE` int(19) DEFAULT NULL COMMENT '教龄（年）',
  `START_YEAR` int(19) DEFAULT NULL COMMENT '最初执教时间（年）',
  `AWARD_NUM` int(19) DEFAULT NULL COMMENT '获奖数量',
  `CCIE_NUM` varchar(255) DEFAULT NULL COMMENT '证书数量',
  `COACH_LEVEL_ID` int(19) DEFAULT NULL COMMENT '教练会员等级',
  `CLASS_NUM` int(19) DEFAULT NULL COMMENT '课程数量',
  `CITY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='@Date: 2018/9/17 0010 下午 22:12';

-- ----------------------------
-- Table structure for t_company
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `ID` int(19) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CREATOR_ID` int(19) DEFAULT NULL COMMENT '创建人id',
  `CREATOR` varchar(255) DEFAULT NULL COMMENT '创建人',
  `UPDATER_ID` int(19) DEFAULT NULL COMMENT '更新人id',
  `UPDATER` varchar(255) DEFAULT NULL COMMENT '更新人',
  `LOCK_VERSION` int(19) DEFAULT NULL COMMENT '乐观锁',
  `NAME` varchar(255) DEFAULT NULL COMMENT '商户公司营业执照名称',
  `COMPANY_LEVEL_ID` int(19) DEFAULT NULL COMMENT '商户等级ID',
  `STAR_LEVEL` int(19) DEFAULT NULL COMMENT '商户星级',
  `IDENTIFICATION` varchar(255) DEFAULT NULL COMMENT '纳税人识别号',
  `LEGAL_PERSON` varchar(255) DEFAULT NULL COMMENT '法人',
  `SET_UP_TIME` timestamp NULL DEFAULT NULL COMMENT '成立时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='null';

-- ----------------------------
-- Table structure for t_coupon
-- ----------------------------
DROP TABLE IF EXISTS `t_coupon`;
CREATE TABLE `t_coupon` (
  `ID` int(19) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CREATOR_ID` int(19) DEFAULT NULL COMMENT '创建人id',
  `CREATOR` varchar(255) DEFAULT NULL COMMENT '创建人',
  `UPDATER_ID` int(19) DEFAULT NULL COMMENT '更新人id',
  `UPDATER` varchar(255) DEFAULT NULL COMMENT '更新人',
  `LOCK_VERSION` int(19) DEFAULT NULL COMMENT '乐观锁',
  `NAME` varchar(255) DEFAULT NULL COMMENT '活动名称',
  `CONTENT` varchar(255) NOT NULL COMMENT '活动内容',
  `EFFECT_TIME` date DEFAULT NULL COMMENT '生效时间',
  `EXPIRE_TIME` date DEFAULT NULL COMMENT '过期时间',
  `COUPON_APPLY_SCOPE` varchar(255) DEFAULT NULL COMMENT '适用范围',
  `APPLY_PROVINCE_ID` int(19) DEFAULT NULL COMMENT '适用的省份id，只要在适用范围是某个省份才有值',
  `APPLY_CITY_ID` int(19) DEFAULT NULL COMMENT '适用的城市id，只要在适用范围是某个城市才有值',
  `APPLY_COMPANY_ID` int(19) DEFAULT NULL COMMENT '适用的商户id，只要在适用范围是某个商户才有值',
  `APPLY_STORE_ID` int(19) DEFAULT NULL COMMENT '适用的场馆id，只要在适用范围是某个场馆才有值',
  `TYPE` varchar(255) DEFAULT NULL,
  `AVAILABLE` varchar(255) DEFAULT NULL,
  `CITY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='@Date: 2018/9/25 0010 下午 22:10';

-- ----------------------------
-- Table structure for t_level
-- ----------------------------
DROP TABLE IF EXISTS `t_level`;
CREATE TABLE `t_level` (
  `ID` int(19) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CREATOR_ID` int(19) DEFAULT NULL COMMENT '创建人id',
  `CREATOR` varchar(255) DEFAULT NULL COMMENT '创建人',
  `UPDATER_ID` int(19) DEFAULT NULL COMMENT '更新人id',
  `UPDATER` varchar(255) DEFAULT NULL COMMENT '更新人',
  `LOCK_VERSION` int(19) DEFAULT NULL COMMENT '乐观锁',
  `NAME` varchar(255) DEFAULT NULL COMMENT '等级名称',
  `TYPE` varchar(255) DEFAULT NULL COMMENT '等级类型',
  `URL` varchar(255) DEFAULT NULL COMMENT '等级图标地址',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='@Date: 2018/9/10 0010 下午 22:10';

-- ----------------------------
-- Table structure for t_menu_item
-- ----------------------------
DROP TABLE IF EXISTS `t_menu_item`;
CREATE TABLE `t_menu_item` (
  `ID` int(19) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CREATOR_ID` int(19) DEFAULT NULL COMMENT '创建人id',
  `CREATOR` varchar(255) DEFAULT NULL COMMENT '创建人',
  `UPDATER_ID` int(19) DEFAULT NULL COMMENT '更新人id',
  `UPDATER` varchar(255) DEFAULT NULL COMMENT '更新人',
  `LOCK_VERSION` int(19) DEFAULT NULL COMMENT '乐观锁',
  `NAME` varchar(255) DEFAULT NULL COMMENT '权限名',
  `TYPE` varchar(255) DEFAULT NULL COMMENT '权限类型',
  `URL` varchar(255) DEFAULT NULL COMMENT '菜单url',
  `PARENT_ID` int(19) DEFAULT NULL COMMENT '父菜单',
  `PARENT_PATH` varchar(255) DEFAULT NULL COMMENT '父菜单路径',
  `PERMISSION` varchar(255) DEFAULT NULL COMMENT '权限控制',
  `SORT_INDEX` int(19) DEFAULT NULL COMMENT '排序',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '备注',
  `LOCKED` varchar(255) DEFAULT NULL,
  `JS_URL` varchar(255) DEFAULT NULL COMMENT 'js真实路径，优先取该值',
  PRIMARY KEY (`ID`),
  KEY `IDX_MI_NAME` (`NAME`),
  KEY `IDX_MI_PARENT` (`PARENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=56310 DEFAULT CHARSET=utf8 COMMENT='@author admin';

-- ----------------------------
-- Table structure for t_operator
-- ----------------------------
DROP TABLE IF EXISTS `t_operator`;
CREATE TABLE `t_operator` (
  `ID` int(19) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CREATOR_ID` int(19) DEFAULT NULL COMMENT '创建人id',
  `CREATOR` varchar(255) DEFAULT NULL COMMENT '创建人',
  `UPDATER_ID` int(19) DEFAULT NULL COMMENT '更新人id',
  `UPDATER` varchar(255) DEFAULT NULL COMMENT '更新人',
  `LOCK_VERSION` int(19) DEFAULT NULL COMMENT '乐观锁',
  `LOGIN_NAME` varchar(255) DEFAULT NULL COMMENT '登录名',
  `PASSWORD` varchar(255) DEFAULT NULL COMMENT '密码',
  `MOBILE` varchar(255) DEFAULT NULL COMMENT '电话号码',
  `LOGIN_TIME` timestamp NULL DEFAULT NULL COMMENT '登录时间',
  `LAST_LOGIN_TIME` timestamp NULL DEFAULT NULL COMMENT '上次登录时间',
  `PASSWORD_ERROR_TIMES` int(19) DEFAULT NULL COMMENT '登录错误次数',
  PRIMARY KEY (`ID`),
  KEY `IDX_O_LOGIN` (`LOGIN_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='@date on 2017/12/28.';

-- ----------------------------
-- Table structure for t_operator_role
-- ----------------------------
DROP TABLE IF EXISTS `t_operator_role`;
CREATE TABLE `t_operator_role` (
  `OPERATOR_ID` decimal(19,0) DEFAULT NULL,
  `ROLE_ID` decimal(19,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_rebate
-- ----------------------------
DROP TABLE IF EXISTS `t_rebate`;
CREATE TABLE `t_rebate` (
  `ID` int(19) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CREATOR_ID` int(19) DEFAULT NULL COMMENT '创建人id',
  `CREATOR` varchar(255) DEFAULT NULL COMMENT '创建人',
  `UPDATER_ID` int(19) DEFAULT NULL COMMENT '更新人id',
  `UPDATER` varchar(255) DEFAULT NULL COMMENT '更新人',
  `LOCK_VERSION` int(19) DEFAULT NULL COMMENT '乐观锁',
  `CLIENT_ID` int(19) DEFAULT NULL COMMENT '用户ID',
  `STORE_ID` int(19) DEFAULT NULL COMMENT '场馆ID',
  `CONSUME_MONEY` varchar(255) DEFAULT NULL COMMENT '消费金额',
  `RETURN_MONEY` varchar(255) DEFAULT NULL COMMENT '返回金额',
  `COUPON_ID` int(19) DEFAULT NULL COMMENT '使用的优惠券',
  `ATTACHMENT` varchar(255) DEFAULT NULL COMMENT '附件',
  `STATUS` varchar(255) DEFAULT NULL COMMENT '返利状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='null';

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `ID` int(19) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CREATOR_ID` int(19) DEFAULT NULL COMMENT '创建人id',
  `CREATOR` varchar(255) DEFAULT NULL COMMENT '创建人',
  `UPDATER_ID` int(19) DEFAULT NULL COMMENT '更新人id',
  `UPDATER` varchar(255) DEFAULT NULL COMMENT '更新人',
  `LOCK_VERSION` int(19) DEFAULT NULL COMMENT '乐观锁',
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `ROLE_TYPE` varchar(255) DEFAULT NULL COMMENT '类型',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '备注',
  `STATUS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX_R_NAME` (`NAME`),
  KEY `IDX_R_TYPE` (`ROLE_TYPE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='@date on 2017/12/28.';

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `ROLE_ID` decimal(19,0) DEFAULT NULL,
  `MENU_ID` decimal(19,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_store
-- ----------------------------
DROP TABLE IF EXISTS `t_store`;
CREATE TABLE `t_store` (
  `ID` int(19) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CREATOR_ID` int(19) DEFAULT NULL COMMENT '创建人id',
  `CREATOR` varchar(255) DEFAULT NULL COMMENT '创建人',
  `UPDATER_ID` int(19) DEFAULT NULL COMMENT '更新人id',
  `UPDATER` varchar(255) DEFAULT NULL COMMENT '更新人',
  `LOCK_VERSION` int(19) DEFAULT NULL COMMENT '乐观锁',
  `NAME` varchar(255) DEFAULT NULL COMMENT '场馆名称',
  `COMPANY_ID` int(19) DEFAULT NULL COMMENT '场馆所属商户公司ID',
  `CITY_ID` int(19) DEFAULT NULL COMMENT '市',
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `LONGITUDE` varchar(255) DEFAULT NULL COMMENT '经度',
  `LATITUDE` varchar(255) DEFAULT NULL COMMENT '纬度',
  `CONTACT_NAME` varchar(255) DEFAULT NULL COMMENT '联系人姓名',
  `CONTACT_PHONE` varchar(255) DEFAULT NULL COMMENT '联系人电话',
  `STORE_LEVEL_ID` int(19) DEFAULT NULL COMMENT '场馆会员等级id',
  `STAR_LEVEL` int(19) DEFAULT NULL COMMENT '星级',
  `IMG_URL` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `RUN_YEARS` varchar(255) DEFAULT NULL COMMENT '经营时间（多少年）',
  `TOTAL_AREA` varchar(255) DEFAULT NULL COMMENT '总面积',
  `TRAINING_AREA` varchar(255) DEFAULT NULL COMMENT '训练面积',
  `SITE_NUMS` int(19) DEFAULT NULL COMMENT '训练场地数量',
  `TOTAL_STU_NUMS` int(19) DEFAULT NULL COMMENT '累计学员人数',
  `IN_STU_NUMS` int(19) DEFAULT NULL COMMENT '在馆学员人数',
  `DISCOUNT_TYPE` varchar(255) DEFAULT NULL COMMENT '场馆折扣，即优惠的百分数',
  `IS_HOT` varchar(255) DEFAULT NULL COMMENT '是否是热门场馆',
  `HOT_LEVEL` int(19) DEFAULT NULL COMMENT '0代表非热门场馆，非0是热门场馆，数字越大越优先显示',
  `SPECIAL_LEVEL` int(19) DEFAULT NULL COMMENT '0代表非特色场馆，非0是特色场馆，数字越大越优先显示',
  `DISCOUNT_CONTENT` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8 COMMENT='null';