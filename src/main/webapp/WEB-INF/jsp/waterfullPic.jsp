<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="net.sf.json.JSONArray" %>
<%@page import="net.sf.json.JSONObject" %>
<%@page import="com.chatRoom.model.Photo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.8.3.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/waterfull.js"></script> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/waterfull.css" type="text/css">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println(path);
System.out.println(basePath);
%>

</head>
<body>
<div id="main">
		<div class="pin">
			<div class="box">
			  <img src="/Login_ssm_mav_picbyte/user/toLookImage?id=13">  <!-- 注意这里的访问路径是正确的是，之前因为在web.xml文件中写成了png,所以折腾了很久 -->
			</div>
		</div>
		<div class="pin">
			<div class="box">
			  <!-- <img src='/Login_ssm_mav_picbyte/static/pic/img2.jpg'> -->
			  <img src="/Login_ssm_mav_picbyte/user/toLookImage?id=14">
			</div>
		</div>
		<div class="pin">
			<div class="box">
			  <img src="/Login_ssm_mav_picbyte/user/toLookImage?id=15">
			</div>
		</div>
		<div class="pin">
			<div class="box">
			  <img src="/Login_ssm_mav_picbyte/user/toLookImage?id=16">
			</div>
		</div>
		<div class="pin">
			<div class="box">
			  <img src="/Login_ssm_mav_picbyte/user/toLookImage?id=17">
			</div>
		</div>
	</div>
</body>
</html>