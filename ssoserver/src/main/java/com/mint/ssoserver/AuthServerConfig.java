package com.mint.ssoserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
//配置授权中心
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer//

				// url:/oauth/token_key,exposes public key for token verification if using JWT
				// tokens
				// 这里配置是开放访问
				.tokenKeyAccess("permitAll()")//

				// url:/oauth/check_token allow check token
				// 是否允许访问oath/check_token这个接口，这里配置是如果验证通过可以访问
				.checkTokenAccess("isAuthenticated()");
	}

// 完成！
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()//
				// （必须的）用来标识客户的Id。
				// ssoclient每个都会配置一个clientid。用于判断是否允许访问授权中心
				.withClient("SampleClientId")

				// （需要值得信任的客户端）客户端安全码，如果有的话。
				// 原理同上
				.secret(passwordEncoder.encode("secret"))

				// 此客户端可以使用的授权类型，默认为空。
				// https://stackoverflow.com/questions/45579623/what-the-configuration-of-spring-security-oauth2-authorizedgranttypes-means-in-p

				// 具体说明：
				// authorization_code = 基本的oauth授权，采用redirect方式，简单说clientid+secret+redirect_uri+
				// 用户名密码登陆
				// 可以参考 https://segmentfault.com/a/1190000012275317

				// implicit = 基本同上，但是只需要redirect_uri 这个参数判断是否允许授权，不需要secret用户名密码
				// 参考：https://segmentfault.com/a/1190000012299236

				// password = 不建议使用，client发送用户名密码来鉴权

				// client_credentials = 用于server - server的方式，简单说 clientid + sercret就可以授权
				// 可以参考 https://segmentfault.com/a/1190000012257809
				
				// refresh_token是spring Security特殊的授权，表示是否可以刷token
				.authorizedGrantTypes("authorization_code")

				// 用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围。
				// 如果客户端配置了scope，就会收到影响
				.scopes("user_info").autoApprove(true)//

				// 判断回调是否合法 does not match one of the registered values
				.redirectUris("http://localhost:8082/ui1/login", "http://localhost:8083/ui2/login");

		// 动态授权：ClientDetailsService clients.withClientDetails(clientDetailsService());
	}
}