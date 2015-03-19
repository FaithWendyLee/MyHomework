package service;

import java.util.ArrayList;

import factory.DaoFactory;
import model.Score;

public class ScoreService implements ScoreServiceInterface{

	private static ScoreService ss=new ScoreService();
	
	@Override
	public void uploadScore(Score s) {
		// TODO Auto-generated method stub
		DaoFactory.getScoreDao().uploadScore(s);
		
	}

	public static ScoreServiceInterface getInstance() {
		// TODO Auto-generated method stub
		return ss;
	}
	
	@Override
	public ArrayList<Score> getScore() {
		// TODO Auto-generated method stub
		return DaoFactory.getScoreDao().getScore();
	}

	public void updateScore(Score s) {
		DaoFactory.getScoreDao().updateScore(s);
		
	}

	@Override
	public Score findScore(int pid) {
		// TODO Auto-generated method stub
		return DaoFactory.getScoreDao().findScore(pid);
	}


	@Override
	public void remove(int planid) {
		DaoFactory.getScoreDao().remove(planid);
		
	}


}
