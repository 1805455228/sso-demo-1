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
@EnableAuthorizationServer //(授权服务)
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


	/**
	 *      *     authorization_code：传统的授权码模式<br>
	 *      *     implicit：隐式授权模式<br>
	 *      *     password：资源所有者（即用户）密码模式<br>
	 *      *     client_credentials：客户端凭据（客户端ID以及Key）模式<br>
	 *      *     refresh_token：获取access token时附带的用于刷新新的token模式
	 * @param clients
	 * @throws Exception
	 */
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
//				.authorizedGrantTypes("client_credentials", "refresh_token", "password", "authorization_code")
				.accessTokenValiditySeconds(1200)
				.refreshTokenValiditySeconds(50000)



				// 用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围。
				// 如果客户端配置了scope，就会收到影响
				//.scopes("all")
				.scopes("user_info")
				.authorities("client")
				.autoApprove(true)//


				// 判断回调是否合法 does not match one of the registered values
				.redirectUris("http://localhost:9001/ui/login", "http://localhost:9002/ui/login")
				.and().build();

		// 动态授权：ClientDetailsService clients.withClientDetails(clientDetailsService());
	}

//	注意：在配置文件中要注意 server.servlet.session.cookie.name 的配置，
//	因为 cookie 不会保存端口，所以要注意客户端的 cookie 名和授权服务器的 cookie 名的不同。


}