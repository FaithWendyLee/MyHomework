package servlets.teacher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.impl.CourseDaoImpl;
import model.Course;

public class setassistant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public setassistant() {
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
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Course c = (Course) session.getAttribute("course");
		String cid = (String) session.getAttribute("courseid");
		int id=Integer.parseInt(cid);
		response.setContentType("text/html");
		String al[] = request.getParameterValues("assistantlist");
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<al.length;i++){
			sb.append(al[i]+" ");
		}
		String assistantlist=sb.toString();
		c.setAssistantlist(assistantlist);
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseDaoImpl courseDaoImpl = (CourseDaoImpl) ac.getBean("courseDaoImpl");
		courseDaoImpl.alterCourse(c);
		response.sendRedirect(request.getContextPath() + "/teacher/setassistant.jsp?param="+id);

	}
}
