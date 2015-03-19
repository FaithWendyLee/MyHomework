package service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.DaoFactory;
import model.Student;

public class StudentService implements StudentServiceInterface
{
	
	private static StudentService stuService=new StudentService();
	
	private StudentService()
	{
		
	}
	
	public static StudentService getInstance()
	{
		return stuService;
	}
	
//	public Student validateStudent(String id,String password)
//	{
//		Student stu=DaoFactory.getStudentDao().find(id,password);
//		return stu;
//	}
	public void addStudent(Student stu){
		DaoFactory.getStudentDao().add(stu);		
	}


	public ArrayList<Student> getStudent(){
		return DaoFactory.getStudentDao().getStudentList();
		
	}

	public void forwardPage(String page,HttpServletRequest req,HttpServletResponse resp) 
					throws ServletException,IOException
	{
		RequestDispatcher dispater=req.getRequestDispatcher(resp.encodeURL(page));
		dispater.forward(req,resp);
	}
	

	public Student getStudent(int id){
		return DaoFactory.getStudentDao().findById(id);
	}

	public void alterStudent(Student stu){
		DaoFactory.getStudentDao().updateStudent(stu);
		
	}

	@Override
	public Student findStudent(String name, String pw) {
		// TODO Auto-generated method stub
		return DaoFactory.getStudentDao().findStudent(name,pw);
	}


}
