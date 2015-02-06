<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%-- <%
	 HttpSession seesion = request.getSession();
	 response.setHeader("Content-Type", "application/octet-stream");
	File file=new File("E://picture//3.jpg");
	try {
		OutputStream outputStream=response.getOutputStream();
		FileUtils.copyFile(file, outputStream);
		out.clear();
		out=pageContext.pushBody();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
%> --%>
<%-- <%
out.clear();
out=pageContext.pushBody();//原来加上这两句jsp页面就不显示了
%> --%>
</head>
<body>
<h1>hellllllo</h1>
<%-- <img  src="<%=request.getContextPath() %>/user/toLookImage" /> --%>
<img alt="" src="/Login_ssm_mav_picbyte/user/toLookImage?id=3"><!-- 原来这里要加上工程名Login_ssm_mav_picbyte -->
</body>
</html>