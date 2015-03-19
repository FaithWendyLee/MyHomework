package service;

import java.util.ArrayList;

import model.Plan;
import model.SampleComment;
import factory.DaoFactory;

public class SampleCommentService implements SampleCommentServiceInterface{
	private static SampleCommentService fs=new SampleCommentService();

	public ArrayList<SampleComment> getSampleComment() {
		// TODO Auto-generated method stub
		try {
			return DaoFactory.getSampleCommentDao().getSampleComment();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static SampleCommentServiceInterface getInstance() {
		// TODO Auto-generated method stub
		return fs;
	}
	public void addSampleComment(SampleComment f) {
		// TODO Auto-generated method stub
		DaoFactory.getSampleCommentDao().addSampleComment(f);
	}

}
