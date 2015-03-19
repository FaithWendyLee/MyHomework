package service;

import java.text.ParseException;
import java.util.ArrayList;

import model.Course;

public interface CourseServiceInterface {
   public ArrayList<Course> getCourse();

	public void addCourse(Course c);

	public Course getCourse(int courseid);

	public void alterCourse(Course c);

}
