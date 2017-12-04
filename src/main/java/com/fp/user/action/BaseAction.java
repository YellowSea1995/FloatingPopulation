package com.fp.user.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.fp.user.service.IdentifyServiceI;
import com.fp.util.tag.PageModel;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	public String tip;
	public String ids;
	// 分页实体，放置页码等信息
	public PageModel pageModel = new PageModel();
	
	@Autowired(required = true)
	public IdentifyServiceI identityService;

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public PageModel getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	
}
