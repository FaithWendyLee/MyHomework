package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.impl.AssistantDaoImpl;
import dao.impl.PersonInChargeDaoImpl;
import dao.impl.StudentDaoImpl;
import dao.impl.TeacherDaoImpl;
import model.Assistant;
import model.PersonInCharge;
import model.Student;
import model.Teacher;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		response.sendRedirect(request.getContextPath() + "/user/Login.jsp");
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

		response.setContentType("text/html");
		String name = request.getParameter("login");
		String pw = request.getParameter("password");
		String identity = request.getParameter("identity");
		session.setAttribute("name", name);
		String addr = "";

		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

		switch (identity) {
		case "系统管理员": {
			if (name.equals("admin") && pw.equals("admin")) {
				addr = "/admin/mnguser.jsp";
			}
		}
			break;
		case "授课教师":{
			TeacherDaoImpl teacherDaoImpl = (TeacherDaoImpl) ac.getBean("teacherDaoImpl");
			Teacher tea=teacherDaoImpl.findTeacher(name, pw);
			if(tea==null)
				JOptionPane.showMessageDialog(null, "该用户不存在！");
			else{
				if(tea.getTeapwd()==null)
					JOptionPane.showMessageDialog(null, "密码错误！");
			       // TODO
				else{    			     	
					int teaid=tea.getTeaid();
					session.setAttribute("teacherid", teaid);
					addr = "/teacher/mycourse.jsp";
               }     					
			}	
		}break;
		case "选课学生":{
			StudentDaoImpl studentDaoImpl = (StudentDaoImpl) ac.getBean("studentDaoImpl");
			Student student=studentDaoImpl.findStudent(name, pw);
			if(student==null)
				JOptionPane.showMessageDialog(null, "该用户不存在！");
			else{
				if(student.getStupwd()==null)
					JOptionPane.showMessageDialog(null, "密码错误！");
			       // TODO
				else{
					int stuid=student.getId();
					System.out.println(student.getId()+","+student.getNumber());
					session.setAttribute("stuid", stuid);
					addr = "/student/mycoursestu.jsp";
               }     					
			}	

		}break;
		case "助教":{
			AssistantDaoImpl assistantDaoImpl = (AssistantDaoImpl) ac.getBean("assistantDaoImpl");
			Assistant ass=assistantDaoImpl.findAssistant(name, pw);
			if(ass==null){
				JOptionPane.showMessageDialog(null, "该用户不存在！");				
			}
			if(ass.getAssistantpwd()==null)
				JOptionPane.showMessageDialog(null, "密码错误！");
		       // TODO
			else{
				int assid=ass.getAssistantid();
				session.setAttribute("assid", assid);
				addr = "/assistant/mycourseass.jsp";
			}
		}break;
		case "教学负责人": {
			PersonInChargeDaoImpl personInChargeDaoImpl = (PersonInChargeDaoImpl) ac.getBean("personInChargeDaoImpl");
			PersonInCharge personincharge=personInChargeDaoImpl.findPersonInCharge(name, pw);
			if(personincharge==null){
				JOptionPane.showMessageDialog(null, "该用户不存在！");				
			}
			if(personincharge.getPicpwd()==null)
				JOptionPane.showMessageDialog(null, "密码错误！");
		       // TODO
			else{
				int personinchargeid=personincharge.getPicid();
				session.setAttribute("personinchargeid", personinchargeid);
				addr = "/personincharge/homeworkinfo.jsp";
			}
		}
		}
		// 检验是否为管理员,若是则跳转到管理员界面

		response.sendRedirect(request.getContextPath() + addr);
	}

}
