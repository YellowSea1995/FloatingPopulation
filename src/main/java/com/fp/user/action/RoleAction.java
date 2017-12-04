package com.fp.user.action;

import java.util.List;

import com.fp.user.bean.Role;
import com.fp.user.bean.User;

public class RoleAction extends BaseAction {

	List<Role> roles;
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	//分页查询角色信息
	public String selectRoleByPage() {
		roles = identityService.selectRoleByPage(pageModel);
		return SUCCESS;
	}
	
	//添加角色
	public String addRole() {
		try {
			identityService.saveRole(role);
			setTip("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			setTip("添加失败!");
		}
		return SUCCESS;
	}
	
	//根据角色id获取角色信息
	public String showUpdateRole() {
		role = identityService.getRoleById(role.getId());
		return SUCCESS;
	}
	
	//修改角色
	public String updateRole() {
		try {
			setTip("修改成功！");
			identityService.updateRole(role);
		} catch (Exception e) {
			e.printStackTrace();
			setTip("修改失败！");
		}
		return SUCCESS;
	}
	
	//删除角色信息
	public String deleteRole() {
		try {
			identityService.deleteRoleByIds(ids.split(","));
			setTip("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			setTip(e.getMessage());
		}
		return SUCCESS;
	}
	
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	//获取当前角色已绑定的用户
	public String selectRoleUser() {
		try {
			users = identityService.findBindUserByRoleId(role.getId(), pageModel);
			role = identityService.getRoleById(role.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//角色解除用户 roleId userId
	public String unbindUser() {
		try {
			identityService.unbindUser(role.getId(), ids.split(","));
			setTip("解除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			setTip(e.getMessage());
		}
		return SUCCESS;
	}
	
	//获取当前角色未绑定的用户信息
	public String selectUnBindUserByRoleId() {
		try {
			users = identityService.selectUnBindUserByRoleId(role.getId(), pageModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//角色绑定用户
	public String bindUser() {
		try {
			identityService.bindUser(role.getId(), ids.split(","));
			setTip("绑定成功！");
		} catch (Exception e) {
			e.printStackTrace();
			setTip(e.getMessage());
		}
		return SUCCESS;
	}
}
