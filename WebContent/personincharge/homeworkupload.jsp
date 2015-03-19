<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>homeworkupload</title>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div class="col-xs-2"></div>
	<div class="col-xs-8">
		<%
		request.setCharacterEncoding("utf-8");
		String month=(String)session.getAttribute("month");;
	%>
		<div class="div-right">
		<form method="post" action="homeworkupload1">
			<input type="text"  name="month" "/> 
			<Button>统计</Button>
			</form>
		</div>
		<center>
		    <h3>作业提交情况统计</h3>
		    <%
		    if(month==null){
		    	
	%>
			<img  alt="还未统计。。。" width=500 height=300 border=0>
		<%
		    }else{
	%>
	<img src="homeworkupload2?param=<%=month%>" alt="还未统计。。。" width=500 height=300 border=0>
	<%
		    }
	%>
		</center>
	</div>
	<div class="col-xs-2"></div>
</body>
</html>