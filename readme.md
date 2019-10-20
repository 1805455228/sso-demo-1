

# 单点登录SSO（SpringSecurityOAuth2）模块
---

## oauth2 授权协议（常见模式）

* 1、授权码模式 （第三方登录）先申请授权码，在申请令牌

* 2、密码模式 （单点登录）（根据用户名、密码申请令牌）


## oauth2认证服务测试

- 1、申请授权码

http://127.0.0.1:8081/auth/oauth/authorize?client_id=SampleClientId&redirect_uri=http://localhost:9001/ui/login&response_type=code&state=BqQOAj

在浏览器输入上面地址，要求输入用户名、密码；成功后跳转上面的重定向地址并带code参数
http://localhost:9001/ui/login?code=1LsjgL&state=BqQOAj

- 2、根据授权码（code）申请访问令牌

http://127.0.0.1:8081/auth/oauth/token

参数：

```xml
POST /oauth/token HTTP/1.1
Host: localhost:8081
Authorization: Basic amVybGFsYUAxNjMuY29tOll5MTIzNDU3
Content-Type: application/x-www-form-urlencoded
```

参数说明：

    请求的Header中有一个Authorization参数，该参数的值是Basic + （clientId:secret Base64值）
    
    grant_type：表示使用的授权模式，必选项，此处的值固定为”authorization_code”。
    code：表示上一步获得的授权码，必选项。
    redirect_uri：表示重定向URI，必选项，且必须与A步骤中的该参数值保持一致。
    client_id：表示客户端ID，必选项。





### OAuth2 在服务提供者上可分为两类：

- 1、授权认证服务：AuthenticationServer

```xml
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {}

```

- 2、资源获取服务：ResourceServer

```xml
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {}

```


    注意：这两者有时候可能存在同一个应用程序中（即SOA架构）。
    在Spring OAuth中可以简便的将其分配到两个应用中（即微服务），
    而且可多个资源获取服务共享一个授权认证服务。

    注意：ResourceServerConfiguration 和 SecurityConfiguration上配置的顺序
    SecurityConfiguration 一定要在 ResourceServerConfiguration 之前，因为 spring 实现安全是通过添加过滤器(Filter)来实现的，
    基本的安全过滤应该在oauth过滤之前, 所以在 SecurityConfiguration 设置 @Order(2) , 在 ResourceServerConfiguration 上设置 @Order(6)

---
## 1、认证服务系统 ssoserver


## 2、应用资源服务系统1 ssoclient




## 3、应用资源服务系统2 ssoclient2


----

## JWT令牌 （json-web-toke）

* 私钥、公钥是一对的 
* 非对称加密算法 如RSA 
* 私钥（生成jwt令牌）、公钥（校验令牌）
* 使用JWT令牌的优点就是（不用在远程调用认证服务去校验令牌的合法性）
* JWT令牌可以存储信息 
* 令牌字符串过长


参考资料：

- https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql

- https://gitee.com/kenhins/spring-oauth2-demo
- https://projects.spring.io/spring-security-oauth/docs/oauth2.html
- https://juejin.im/post/5a3cbce05188252582279467#heading-6
- http://www.spring4all.com/article/582




重构：作者：Martin F

grasp

solid

