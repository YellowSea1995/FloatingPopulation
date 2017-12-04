﻿﻿<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="oa" uri="/oa"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>OA办公管理系统-用户管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link href="${ctx}/fkjava.ico" rel="shortcut icon" type="image/x-icon" />
<link href="${ctx}/css/common/commons.css" type="text/css"
	rel="stylesheet" />
<!-- 引入分页样式 -->
<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
	$(function() {
		//为tr绑定mouseover  mouseout事件
		$("tr[id^='data_']").hover(function() {
			//触发mouseover事件
			$(this).css("backgroundColor", "#eeccff").css("cursor", "pointer");
		}, function() {
			//判断当前tr对应的checkbox是否选中
			//获取tr的id
			var trId = this.id;
			//获取当前tr对应的checkbox的id
			var boxId = trId.replace("data_", "box_");
			//判断当前checkbox是否选中
			var flag = $("#" + boxId).prop("checked");
			if (!flag) {
				//触发mouseout事件
				$(this).css("backgroundColor", "#ffffff");
			}
		}).click(function() {
			//获取tr对应的checkbox的id
			var boxId = this.id.replace("data_", "box_");
			$("#" + boxId).trigger("click");

			//获取选中的子checkbox个数
			var checkedLength = boxes.filter(":checked").length;
			//判断全选是否需要选中
			$("#checkAll").prop("checked", checkedLength == boxes.length);

		})

		//获取所有的子checkbox
		var boxes = $("input[id^='box_']");
		//为全选绑定事件
		$("#checkAll").click(function() {
			//判断全选是否选中
			var flag = $("#checkAll").prop("checked");
			boxes.attr("checked", flag);
			//触发tr的mouseover  | mouseout事件
			$("tr[id^='data_']").trigger(flag ? "mouseover" : "mouseout");
		})
		//为所有的子boxes绑定事件(若子box没有全部选中，要取消全选)
		boxes.click(function(event) {
			//阻止事件传播
			event.stopPropagation();
			var checkedLength = boxes.filter(":checked").length;
			$("#checkAll").prop("checked", checkedLength == boxes.length);

		})

		//获取用户选中的部门信息
		var deptId = "${user.dept.id}";
		//异步加载部门信息
		$.ajax({
			type : "post",
			url : "${ctx}/user/ajaxLoadDept.action",
			dataType : "json",//指定返回的数据  json格式字符窜
			success : function(msg) {
				$.each(msg, function() {
					$("<option>").attr("value", this.id).text(this.name).attr(
							"selected", deptId == this.id).appendTo(
							"#deptSelect");
				})

			},
			error : function() {
				$.messager.alert('温馨提示', '数据加载异常!', 'warning');

			}
		})

		//为添加按钮绑定事件
		$("#addUser")
				.click(
						function() {

							$("#dialog")
									.dialog(
											{ //jqueryEasyUi
												title : "添加用户", // 标题
												cls : "easyui-dialog", // class
												width : 580, // 宽度
												height : 320, // 高度
												maximizable : true, // 最大化
												minimizable : false, // 最小化
												collapsible : true, // 可伸缩
												modal : true, // 模态窗口
												onClose : function() { // 关闭窗口
													window.location = "${ctx}/user/selectUser.jspx?pageModel.pageIndex=${pageModel.pageIndex}&user.name=${user.name}&user.phone=${user.phone}&user.dept.id=${user.dept.id}&user.status = ${user.status}";

												}
											});

							//通过iframe去加载addUser.jsp
							$("#iframe").attr("src",
									"${ctx}/user/showAddUser.jspx").show();
						})

		//为删除按钮绑定事件
		$("#deleteUser")
				.click(
						function() {
							var boxes = $("input[id^='box_']:checked");
							if (boxes.length == 0) {
								$.messager.alert('温馨提示', '请选择需要删除的用户!',
										'warning');
							} else {
								$.messager
										.confirm(
												'温馨提示',
												'您确认删除选中记录吗?',
												function(r) {
													if (r) {
														var arr = new Array();
														//获取需要删除的用户的id
														$
																.each(
																		boxes,
																		function() {
																			arr
																					.push(this.value);
																		})
														//删除用户信息
														window.location = "${ctx}/user/deleteUser.jspx?ids="
																+ arr.join(",")
																+ "&pageModel.pageIndex=${pageModel.pageIndex}&user.name=${user.name}&user.phone=${user.phone}&user.dept.id=${user.dept.id}&user.status = ${user.status}";
													}
												});
							}

						})
		//为修改按钮绑定事件
		$("#updateUser")
				.click(
						function() {
							//获取用户选中的checkbox
							var boxes = $("input[id^='box_']:checked");
							if (boxes.length == 0) {
								$.messager.alert('温馨提示', '请选择需要修改的用户!',
										'warning');
							} else if (boxes.length > 1) {
								$.messager.alert('温馨提示', '一次只能修改一个用户，请核实!',
										'warning');
							} else {
								//获取需要修改的用户id
								var userId = boxes.val();
								$("#dialog")
										.dialog(
												{ //jqueryEasyUi
													title : "修改用户", // 标题
													cls : "easyui-dialog", // class
													width : 580, // 宽度
													height : 320, // 高度
													maximizable : true, // 最大化
													minimizable : false, // 最小化
													collapsible : true, // 可伸缩
													modal : true, // 模态窗口
													onClose : function() { // 关闭窗口
														window.location = "${ctx}/user/selectUser.jspx?pageModel.pageIndex=${pageModel.pageIndex}&user.name=${user.name}&user.phone=${user.phone}&user.dept.id=${user.dept.id}&user.status = ${user.status}";
													}
												});
								//通过iframe去加载updateUser.jsp
								$("#iframe").attr(
										"src",
										"${ctx}/user/showUpdateUser.jspx?user.userId="
												+ userId).show();
							}
						})

		//为审核按钮绑定事件
		$("#checkUser")
				.click(
						function() {
							var boxes = $("input[id^='box_']:checked");
							if (boxes.length == 0) {
								$.messager.alert('温馨提示', '请选择需要审核的用户!',
										'warning');
							} else {
								//获取审核状态
								var status = $("#checkStatus").val();
								if (status == -1) {
									$.messager.alert('温馨提示', '请选择审核状态!','warning');
								} else {
									var arr = new Array();
									//获取需要审核的用户的id
									$.each(boxes, function() {
										arr.push(this.value);
									})
									//审核用户信息
									window.location = "${ctx}/user/checkUser.jspx?ids="
											+ arr.join(",")	+ "&status="+ status
											+ "&pageModel.pageIndex=${pageModel.pageIndex}&user.name=${user.name}&user.phone=${user.phone}&user.dept.id=${user.dept.id}&user.status = ${user.status}";
								}
							}
		})
		
		//获取用户的权限信息
		var userPopedoms = "${userPopedom}";
		$("input[name='pop_btn']").each(function(){
			if (userPopedoms.indexOf(this.id) == -1) {
				//将按钮隐藏
				$(this).hide();
				$(this).removeAttr("id");
			}
		})
	})
