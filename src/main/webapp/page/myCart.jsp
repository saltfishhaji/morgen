<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>MyCartPage</title>

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

select {
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

<body>
	<div >
		<center>
			<font color="white" ><h1 color=#F00>购物车</h1></font>
			<form  action="${ctx}/cart/buy"  method="post">
			<font color="white" ><h3 color=#F00>总价： ${sumall}</h3></font>
			交易密码：<input  id="tps" type="password" name="tps" >	
  			<input type="submit" value="购买"></form>
		</center>

	</div>

<div>
	<div >
	<center>

	<table id="tab" class="hovertable">
	<br/><br/>
  		<tr>
  			<th><font size='5'>书名</font></th>
  			<th><font size='5'>数量</font></th>
  			<th><font size='5'>小计</font></th>
  			<th><font size='5'>修改数量</font></th>
  			<th><font size='5'>确认修改</font></th>
  			<th><font size='5'>删除</font></th>

  		</tr>		
  		<c:forEach items="${pageModel.datas}"  var="cart">
  		<tr  onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';"> 

  			<td align=center><font size='5'>${cart.bookName}</font></td>
  			<td align=center><font size='5'>${cart.num}</font></td>
  			<td align=center><font size='5'>${cart.sum}</font></td>
  			<form  action="${ctx}/cart/changeCart"  method="post">
  			<td><input  id="bookNum" type="text" name="bookNum" class="text" onfocus="this.value = '';"></td>
			<input type="hidden" id="cartId" type="text" name="cartId" class="text" value='${cart.cartId}'>		
			<input type="hidden" id="bookId" type="text" name="bookId" class="text" value='${cart.bookId}'>		
  			<td><input type="submit" value="确认修改"></td></form>
  						
  			<form  action="${ctx}/cart/delete"  method="post">
			<input type="hidden" id="cartId" type="text" name="cartId" class="text" value='${cart.cartId}'>		
			<input type="hidden" id="bookId" type="text" name="bookId" class="text" value='${cart.bookId}'>		
  			<td><input type="submit" value="删除"></td></form>	
  		</tr></c:forEach>	 
  	</table> 
  	
  	<br/>
  	<!-- 分页 -->
  	  <div align="center">
               <a href="${pageContext.request.contextPath}/cart/showCart"><font color="white" size="4">首页</font></a>
               <c:if test="${pageModel.pageNo>1&&pageModel.datas!=null}"><a href="${pageContext.request.contextPath}/cart/showCart?pageNo=${pageModel.pageNo-1}"><font color="white" size="4">上一页</font></a></c:if>
               <c:if test="${pageModel.pageNo<pageModel.totalpage&&pageModel.datas!=null}"><a href="${pageContext.request.contextPath}/cart/showCart?pageNo=${pageModel.pageNo+1}"><font color="white" size="4">下一页</font></a></c:if>
               <c:if test="${pageModel.totalpage!=0}"><a href="${pageContext.request.contextPath}/cart/showCart?pageNo=${pageModel.totalpage}"><font color="white" size="4">尾页</font></a></c:if>
                                           <font color="white" size="4"> 总页数:${pageModel.totalpage}</font>
                                            <font color="white" size="4">总数量:${pageModel.totalrecode}</font>
                                            <font color="white" size="4">当前页:${pageModel.pageNo}</font>
        </div>	
	</center>
	</div> 	
</div>
</body>
</html>