package com.fp.user.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.fp.user.bean.User;
import com.fp.util.CookieUtils;
import com.fp.util.OAContant;

public class UserAction extends BaseAction {

	private String userName;
	private String password;
	private String vcode;
	private String key;
	private Short status;// 用户审核状态
	// user用于传递分页查询条件
	private User user;
	// 用户保存查询结果
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// 验证用户名是否存在
	public String validUserName() {

		// 根据用户名获取用户信息
		user = identityService.findUserByName(userName);
		if (user != null) {
			try {
				// 将信息响应至客户端
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().print("您输入的用户名已存在请重新输入！");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return NONE;
	}

	// 添加用户
	public String addUser() {
		try {
			identityService.saveUser(user);
			setTip("添加成功！");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			setTip("添加失败！");
		}
		return SUCCESS;
	}

	// 删除用户信息
	public String deleteUser() {
		try {
			identityService.deleteByIds(ids.split(","));
			setTip("删除成功！");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			setTip(e.getMessage());
		}
		return SUCCESS;
	}

	// 根据用户id获取用户信息
	public String showUpdateUser() {
		user = identityService.findUserByName(user.getUserId());
		return SUCCESS;
	}

	// 修改用户
	public String updateUser() {
		try {
			setTip("修改成功！");
			identityService.updateUser(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			setTip("修改失败！");
		}

		return SUCCESS;
	}

	// 审核用户信息
	public String checkUser() {
		try {
			identityService.checkUserByIds(ids.split(","), status);
			setTip("审核成功！");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			setTip(e.getMessage());
		}
		return SUCCESS;
	}

	// 多条件分页查询用户信息
	public String selectUserByPage() {
		if (user != null){
			System.out.println("user:"+user.toString());
			System.out.println("用户状态 in Action："+user.getStatus());
		}
		users = identityService.selectUserByPage(user, pageModel);
		return SUCCESS;
	}

	// 异步登录
	public String ajaxLogin() {
		String tip = "";
		// 获取session中的验证码
		String code = (String) ServletActionContext.getRequest().getSession().getAttribute(OAContant.VCODE);
		// 判断验证码是否正确
		if (!code.equals(vcode)) {
			tip = "您输入的验证码不正确，请核实！";
		} else {
			// 根据登录名获取用户信息
			User user = identityService.findUserByName(userName);
			if (user != null) {
				// 判断密码是否输入正确
				if (!user.getPassWord().equals(password)) {
					tip = "您输入密码不正确，请核实！";
				} else {
					// 将用户信息存放在session中
					ServletActionContext.getRequest().getSession().setAttribute(OAContant.SESSION_USER, user);
					// 判断是否记住用户
					if ("1".equals(key)) {
						// 将用户信息存放在cookie中 第一个参数：cookie名字 第二个参数：用户名（唯一） 第三个参数：单位
						// 为秒
						CookieUtils.addCookie(OAContant.COOKNAME, userName, 7 * 24 * 60 * 60);
					}
				}
			} else {
//				tip = "您输入登录名不正确，请核实！";
				
				//将用户信息存放在session中
				ServletActionContext.getRequest().getSession().setAttribute(OAContant.SESSION_USER, user);
				
				//用户登录成功是，将用户的权限信息存放在session中
				List<String> userOperas = identityService.findUserOperasByUserId(userName);
				ServletActionContext.getRequest().getSession().setAttribute(OAContant.USER_POPEDOM, userOperas);
				
				//判断是否记住用户
				if ("1".equals(key)) {
					// 将用户信息存放在cookie中 第一个参数：cookie名字 第二个参数：用户名（唯一） 第三个参数：单位
					// 为秒
					CookieUtils.addCookie(OAContant.COOKNAME, userName, 7 * 24 * 60 * 60);
				}
			}
		}
		// 将信息响应至客户端
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(tip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}

	// 用户退出
	public String userLogout() {
		// 将用户信息从session中清除
		ServletActionContext.getRequest().getSession().removeAttribute(OAContant.SESSION_USER);
		// 将用户信息从cookie中清除
		CookieUtils.removeCookie(OAContant.COOKNAME);
		return LOGIN;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

}
