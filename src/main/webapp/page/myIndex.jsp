<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="internationalisation" /></title>

<!-- 样式 -->
<style type="text/css">
a:link{color:#000000;text-decoration:none}
a:visited{color:#000000;text-decoration:none}

#nav {
 	clear: both;    
    height: 50px;   
    line-height: 50px;   
    background: #0090CE;   
    padding: 0 20px;   
    color: White;   
    -moz-box-shadow: 5px 5px 10px #B7B7B7;   
    box-shadow: 5px 5px 10px #B7B7B7; 
}
#nav li {
	display: inline;
}

#nav li a {
 	text-shadow: 0 1px 0 rgba(0,0,0,0.3);   
    color: White;   
    display: inline-block;   
    width: 100px;   
    height: 100%;   
    width: 150px;  
    font-size: 5;   
    text-align: center;   
    margin-right: 10px;  
}

#nav li a:hover{background: #0074A6;color:White; } 

#content {
}
h2 {
	margin: 0;
	padding: 0.5em 0;
	color:#568945;
	font-family:Helvetica, Arial, Sans-serif;
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
div#clock{ 
position:fixed; 
top:24px; 
right:10px; 

} 
</style>

<!-- 引入的样式和js脚本 -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/jquery-easyui/themes/icon.css"></link>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/jquery-easyui/jquery.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/jquery-easyui/themes/default/easyui.css"></link>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/jquery-easyui/styles.css" />
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/jquery-easyui/sliding_effect.js"></script>


<!-- 显示系统时间 -->
<script language="javascript"> 
function fix(num, length) {
	  return ('' + num).length < length ? ((new Array(length + 1)).join('0') + num).slice(-length) : '' + num;
	}

function realSysTime(clock){ 
var now=new Date(); //创建Date对象 
var year=now.getFullYear(); //获取年份 
var month=now.getMonth(); //获取月份 
var date=now.getDate(); //获取日期 
var day=now.getDay(); //获取星期 
var hour=fix(now.getHours(),2); //获取小时 
var minu=fix(now.getMinutes(),2); //获取分钟 
var sec=fix(now.getSeconds(),2); //获取秒钟 
month=month+1; 
var time=year+"/"+month+"/"+date+" "+" "+hour+":"+minu+":"+sec; //组合系统时间 
clock.innerHTML=time; //显示系统时间 
} 
window.onload=function(){ 
window.setInterval("realSysTime(clock)",1000); //实时获取并显示系统时间 
} 
</script>
</head>

<!-- 员工界面 -->
<body class="easyui-layout" > 

<!-- 员工背景图片 -->  
<div id="div1"><img src="${ctx}/MySystem/pictures/bg2.jpg" /></div>

<center> 
<h1><font face="楷体" color="white"><spring:message code="title" /></font></h1><font color="green"  size="5">


    
<!-- 系统时钟区 -->
<div id="clock"></div></font>

<!-- 功能选项条 -->
<div id="sliding-navigation" style="height:70px;">
<div ><font face="楷体" size='5'>
<ul id="nav">  			<li ><font color="white" ><spring:message code="welcome" /> <sec:authentication property ="name"/> !  </font></li>
						<li class="sliding-element"><a href="${ctx}/MySystem/book/showBooks" target="myframe"><font color="white" ><spring:message code="showBooks" /></font></a></li>
						<li class="sliding-element"><a href="${ctx}/MySystem/cart/showCart" target="myframe"><font color="white" ><spring:message code="showCart" /></font></a></li>
						<li class="sliding-element"><a href="${ctx}/MySystem/cart/showBuy" target="myframe"><font color="white" ><spring:message code="showBuy" /></font></a></li>
						<li class="sliding-element"><a href="${ctx}/MySystem/page/infoManager.jsp" target="myframe"><font color="white" ><spring:message code="infoManager" /></font></a></li>
						<li class="sliding-element"><a href="${ctx}/MySystem/page/webso.jsp" target="myframe"><font color="white" ><spring:message code="webso" /></font></a></li>
					<sec:authorize access="hasRole('ROLE_ADMIN')"><li class="sliding-element"><a href="${ctx}/MySystem/userTrail/userTrailList" target="myframe"><font color="white" ><spring:message code="userTrailList" /></font></a></li>
					<li class="sliding-element"><a href="${ctx}/MySystem/page/addBook.jsp" target="myframe"><font color="white" ><spring:message code="addBook" /></font></a></li>
					<li class="sliding-element"><a href="${ctx}/MySystem/userTrail/userList" target="myframe"><font color="white" ><spring:message code="userList" /></font></a></li>
					</sec:authorize>

						<li class="sliding-element"><a href="${ctx}/MySystem/userTrail/logOut"><font color="white" ><spring:message code="logOut" /></font></a></li>
					</ul></font>
				</div></div>
</center>
 
    <!-- 功能操作区 -->
    <div id="content" > 
    <iframe name="myframe" id="myframe" frameborder="0" style="padding: 0px; width: 100%; height: 1000px;"></iframe>  	
        </div>
  
     <!-- 底栏 -->
    <div data-options="region:'south'" style="height:20px;">
    <div style="float:left">Language: <a href="?lang=zh_CN"><spring:message code="language.cn" /></a> - <a href="?lang=en_US"><spring:message code="language.en" /></a> </div>
    <div style="float:left;margin-left:35%">Copyright @ MyCompany. All rights reserved.</div>  
	</div>    
</body>  
</html>