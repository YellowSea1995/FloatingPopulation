<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>OA办公管理系统-权限管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/fkjava.ico" rel="shortcut icon" type="image/x-icon" />
	<link href="${ctx}/css/common/commons.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${ctx}/js/dtree/dtree.js"></script>
	<script type="text/javascript">
		$(function(){
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
			
			//判断当前角色在该模块下拥有的权限信息，已经拥有的加上背景色同时打钩
			var operas = "${operas}";
			$.each(boxes, function(){
				if (operas.indexOf(this.value) != -1) {
					$(this).trigger("mouseover").trigger("click");
				}
			})
			
			//为绑定操作按钮绑定事件
			$("#bindModule").click(function(){
				var boxes = $("input[id^='box_']:checked");
				if (boxes.length == 0) {
					$.messager.alert('温馨提示', '请选择需要绑定的操作!','warning');
				} else {
					var arr = new Array();
					//获取需要绑定的操作的code
					$.each(boxes, function(){
						arr.push(this.value);
					})
					//绑定操作
					window.location = "${ctx}/popedom/bindPopedom.jspx?role.id=${role.id}&ids="
						+ arr.join(",") + "&parentCode=${parentCode}";
				}
			})
		});
	</script>
</head>
<body>
	<!-- 工具按钮区 -->
	<table>
		<tr>
			<td><input type="button" value="绑定操作" id="bindModule"/></td>
			<td><input type="button" value="返回" id="backBtn" onclick="history.back()"/>
			    &nbsp;当前角色：【<font color="red">${role.name}</font>】
			    &nbsp;<font color="red">${tip}</font></td>
		</tr>
	</table>
	
	<!-- 数据展示区 -->
	<table width="100%" cellpadding="5" cellspacing="1" style="background-image: url('${ctx}/images/system/t2.jpg');">
		<tr>
			<th width="60px;"><input type="checkbox" id="checkAll"/>全部</th>
			<th>编号</th>
			<th>名称</th>
			<th>链接</th>
			<th>备注</th>
		</tr>
		<tbody style="background-color: #FFFFFF;">
			<s:iterator value="modules" status="stat">
				<tr id="data_${stat.index}">
					<td><input type="checkbox" id="box_${stat.index}" value="${code}"/>${stat.count}</td>
					<td><s:property value="code"/></td>
					<td><s:property value="name"/></td>
					<td><s:property value="url"/></td>
					<td><s:property value="remark"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</body>
</html>