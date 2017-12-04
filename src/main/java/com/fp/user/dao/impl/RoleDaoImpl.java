package com.fp.user.dao.impl;

import java.util.List;

import com.fp.user.bean.Role;
import com.fp.user.bean.User;
import com.fp.user.dao.IRoleDao;
import com.fp.user.dao.base.impl.HibernateDaoImpl;
import com.fp.util.tag.PageModel;

public class RoleDaoImpl extends HibernateDaoImpl implements IRoleDao {

	//角色分页查询
	@Override
	public List<Role> selectRoleByPage(PageModel pageModel) {
		// TODO Auto-generated method stub
		String hql = "from Role ";
		
		return this.findByPage(hql, pageModel, null);
	}

	//删除角色
	@Override
	public void deleteRoleByIds(String[] ids) {
		// TODO Auto-generated method stub
		Long[] roleIds = new Long[ids.length];
		StringBuffer hql = new StringBuffer();
		hql.append("delete from Role r where r.id in(");
		
		for(int i=0;i<ids.length;i++){
			hql.append(i == ids.length-1 ? "?)":"?,");
			roleIds[i] = Long.valueOf(ids[i]);
		}
		
		this.bulkUpdate(hql.toString(), roleIds);
		
	}

	//获取当前角色已经绑定的用户
	@Override
	public List<User> findBindUserByRoleId(Long id, PageModel pageModel) {
		// TODO Auto-generated method stub
		//List<Long> ids = new ArrayList<>();
		//ids.add(id);
		String hql = "select u from User u inner join u.roles r where r.id = "+id;
		
		
		return this.findByPage(hql, pageModel, null);
	}

	@Override
	public List<User> selectUnBindUserByRoleId(Long id,PageModel pageModel) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("select u from User u where u.userId not  in (");
		//获取当前角色已经的绑定哪些用户id
		hql.append("select u.userId from User u inner join u.roles r where r.id = " + id);
		hql.append(")");
		System.out.println("hql:"+hql.toString());
		return this.findByPage(hql.toString(), pageModel, null);
	}

}
