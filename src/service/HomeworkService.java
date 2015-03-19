package service;

import java.util.ArrayList;

import factory.DaoFactory;
import model.Homework;
import model.Plan;

public class HomeworkService implements HomeworkServiceInterface{
	private static HomeworkService hs=new HomeworkService();

	public void uploadHw(Homework hw) {
		// TODO Auto-generated method stub
		DaoFactory.getHomeworkDao().uploadHw(hw);
	}

	public static HomeworkServiceInterface getInstance() {
		// TODO Auto-generated method stub
		return hs;
	}

	public ArrayList<Homework> getHomework() {
		// TODO Auto-generated method stub
		return DaoFactory.getHomeworkDao().getHomework();
	}

	@Override
	public void remove(int planid) {
		// TODO Auto-generated method stub
		DaoFactory.getHomeworkDao().remove(planid);
	}

	@Override
	public Homework getHomeworkBypid(int planid) {
		// TODO Auto-generated method stub
		return DaoFactory.getHomeworkDao().getHomeworkBypid(planid);
	}
}
