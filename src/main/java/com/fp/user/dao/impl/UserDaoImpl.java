package com.fp.user.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fp.user.bean.User;
import com.fp.user.dao.IUserDao;
import com.fp.user.dao.base.impl.HibernateDaoImpl;
import com.fp.util.tag.PageModel;

public class UserDaoImpl extends HibernateDaoImpl implements IUserDao {

	//用户分页查询
	@Override
	public List<User> findUsersByPage(User user, PageModel pageModel) {
		// TODO Auto-generated method stub
		//定义集合用于封装查询条件
		List<Object> params = new ArrayList<>();
		//定义hql查询语句
		StringBuffer hql = new StringBuffer();
	     hql.append("select u from User u where 1=1 ");
		if(user!=null&&StringUtils.isNotEmpty(user.getName())){
			hql.append(" and u.name like ?");
			params.add("%"+user.getName()+"%");
		}
		
		if(user!=null&&StringUtils.isNotEmpty(user.getPhone())){
			hql.append(" and u.phone like ?");
			params.add("%"+user.getPhone()+"%");
		}
		
		if(user!=null&&user.getStatus()!=null&&user.getStatus()>=0){
			hql.append(" and u.status = ?");
			params.add(user.getStatus());
		}
		if(user!=null&&user.getDept()!=null&&user.getDept().getId()!=null&&user.getDept().getId()>0){
			hql.append(" and u.dept.id = ?");
			params.add(user.getDept().getId());
		}
		return this.findByPage(hql.toString(), pageModel, params);
	}

	//删除用户信息
	@Override
	public void deleteUser(String[] ids) {
		// TODO Auto-generated method stub
		StringBuffer  hql =  new StringBuffer();  // ?,?)
		hql.append("delete from User u  where u.userId in (");
		for(int i=0;i<ids.length;i++){
			hql.append(i==ids.length-1?"?)":"?,");
		}
		
		//执行hql语句
		this.bulkUpdate(hql.toString(), ids);
		
	}

}
