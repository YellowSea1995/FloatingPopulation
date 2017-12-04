package com.fp.user.dao;

import java.util.List;

import com.fp.user.bean.User;
import com.fp.user.dao.base.HibernateDao;
import com.fp.util.tag.PageModel;

public interface IUserDao extends HibernateDao{

	//用户分页查询
	List<User> findUsersByPage(User user, PageModel pageModel);

	//删除用户
	void deleteUser(String[] ids);

}
