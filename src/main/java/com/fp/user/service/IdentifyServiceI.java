package com.fp.user.service;

import java.util.List;

import com.fp.user.bean.Dept;
import com.fp.user.bean.Module;
import com.fp.user.bean.Role;
import com.fp.user.bean.User;
import com.fp.util.tag.PageModel;

public interface IdentifyServiceI {
	
	public List<Dept> getAllDept();

	public User findUserByName(String userName);
	
	//分页查询用户信息
	public List<User> selectUserByPage(User user, PageModel pageModel);

	//异步加载部门信息
	public String ajaxLoadDept();

	//异步加载部门及职位信息
	String ajaxLoadDeptAndJob();

	//添加用户
	void saveUser(User user) throws Exception;
	
	//删除用户
	void deleteByIds(String[] split);
	
	//修改用户
	void updateUser(User user);
	
	//审核用户
	void checkUserByIds(String[] split, Short status);

	//分页查询角色信息
	public List<Role> selectRoleByPage(PageModel pageModel);

	//添加角色
	public void saveRole(Role role);

	//根据角色id获取角色信息
	Role getRoleById(Long id);

	//修改角色
	public void updateRole(Role role);

	//删除角色信息
	public void deleteRoleByIds(String[] ids);

	//获取角色绑定用户
	public List<User> findBindUserByRoleId(Long id, PageModel pageModel);

	//角色解除用户
	void unbindUser(Long id, String[] ids);

	//获取当前角色未绑定的用户信息
	public List<User> selectUnBindUserByRoleId(Long id, PageModel pageModel);

	//绑定用户
	public void bindUser(Long id, String[] ids);

	//异步加载模块信息
	public String ajaxLoadModule();

	//根据模块的code获取模块信息
	public List<Module> selectModuleByPage(String parentCode, PageModel pageModel);

	//删除模块信息
	void deleteModule(String[] ids);

	//根据模块code获取模块信息
	public Module getModuleByCode(String code);

	//更新模块
	public void updateModule(Module module);

	//添加模块
	void addModule(Module module, String parentCode);

	//加载一级二级模块
	public String ajaxFirstAndSecondModule();

	//根据模块code获取子模块
	public List<Module> loadThirdModule(String parentCode);

	//获取角色所拥有的权限
	public List<String> getOperasByRoleIdAndCode(String parentCode, Long roleId);

	//绑定操作
	public void bindPopedom(Long roleId, String[] ids, String parentCode);

	//根据当前用户所拥有的角色，获取该用户拥有的操作模块
	public String ajaxLoadModuleByUserId(String userId);

	//获取登录用户权限信息
	public List<String> findUserOperasByUserId(String userId);
}
