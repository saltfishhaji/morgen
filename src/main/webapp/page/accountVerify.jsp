<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 注册账户审核页面 -->
<html>
<head>
<title>AccountVerifyPage</title>

<!-- 引入的样式和js文件 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jquery-easyui/themes/icon.css"></link>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui/jquery.min.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jquery-easyui/themes/default/easyui.css"></link>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>

<!-- 样式 -->
<style type="text/css">
a:link{color:#AA0000;text-decoration:none}
a:visited{color:#AA0000;text-decoration:none}
input[id="sub"]
{
	width:211px;
	height:49px;
	line-height:24px;
	font-size:24px;
	color:#999;	
	background:url("../pictures/bg32.jpg") no-repeat left top;
	padding:0px 22px 2px 0px;
}

input[type="text"] {
	font-family: 'Open Sans', sans-serif;
	padding: 1em 1em 1em 1em;
	width:12%;
	color: #3D3E6A;
	font-size: 14px;
	outline: none;
	background: #f5f6f7;
	border: none;
	font-weight: 100;
	margin-bottom: 1em;
	border: 1px solid #A8AEC5;
	border-radius: .4em;
	-webkit-border-radius: 0.4em;
	-o-border-radius: 0.4em;
	-moz-border-radius: 0.4em;
}


.login-form {
	width: 33%;
	height: 100%;
	margin: 0 auto;
	text-align: center;
	position: relative;
	
}
div#div1{ 
position:relative;
left:45%;
top:10%;

} 
table.hovertable {
 font-family: verdana,arial,sans-serif;
 font-size:11px;
 color:#333333;
 border-width: 2px;
 border-color: #999999;
 border-collapse: collapse;
}
table.hovertable th {
 background-color:#c3dde0;
 border-width: 2px;
 padding: 24px;
 border-style: solid;
 border-color: #a9c6c9;
}
table.hovertable tr {
 background-color:#d4e3e5;
}
table.hovertable td {
 border-width: 2px;
 padding: 24px;
 border-style: solid;
 border-color: #a9c6c9;}
</style>
</head>

<!-- 注册账户审核界面 -->
<body>
	<div >
		<center>
			<font color="white" ><h1>审核用户账户</h1></font>
		</center>
	</div>
	<div >
	<center>	
	<!-- 搜索 -->	
				<form action="${ctx}/userTrail/findByName"  method="post">
				<!-- 用户名 -->
					<input id="username" type="text" name="username" class="text" value = 'USERNAME'
						onfocus="this.value = '';"
						onblur="if (this.value == '') {this.value = 'username';}"><br/>
                <!-- 搜索按钮 -->
				<input id="sub"  type="submit" value="搜索">
				</form></center></div>
	<div><center><br/><br/>
	<!-- 申请账户列表 -->
	<table id="tab" class="hovertable">	
  		<tr>
  			<th><font size='5'>用户名</font></th>
  			<th><font size='5'>邮箱</font></th>
  			<th><font size='5'>年龄</font></th>
  			<th><font size='5'>设置权限</font></th>
  			<th><font size='5'>通过</font></th>
  			<th><font size='5'>驳回</font></th>
  		</tr>
  		<c:forEach items="${pageModel.datas}" var="user"> 
  		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
		
			<input type="hidden" id="utid" type="text" name="utid" class="text" value='${user.utid}'>
  			<td><font size='5'>${user.username}</font></td>
  			<td><font size='5'>${user.email}</font></td>
  			<td><font size='5'>${user.age}</font></td>
  			<!-- 通过账户审核 -->
  			<form  action="${ctx}/userTrail/passUser"  method="post">
			<input type="hidden" id="utid" type="text" name="utid" class="text" value='${user.utid}'>
  			<td><select id="priority" type="select" name="priority" >
						<option value="0">顾客</option>
						<option value="1">管理员</option>
	
			</select></td>
  			<td><input type="submit" value="通过"></td></form>
  			
  			<!-- 驳回账户审核 -->
  			<td><form  action="${ctx}/userTrail/rejectUser"  method="post">
			<input type="hidden" id="utid" type="text" name="utid" class="text" value = '${user.utid}'> 
  			<input type="submit" value="驳回">
  			</form></td>
  		</tr>	 
		</c:forEach>
	</table><br/>
	<!-- 分页 -->
		     <div align="center">
               <a href="${pageContext.request.contextPath}/userTrail/userTrailList"><font color="white" size="4">首页</font></a>
               <c:if test="${pageModel.pageNo>1&&pageModel.datas!=null}"><a href="${pageContext.request.contextPath}/userTrail/userTrailList?pageNo=${pageModel.pageNo-1}&username=${pageModel.username}"><font color="white" size="4">上一页</font></a></c:if>
               <c:if test="${pageModel.pageNo<pageModel.totalpage&&pageModel.datas!=null}"><a href="${pageContext.request.contextPath}/userTrail/userTrailList?pageNo=${pageModel.pageNo+1}&username=${pageModel.username}"><font color="white" size="4">下一页</font></a></c:if>
               <c:if test="${pageModel.totalpage!=0}"><a href="${pageContext.request.contextPath}/userTrail/userTrailList?pageNo=${pageModel.totalpage}&username=${pageModel.username}"><font color="white" size="4">尾页</font></a></c:if>
                <font color="white" size="4"> 总页数:${pageModel.totalpage}</font>
                <font color="white" size="4"> 总数量:${pageModel.totalrecode}</font>
                <font color="white" size="4"> 当前页:${pageModel.pageNo}</font>
        </div>	
	</center>
	</div>
</body>
</html>