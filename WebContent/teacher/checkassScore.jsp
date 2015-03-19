<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="factory.DaoFactory"%>
<%@page import="model.Plan"%>
<%@page import="model.Course"%>
<%@page import="model.Score"%>
<%@page import="service.ScoreService"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="dao.impl.*"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>assscore</title>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/scores.js"></script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<jsp:include page="left.jsp"></jsp:include>
	<div class="col-xs-8">
		<%
			request.setCharacterEncoding("utf-8");
			String cid = (String) request.getParameter("param");
			int ccid=Integer.parseInt(cid);
			ApplicationContext ac = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			CourseDaoImpl courseDaoImpl = (CourseDaoImpl) ac
					.getBean("courseDaoImpl");
			PlanDaoImpl planDaoImpl = (PlanDaoImpl) ac.getBean("planDaoImpl");
				Course c= courseDaoImpl.getCourse(ccid);
			for (int i = planDaoImpl.getPlanList()
					.size() - 1; i >= 0; i--) {
				Plan p = planDaoImpl.getPlanList()
						.get(i);
				String pcid = p.getCid() + "";

				if (pcid.equals(cid)) {
		%>
		<div class="panel panel-default">
			<div class="panel-body courseinfo">
				作业<%=p.getPlannumber()%><br>
				<hr>
				<%
				ScoreDaoImpl scoreDaoImpl = (ScoreDaoImpl) ac
				.getBean("scoreDaoImpl");
					ArrayList<Score> sl = scoreDaoImpl.getScore();
							String pathwenjian = "D://apache-tomcat-7.0.56//webapps//data//assistant";
							//声明集合存放目录下所有文件的文件名
							ArrayList<Score> ScoreList = new ArrayList<Score>();
							File folder = new File(pathwenjian);
							//判断文件夹是否存在并且是否是一个目录
							if (folder.exists() && folder.isDirectory()) {
								//获得目录中所有文件及目录
								File[] files = folder.listFiles();
								for (File file : files) {
									//如果是文件
									if (file.isFile()) {
										//将文件名放入集合中
										for(int j=0;j<sl.size();j++){
											if(sl.get(j).getScorename().equals(file.getName())&&p.getPid()==sl.get(j).getPlanid()){
												ScoreList.add(sl.get(j));
											}
										}
									}
								}
							}
							//此处设计不好，需要限制提交score的name必须unique！；
							//需要限制每个plan只对应一个score
							//哪天知道怎么改了，记得来改一下~

							for (int k = 0; k < ScoreList.size(); k++) {
								String scorename=ScoreList.get(k).getScorename();
								String state=ScoreList.get(k).getScorecheck();
							    Date nowDate = new Date();
								if (!c.Isbetween(nowDate)) {
									%>
									<%=scorename%>
									<%
								}
								else{
				%>
				<form id="form" method="GET" action="../checkassscore">
					<a href="../downloadscore?filename=<%=scorename%>"><%=scorename%></a><br />
					<% 
					if(state.equals("agree")){
					%>
					<div class="div-right">
						<h4>已发布~</h4>
					</div>
					
					<% 
					}else if(state.equals("disagree")){
					%>
					<div class="div-right">
						<h4>未通过，需重批~</h4>
					</div>
					
					<% 
					}else if(state.equals("wait")){
					%>
					<div class="div-right">
						<input type="hidden" value="<%=p.getPid()%>" name="planid">
						<input type="hidden" value="<%=cid%>" name="courseid">
						<input type="submit" value="同意发布" name="agree" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit"
							value="不同意" name="disagree" />
					</div>
					<% 
					}
					%>
				</form>
				<%
					}}
				%>
			</div>
		</div>

		<%
			
			}}
		%>

	</div>
	<div class="col-xs-2"></div>



</body>
</html>
