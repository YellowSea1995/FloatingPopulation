package com.fp.user.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.fp.user.bean.Module;

public class ModuleAction extends BaseAction {

	private List<Module> modules;
	private String parentCode;
	
	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	//异步加载模块信息
	public String ajaxLoadModule() {
		try {
			String moduleStr = identityService.ajaxLoadModule();
			ServletActionContext.getResponse().getWriter().print(moduleStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	//模块分页查询
	public String selectModule() {
		try {
			modules = identityService.selectModuleByPage(parentCode, pageModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//删除模块信息
	public String deleteModule() {
		try {
			System.out.println("ids:" + ids);
			identityService.deleteModule(ids.split(","));
			setTip("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			setTip(e.getMessage());
		}
		return SUCCESS;
	}
	
	private String code;
	private Module module;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
	
	//根据模块code获取模块信息
	public String showUpdateModule() {
		try {
			module = identityService.getModuleByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//更新模块信息
	public String updateModule() {
		try {
			identityService.updateModule(module);
			setTip("更新成功！");
		} catch (Exception e) {
			e.printStackTrace();
			setTip(e.getMessage());
		}
		return SUCCESS;
	}
	
	//添加模块信息
	public String addModule() {
		try {
			identityService.addModule(module, parentCode);
			setTip("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			setTip(e.getMessage());
		}
		return SUCCESS;
	}
}
