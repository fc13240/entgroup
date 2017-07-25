# 数据库更新记录

###<b> 毛翔宇
> 广告表 : sys_ad
>+ 新增字段 监测代码 tracking_code

> 订单表 : sys_ad_order
>+ 新增字段 检测代码 tracking_code

###<b> 崔晓坤
> 客户表 : sys_company
>+ 新增字段 开户许可证路径 open_account_permit_path
>+ 新增字段 组织机构代码证路径 organization_code_path

> 角色表 : sys_role
>+ 新增字段 角色等级(0-超级管理员;1-管理员;2-总运营;3-运营) role_level

> 订单表 : sys_ad_order
>+ 新增字段 订单名称 order_name

###<b> 彭世鑫
> 新增表 — 视频播放记录表 : sys_video_play_record
>+ 视频播放记录表id id
>+ 外键-节目id program_id
>+ 外键-视频id video_id
>+ 设备编码 device_id
>+ 播放时间 play_time
>+ 设备平台 device_platform
>+ IP地址 ip
>+ 注册代码 region_code
>+ 城市代码 city_code
>+ 国家代码 county_code
>+ 浏览器类型 browser
>+ 设备类型 device_type
>+ 应用包名 pn
>+ 网络类型 net
>+ 手机系统版本 osv
>+ 设备名 bn
>+ 设备屏幕宽度 sw
>+ 设备屏幕高度 sh
>+ SDK版本号 sdk_version

> 新增表 — 播放记录整合表 : sys_video_play_record_note
>+ 播放记录整合表主键 id
>+ 外键-视频表id video_id
>+ 视频播放总次数统计 play_record_count
>+ 视频播放时间-视频播放次数 统计 times
>+ 备用字段1 spare1
>+ 备用字段2 spare2

> 修改表 — 播放记录整合表 : sys_video_play_record_note
>+ 入库时间 entry_time (由spare1字段变更)

###<b> 曲明慧
> 订单表 : sys_ad_order
>+ 新增字段 订单开始投放时间 begin_time
>+ 新增字段 订单结束投放时间 end_time

> 曝光统计表 : sys_ad_display_count
>+ 新增字段 点击次数 click_count
>+ 完善备注