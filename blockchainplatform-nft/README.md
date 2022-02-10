- 注册百度超级链开放网络账户https://xuper.baidu.com/  完成账户初始化
- 将web模块sql目录下的.sql文件导入数据库,将私钥,address和安全码(passwd)填入`nft_chain_plat`表中,并将文件放入到classpath下的指定目录中
  > passwd字段是使用SecUtils.encoderByRSAPrivateKey()加密后的数据
- 将web模块的的static.zip文件解压到与application.yml中staticdir属性值对应的目录下
- 完善微信配置表中数据,用于微信登录和微信支付
- 用户名/密码:`admin/123`