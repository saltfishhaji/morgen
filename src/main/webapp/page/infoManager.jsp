<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 个人信息管理页面 -->
<html>
<head>
<title>RegisterPage</title>

<!-- 引入的样式和js脚本 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jquery-easyui/themes/icon.css"></link>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jquery-easyui/themes/default/easyui.css"></link>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>


<!-- 样式 -->
<style type="text/css">
input[type="text"] {
	font-family: 'Open Sans', sans-serif;
	width: 40%;
	padding: 1em 1em 1em 1em;
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

input[type="password"] {
	font-family: 'Open Sans', sans-serif;
	width: 40%;
	padding: 1em 1em 1em 1em;
	color: #3D3E6A;
	font-size: 14px;
	outline: none;
	background: #f5f6f7;
	border: none;
	margin-bottom: 1em;
	border: 1px solid #A8AEC5;
	border-radius: .4em;
	-webkit-border-radius: 0.4em;
	-o-border-radius: 0.4em;
	-moz-border-radius: 0.4em;
}

input[id="bnt"]{
	background: #ff6600;
	padding: .8em .6em .8em .6em;
	outline: none;
	border: none;
	font-size: 1em;
	cursor: pointer;
	width: 20%;
	color: #fff;
	text-transform: uppercase;
	font-weight: bold;
	border-top-right-radius: .4em;
	-webkit-border-top-right-radius: .4em;
	-o-border-top-right-radius: .4em;
	-moz-border-top-right-radius: .4em;
	border-bottom-right-radius: .4em;
	-webkit-border-bottom-right-radius: .4em;
	-o-border-bottom-right-radius: .4em;
	-moz-border-bottom-right-radius: .4em;
	position: absolute;
	top: 100%;
	right: 40%;
}

.login-form {
	width: 33%;
	height: 100%;
	margin: 0 auto;
	text-align: center;
	position: relative;
}

.copy-right {
	text-align: center;
	padding-top: 10em;
	color: #fff;
}
div#div1{ 
position:fixed; 
top:0; 
left:0; 
bottom:0; 
right:0; 
z-index:-1; 
} 
div#div1 > img { 
height:100%; 
width:100%; 
border:0; 
} 
div#div2{ 
position:absolute;
left:41%;
bottom:0;

} 

</style>

<!-- 验证输入有效 -->
<
</head>


<!-- 管理个人信息界面 -->
<body class="easyui-layout" >
	<div >
		<center>
			<font color="white" ><h1 color=#F00>管理我的信息</h1></font>
		</center>
	</div>
	<div>
<br/>
		<div class="login-form">
		<!-- 修改信息区 -->
			<form action="${ctx}/MySystem/userTrail/editUser"  method="post">
				<div class="form-text">
					<input type="hidden" id="utid" type="text" name="utid" class="text" value = '${uT.utid}'>
					<input type="hidden" id="tradePass" type="text" name="tradePass" class="text" value = '${uT.tradePass}'>
					<font color="white" size="5">名字：</font><input id="username" type="text" name="username" class="text" value = '${uT.username}'
						onfocus="this.value = '';"
						onblur="if (this.value == '') {this.value = '${uT.username}';}"><br/>

					<font color="white" size="5">邮箱：</font><input id="email" type="text" name="email" class="text" value='${uT.email}'
						onfocus="this.value = '';"
						onblur="if (this.value == '') {this.value = '${uT.email}';}"><br/>
						

					<font color="white" size="5">年龄：</font><input id="age" type="text" name="age" value='${uT.age}'
						onfocus="this.value = '';"
						onblur="if (this.value == '') {this.value = '${uT.age}';}"><br/>
					</div>
				<input class="btnform" id="bnt" type="submit" value="提交">
			</form>
		</div>
	</div>
</body>
</html>