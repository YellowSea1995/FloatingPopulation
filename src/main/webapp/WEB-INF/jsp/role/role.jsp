<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="oa" uri="/oa"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>OA办公管理系统-角色管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/fkjava.ico" rel="shortcut icon" type="image/x-icon" />
	<link href="${ctx}/css/common/commons.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet"/>
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
		
		
		//为添加角色绑定事件
		$("#addRole").click(function(){				
			$("#divDialog").dialog({    //jqueryEasyUi
				title : "添加角色", // 标题
				cls : "easyui-dialog", // class
				width : 580, // 宽度
				height : 320, // 高度
				maximizable : true, // 最大化
				minimizable : false, // 最小化
				collapsible : true, // 可伸缩
				modal : true, // 模态窗口
				onClose : function(){ // 关闭窗口
                       window.location = "${ctx}/role/selectRole.jspx?pageModel.pageIndex=${pageModel.pageIndex}";
				}
			});
	    	
	    	//通过iframe去加载addRole.jsp
	   		$("#iframe").attr("src","${ctx}/role/showAddRole.jspx").show();				
		})
		
		//为修改角色绑定事件
		$("#updateRole").click(
			function() {
				//获取用户选中的checkbox
				var boxes = $("input[id^='box_']:checked");
				if (boxes.length == 0) {
					$.messager.alert('温馨提示', '请选择需要修改的角色!','warning');
				} else if (boxes.length > 1) {
					$.messager.alert('温馨提示', '一次只能修改一个角色，请核实!','warning');
				} else {
					//获取需要修改的用角色id
					var roleId =boxes.get(0).value;
					$("#divDialog").dialog({ //jqueryEasyUi
						title : "修改角色", // 标题
						cls : "easyui-dialog", // class
						width : 580, // 宽度
						height : 320, // 高度
						maximizable : true, // 最大化
						minimizable : false, // 最小化
						collapsible : true, // 可伸缩
						modal : true, // 模态窗口
						onClose : function() { // 关闭窗口
							window.location = "${ctx}/role/selectRole.jspx?pageModel.pageIndex=${pageModel.pageIndex}";
						}
					});					
					//通过iframe去加载updateRole.jsp
					$("#iframe").attr("src","${ctx}/role/showUpdateRole.jspx?role.id="+ roleId).show();
				}
		})
		
		//为删除按钮绑定事件
		$("#deleteRole").click(
			function() {
				var boxes = $("input[id^='box_']:checked");
				if (boxes.length == 0) {
					$.messager.alert('温馨提示', '请选择需要删除的角色!','warning');
				}
				else {
					$.messager.confirm('温馨提示','您确认删除选中记录吗?',
						function(r) {
							if (r) {
								var arr = new Array();
								//获取需要删除的用户的id
								$.each(boxes,function() {arr.push(this.value);})
								//删除角色信息
								window.location = "${ctx}/role/deleteRole.jspx?ids="
									+ arr.join(",") + "&pageModel.pageIndex=${pageModel.pageIndex}";
							}
						});
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
<body style="overflow: hidden;width: 100%;height: 100%;">
	<!-- 工具按钮区 -->
	<table>
		<tr>
			<td><input type="button" name="pop_btn" value="添加" id="addRole"/></td>
			<td><input type="button" name="pop_btn" value="修改" id="updateRole"/></td>
			<td><input type="button" name="pop_btn" value="删除" id="deleteRole"/>
			    &nbsp;<font color="red">${tip}</font></td>
		</tr>
	</table>
	
	<!-- 数据展示区 -->
	<table width="100%" cellpadding="5" cellspacing="1" style="background-image: url('${ctx}/images/system/t2.jpg');">
		<tr>
			<th width="60px;"><input type="checkbox" id="checkAll"/>全部</th>
			<th>名称</th>
			<th>备注</th>
			<th>操作</th>
			<th>创建日期</th>
			<th>创建人</th>
			<th>修改日期</th>
			<th>修改人</th>
		</tr>
		<tbody style="background-color: #FFFFFF;">
			<s:iterator value="roles" status="stat">
				<tr id="data_${stat.index}">
					<td><input type="checkbox" id="box_${stat.index}" value="${id}"/>${stat.count}</td>
					<td><s:property value="name"/></td>
					<td><s:property value="remark"/></td>
					<td><a href="${ctx}/role/selectRoleUser.jspx?role.id=${id}">绑定用户</a>|
						<a href="${ctx}/popedom/mgrPopedom.jspx?role.id=${id}">绑定操作</a></td>
					<td><s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:property value="creater.name"/></td>
					<td><s:date name="modifyDate" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:property value="modifier.name"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	
	<!-- 分页标签区 -->
	<oa:pager pageIndex="${pageModel.pageIndex}" 
				  pageSize="${pageModel.pageSize}" 
				  recordCount="${pageModel.recordCount}" 
				  pageStyle="digg"
				  submitUrl="${ctx}/role/selectRole.jspx?pageModel.pageIndex={0}"/>
	
	<!-- div作为弹出窗口 -->
    <div id="divDialog" style="overflow: hidden;">
		<iframe id="iframe" frameborder="0" width="100%" height="100%" style="display:none;"></iframe>
	</div>
</body>
</html>