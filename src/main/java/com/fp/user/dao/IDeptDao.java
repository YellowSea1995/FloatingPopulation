package com.fp.user.dao;

import java.util.List;
import java.util.Map;

import com.fp.user.dao.base.HibernateDao;

public interface IDeptDao extends HibernateDao{

	List<Map<String, Object>> ajaxLoadDept();
}
