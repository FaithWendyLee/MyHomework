package servlets.teacher;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.impl.CourseDaoImpl;
import dao.impl.PlanDaoImpl;
import model.Plan;

/**
 * Servlet implementation class makeplan
 */
@WebServlet("/makehomeworkplan")
public class makeHomeworkplan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public makeHomeworkplan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();

		response.setContentType("text/html");
		String courseid=request.getParameter("courseid");
		int cid=Integer.parseInt(courseid);
		String pnumber=request.getParameter("plannumber");
		String datestu=request.getParameter("datestu");
		String dateass = request.getParameter("dateass");
		String format = request.getParameter("format");
		String score = request.getParameter("score");
		String difficulty = request.getParameter("difficulty");
		String content = request.getParameter("content");
		Plan p = new Plan();
		p.setCid(cid);
		p.setPlannumber(pnumber);
		p.setStuduetime(datestu);
		p.setAssduetime(dateass);
		p.setFormat(format);
		p.setScore(score);
		p.setDifficulty(difficulty);
		p.setContent(content);
		Date nowDate = new Date();
		String ptime=(new SimpleDateFormat("yyyy-MM-dd")).format(nowDate); 
		p.setPtime(ptime);
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		PlanDaoImpl planDaoImpl = (PlanDaoImpl) ac.getBean("planDaoImpl");
		planDaoImpl.addPlan(p);
		session.setAttribute("plan", p);
		response.sendRedirect(request.getContextPath() + "/teacher/makehomeworkplan.jsp?param="+cid);
	}

}