</script>
</head>
<body style="width: 100%; height: 100%; overflow: hidden">
	<!-- 工具按钮区 -->
	<s:form cssStyle="margin-bottom:1px;" action="/user/selectUser.jspx"
		method="post" theme="simple">
		<table>
			<tr>
				<td><input type="button" name="pop_btn" id="addUser" value="添加" /></td>
				<td><input type="button" name="pop_btn" id="updateUser"
					value="修改" /></td>
				<td><input type="button" name="pop_btn" id="deleteUser"
					value="删除" /></td>
				<td id="status">状态：<s:select id="checkStatus"
						name="user.status"
						list="#{0:'请选择状态',1:'新建',2 : '审核',3 : '不通过', 4 : '冻结'}" /></td>
				<td><input type="button" name="pop_btn" id="checkUser"
					value="审核" /></td>
				<td>姓名：<s:textfield name="user.name" size="12" /></td>
				<td>手机号码：<s:textfield name="user.phone" size="12" /></td>
				<td>部门：<select name="user.dept.id" id="deptSelect">
						<option value="0">==请选择==</option>
				</select></td>
				<td><input type="submit" value="查询" /><span
					style="color: #ff0000;">${tip }</span></td>
			</tr>
		</table>
	</s:form>
	<!-- 数据展示区 -->
	<table width="100%" cellpadding="5" cellspacing="1"
		style="background-image: url('${ctx}/images/system/t2.jpg')">
		<tr>
			<th style="width: 5%; text-align: center;"><input
				type="checkbox" id="checkAll" /></th>
			<th style="width: 8%; text-align: center;">账号</th>
			<th style="width: 6%; text-align: center;">姓名</th>
			<th style="width: 5%; text-align: center;">性别</th>
			<th style="width: 8%; text-align: center;">部门</th>
			<th style="width: 8%; text-align: center;">职位</th>
			<th style="width: 8%; text-align: center;">手机号码</th>
			<th style="width: 10%; text-align: center;">邮箱</th>
			<th style="width: 6%; text-align: center;">状态</th>
			<th style="width: 12%; text-align: center;">创建日期</th>
			<th style="width: 6%; text-align: center;">创建人</th>
			<th style="width: 12%; text-align: center;">审核日期</th>
			<th style="width: 6%; text-align: center;">审核人</th>
		</tr>
		<tbody style="background-color: #FFFFFF;">

			<s:iterator value="users" status="stat">
				<tr id="data_${stat.count}" align="center">
					<td><input type="checkbox" id="box_${stat.count}"
						value="${userId}" /></td>
					<td><s:property value="userId" /></td>
					<td><s:property value="name" /></td>
					<td>${sex == 1 ? '男' : '女' }</td>
					<td><s:property value="dept.name" /></td>
					<td><s:property value="job.name" /></td>
					<td><s:property value="phone" /></td>
					<td><s:property value="email" /></td>
					<td><s:if test="status == 1">
							<font color="red">新建</font>
						</s:if> <s:elseif test="status == 2">审核</s:elseif> <s:elseif
							test="status == 3">不通过</s:elseif> <s:else>冻结</s:else></td>
					<td><s:date name="createDate" format="yyyy-MM-dd HH:mm:ss" /></td>
					<td><s:property value="creater.name" /></td>
					<td><s:date name="checkDate" format="yyyy-MM-dd HH:mm:ss" /></td>
					<td><s:property value="checker.name" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<!-- 此div不显示 作为弹出框  -->
	<div id="dialog">
		<iframe id="iframe" style="display: none; height: 100%; width: 100%"
			frameborder=0></iframe>
	</div>

	<!-- 分页标签区 -->
	<oa:pager pageIndex="${pageModel.pageIndex}"
		pageSize="${pageModel.pageSize}"
		recordCount="${pageModel.recordCount}" pageStyle="digg"
		submitUrl="${ctx}/user/selectUser.jspx?pageModel.pageIndex={0}&user.name=${user.name}&user.phone=${user.phone}&user.dept.id=${user.dept.id}&user.status=${user.status}" />

</body>
</html>