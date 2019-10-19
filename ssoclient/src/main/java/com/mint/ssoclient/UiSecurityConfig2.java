package com.mint.ssoclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

//@Configuration
//@EnableOAuth2Client //密码类型客户端（客户端凭据模式）
//@Order
public class UiSecurityConfig2 extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(UiSecurityConfig2.class);

	@Value("${security.oauth2.client.accessTokenUri}")
	private String oauthHost;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**")
				.authorizeRequests()//对授权请求进行配置
				.antMatchers("/index","/login**","/logout").permitAll()
				.anyRequest().authenticated()

				// 官方版登出
				.and().logout().logoutSuccessUrl("/").deleteCookies("JSESSIONID");


		//自定义登出
//				.and().logout().permitAll()
//				.logoutSuccessHandler(
//						((request, response, authentication) -> {
//							logger.info("客户端1 登出");
//							response.sendRedirect(oauthHost.split("oauth")[0] + "logout?callback=http://" +  request.getHeader("Host"));
//						})
//				);
				http.cors().disable();
				http.csrf().disable();
	}
}