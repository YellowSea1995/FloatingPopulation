package com.fp.user.dao.impl;

import java.util.List;

import com.fp.user.bean.Module;
import com.fp.user.dao.IPopedomDao;
import com.fp.user.dao.base.impl.HibernateDaoImpl;
import com.fp.util.OAContant;

public class PopedomDaoImpl extends HibernateDaoImpl implements IPopedomDao {

	@Override
	public List<Object[]> ajaxFirstAndSecondModule() {
		// TODO Auto-generated method stub
		String hql = "select  m.code , m.name from Module m  where length(m.code) <= ?";
		
		return (List<Object[]>)this.find(hql, new Integer[]{OAContant.COODLENGTH * 2});
	}

	
	@Override
	public List<Module> loadThirdModule(String parentCode) { 
		// TODO Auto-generated method stub
		String hql = "select  m from Module m  where m.code like ? and length(m.code) = ?";

		return (List<Module>)this.find(hql, new Object[]{parentCode+"%",parentCode.length()+OAContant.COODLENGTH});
	}

    //获取权限信息
	@Override
	public List<String> getOperasByRoleIdAndCode(String parentCode, Long roleId) {
		// TODO Auto-generated method stub
		String hql = "select p.opera.code from Popedom p where p.module.code = ? and p.role.id = ?";
		
		return (List<String>)this.find(hql, new Object[]{parentCode,roleId});
	}

	//删除当前角色在指定模块下的所有操作
	@Override
	public void deletePopedomByRoleIdAndParentCode(Long roleId, String parentCode) {
		String hql = "delete Popedom p where p.role.id = ? and p.module.code = ?";
		this.bulkUpdate(hql, new Object[]{roleId, parentCode});
	}

	//根据当前用户所拥有的角色，获取该用户拥有的操作模块
	@Override
	public List<String> ajaxLoadModuleByUserId(String userId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select distinct(p.module.code) from Popedom p where p.role.id in (");
		hql.append(" select r.id from Role r inner join r.users u where u.userId = ?)");
		return (List<String>)this.find(hql.toString(), new String[]{userId});
	}

	//获取登录用户权限信息
	@Override
	public List<String> findUserOperasByUserId(String userId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select distinct(p.opera.code) from Popedom p where p.role.id in (");
		hql.append(" select r.id from Role r inner join r.users u where u.userId = ?)");
		return (List<String>)this.find(hql.toString(), new String[]{userId});
	}

}
