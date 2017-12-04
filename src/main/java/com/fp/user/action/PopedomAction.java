package com.fp.user.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.fp.user.bean.Module;
import com.fp.user.bean.Role;
import com.fp.user.bean.User;
import com.fp.util.OAContant;

public class PopedomAction extends BaseAction {

	private Role role;
	private String parentCode;
	private List<Module> modules;
	List<String> operas;	//权限信息
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	public List<String> getOperas() {
		return operas;
	}
	public void setOperas(List<String> operas) {
		this.operas = operas;
	}
	
	//加载一级二级模块
	public String ajaxFirstAndSecondModule() {
		try {
			String result = identityService.ajaxFirstAndSecondModule();
			ServletActionContext.getResponse().getWriter().print(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	//根据模块code获取模块下的所有子模块并且根据模块code和角色id获取当前角色在该模块下已经绑定了那些操作
	public String selectThirdModule() {
		try {
			//根据模块code获取子模块
			modules = identityService.loadThirdModule(parentCode);
			//获取角色信息
			role = identityService.getRoleById(role.getId());
			//获取角色所拥有的权限
			operas = identityService.getOperasByRoleIdAndCode(parentCode, role.getId());
			System.out.println("权限信息：" + operas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//绑定操作
	public String bindPopedom() {
		try {
			setTip("绑定成功！");
			identityService.bindPopedom(role.getId(), ids.split(","), parentCode);
		} catch (Exception e) {
			e.printStackTrace();
			setTip(e.getMessage());
		}
		return SUCCESS;
	}
	
	//根据当前用户所拥有的角色，获取该用户拥有的操作模块
	public String ajaxLoadModuleByUserId() {
		try {
			User u = (User) ServletActionContext.getRequest().getSession().getAttribute(OAContant.SESSION_USER);
			System.out.println("u.getUserId():" + u.getUserId());
			String result = identityService.ajaxLoadModuleByUserId(u.getUserId());
			ServletActionContext.getResponse().getWriter().print(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
}
