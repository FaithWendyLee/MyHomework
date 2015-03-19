package service;

import java.text.ParseException;
import java.util.ArrayList;

import factory.DaoFactory;
import model.Course;

public class CourseService implements CourseServiceInterface{
	private static CourseService cs=new CourseService();

	public ArrayList<Course> getCourse() {
		// TODO Auto-generated method stub
		return DaoFactory.getCourseDao().getCourseList();
	}

	public static CourseServiceInterface getInstance() {
		// TODO Auto-generated method stub
		return cs;
	}

	public void addCourse(Course c) {
		// TODO Auto-generated method stub
		DaoFactory.getCourseDao().addCourse(c);
	}

	@Override
	public Course getCourse(int courseid) {
		// TODO Auto-generated method stub
		return DaoFactory.getCourseDao().getCourse(courseid);
	}

	@Override
	public void alterCourse(Course c) {
		// TODO Auto-generated method stub
		DaoFactory.getCourseDao().alterCourse(c);
	}



}
