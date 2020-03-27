# Getting Started
## 项目介绍 (种子项目)
springboot  mybatis-plus mysql lombok
## 项目结构
```text
├── README.md 
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── start
    │   │           └── mb
    │   │               └── boot
    │   │                   ├── BootApplication.java
    │   │                   ├── common （公共包）
    │   │                   │   └── Exception
    │   │                   │       ├── BaseException.java
    │   │                   │       └── RequestParameterError.java
    │   │                   │   ├── MyBatisPlusConfig.java
    │   │                   │   ├── MysqlGenerator.java  （使用逆向工程）
    │   │                   │   └── WebMvcConfigurer.java
    │   │                   ├── model （对象）
    │   │                   │   ├── Test.java
    │   │                   │   ├── data
    │   │                   │   │   └── ResultEntity.java
    │   │                   │   └── entity
    │   │                   │       └── MsgKefuNews.java
    │   │                   ├── persistence （持久层）
    │   │                   │   ├── aa.java
    │   │                   │   ├── dao
    │   │                   │   └── mapper
    │   │                   │       └── MsgKefuNewsMapper.java
    │   │                   ├── rest （视图层）
    │   │                   │   ├── BaseController.java
    │   │                   │   └── MsgKefuNewsController.java
    │   │                   └── server （业务层）
    │   │                       ├── MsgKefuNewsService.java
    │   │                       └── impl
    │   │                           └── MsgKefuNewsServiceImpl.java
    │   └── resources
    │       ├── application.properties
    │       ├── application.yml
    │       ├── com
    │       │   └── start
    │       │       └── mb
    │       │           └── boot
    │       │               └── persistence
    │       │                   └── mapper
    │       │                       └── MsgKefuNewsMapper.xml
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── com
                └── start
                    └── mb
                        └── boot
                            └── BootApplicationTests.java
```
