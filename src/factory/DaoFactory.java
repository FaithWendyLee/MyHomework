package factory;

import service.PersonInChargeService;
import dao.AssistantDao;
import dao.CourseDao;
import dao.PersonInChargeDao;
import dao.SampleCommentDao;
import dao.HomeworkDao;
import dao.PlanDao;
import dao.ScoreDao;
import dao.StudentDao;
import dao.TeacherDao;
import dao.impl.AssistantDaoImpl;
import dao.impl.CourseDaoImpl;
import dao.impl.PersonInChargeDaoImpl;
import dao.impl.SampleCommentDaoImpl;
import dao.impl.HomeworkDaoImpl;
import dao.impl.PlanDaoImpl;
import dao.impl.ScoreDaoImpl;
import dao.impl.StudentDaoImpl;
import dao.impl.TeacherDaoImpl;


public class DaoFactory {
	public static StudentDao getStudentDao()
	{
		return StudentDaoImpl.getInstance();
	}

	public static AssistantDao getAssistantDao() {
		// TODO Auto-generated method stub
		return AssistantDaoImpl.getInstance();
	}

	public static TeacherDao getTeacherDao() {
		// TODO Auto-generated method stub
		return TeacherDaoImpl.getInstance();
	}

	public static CourseDao getCourseDao() {
		// TODO Auto-generated method stub
		return CourseDaoImpl.getInstance();
	}

	public static PlanDao getPlanDao() {
		// TODO Auto-generated method stub
		return PlanDaoImpl.getInstance();
	}

	public static SampleCommentDao getSampleCommentDao() {
		// TODO Auto-generated method stub
		return SampleCommentDaoImpl.getInstance();
	}

	public static HomeworkDao getHomeworkDao() {
		// TODO Auto-generated method stub
		return HomeworkDaoImpl.getInstance();
	}
	
	public static ScoreDao getScoreDao(){
		return ScoreDaoImpl.getInstance();
	}

	public static PersonInChargeDao getPersonInChargeDao() {
		// TODO Auto-generated method stub
		return PersonInChargeDaoImpl.getInstance();
	}
	
}