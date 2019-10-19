package com.mint.ssoserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

//@Configuration
//@EnableAuthorizationServer
public class AuthServerConfig2 extends AuthorizationServerConfigurerAdapter {
	/**
	 * 第三方用户客户端详情
	 * Grant Type代表当前授权的类型：
	 * <p>
	 *     authorization_code：传统的授权码模式<br>
	 *     implicit：隐式授权模式<br>
	 *     password：资源所有者（即用户）密码模式<br>
	 *     client_credentials：客户端凭据（客户端ID以及Key）模式<br>
	 *     refresh_token：获取access token时附带的用于刷新新的token模式
	 * </p>
	 *
	 * @param clients
	 * @throws Exception
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.jdbc(dataSource)
//				.withClient("client_1")
//				.secret("123456")
//				.resourceIds(DEMO_RESOURCE_ID)
//				.redirectUris("https://www.baidu.com", "http://localhost:8081/product/1", "http://localhost:8083/login")
//				.accessTokenValiditySeconds(1200)
//				.refreshTokenValiditySeconds(50000)
//				.authorizedGrantTypes("client_credentials", "refresh_token", "password", "authorization_code")
//				.scopes("all")
//				.authorities("client")
//				.autoApprove(true)
//				.and().build();
	}
}
