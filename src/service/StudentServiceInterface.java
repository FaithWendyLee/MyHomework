package service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;

public interface StudentServiceInterface {
//	public Student validateStudent(String id,String password);
	
	public void forwardPage(String page,HttpServletRequest req,HttpServletResponse resp) 
					throws ServletException,IOException;
	public ArrayList<Student> getStudent();
	public void addStudent(Student stu);
	public Student getStudent(int id);
	public void alterStudent(Student stu);
	public Student findStudent(String name, String pw);
	
}
