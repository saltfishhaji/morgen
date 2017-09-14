<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript">
var socket =null;
$(function(){
	function parseObj(strData){//转换对象
	    return (new Function( "return " + strData ))();
	};
	//创建socket对象
	socket = new WebSocket("ws://localhost:8080/MySystem/websocket/chat");
	//连接创建后调用
	socket.onopen = function() {
		$("#showMsg").append("连接成功...<br/>");
	};
	//接收到服务器消息后调用
	socket.onmessage = function(message) {
		var data=parseObj(message.data);
		if(data.type=="message"){
			$("#showMsg").append("<span style='display:block'>"+data.text+"</span>");
		}
		if(data.type=="userList"){
			$("#showUsr").html("");
			$("#showUsr").append("<span style='display:block'>"+data.text+"</span>");
		}
	};
	//关闭连接的时候调用
	socket.onclose = function(){
		alert("close");
	};
	//出错时调用
	socket.onerror = function() {
		alert("error");
	};
	$("#sendButton").click(function() {
		socket.send($("#msg").val());
	});

});
</script>
<style>
div#showUsr{ 
position:absolute;
left:19%;
top:8%;

} 
</style>
</head>
<body>

<center>
<font color="white" ><h1 color=#F00>聊天室</h1></font>
	<div id="showMsg" style="size:10px ;background:#FFF; border: 1px solid; width: 500px; height: 400px; overflow: auto;"></div>
	<div id="showUsr" style="size:10px ;background:#FFF; border: 1px solid; width: 100px; height: 400px; overflow: auto;"></div>
	<div>
		<input type="text" id="msg" /> 
		<input type="button" id="sendButton" value="发送" />
	</div></center>
</body>
</html>