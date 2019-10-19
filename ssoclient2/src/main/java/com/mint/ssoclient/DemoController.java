package com.mint.ssoclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DemoController {

	@GetMapping(value = "/index")
	public String index() {
		return "开放访问路径";
	}

	@GetMapping(value = "/se")
	public String securedPage() {
		return "授权才能访问路径";
	}
}
