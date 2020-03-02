

# becli

### 项目介绍

`becli`提供一套多结构的后端脚手架
从基础的SSM架构到添加Redis缓存到消息队列MQ到领域驱动DDD模式到微服务架构
从传统单一项目逐步递增成一个微服务的目录结构
基于SpringBoot+SpringMVC+MyBatis的后台基础结构
后台管理系统包含用户管理、角色管理、权限管理、菜单管理、配置管理、字典管理、日志管理、设置等模块。

项目目录 | 说明
----|----
`becli-base`		|一个SpringBoot+SpringMVC+MyBatis的后端脚手架
`becli-cache`		|在`becli`的基础上加Redis
`becli-mq`			|在`becli-cache`的基础上加MQ
`becli-ddd`		    |在`becli-mq`的基础上重构为DDD目录模型
`becli-cloud`		|在`becli-ddd`的基础上重构为微服务项目