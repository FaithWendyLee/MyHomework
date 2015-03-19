package dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate4.HibernateTemplate;

import model.Assistant;
import model.Course;
import model.Homework;
import dao.CourseDao;

public class CourseDaoImpl implements CourseDao{
	@Resource
	private SessionFactory sessionFactory;
	private static CourseDaoImpl courseDao = new CourseDaoImpl();

	public static CourseDao getInstance() {
		// TODO Auto-generated method stub
		return courseDao;
	}

	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	@Override
	public ArrayList<Course> getCourseList() {
		ArrayList<Course> clist = (ArrayList<Course>) new HibernateTemplate(sessionFactory).find("from Course");
		return clist;

	}

	public void addCourse(Course c) {
		new HibernateTemplate(sessionFactory).save(c);
	}

	public Course getCourse(int courseid) {
		Course c = new HibernateTemplate(sessionFactory).get(Course.class, courseid);
		return c;
	}

	@Override
	public void alterCourse(Course c) {
		new HibernateTemplate(sessionFactory).update(c);
	}

}
