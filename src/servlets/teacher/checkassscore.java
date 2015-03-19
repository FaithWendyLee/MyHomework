package servlets.teacher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.impl.PlanDaoImpl;
import dao.impl.ScoreDaoImpl;
import model.Score;

/**
 * Servlet implementation class checkassscore
 */
// @WebServlet("/checkassscore")
public class checkassscore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public checkassscore() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		response.setContentType("text/html");

		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		ScoreDaoImpl scoreDaoImpl = (ScoreDaoImpl) ac.getBean("scoreDaoImpl");
		String agree = request.getParameter("agree");
		String disagree = request.getParameter("disagree");
		String planid = request.getParameter("planid");
		String cid = request.getParameter("courseid");
		int pid=Integer.parseInt(planid);
		if (agree != null) {
			Score s=scoreDaoImpl.findScore(pid);
			s.setScorecheck("agree");
			scoreDaoImpl.updateScore(s);
			response.sendRedirect(request.getContextPath()+"/teacher/checkassScore.jsp?param="+cid);
		}
		if (disagree != null) {
			Score s=scoreDaoImpl.findScore(pid);
			s.setScorecheck("disagree");
			scoreDaoImpl.updateScore(s);
			response.sendRedirect(request.getContextPath()+"/teacher/checkassScore.jsp?param="+cid);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
