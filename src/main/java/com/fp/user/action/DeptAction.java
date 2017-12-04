package com.fp.user.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.fp.user.bean.Dept;
import com.fp.user.service.IdentifyServiceI;
import com.opensymphony.xwork2.ActionSupport;

public class DeptAction extends ActionSupport {
	
	@Autowired(required=true)
	private IdentifyServiceI identifyService;
	
	//获取部门信息
	public String getDept() {
		//json格式 [{id:0001,name:测试部},{id:0002,name:开发部}]
		String jsonStr = identifyService.ajaxLoadDept();
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	//获取部门以及职位信息
	public String ajaxLoadDeptAndJob() {
		//json格式 [{id:0001,name:测试部},{id:0002,name:开发部}]
		String jsonStr = identifyService.ajaxLoadDeptAndJob();
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String execute(){
		System.out.println("hello");
		List<Dept> depts=identifyService.getAllDept();
		System.out.println(depts.size());
		return NONE;
	}

}