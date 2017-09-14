<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 查看差旅申请状态 -->
<html>
<head>
<title>BookShelf</title>

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
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.soap.js"></script>
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

input[id="subInput"] {
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


<script>
	  function hello(obj)
	  {
		var bookId=$(obj).attr("value");
		var data={'bookId':bookId};  	
     	$.ajax({
    	     type: 'POST',
    	     url: "${ctx}/book/getSOAP" ,
    	     //url: "${ctx}/book/getREST",
   	     	data: data ,
    	     success: function(data){
    	    	$.messager.alert("详细信息",data);  	
    	    },
    	    error : function() {  
    	    	alert("异常！");  
    		       }  
    	});  
	  }
</script>
</head>


<body>
	<div >
		<center>
			<font color="white" ><h1 color=#F00>书店</h1></font>
		</center>

	</div>

<div>
	<div >
	<center>
		<form action="${ctx}/book/findBook"  method="post">
		查找类型：
					<select id="choice"  name="choice" >
						<option value="bName">书名</option>
						<option value="cat">类型</option>
			</select>
			<input id="subInput" type="text" name="subInput" class="text" 
						onfocus="this.value = '';">
			<br/>
                <!-- 搜索按钮 -->
				<input id="sub" type="submit" value="搜索">
				</form>
	<!-- 申请列表 -->
	<table id="tab" class="hovertable">
	<br/><br/>
  		<tr>
  			<th><font size='5'>书名</font></th>
  			<th><font size='5'>价格</font></th>
  			<th><font size='5'>库存</font></th>
  			<th><font size='5'>分类</font></th>
  			<th><font size='5'>购买数量</font></th>
  			<th><font size='5'>操作</font></th>
  			<sec:authorize access="hasRole('ROLE_ADMIN')">
  			<th><font size='5'>操作</font></th>
  			</sec:authorize>
  		</tr>		
  		<c:forEach items="${pageModel.datas}"  var="book">
  		<tr  onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';"> 

  			<td align=center><font size='5' value='${book.bookId}' onclick="hello(this)">${book.bookName}</font></td>
  			<td align=center><font size='5'>${book.price}</font></td>
  			<td align=center><font size='5'>${book.stock}</font></td>
  			<td align=center><font size='5'>${book.category}</font></td>
  			<form  action="${ctx}/book/putCart"  method="post">
			<input type="hidden" id="bookId" type="text" name="bookId" class="text" value='${book.bookId}'>
  			<td><input  id="bookNum" type="text" name="bookNum" class="text" onfocus="this.value = '';"></td>
  			<td><input  type="submit" value="加入购物车"></td></form>
  			
  			<sec:authorize access="hasRole('ROLE_ADMIN')">
  			<form action="${ctx}/book/getBook/${book.bookId}"  method="post">
  			<td><input type="submit" value="修改"></td></th>
  			</form>
  			</sec:authorize>
		
  		</tr></c:forEach>	 
  	</table> 
  	
  	<br/>
  	<!-- 分页 -->
  	  <div align="center">
               <a href="${pageContext.request.contextPath}/book/showBooks"><font color="white" size="4">首页</font></a>
               <c:if test="${pageModel.pageNo>1&&pageModel.datas!=null}"><a href="${pageContext.request.contextPath}/book/showBooks?pageNo=${pageModel.pageNo-1}"><font color="white" size="4">上一页</font></a></c:if>
               <c:if test="${pageModel.pageNo<pageModel.totalpage&&pageModel.datas!=null}"><a href="${pageContext.request.contextPath}/book/showBooks?pageNo=${pageModel.pageNo+1}"><font color="white" size="4">下一页</font></a></c:if>
               <c:if test="${pageModel.totalpage!=0}"><a href="${pageContext.request.contextPath}/book/showBooks?pageNo=${pageModel.totalpage}"><font color="white" size="4">尾页</font></a></c:if>
                                           <font color="white" size="4"> 总页数:${pageModel.totalpage}</font>
                                            <font color="white" size="4">总数量:${pageModel.totalrecode}</font>
                                            <font color="white" size="4">当前页:${pageModel.pageNo}</font>
        </div>	
	</center>
	</div> 	
</div>
</body>
</html>