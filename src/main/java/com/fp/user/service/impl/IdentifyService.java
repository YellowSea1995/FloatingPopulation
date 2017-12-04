package com.fp.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fp.user.bean.Dept;
import com.fp.user.bean.Module;
import com.fp.user.bean.Popedom;
import com.fp.user.bean.Role;
import com.fp.user.bean.User;
import com.fp.user.dao.IDeptDao;
import com.fp.user.dao.IGeneralDao;
import com.fp.user.dao.IJobDao;
import com.fp.user.dao.IModuleDao;
import com.fp.user.dao.IPopedomDao;
import com.fp.user.dao.IRoleDao;
import com.fp.user.dao.IUserDao;
import com.fp.user.service.IdentifyServiceI;
import com.fp.util.OAContant;
import com.fp.util.OAException;
import com.fp.util.tag.PageModel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Transactional(readOnly=false, rollbackFor={Exception.class})
@Service
public class IdentifyService implements IdentifyServiceI {
	
	@Autowired(required=true)
	private IDeptDao deptDao;
	@Autowired(required=true)
	private IUserDao userDao;
	@Autowired(required=true)
	private IJobDao jobDao;
	@Autowired(required=true)
	private IGeneralDao generalDao;
	@Autowired(required=true)
	private IPopedomDao popedomDao;

	@Override
	public List<Dept> getAllDept() {
		System.out.println("in service");
		List<Dept> depts=deptDao.find(Dept.class);
		return depts;
	}
	
