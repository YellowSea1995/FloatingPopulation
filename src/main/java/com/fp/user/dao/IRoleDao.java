package com.fp.user.dao;

import java.util.List;

import com.fp.user.bean.Role;
import com.fp.user.bean.User;
import com.fp.user.dao.base.HibernateDao;
import com.fp.util.tag.PageModel;

public interface IRoleDao extends HibernateDao{

	//角色分页查询
	List<Role> selectRoleByPage(PageModel pageModel);

	//删除角色
	void deleteRoleByIds(String[] ids);

	//获取当前角色已绑定的用户
	List<User> findBindUserByRoleId(Long id, PageModel pageModel);

	 //获取当前角色未绑定的用户信息
	List<User> selectUnBindUserByRoleId(Long id,PageModel pageModel);

}
