package com.fp.util;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

public class CookieUtils {

	//添加cookie
	public static void addCookie(String cookNAME, String userName, int time) {
		// TODO Auto-generated method stub
		//获取cookie
		Cookie cookie = getCookieByName(cookNAME);
		System.out.println("cookie:"+cookie);
		if(cookie==null){
			cookie = new Cookie(cookNAME, userName);
		}
		
		System.out.println("cookie名字："+cookie.getName());
		
		//设置cookie存活时间
		cookie.setMaxAge(time);
		//  http://192.168.10.222:8080/
		cookie.setPath("/");
		
		//将cookie响应至客户端
		ServletActionContext.getResponse().addCookie(cookie);
	}

	
	//根据cookie名字获取cookie
	public static Cookie getCookieByName(String cookNAME) {
		// TODO Auto-generated method stub
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals(cookNAME)){
					return cookie;
				}
				
			}
		}
		
		return null;
	}

	//删除cookie信息
	public static void removeCookie(String cOOKNAME) {
		// TODO Auto-generated method stub
		//获取cookie
		Cookie cookie = getCookieByName(cOOKNAME);
		if(cookie!=null){			
						
			//设置cookie存活时间
			cookie.setMaxAge(0);
			//  http://192.168.10.222:8080/
			cookie.setPath("/");
			
			//将cookie响应至客户端
			ServletActionContext.getResponse().addCookie(cookie);
		}
		
	}
	
	
	
	
	
	

}
