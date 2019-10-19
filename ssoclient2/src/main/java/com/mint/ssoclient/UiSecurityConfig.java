package com.mint.ssoclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class UiSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${security.oauth2.client.access-token-uri}")
	private String oauthHost;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests()//
				.antMatchers("/login**", "/index").permitAll()//
				.anyRequest().authenticated()//
				//官方版登出
				//.and().logout().logoutSuccessUrl("/index").permitAll();

				.and().logout().permitAll()
				.logoutSuccessHandler(
						((request, response, authentication) -> {
							response.sendRedirect(oauthHost.split("oauth")[0] + "logout?callback=http://" +  request.getHeader("Host"));
						})
				);
		;
	}
}