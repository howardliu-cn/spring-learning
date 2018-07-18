# Spring Cloud Zuul 与 Spring Security结合

## 场景定义：

在实际开发过程中，前端使用Angular，后端使用Spring Boot做开发，但是因为是微服务架构（或者是多模块），服务分散在多个服务中，需要一个集中的权限控制。

## 使用

可以将Spring Cloud Zuul与Spring Security结合，实现权限控制。在该项目中，通过Zuul的代理，实现对页面和api接口的转发调用。