<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>OA办公管理系统-角色绑定用户管理</title>
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
			
			//为绑定用户按钮绑定事件
			$("#bindUser").click(function(){
				//获取选中的子checkbox
				var boxes = $("input[id^='box_']:checked");
				if (boxes.length == 0) {
					$.message.alert("温馨提示", "请选择需要绑定的用户！", "warning");
				} else {
					var arr = new Array();
					$.each(boxes, function(){
						arr.push(this.value);
					})
					window.location = "${ctx}/role/bindUser.jspx?pageModel.pageIndex=${pageModel.pageIndex}&role.id=${role.id}&ids="+arr.join(",");
				}
			})
		})
	</script>
</head>
<body style="overflow: hidden;">
	<!-- 工具按钮区 -->
	<table>
		<tr>
			<td><input type="button" value="绑定" id="bindUser"/>
			&nbsp;<font color="red">${tip}</font></td>
		</tr>
	</table>
	
	<!-- 数据展示区 -->
	<table width="100%" cellpadding="5" cellspacing="1" style="background-image: url('${ctx}/images/system/t2.jpg');">
		<tr>
			<th><input type="checkbox" id="checkAll"/>全部</th>
			<th>编号</th>
			<th>姓名</th>
			<th>职位</th>
			<th>邮箱</th>
			<th>状态</th>
			<th>创建日期</th>
			<th>创建人</th>
		</tr>
		<tbody style="background-color: #FFFFFF;">
			<s:iterator value="users" status="stat">
				<tr id="data_${stat.index}">
					<td><input type="checkbox" id="box_${stat.index}" value="${userId}"/>${stat.count}</td>
					<td><s:property value="userId"/></td>
					<td><s:property value="name"/></td>
					<td><s:property value="job.name"/></td>
					<td><s:property value="email"/></td>
					<td>
						<s:if test="status == 0">
							<font color="red">新建</font>
						</s:if>
						<s:elseif test="status == 1">审核</s:elseif>
						<s:elseif test="status == 2">不通过</s:elseif>
						<s:else>冻结</s:else>
					</td>
					<td><s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:property value="creater.name"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	
	<!-- 分页标签区 -->
	<fkjava:pager pageIndex="${pageModel.pageIndex}" 
				  pageSize="${pageModel.pageSize}" 
				  recordCount="${pageModel.recordCount}" 
				  submitUrl="${ctx}/admin/identity/showBindUser.jspx?pageModel.pageIndex={0}&role.id=${role.id}"/>
	
</body>
</html>