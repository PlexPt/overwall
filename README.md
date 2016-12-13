# overwall
爱墙科技的客户端

#API

| 请求地址 | 参数 | 请求类别 | 说明 |
| --- | --- | --- | --- |
| /api/token | emailpasswd | POST | 根据邮箱和密码获取token |
| /api/register | name 昵称email邮箱passwd密码repasswd确认密码imtype 联系方式（1：微信，2:QQ，3:Google+,4:Telegram ）wechat 联系方式账号code 邀请码 | POST | 注册 |
| /api/checkIn | access\_token | GET | 签到 |
| /api/node | access\_token | GET | 节点列表 |
| /api/user/{id} | access\_token | GET | 获取用户信息 |
| /api/invite | access\_token | GET | 获取用户邀请码列表 |
| /api/invite | access\_token | POST | 生成邀请码 |
| /api/shop | access\_token | GET | 获取商店商品列表 |
| /api/coupon\_check | access\_tokencoupon 优惠码shop  商品编号 | POST | 验证优惠码商品编号是否有效 |
| /api/buy | access\_tokenautorenew 是否自动续订1 或者0shop 商品编号coupon 优惠码 | POST | 购买 |
| /api/bought | access\_token | GET | 购买记录 |
| /api/delBought | access\_tokenid  购买记录ID | GET | 退订 |
| /api/announcement | access\_token | GET | 公告列表 |
