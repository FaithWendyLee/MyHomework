<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>homeworkinfo</title>
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
		<form method="post" action="homeworkinfo">
			<input type="text"  name="month" "/> 
			<Button>统计</Button>
			</form>
		</div>
		<center>
		    <%
		    if(month==null){

	%>
			    <h3>作业难度情况统计</h3>
			<img  alt="还未统计。。。" width=500 height=300 border=0>
		<%
		    }else{
	%>
	
		    <h3>作业难度情况统计<%=month%></h3>
	<img src="difficulty?param=<%=month%>" alt="还未统计。。。" width=500 height=300 border=0>
	<%
		    }
	%>
	
	
	
	
		    <%
		    if(month==null){
		    	
	%>
	<h3>作业安排情况统计</h3>
			<img  alt="还未统计。。。" width=500 height=300 border=0>
		<%
		    }else{
	%>
	<h3>作业文件格式情况统计<%=month%></h3>
	<img src="format?param=<%=month%>" alt="还未统计。。。" width=500 height=300 border=0>
	<%
		    }
	%>
		</center>
	</div>
	<div class="col-xs-2"></div>
</body>
</html>