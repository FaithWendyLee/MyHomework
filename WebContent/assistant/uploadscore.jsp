<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="factory.DaoFactory"%>
<%@page import="model.Score"%>
<%@page import="model.Plan"%>
<%@page import="service.ScoreService"%>
<%@page import="service.PlanService"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="dao.impl.*"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="plan" class="model.Plan" scope="page">
</jsp:useBean>
<jsp:useBean id="score" class="model.Score" scope="page">
</jsp:useBean>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>uploadScore</title>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/homeworks.js"></script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>

	<jsp:include page="left.jsp"></jsp:include>
	<div class="col-xs-8">
		<%
			request.setCharacterEncoding("utf-8");
			int id = (Integer) request.getSession().getAttribute("assid");
			String cid = (String) request.getParameter("param");
			ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
			PlanDaoImpl planDaoImpl = (PlanDaoImpl) ac.getBean("planDaoImpl");
			for (int i = planDaoImpl.getPlanList()
					.size() - 1; i >= 0; i--) {
				Plan p = planDaoImpl.getPlanList()
						.get(i);
				pageContext.setAttribute("plan", p);
				String pcid = p.getCid() + "";

				if (pcid.equals(cid)) {
		%>
		<div class="panel panel-default">
			<div class="panel-body courseinfo">
				作业<%=p.getPlannumber()%><br>
				<hr>
				作业编号：<%=p.getPlannumber()%><br> 助教提交截止日期：<%=p.getAssduetime()%><br>
				作业文件格式：<%=p.getFormat()%><br> 分数：<%=p.getScore()%><br> 难度：<%=p.getFormat()%><br>
				内容：<%=p.getContent()%><br>

				<%
				CourseDaoImpl courseDaoImpl = (CourseDaoImpl) ac.getBean("courseDaoImpl");
					if (courseDaoImpl.getCourse(Integer.parseInt(cid)).isOverdue(
									p.getAssduetime())) {
				%>
				未提交，已结束~
				<%
					} else {
						ScoreDaoImpl scoreDaoImpl = (ScoreDaoImpl) ac.getBean("scoreDaoImpl");
								Score s = scoreDaoImpl
										.findScore(p.getPid());
								if (s == null) {
									session.setAttribute("cid", cid);
									session.setAttribute("assid", id);
				%>
				<a href="submit.jsp?id=<%=p.getPid()%>">提交</a>
				<%
					} else {
									if (s.getScorecheck().equals("agree")) {
				%>
				已成功提交~
				<%
					}else if (s.getScorecheck().equals("disagree")) {
										session.setAttribute("cid", cid);
										session.setAttribute("assid", id);
				%>
				<a href="submit.jsp?id=<%=p.getPid()%>">需重新提交</a>
				<%
					}else if (s.getScorecheck().equals("wait")) {
				%>
				正在审核~
				<%
					}
								}
							}
				%>
			</div>
		</div>
		<%
			}
			}
		%>

	</div>
	<div class="col-xs-2"></div>



</body>
</html>