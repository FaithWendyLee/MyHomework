package service;

import java.util.ArrayList;

import factory.DaoFactory;
import model.Assistant;
import model.Student;

public class AssistantService implements AssistantServiceInterface{
	private static AssistantService assistanceService=new AssistantService();


	public void addAssistant(Assistant assistant){
		DaoFactory.getAssistantDao().add(assistant);	
	}

	public static AssistantServiceInterface getInstance() {
		// TODO Auto-generated method stub
		return assistanceService;
	}
	public ArrayList<Assistant> getAssistant(){
		return DaoFactory.getAssistantDao().getAssistantList();
		
	}

	public Assistant getAssistant(int assistantid) {
		// TODO Auto-generated method stub
		return DaoFactory.getAssistantDao().findbyID(assistantid);
	}

	public void alterAssistant(Assistant ass) {
		// TODO Auto-generated method stub
		DaoFactory.getAssistantDao().alterassistant(ass);
	}

	@Override
	public Assistant findAssistant(String name, String pwd) {
		// TODO Auto-generated method stub
		return DaoFactory.getAssistantDao().findAssistant(name, pwd);
	}
}
