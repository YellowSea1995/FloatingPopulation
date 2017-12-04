package com.fp.user.dao.impl;

import java.util.List;
import java.util.Map;

import com.fp.user.dao.IJobDao;
import com.fp.user.dao.base.impl.HibernateDaoImpl;


public class JobDaoImpl extends HibernateDaoImpl implements IJobDao {

	//加载职位信息
	@Override
	public List<Map<String, Object>> ajaxLoadJob() {
		String hql = "select new Map(j.id as id ,j.name as name) from Job j order by id asc";
		return this.find(hql);
	}

}
