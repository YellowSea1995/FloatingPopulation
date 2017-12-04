package com.fp.user.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fp.user.bean.Module;
import com.fp.user.dao.IModuleDao;
import com.fp.user.dao.base.impl.HibernateDaoImpl;
import com.fp.util.OAContant;
import com.fp.util.tag.PageModel;

public class ModuleDaoImpl extends HibernateDaoImpl implements IModuleDao {

	//查询模块信息
	@Override
	public List<Map<String, Object>> ajaxLoadModule() {
		// TODO Auto-generated method stub
		String hql = "select new Map(m.code as code,m.name as name) from Module m";
		return this.find(hql);
	}

	//分页查询模块信息
	@Override
	public List<Module> selectModuleByPage(String parentCode, PageModel pageModel) {
		// TODO Auto-generated method stub
		List<Object> params = new ArrayList<>();
		//  0001    0001%
		StringBuffer hql = new StringBuffer();
		hql.append("select m from Module m where m.code like ? and length(m.code) = ?");
		params.add((parentCode == null||parentCode.equals("")?"%":parentCode+"%"));
		params.add((parentCode == null||parentCode.equals(""))?OAContant.COODLENGTH : parentCode.length()+OAContant.COODLENGTH);
		System.out.println("hql.toString():"+hql.toString());
		return this.findByPage(hql.toString(), pageModel, params);
	}

	//删除模块信息
	@Override
	public void deleteModule(String id) {
		// TODO Auto-generated method stub
		String hql = "delete Module m where m.code like ?";
		this.bulkUpdate(hql, new String[]{id});
	}

}
