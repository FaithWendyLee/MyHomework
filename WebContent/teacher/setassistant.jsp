<%@page import="model.Assistant"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="model.Course"%>
<%@page import="dao.impl.*"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="assistant" class="model.Assistant" scope="page">
</jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>MyHomework</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>

	<%
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CourseDaoImpl courseDaoImpl = (CourseDaoImpl) ac
				.getBean("courseDaoImpl");
		request.setCharacterEncoding("utf-8");
		String id = (String) request.getParameter("param");
		int cid = Integer.parseInt(id);
		Course c = courseDaoImpl.getCourse(cid);
		session.setAttribute("course", c);
		session.setAttribute("courseid", id);
	%>
	<jsp:include page="left.jsp"></jsp:include>

	<div class="col-xs-8">

		<%
			
		%>

		<div class="panel panel-default">
			<div class="panel-body courseinfo">
				助教信息<br>
				<hr>
				助教：<%=c.getAssistantlist()%><br>
			</div>
		</div>


		<%
		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse("2015-04-01");
			Date nowDate = new Date();
			if(!c.Isbetween(nowDate)){
				%>
				&nbsp;
				<%		
			}else{
			if (date.before(nowDate)) {
		%>
		&nbsp;
		<%
			} else {
		%>
		<form name="set_assistant" method="post" action="../setassistant">
			<div class="div-right">
				<a href="javascript:document.set_assistant.submit();">确定</a>
			</div>
			<table class="table table-bordered">
				<caption>
					<h3>安排助教</h3>
				</caption>
				<thead>
					<tr>
						<th>助教编号</th>
						<th>助教姓名</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<%
					AssistantDaoImpl assistantDaoImpl = (AssistantDaoImpl) ac.getBean("assistantDaoImpl");
					
						for (int i = 0; i < assistantDaoImpl.getAssistantList().size(); i++) {
								Assistant ass = assistantDaoImpl.getAssistantList()
										.get(i);
								pageContext.setAttribute("assistant", ass);
					%>

					<TR>
						<TD><jsp:getProperty name="assistant" property="assistantid" /></TD>
						<TD><jsp:getProperty name="assistant"
								property="assistantname" /></TD>
						<TD><input type="checkbox" name="assistantlist"
							value="<%=ass.getAssistantname()%>"></TD>
						<%
							}
						%>
					</TR>
				</tbody>
			</table>
		</form>
		<%
			}}
		%>
	</div>
	<div class="col-xs-2"></div>

</body>
</html>