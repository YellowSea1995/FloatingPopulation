package com.fp.user.dao;

import java.util.List;

import com.fp.user.bean.Module;
import com.fp.user.dao.base.HibernateDao;

public interface IPopedomDao extends HibernateDao{

	//加载一级二级模块
	List<Object[]> ajaxFirstAndSecondModule();

	List<Module> loadThirdModule(String parentCode);

	//获取权限信息
	List<String> getOperasByRoleIdAndCode(String parentCode, Long roleId);

	//删除当前角色在指定模块下的所有操作
	void deletePopedomByRoleIdAndParentCode(Long roleId, String parentCode);

	//根据当前用户所拥有的角色，获取该用户拥有的操作模块
	List<String> ajaxLoadModuleByUserId(String userId);

	//获取登录用户权限信息
	List<String> findUserOperasByUserId(String userId);

}
