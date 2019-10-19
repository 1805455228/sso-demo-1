package com.mint.ssoclient;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
public class DemoController {
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

	@GetMapping(value = "/index")
	public String index(HttpServletRequest request,OAuth2Authentication authentication) {
		logger.info("===1===request:{} ,auth: {}",request,authentication);
		return "开发访问路径: "+ JSON.toJSONString(authentication);
	}

	@GetMapping(value = "/se")
	public String securedPage(HttpServletRequest request, OAuth2Authentication authentication) {
		logger.info("===2===request:{} ,auth: {}",request,authentication);
		return "授权才能访问路径: "+ JSON.toJSONString(authentication);
	}
}
