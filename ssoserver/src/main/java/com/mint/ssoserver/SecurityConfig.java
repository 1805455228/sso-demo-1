package com.mint.ssoserver;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatchers()//
				.antMatchers("/login", "/oauth/authorize","/logout")//
				.and().authorizeRequests().anyRequest().authenticated()
				.and().formLogin().permitAll();

				//不使用官方的单点登出（自定义登出）
//				.and().logout().permitAll()
//				.logoutSuccessHandler(
//						(request, response, authentication) -> {
//							String callback = request.getParameter("callback");
//							if (callback == null){
//								callback = "/login?logout";
//							}
//							if (authentication == null) {
//								logger.info("[AUTH] {} logout success. no authentication", request.getSession().getId());
//							} else {
//								logger.info("[AUTH] {} logout success. auth:{}", request.getSession().getId(), JSON.toJSONString(authentication.getDetails()));
//							}
//							response.sendRedirect(callback);
//						}
//				);
				http.cors().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("123456")).roles("USER");
		auth.inMemoryAuthentication().withUser("xuan").password(passwordEncoder().encode("123")).roles("USER");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}