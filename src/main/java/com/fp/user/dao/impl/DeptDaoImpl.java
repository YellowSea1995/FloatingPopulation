package com.fp.user.dao.impl;

import java.util.List;
import java.util.Map;

import com.fp.user.dao.IDeptDao;
import com.fp.user.dao.base.impl.HibernateDaoImpl;

public class DeptDaoImpl extends HibernateDaoImpl implements IDeptDao {

	
	//获取部门id 、 部门名字
	@Override
	public List<Map<String, Object>> ajaxLoadDept() {
		String hql = "select new Map(d.id as id ,d.name as name) from Dept d order by id asc";
		return this.find(hql);
	}

}
