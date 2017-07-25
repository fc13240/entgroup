/*init sys_authority*/
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon_open,icon_close,is_open,deleted) VALUES 
(1001,'系统首页',2,'module:systemindex:view','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_open.png','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_close.png',0,1);

INSERT INTO sys_authority (id,NAME,type,authority_identity,icon_open,icon_close,is_open,deleted) VALUES 
(1002,'系统日志',1,'module:syslog:view','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_open.png','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_close.png',0,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100201,'系统日志列表',1,'sys:syslog:list','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1002,0);

INSERT INTO sys_authority (id,NAME,type,authority_identity,icon_open,icon_close,is_open,deleted) VALUES 
(1003,'意见反馈',1,'module:feedback:view','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_open.png','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_close.png',0,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100301,'意见反馈列表',1,'sys:feedback:list','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1003,0);

INSERT INTO sys_authority (id,NAME,type,authority_identity,icon_open,icon_close,is_open,deleted) VALUES 
(1004,'资讯管理',1,'module:information:view','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_open.png','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_close.png',0,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100401,'资讯列表',1,'sys:information:list','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1004,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100402,'新增资讯',1,'sys:information:add','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1004,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100403,'编辑资讯',1,'sys:information:update','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1004,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100404,'删除资讯',1,'sys:information:delete','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1004,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100405,'发布/取消发布资讯',1,'sys:information:publish','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1004,0);

INSERT INTO sys_authority (id,NAME,type,authority_identity,icon_open,icon_close,is_open,deleted) VALUES 
(1005,'消息管理',1,'module:message:view','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_open.png','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_close.png',0,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100501,'消息列表',1,'sys:message:list','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1005,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100502,'新增消息',1,'sys:message:add','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1005,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100503,'编辑消息',1,'sys:message:update','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1005,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100504,'删除消息',1,'sys:message:delete','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1005,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100505,'发布/取消发布消息',1,'sys:message:publish','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1005,0);

INSERT INTO sys_authority (id,NAME,type,authority_identity,icon_open,icon_close,is_open,deleted) VALUES 
(1006,'角色管理',2,'module:role:view','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_open.png','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_close.png',0,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100601,'角色列表',1,'sys:role:list','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1006,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100602,'新增角色',2,'sys:role:add','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1006,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100603,'编辑角色',2,'sys:role:update','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1006,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100604,'删除角色',2,'sys:role:delete','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1006,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100605,'设置权限',2,'sys:role:setauthority','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1006,0);

INSERT INTO sys_authority (id,NAME,type,authority_identity,icon_open,icon_close,is_open,deleted) VALUES 
(1007,'用户管理',2,'module:user:view','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_open.png','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_close.png',0,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100701,'用户列表',2,'sys:user:list','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1007,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100702,'新增用户',2,'sys:user:add','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1007,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100703,'编辑用户',2,'sys:user:update','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1007,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100704,'删除用户',2,'sys:user:delete','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1007,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100705,'账号启用/停用',2,'sys:user:enabled','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1007,0);

INSERT INTO sys_authority (id,NAME,type,authority_identity,icon_open,icon_close,is_open,deleted) VALUES 
(1008,'广告主管理',2,'module:advertiser:view','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_open.png','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_close.png',0,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100801,'广告主列表',2,'sys:advertiser:list','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1008,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100802,'新增广告主',2,'sys:advertiser:add','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1008,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100803,'编辑广告主',2,'sys:advertiser:update','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1008,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100804,'删除广告主',2,'sys:advertiser:delete','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1008,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100805,'账号启用/停用',2,'sys:advertiser:enabled','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1008,0);

INSERT INTO sys_authority (id,NAME,type,authority_identity,icon_open,icon_close,is_open,deleted) VALUES 
(1009,'SDK下载',2,'module:sdk:view','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_open.png','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_close.png',0,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100901,'浏览当前发布版本',2,'sys:sdk:view','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1009,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100902,'查看历史版本',2,'sys:sdk:list','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1009,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100903,'上传SDK',1,'sys:sdk:upload','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1009,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100904,'发布SDK',1,'sys:sdk:publish','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1009,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(100905,'下载SDK',1,'sys:sdk:download','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1009,0);

INSERT INTO sys_authority (id,NAME,type,authority_identity,icon_open,icon_close,is_open,deleted) VALUES 
(1010,'广告中心',2,'module:advertisingcenter:view','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_open.png','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_close.png',0,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101001,'广告列表',2,'sys:advertising:list','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1010,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101002,'添加广告',2,'sys:advertising:add','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1010,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101003,'删除广告',2,'sys:advertising:delete','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1010,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101004,'查看已标注的视频列表',2,'sys:video:loadlabelad','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1010,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101005,'广告设置',2,'sys:video:list','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1010,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(10100501,'手动标注广告',2,'sys:videoad:label','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,101005,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(10100502,'智能匹配广告',2,'sys:videoad:matching','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,101005,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(10100503,'查看视频中的广告列表',2,'sys:videoad:reloadadlist','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,101005,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(10100504,'删除视频中的广告',2,'sys:videoad:deletead','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,101005,0);

INSERT INTO sys_authority (id,NAME,type,authority_identity,icon_open,icon_close,is_open,deleted) VALUES 
(1011,'数据中心',2,'module:datacenter:view','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_open.png','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_close.png',0,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101101,'收入统计',2,'sys:datastatistics:income','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1011,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101102,'广告统计',2,'sys:datastatistics:advertising','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1011,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101103,'流量统计',2,'sys:datastatistics:flow','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1011,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101104,'SDK下载统计',2,'sys:datastatistics:sdkdownload','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1011,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101105,'广告主用户统计',2,'sys:datastatistics:advertiser','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1011,0);

INSERT INTO sys_authority (id,NAME,type,authority_identity,icon_open,icon_close,is_open,deleted) VALUES 
(1012,'财务中心',2,'module:financecenter:view','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_open.png','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_close.png',0,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101201,'账号充值',2,'sys:finance:recharge','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1012,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101202,'充值记录',2,'sys:finance:rechargerecord','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1012,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101203,'收入记录',1,'sys:finance:incomerecord','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1012,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101204,'转账汇款',1,'sys:finance:transfer','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1012,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101205,'支出记录',1,'sys:finance:expenserecord','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1012,0);

INSERT INTO sys_authority (id,NAME,type,authority_identity,icon_open,icon_close,is_open,deleted) VALUES 
(1013,'个人中心',2,'module:personalcenter:view','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_open.png','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_close.png',0,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101301,'基本信息设置',2,'sys:personal:usersetting','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1013,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101302,'企业信息设置',2,'sys:personal:entertisesetting','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1013,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101303,'消息中心',2,'sys:personal:messagelist','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1013,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101304,'通知设置',2,'sys:personal:notifysetting','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1013,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101305,'修改密码',2,'sys:personal:modifypassword','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1013,0);

INSERT INTO sys_authority (id,NAME,type,authority_identity,icon_open,icon_close,is_open,deleted) VALUES 
(1014,'数据抓取',1,'module:datacapture:view','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_open.png','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/1_close.png',0,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101401,'数据导入',1,'sys:datacapture:import','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1014,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101402,'节目审核',1,'sys:datacapture:programcheck','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1014,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101403,'子节目审核',1,'sys:datacapture:prochildrencheck','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1014,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(101404,'百度识图',1,'sys:datacapture:baidupicrecogntion','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,1014,0);

INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(10140201,'查看',1,'sys:datacapture:programcheckview','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,101402,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(10140202,'修改',1,'sys:datacapture:programcheckupdate','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,101402,0);
INSERT INTO sys_authority (id,NAME,type,authority_identity,icon,is_open,parent_id,deleted) VALUES 
(10140203,'通过审核',1,'sys:datacapture:programcheckdocheck','/resources/js/zTree_v3.5.16/css/zTreeStyle/img/diy/2.png',0,101402,0);


/*init sys_company(xiaobai)*/
INSERT INTO sys_company (id, company_name, address, postcode, phone_number, balance, status, secret_key, deleted, creator, created, modifier, updated) 
VALUES (1,'北京小白世纪网络科技有限公司','北京市海淀区东升园公寓12号楼905',null,null,0,null,null,0,null,null,null,null);


/*init sys_platform(xiaobai)*/


/*init sys_ad_org(xiaobai)*/


/*init sys_user(admin)*/
INSERT INTO sys_user 
(id, login_name, password, real_name, mobile, phone_number, email, qq, company_id, is_admin, enabled, deleted, remark, salt, last_login_date, manager, creator, created, modifier, updated)
VALUES
(1,'admin@xiaobai.com','0f446a96aa41ad5e73187e65536f185fc6343c2c',null,null,null,'admin@xiaobai.com',null,1,1,1,0,null,'69d841603d5e900d',null,null,null,null,null,null);


/*init sys_role(admin、advertisingAlliance、advertiser)*/
INSERT INTO sys_role (id, name, role_identity, description, deleted, parent_id, creator, created, modifier, updated) 
VALUES (1,'系统管理员','administrator','创建系统用户，并分配角色',0,null,null,null,null,null);
INSERT INTO sys_role (id, name, role_identity, description, deleted, parent_id, creator, created, modifier, updated) 
VALUES (2,'广告联盟','advertisingAlliance','创建及管理广告主用户、管理公司内部用户及广告相关业务',0,null,null,null,null,null);
INSERT INTO sys_role (id, name, role_identity, description, deleted, parent_id, creator, created, modifier, updated) 
VALUES (3,'广告主','advertiser','管理公司内部用户及广告相关业务',0,null,null,null,null,null);


/*init sys_user_role(admin)*/
INSERT INTO sys_user_role (id, user_id, role_id) VALUES (1,1,1);


/*init sys_role_authority(admin、advertisingAlliance、advertiser)*/
/*admin*/
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (1,1,1006);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (2,1,100601);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (3,1,100602);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (4,1,100603);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (5,1,100604);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (6,1,100605);

INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (7,1,1007);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (8,1,100701);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (9,1,100702);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (10,1,100703);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (11,1,100704);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (12,1,100705);

INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (13,1,1013);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (14,1,101301);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (15,1,101305);

/*advertisingAlliance*/
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (16,2,1006);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (17,2,100601);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (18,2,100602);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (19,2,100603);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (20,2,100604);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (21,2,100605);

INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (22,2,1007);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (23,2,100701);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (24,2,100702);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (25,2,100703);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (26,2,100704);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (27,2,100705);

INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (28,2,1008);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (29,2,100801);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (30,2,100802);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (31,2,100803);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (32,2,100804);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (33,2,100805);

INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (34,2,1013);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (35,2,101301);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (36,2,101302);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (37,2,101303);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (38,2,101304);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (39,2,101305);


/*advertiser*/
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (40,3,1006);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (41,3,100601);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (42,3,100602);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (43,3,100603);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (44,3,100604);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (45,3,100605);

INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (46,3,1007);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (47,3,100701);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (48,3,100702);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (49,3,100703);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (50,3,100704);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (51,3,100705);

INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (52,3,1013);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (53,3,101301);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (54,3,101302);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (55,3,101303);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (56,3,101304);
INSERT INTO sys_role_authority (id, role_id, authority_id) VALUES (57,3,101305);


