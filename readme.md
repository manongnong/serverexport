#  server export 说明
##  整体介绍
serverexport-websocket 通过websocket，控制serverexport-app1
导出数据，并且发送给serverexport-websocket存储到H2数据库中，适用于
从多个浏览器页页面收集数据，并且SQL进行加工后导出。
##  项目结构
1. serverexport-app1应用  
http://localhost:8080/serverexport-app1/echo   
访问验证程序正常启动  
http://localhost:8080/serverexport-app1/exportData   
提供数据查询功能
2. serverexport-websocket应用  
http://localhost:8081/serverexport-websocket/websocket/sendInitCommand    
命令serverexport-app1页面安装Jquery脚本  
http://localhost:8081/serverexport-websocket/websocket/sendExportCommand  
命令serverexport-app1页面执行导出脚本  
##  步骤
###  步骤一 启动 serverexport-websocket
1. 启动h2数据库服务  
  拷贝serverexport-websocket resources/h2到本机目录，  
  点击h2.bat 启动H2数据库服务，通过 http://localhost:8082/login.jsp  
  jdbc:h2:tcp://localhost/./db/serverexport test test 可查看数据库表  
2. 启动serverexport-websocket应用  
  http://localhost:8081/serverexport-websocket/echo   
  验证应用其他正常
  
###  步骤二 启动 serverexport-app1
1. 启动serverexport-app1  
   通过浏览器页面访问http://localhost:8080/serverexport-app1/echo 
2. 通过浏览器控制台执行serverexport-websocket resources/js/init.js  
   通过websocket连接服务端serverexport-websocket,并且注册监听器。

###  步骤三 发送初始化命令
1. 执行http://localhost:8081/serverexport-websocket/websocket/sendInitCommand  
  通过serverexport-websocket向serverexport-app1的已经注册websocket连接的
  页面发送初始化命令，初始化Jquery脚本，用于后续执行AJAX
2. 执行http://localhost:8081/serverexport-websocket/websocket/sendExportCommand  
  通过serverexport-websocket向serverexport-app1的已经注册websocket连接的
  页面发送要执行导出的JS脚本，浏览器页面访问执行完成后把直接结果发送给服务端，
  服务端把收到的数据写入H2数据库
3. 通过http://localhost:8082/login.jsp查询H2数据库写入的内容  

##  问题说明
1. 想从HTTPS的页面注册websocket,需要使用wss,服务端启动HTTPS,  
   打开HttpsConfig配置类，打开ssl配置，init.js改成wss

##  特殊说明
本项目例子代码，来源https://github.com/xkcoding/spring-boot-demo
并且做了修改，感谢源作者
