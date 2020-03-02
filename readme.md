# crab

### 项目介绍

crab项目是一个前后端分离的通用后台管理系统脚手架

后台管理系统包含用户管理、角色管理、权限管理、菜单管理、配置管理、字典管理、日志管理、等模块。



### 组织结构
```
crab
├── commom                  -- 工具类及通用代码
├── sys                     -- 后台管理系统接口
├── test                    -- 整合时写的一些测试接口
└── Application             -- 项目启动类
```
### 目录结构


#### common公共类
```
common                         
├── annotation              -- 注解
├── aop                     -- 切面
├── config                  -- 配置
├── exception               -- 异常
├── tool                    -- 需注入的工具类|或三方工具类
└── util                    -- 静态方法工具类
```
#### sys系统接口
```
sys                        
├── auth                    -- 授权
├── controller              -- 主要做参数的校验
├── entity                  -- 数据库表一一对应
├── mapper                  -- 主要操作数据库CRUD
└── service                 -- 主要业务逻辑的方法定义
        └── impl service    -- 业务逻辑的实现类
```
#### 
### 技术选型
#### 后端技术
| 技术         | 说明                | 官网                                                         |
| ------------ | ------------------- | ------------------------------------------------------------ |
| Spring Boot  | 容器+MVC框架        | [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot) |
| Apache Shiro | 认证和授权框架      | [http://shiro.apache.org/](http://shiro.apache.org/)         |
| MyBatis      | ORM框架             | [http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html) |
| MyBatis-Plus | 数据层代码生成      | [https://mp.baomidou.com/](https://mp.baomidou.com/)         |
| PageHelper   | MyBatis物理分页插件 | [http://git.oschina.net/free/Mybatis_PageHelper](http://git.oschina.net/free/Mybatis_PageHelper) |
| Swagger-UI   | 文档生产工具        | [https://github.com/swagger-api/swagger-ui](https://github.com/swagger-api/swagger-ui) |
| Druid        | 数据库连接池        | [https://github.com/alibaba/druid](https://github.com/alibaba/druid) |
| OSS          | 又拍云存储          | [https://github.com/upyun/java-sdk](https://github.com/upyun/java-sdk) |
| JWT          | JWT登录支持         | [https://github.com/jwtk/jjwt](https://github.com/jwtk/jjwt) |
| Lombok       | 简化对象封装工具    | [https://github.com/rzwitserloot/lombok](https://github.com/rzwitserloot/lombok) |



