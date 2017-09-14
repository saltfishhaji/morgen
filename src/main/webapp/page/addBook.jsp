<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 注册页面 -->
<html>
<head>
<title>AddBookPage</title>

<!-- 引入的样式和js脚本 -->
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
</head>


<!-- 注册界面 -->
<body class="easyui-layout" >

		<center>
			<font color="white" ><h1 color=#F00>加入新书</h1></font>
		</center>
<br/><br/>

		<div class="login-form">
		<!-- 填写信息区 -->
<form action ="${ctx}/MySystem/book/addBook" method="POST">  
				<div class="form-text">				
					<font color="white" size="5">书名：</font><input id="bookName" type="text" name="bookName" class="text" value = 'BOOKNAME'
						onfocus="this.value = '';"
						onblur="if (this.value == '') {this.value = 'BOOKNAME';}"><br/>
	
					<font color="white" size="5">价格：</font><input id="price" type="text" name="price" value="0" onfocus="this.value = '';"
						onblur="if (this.value == '') {this.value = '0';}"><br/>
					<font color="white" size="5">数量：</font><input id="stock" type="text" name="stock" value="0" onfocus="this.value = '';"
						onblur="if (this.value == '') {this.value = '0';}"><br/>			
					<font color="white" size="5">分类：</font><input id="category" type="text" name="category" class="text" value="CATEGORY"
						onfocus="this.value = '';"
						onblur="if (this.value == '') {this.value = 'CATEGORY';}"><br/>
					
					
					<font color="white" size="5">描述：</font><input id="description" type="text" name="description" value="DESCRIPTION"
						onfocus="this.value = '';"
						onblur="if (this.value == '') {this.value = 'DESCRIPTION';}"><br/>
						
	

				</div>
				<input id="bnt" type="submit" value="加入书架"></form>
			
		</div>

</body>
</html>