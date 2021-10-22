# Qingxinjiajiao_Android

#### 介绍
倾心家教android端，移通学院《Android Studio》移动应用开发从入门到实战的案例

#### 软件架构
倾心教教安卓端服务，用于前端页面展示和处理业务逻辑，与数据库交互需要启动倾心家教web端服务
安卓部分通过httpclient向web端发送请求，web端接收请求后通过mybatis查询mysql数据库，安卓获取response后取出数据
web端链接如下
https://gitee.com/wen_lung_lee404/dedicated-tutor-web-service


#### 安装教程

需更改res/values目录下的string文件，里面为web服务的地址。
因为是通过虚拟器访问web端服务，使用ip地址不是localhost，因为Android的底层是Linux kernel，包括Android本身就是一个操作系统，因此，这时我们在模拟器的浏览器中输入的localhost或127.0.0.1所代表的是Android模拟器（Android虚拟机），而不是你的电脑。
所有在Android中，将我们本地电脑的地址映射为10.0.2.2，或者使用ipconfig命令，查看ip地址

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
