package dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate4.HibernateTemplate;

import model.Assistant;
import model.Score;
import dao.ScoreDao;

public class ScoreDaoImpl implements ScoreDao {
	@Resource
	private SessionFactory sessionFactory;
	private static ScoreDaoImpl sDao = new ScoreDaoImpl();

	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	public void uploadScore(Score s) {
		new HibernateTemplate(sessionFactory).save(s);
	}

	public static ScoreDaoImpl getInstance() {
		// TODO Auto-generated method stub
		return sDao;
	}

	public ArrayList<Score> getScore() {
		ArrayList<Score> slist = (ArrayList<Score>) new HibernateTemplate(sessionFactory).find("from Score");
		return slist;
	}

	public void updateScore(Score s) {
		new HibernateTemplate(sessionFactory).update(s);

	}

	@Override
	public Score findScore(int pid) {
		Score s = new HibernateTemplate(sessionFactory).get(Score.class, pid);
		return s;
	}

	@Override
	public void remove(int planid) {
		Score s = new HibernateTemplate(sessionFactory).get(Score.class, planid);
		new HibernateTemplate(sessionFactory).delete(s);
	}


}
