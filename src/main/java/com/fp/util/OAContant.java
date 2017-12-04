package com.fp.util;

import org.apache.struts2.ServletActionContext;

import com.fp.user.bean.User;

public class OAContant {

	//用户信息对应key值
	public final static String SESSION_USER = "session_user";
	
	//验证码对应key  
	public static String VCODE = "vcode";
	
	//cookie名字
    public static String COOKNAME = "oaCookIeName";
    
    //指定模块code长度
    public static int COODLENGTH = 4;
    
    //用户权限		---针对页面中的按钮
    public static String USER_POPEDOM = "userPopedom";

	public static User getCurrentUser() {
		// TODO Auto-generated method stub
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute(OAContant.SESSION_USER);
		return user;
	}
}