    //根据用户名获取用户信息
	public User findUserByName(String userName) {
		try {
			return userDao.get(User.class, userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//分页查询用户信息
	@Override
	public List<User> selectUserByPage(User user, PageModel pageModel) {
		try {
			
			return userDao.findUsersByPage(user, pageModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//异步加载部门信息
	@Override
	public String ajaxLoadDept() {
		try {
			// [{id:0001,name:测试部},{id:0002,name:开发部}] json格式字符串
			// [{name=技术部, id=1}, {name=运营部, id=2}, {name=财务部, id=3}, {name=人事部, id=4}, {name=总公办, id=5}]
			List<Map<String, Object>> depts = deptDao.ajaxLoadDept();
			JSONArray jsonArray = new JSONArray();
			//将集合转成json格式字符串
			return jsonArray.fromObject(depts).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//异步加载部门及职位信息
	@Override
	public String ajaxLoadDeptAndJob() {
		try {
			JSONObject json = new JSONObject();
			// [{id:0001,name:测试部},{id:0002,name:开发部}] json格式字符串
			// [{name=技术部, id=1}, {name=运营部, id=2}, {name=财务部, id=3}, {name=人事部, id=4}, {name=总公办, id=5}]
			List<Map<String, Object>> depts = deptDao.ajaxLoadDept();
			json.put("depts", depts);
			
			List<Map<String, Object>> jobs = jobDao.ajaxLoadJob();
			json.put("jobs", jobs);
			return json.toString();
			//将集合转成json格式字符串
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("异步加载部门以及职位信息失败！", e);
		}
	}
	
	//添加用户
	@Override
	public void saveUser(User user) throws Exception {
		try {
			user.setCreateDate(new Date());
			//设置创建人
			user.setCreater(OAContant.getCurrentUser());
			userDao.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("用户保存失败", e);
		}
	}
	
	// 删除用户
	@Override
	public void deleteByIds(String[] ids) {
		try {
			userDao.deleteUser(ids);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("用户删除失败！", e);
		}
	}

	// 修改用户
	@Override
	public void updateUser(User user) {
		try {

			// 设置修改人以及修改时间
			User u = userDao.get(User.class, user.getUserId());
			u.setAnswer(user.getAnswer());
			u.setDept(user.getDept());
			u.setEmail(user.getEmail());
			u.setJob(user.getJob());
			u.setModifier(OAContant.getCurrentUser());
			u.setModifyDate(new Date());
			u.setName(user.getName());
			u.setPhone(user.getPhone());
			u.setQqNum(user.getQqNum());
			u.setQuestion(user.getQuestion());
			u.setSex(user.getSex());
			u.setTel(user.getTel());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 审核用户
	@Override
	public void checkUserByIds(String[] ids, Short status) {
		try {
			for (int i = 0; i < ids.length; i++) {
				User u = userDao.get(User.class, ids[i]);
				u.setChecker(OAContant.getCurrentUser());
				u.setCheckDate(new Date());
				u.setStatus(status);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("用户审核失败！", e);
		}
	}

	@Autowired(required = true)
	private IRoleDao roleDao;
	
	//分页查询角色信息
	@Override
	public List<Role> selectRoleByPage(PageModel pageModel) {
		try {
			List<Role> roles = roleDao.selectRoleByPage(pageModel);
			return roles;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("用户分页查询失败！", e);
		}
	}

	//添加角色
	@Override
	public void saveRole(Role role) {
		try {
			role.setCreater(OAContant.getCurrentUser());
			role.setCreateDate(new Date());
			roleDao.save(role);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("角色保存失败", e);
		}
	}

	//根据角色id获取角色信息
	@Override
	public Role getRoleById(Long id) {
		try {
			return roleDao.get(Role.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("根据角色id获取角色信息失败！", e);
		}
	}

	//修改角色
	@Override
	public void updateRole(Role role) {
		try {
			Role r =  roleDao.get(Role.class, role.getId());
			r.setModifier(OAContant.getCurrentUser());
			r.setModifyDate(new Date());
			r.setName(role.getName());
			r.setRemark(role.getRemark());
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("角色修改失败！", e);
		}
	}

	//删除角色信息
	@Override
	public void deleteRoleByIds(String[] ids) {
		try {
			roleDao.deleteRoleByIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("角色删除失败：" + e.getMessage());
		}
	}

	//获取角色绑定用户
	@Override
	public List<User> findBindUserByRoleId(Long id, PageModel pageModel) {
		try {
			List<User> users = roleDao.findBindUserByRoleId(id, pageModel);
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("根据角色id获取已绑定的用户信息失败！", e);
		}
	}

	//角色解除用户
	@Override
	public void unbindUser(Long id, String[] ids) {
		try {
			//根据角色id获取角色
			Role role = roleDao.get(Role.class, id);
			//获取角色已经绑定的用户信息
			Set<User> users = role.getUsers();
			
			for (String userId : ids) {
				//根据用户id获取用户信息
				User u = this.findUserByName(userId);
				//将用户信息从已绑定的用户集合中清除
				users.remove(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("根据角色id解除用户失败！", e);
		}
	}

	//获取当前角色未绑定的用户信息
	@Override
	public List<User> selectUnBindUserByRoleId(Long id, PageModel pageModel) {
		try {
			return roleDao.selectUnBindUserByRoleId(id, pageModel);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("根据角色id获取未绑定的用户信息失败！", e);
		}
	}

	//绑定用户
	@Override
	public void bindUser(Long id, String[] ids) {
		try {
			//根据角色id获取角色信息
			Role role = roleDao.get(Role.class, id);
			//获取角色已经绑定的用户
			Set<User> users = role.getUsers();
			
			for (int i=0; i<ids.length; i++) {
				User user = this.findUserByName(ids[i]);
				//将需要绑定的用户，存放在集合中
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("角色绑定用户信息失败！", e);
		}
	}

	@Autowired(required=true)
	private IModuleDao moduleDao;
	
	//异步加载模块信息
	@Override
	public String ajaxLoadModule() {
		try {
			//{name=系统管理，code=0001}, {name=用户管理，code=00010001}]==>[{name:系统管理，code:0001}, {name:用户管理，code:00010001}]
			List<Map<String, Object>> params = moduleDao.ajaxLoadModule();
			System.out.println("params:" + params.toString());
			JSONArray arr = new JSONArray();
			
			for (int i=0; i<params.size(); i++) {
				JSONObject obj = new JSONObject();
				String id = (String) params.get(i).get("code");
				obj.put("name", params.get(i).get("name"));
				obj.put("id", id);
				obj.put("pid", id.length()==4?"1":id.substring(0, id.length()-OAContant.COODLENGTH));
				arr.add(obj);
			}
			return arr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("模块信息加载失败！", e);
		}
	}

	//根据模块的code获取模块信息
	@Override
	public List<Module> selectModuleByPage(String parentCode, PageModel pageModel) {
		try {
			return moduleDao.selectModuleByPage(parentCode, pageModel);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("模块信息分页查询失败！", e);
		}
	}

	//删除模块信息
	@Override
	public void deleteModule(String[] ids) {
		try {
			for (String id : ids) {
				moduleDao.deleteModule(id + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("删除失败，该模块已被其他模块关联，无法删除：" + e.getMessage(), e);
		}
	}

	//根据模块code获取模块信息
	@Override
	public Module getModuleByCode(String code) {
		try {
			return moduleDao.get(Module.class, code);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("根据模块code获取模块信息失败！", e);
		}
	}

	//更新模块
	@Override
	public void updateModule(Module module) {
		try {
			//持久化状态
			Module m = moduleDao.get(Module.class, module.getCode());
			m.setModifier(OAContant.getCurrentUser());
			m.setModifyDate(new Date());
			m.setRemark(module.getRemark());
			m.setName(module.getName());
			m.setUrl(module.getUrl());
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("模块更新失败！", e);
		}
	}

	//添加模块
	@Override
	public void addModule(Module module, String parentCode) {
		try {
			//设置模块编号
			module.setCode(generalDao.getModuleCode("code", Module.class, parentCode));
			module.setCreateDate(new Date());
			module.setCreater(OAContant.getCurrentUser());
			moduleDao.save(module);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("模块添加失败！", e);
		}
	}

	//加载一级二级模块
	@Override
	public String ajaxFirstAndSecondModule() {
		try {
			List<Object[]> list = popedomDao.ajaxFirstAndSecondModule();
			JSONArray arr = new JSONArray();
			for (int i=0; i<list.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("id", list.get(i)[0]);
				obj.put("pid", String.valueOf(list.get(i)[0]).length() == 4 ? "1" : list.get(i)[0].toString().substring(0, OAContant.COODLENGTH));
				obj.put("name", list.get(i)[1]);
				arr.add(obj);
			}
			return arr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("权限信息加载失败！", e);
		}
	}

	//根据模块code获取子模块
	@Override
	public List<Module> loadThirdModule(String parentCode) {
		try {
			return popedomDao.loadThirdModule(parentCode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("模块信息加载失败！", e);
		}
	}

	//获取权限信息
	@Override
	public List<String> getOperasByRoleIdAndCode(String parentCode, Long roleId) {
		try {
			return popedomDao.getOperasByRoleIdAndCode(parentCode, roleId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("权限信息加载失败！", e);
		}
	}

	//绑定操作
	@Override
	public void bindPopedom(Long roleId, String[] ids, String parentCode) {
		try {
			//删除当前角色在指定模块下的所有操作
			popedomDao.deletePopedomByRoleIdAndParentCode(roleId, parentCode);
			//重新绑定操作
			for (int i=0; i<ids.length; i++) {
				Popedom p = new Popedom();
				p.setCreateDate(new Date());
				p.setCreater(OAContant.getCurrentUser());
				
				Role r = new Role();
				r.setId(roleId);
				p.setRole(r);
				
				Module m = new Module();
				m.setCode(parentCode);
				p.setModule(m);
				
				Module popedom = new Module();
				popedom.setCode(ids[i]);
				
				p.setOpera(popedom);
				popedomDao.save(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("绑定操作失败!", e);
		}
	}

	//根据当前用户所拥有的角色，获取该用户拥有的操作模块
	@Override
	public String ajaxLoadModuleByUserId(String userId) {
		try {
			//0001001 00020003二级模块
			List<String> moduleCodes = popedomDao.ajaxLoadModuleByUserId(userId);
			System.out.println("moduleCodes:" + moduleCodes.toString());
			//拥于存放一级模块的code
			Set<String> firstCodes = new HashSet<>();
			JSONArray arr = new JSONArray();
			//根据模块编号获取模块信息，将用户能看到的二级模块信息放在JSONArray数组
			for (int i=0; i<moduleCodes.size(); i++) {
				Module module = moduleDao.get(Module.class, moduleCodes.get(i));
				JSONObject obj = new JSONObject();
				obj.put("id", module.getCode());
				obj.put("pid", module.getCode().substring(0, OAContant.COODLENGTH));
				obj.put("name", module.getName());
				obj.put("url", module.getUrl());
				arr.add(obj);
				firstCodes.add(module.getCode().substring(0, OAContant.COODLENGTH));
			}
			//填充一级模块信息
			Iterator<String> its = firstCodes.iterator();
			while (its.hasNext()) {
				//获取一级模块code
				String firstCode = its.next();
				Module firstModule = moduleDao.get(Module.class, firstCode);
				JSONObject obj = new JSONObject();
				obj.put("id", firstModule.getCode());
				obj.put("pid", "1");
				obj.put("name", firstModule.getName());
				obj.put("url", "#");
				arr.add(obj);
			}
			return arr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("用户权限信息获取失败！", e);
		}
	}

	//获取登录用户权限信息
	@Override
	public List<String> findUserOperasByUserId(String userId) {
		try {
			//00010010001	000200030001	三级模块	/user/addUser.jspx	/user/updateUser.jspx
			List<String> moduleCodes = popedomDao.findUserOperasByUserId(userId);
			List<String> urls = new ArrayList<>();
			for (int i=0; i<moduleCodes.size(); i++) {
				Module m = moduleDao.get(Module.class, moduleCodes.get(i));
				urls.add(m.getUrl());
			}
			return urls;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("用户权限信息获取失败！", e);
		}
	}
}
