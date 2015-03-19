package servlets.admin;

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

import dao.impl.AssistantDaoImpl;
import dao.impl.PersonInChargeDaoImpl;
import dao.impl.StudentDaoImpl;
import dao.impl.TeacherDaoImpl;
import model.Assistant;
import model.PersonInCharge;
import model.Student;
import model.Teacher;

/**
 * Servlet implementation class alteruser2
 */
@WebServlet("/Alteruser2")
public class alteruser2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public alteruser2() {
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
		HttpSession session=request.getSession();	
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		char flag=(char) session.getAttribute("flag");
		if(flag=='0'){
			StudentDaoImpl studentDaoImpl = (StudentDaoImpl) ac.getBean("studentDaoImpl");
			Student stu=(Student)session.getAttribute("student");
			int stuid=(Integer)session.getAttribute("studentid");
			response.setContentType("text/html");

			String number=request.getParameter("number");
			String stuname=request.getParameter("name");
			String stupwd=request.getParameter("pwd");

			stu.setId(stuid);
			stu.setNumber(number);
			stu.setStuname(stuname);
			stu.setStupwd(stupwd);
			studentDaoImpl.updateStudent(stu);
//			session.setAttribute("student", stu);		
			
			JOptionPane.showMessageDialog(null,"修改成功！");
			
			response.sendRedirect(request.getContextPath() + "/admin/mnguser.jsp");
			
		}else if(flag=='1'){
			AssistantDaoImpl assistantDaoImpl = (AssistantDaoImpl) ac.getBean("assistantDaoImpl");
			
			Assistant ass=(Assistant)session.getAttribute("assistant");
			int assid=(Integer)session.getAttribute("assistantid");
			response.setContentType("text/html");
			String assname=request.getParameter("name");
			String asspwd=request.getParameter("pwd");
			ass.setAssistantid(assid);
			ass.setAssistantname(assname);
			ass.setAssistantpwd(asspwd);
			assistantDaoImpl.alterassistant(ass);;
//			session.setAttribute("assistant", ass);		
			JOptionPane.showMessageDialog(null,"修改成功！");
			response.sendRedirect(request.getContextPath() + "/admin/mnguser.jsp");
		}else if(flag=='2'){
			TeacherDaoImpl teacherDaoImpl = (TeacherDaoImpl) ac.getBean("teacherDaoImpl");
			Teacher tea=(Teacher) session.getAttribute("teacher");
			int teaid=(int) session.getAttribute("teacherid");
			response.setContentType("text/html");
			String teaname=request.getParameter("name");
			String teapwd=request.getParameter("pwd");
			tea.setTeaid(teaid);
			tea.setTeaname(teaname);
			tea.setTeapwd(teapwd);
			teacherDaoImpl.alterTeacher(tea);
//			session.setAttribute("teacher", tea);
			JOptionPane.showMessageDialog(null,"修改成功！");
			response.sendRedirect(request.getContextPath() + "/admin/mnguser.jsp");
		}else if(flag=='3'){
			PersonInCharge rp=(PersonInCharge) session.getAttribute("pic");
			int rpid=(int)session.getAttribute("picid");
			response.setContentType("text/html");
			String rpname=request.getParameter("name");
			String rppwd=request.getParameter("pwd");
			rp.setPicid(rpid);
			rp.setPicname(rpname);
			rp.setPicpwd(rppwd);
			PersonInChargeDaoImpl picDaoImpl = (PersonInChargeDaoImpl) ac.getBean("personInChargeDaoImpl");
			picDaoImpl.alterPersonInCharge(rp);
			JOptionPane.showMessageDialog(null,"修改成功！");
			response.sendRedirect(request.getContextPath() + "/admin/mnguser.jsp");
			
		}
	}

}
