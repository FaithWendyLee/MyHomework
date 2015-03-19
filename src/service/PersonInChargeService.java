package service;

import java.util.ArrayList;

import factory.DaoFactory;
import model.PersonInCharge;

public class PersonInChargeService implements PersonInChargeServiceInterface{
	static PersonInChargeService pics=new PersonInChargeService();
	
	
	public static PersonInChargeServiceInterface getInstance() {
		// TODO Auto-generated method stub
		return pics;
	}

	@Override
	public PersonInCharge findPersonInCharge(String name, String pw) {
		// TODO Auto-generated method stub
		return DaoFactory.getPersonInChargeDao().findPersonInCharge(name, pw);
	}

	@Override
	public void addPersonInCharge(PersonInCharge pic) {
		DaoFactory.getPersonInChargeDao().addPersonInCharge(pic);
		
	}

	@Override
	public PersonInCharge getPersonInCharge(int userid) {
		// TODO Auto-generated method stub
		return DaoFactory.getPersonInChargeDao().getPersonInCharge(userid);
	}

	@Override
	public void alterPersonInCharge(PersonInCharge pic) {
		DaoFactory.getPersonInChargeDao().alterPersonInCharge(pic);
		
	}

	@Override
	public ArrayList<PersonInCharge> getPersonInCharge() {
		// TODO Auto-generated method stub
		return DaoFactory.getPersonInChargeDao().getPersonInCharge();
	}

}
