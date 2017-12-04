<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>办公管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
	<meta name="Keywords" content="keyword1,keyword2,keyword3"/>
	<meta name="Description" content="网页信息的描述" />
	<meta name="Author" content="gdcct.gov.cn" />
	<meta name="Copyright" content="All Rights Reserved." />
	<link href="pujin.ico" rel="shortcut icon" type="image/x-icon" />

	<link rel="StyleSheet" href="${ctx }/js/dtree/dtree.css" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/dtree/dtree.js"></script>
	
	<style type="text/css">
		html,body{ height:100%;}
		a{color:#6a6f71; text-decoration:none;}
	</style>	
	
	<script type="text/javascript">
		/* d = new dTree('d');
		//创建根节点
		d.add("1", -1, '办公管理系统');
		//发送ajax 查询数据库
		$.ajax({
			   type : "post",
			   dataType : "json",
			   url : "${ctx}/popedom/ajaxLoadModuleByUserId.jspx",
			   async : false,	//同步
			   success : function(msg){
				   //第一个参数：节点id 第二个参数：父节点id 第三个参数：节点名称 第四个参数：请求地址 第五个参数：节点标题  第六个参数：跳转的目标位置 第七个参数：图片路径
				   $.each(msg, function(){
					   d.add(this.id, this.pid, this.name, 
							   "javascript:parent.mainframe.addTab('" 
									   + this.id + "','" + this.name + "','" + "${ctx}/" + this.url + "')", this.name, "mainframe");
				   })
				   //将dtree放在页面中
				   $("#tree").html(d.toString());
				   d.openTo("1");
			   },
			   error : function(){
				   $.messager.alert("温馨提示", "数据加载异常", "warning");
			   }
		})
		document.write(d);
		d.openTo("2"); */
	</script>
  </head>
 <body class="leftmenubody">
 	<div class="topdivyc">
    	<a href="javascript:void(0);" class="a_link" title="隐藏菜单栏" id="nav_title"><img src="./images/system/left_yc.gif" onclick= "ShowNav(this);"/>隐藏时反显示的图片<img src="images/left_xs.gif" /></a>
    </div>
    <div class="bodytextmenu" id="shumenu">
	    <div class="shumenu" >
	    	<div >
				<script type="text/javascript">
					d = new dTree('d');
					d.add(0, -1, '办公管理系统');
					d.add(1, 0, '假期管理', 'javascript:void(0)');
					d.add(2, 0, '系统管理', 'javascript:void(0)');
					d.add(3, 1, '假期类型', 'http://my.sise.com.cn');
					d.add(4, 1, '假期明细', 'javascript:void(0)');
					d.add(5, 1, '假期审批', 'javascript:void(0)');
					d.add(6, 1, '用户请假', 'javascript:void(0)');
					d.add(7, 2, '用户管理', "javascript:parent.mainframe.addTab('001', '用户管理', '${ctx}/user/selectUser.jspx')", "用户管理", "mainframe");
					d.add(8, 2, '角色管理', "javascript:parent.mainframe.addTab('002', '角色管理', '${ctx}/role/selectRole.jspx')", "角色管理", "mainframe");
					d.add(9, 2, '操作管理', "javascript:parent.mainframe.addTab('003', '操作管理', '${ctx}/module/mgrModule.jspx')", "操作管理", "mainframe");
					document.write(d);
					d.openTo("2");
				</script>
			</div>
	    </div>
    </div>
  </body>
</html>
