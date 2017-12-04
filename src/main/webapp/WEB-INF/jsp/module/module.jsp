<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="oa" uri="/oa"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>OA办公管理系统-模块管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/fkjava.ico" rel="shortcut icon" type="image/x-icon" />
	<link href="${ctx}/css/common/commons.css" type="text/css" rel="stylesheet" />
	<!-- 引入分页样式 -->
	<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript">
		$(function(){
			if ("${tip}") {
				//刷新左侧页面
				parent.moduleLeftFrame.location.reload();
			}
			
			//为删除模块按钮绑定事件
			$("#deleteModule").click(function(){
				var boxes = $("input[id^='box_']:checked");
				if (boxes.length == 0) {
					$.messager.alert('温馨提示', '请选择需要删除的模块！', 'warning');
				} else {
					$.messager.confirm('温馨提示', '您确认删除选中记录吗？', function(r){
						if (r) {
							var arr = new Array();
							//获取需要删除的用户的id
							$.each(boxes, function(i, item){
								arr.push(item.value);
							})
							//删除模块信息
							window.location = "${ctx}/module/deleteModule.jspx?ids="+arr.join(",")+"&pageModel.pageIndex=${pageModel.pageIndex}";
						}
					});
				}
			})
			
			//为修改模块绑定事件
			$("#updateModule").click(function(){
				//获取所有的子checkbox
				var boxes = $("input[id^='box_']:checked");
				if (boxes.length == 0) {
					$.messager.alert('温馨提示', '请选择需要修改的模块！', 'warning');
				} else if (boxes.length > 1) {
					$.messager.alert('温馨提示', '一次只能修改一个模块，请核实！', 'warning');
				} else {
					//获取需要修改的模块的code
					var code = boxes.val();
					$("#divDialog").dialog({ //jqueryEasyUi
						title : "修改模块", // 标题
						cls : "easyui-dialog", // class
						width : 580, // 宽度
						height : 320, // 高度
						maximizable : true, // 最大化
						minimizable : false, // 最小化
						collapsible : true, // 可伸缩
						modal : true, // 模态窗口
						onClose : function() { // 关闭窗口
							window.location = "${ctx}/module/selectModule.jspx?pageModel.pageIndex=${pageModel.pageIndex}&parentCode=${parentCode}";
						}
					});					
					//通过iframe去加载updateModel.jsp
					$("#iframe").attr("src","${ctx}/module/showUpdateModule.jspx?code="+code).show();
				}
			})
			
			//为添加模块绑定事件
			$("#addModule").click(function(){
				$("#divDialog").dialog({ //jqueryEasyUi
					title : "添加模块", // 标题
					cls : "easyui-dialog", // class
					width : 580, // 宽度
					height : 320, // 高度
					maximizable : true, // 最大化
					minimizable : false, // 最小化
					collapsible : true, // 可伸缩
					modal : true, // 模态窗口
					onClose : function() { // 关闭窗口
						window.location = "${ctx}/module/selectModule.jspx?pageModel.pageIndex=${pageModel.pageIndex}&parentCode=${parentCode}";
					}
				});					
				//通过iframe去加载updateModel.jsp
				$("#iframe").attr("src","${ctx}/module/showAddModule.jspx?parentCode=${parentCode}").show();
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
			<td><input type="button" value="添加" id="addModule"/></td>
			<td><input type="button" value="修改" id="updateModule"/></td>
			<td><input type="button" value="删除" id="deleteModule"/>
			    &nbsp;<font color="red">${tip}</font></td>
		</tr>
	</table>
	
	<!-- 数据展示区 -->
	<table width="100%" cellpadding="5" cellspacing="1" style="background-image: url('${ctx}/images/system/t2.jpg');">
		<tr>
			<th width="60px;"><input type="checkbox" id="checkAll"/>全部</th>
			<th>编号</th>
			<th>名称</th>
			<th>备注</th>
			<th>链接</th>
			<th>操作</th>
			<th>创建日期</th>
			<th>创建人</th>
			<th>修改日期</th>
			<th>修改人</th>
		</tr>
		<tbody style="background-color: #FFFFFF;">
			<s:iterator value="modules" status="stat">
				<tr id="data_${stat.index}">
					<td><input type="checkbox" id="box_${stat.index}" value="${code}"/>${stat.count}</td>
					<td><s:property value="code"/></td>
					<td><s:property value="name"/></td>
					<td><s:property value="remark"/></td>
					<td><s:property value="url"/></td>
					<td><a href="${ctx}/module/selectModule.jspx?parentCode=${code}">查看下级</a></td>
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
				  submitUrl="${ctx}/module/selectModule.jspx?pageModel.pageIndex={0}&parentCode=${parentCode}"/>
	
	<!-- div作为弹出窗口 -->
    <div id="divDialog" style="overflow: hidden;">
		<iframe id="iframe" frameborder="0" width="100%" height="100%" style="display:none;"></iframe>
	</div>
</body>
</html>