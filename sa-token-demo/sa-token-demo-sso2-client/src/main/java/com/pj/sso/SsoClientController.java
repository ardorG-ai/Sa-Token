package com.pj.sso;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.sso.SaSsoHandle;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;

/**
 * Sa-Token-SSO Client端 Controller 
 * @author kong
 */
@RestController
public class SsoClientController {

	/*
	 * SSO-Client端：处理所有SSO相关请求 
	 * 		http://{host}:{port}/sso/login			-- Client端登录地址，接受参数：back=登录后的跳转地址 
	 * 		http://{host}:{port}/sso/logout			-- Client端单点注销地址（isSlo=true时打开），接受参数：back=注销后的跳转地址 
	 * 		http://{host}:{port}/sso/logoutCall		-- Client端单点注销回调地址（isSlo=true时打开），此接口为框架回调，开发者无需关心
	 */
	@RequestMapping("/")
	public String index() {
		String str = "<h2>Sa-Token SSO-Client 应用端</h2>" + 
					"<p>当前会话是否登录：" + StpUtil.isLogin() + "</p>" + 
					"<p><a href=\"javascript:location.href='/sso/login?back=' + encodeURIComponent(location.href);\">登录</a> " + 
					"<a href='/sso/logout?back=self'>注销</a></p>"; 
		return str;
	}
	
	// SSO-Client端：处理所有SSO相关请求 
	@RequestMapping("/sso/*")
	public Object ssoRequest() {
		return SaSsoHandle.clientRequest();
	}

	// 全局异常拦截 
	@ExceptionHandler
	public SaResult handlerException(Exception e) {
		e.printStackTrace(); 
		return SaResult.error(e.getMessage());
	}
	
}
