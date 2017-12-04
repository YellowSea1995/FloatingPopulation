package com.fp.user.dao;

import java.util.List;
import java.util.Map;

import com.fp.user.bean.Module;
import com.fp.user.dao.base.HibernateDao;
import com.fp.util.tag.PageModel;

public interface IModuleDao extends HibernateDao{

	//查询模块信息
	List<Map<String, Object>> ajaxLoadModule();

	//分页查询模块信息
	List<Module> selectModuleByPage(String parentCode, PageModel pageModel);

	//删除模块信息
	void deleteModule(String id);

}
