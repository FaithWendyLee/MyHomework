package service;

import java.text.ParseException;
import java.util.ArrayList;

import sun.util.calendar.BaseCalendar.Date;
import factory.DaoFactory;
import model.Plan;

public class PlanService implements PlanServiceInterface{
	private static PlanService ps=new PlanService();

	@Override
	public ArrayList<Plan> getPlan() {
		// TODO Auto-generated method stub
		try {
			return DaoFactory.getPlanDao().getPlanList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static PlanServiceInterface getInstance() {
		// TODO Auto-generated method stub
		return ps;
	}
	@Override
	public void addPlan(Plan p) {
		// TODO Auto-generated method stub
		DaoFactory.getPlanDao().addPlan(p);
	}

	@Override
	public Plan findPlan(int pid) {
		// TODO Auto-generated method stub
		return DaoFactory.getPlanDao().findPlan(pid);
	}
}
