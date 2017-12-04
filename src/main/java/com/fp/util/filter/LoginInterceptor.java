package com.fp.util.filter;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.fp.user.bean.User;
import com.fp.user.service.IdentifyServiceI;
import com.fp.util.CookieUtils;
import com.fp.util.OAContant;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

//登录拦截器
public class LoginInterceptor extends AbstractInterceptor {
	
	@Autowired(required = true)
	private IdentifyServiceI identifyService;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 从session中获取用户信息
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(OAContant.SESSION_USER);
		// session中没有用户信息，再判断cookie中是否有用户信息
		if (user == null) {
			// 根据cookie名字获取cookie信息
			Cookie cookie = CookieUtils.getCookieByName(OAContant.COOKNAME);
			if (cookie != null) {
				// 获取用户登录名
				String userId = cookie.getValue();
				// 根据用户id获取用户信息
				user = identifyService.findUserByName(userId);
				// 将用户信息存放在session中
				ServletActionContext.getRequest().getSession().setAttribute(OAContant.SESSION_USER, user);
			}			
		}
		//设置字符编码
		ServletActionContext.getRequest().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		return user == null ? Action.LOGIN : invocation.invoke();
	}
}
