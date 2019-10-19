package com.mint.ssoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer //(资源服务---用户获取用户信息（其实可以抽取出独立成服务）)
public class AuthorizationServerApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}
}