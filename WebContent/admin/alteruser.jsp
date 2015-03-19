<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyHomework</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div class="container">
		<form method="post" action="../Alteruser2">
		<h2>修改用户</h2>
			<label>学号</label><input type="text" name="number" placeholder="仅选课学生填"/><br> <br> 
			<label>姓名</label><input type="text" name="name"/><br> <br> 
			<label>密码</label><input type="password" name="pwd"/><br> <br> 
			<button class="btn btn-lg btn-primary ">确定</button>
		</form>
	</div>
</body>
</html>