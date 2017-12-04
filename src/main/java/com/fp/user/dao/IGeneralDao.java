package com.fp.user.dao;



import com.fp.user.dao.base.HibernateDao;

public interface IGeneralDao extends HibernateDao{

	String getModuleCode(String field, Class<?> table, String parentCode);

	

}
