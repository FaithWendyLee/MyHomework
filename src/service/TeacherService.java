package service;

import java.util.ArrayList;
import java.util.Calendar;

import factory.DaoFactory;
import model.Teacher;

public class TeacherService implements TeacherServiceInterface{
	private static TeacherService teaService=new TeacherService();

	public static TeacherService getInstance() {
		// TODO Auto-generated method stub
		return teaService;
	}

	public ArrayList<Teacher> getTeacher() {
		// TODO Auto-generated method stub
		return DaoFactory.getTeacherDao().getTeacherList();
	}

	public void addTeacher(Teacher tea) {
		// TODO Auto-generated method stub
		DaoFactory.getTeacherDao().addTeacher(tea);
		
	}

	@Override
	public Teacher getTeacher(int userid) {
		// TODO Auto-generated method stub
		return DaoFactory.getTeacherDao().getTeacher(userid);
	}

	public void alterTeacher(Teacher tea) {
		// TODO Auto-generated method stub
		DaoFactory.getTeacherDao().alterTeacher(tea);
		
	}

	@Override
	public Teacher findTeacher(String name, String pw) {
		// TODO Auto-generated method stub
		return DaoFactory.getTeacherDao().findTeacher(name,pw);
	}

	@Override
	public boolean isOverdue(String date) {
		// TODO Auto-generated method stub
		Calendar c=Helper.str_d(date);
		return c.before(Calendar.getInstance());
	}

}
