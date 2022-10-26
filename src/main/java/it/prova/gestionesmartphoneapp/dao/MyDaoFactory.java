package it.prova.gestionesmartphoneapp.dao;

import it.prova.gestionesmartphoneapp.dao.app.AppDAO;
import it.prova.gestionesmartphoneapp.dao.app.AppDAOImpl;
import it.prova.gestionesmartphoneapp.dao.smartphone.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.dao.smartphone.SmartphoneDAOImpl;

public class MyDaoFactory {
	
	private static AppDAO appDaoInstance = null;
	private static SmartphoneDAO smartphoneDAOInstance = null;
	
	public static AppDAO getaAppDAOInstance() {
		if(appDaoInstance == null)
			appDaoInstance = new AppDAOImpl();
		return appDaoInstance;
	}
	
	public static SmartphoneDAO getSmartphoneDAOInstance() {
		if(smartphoneDAOInstance == null)
			smartphoneDAOInstance = new SmartphoneDAOImpl();
		return smartphoneDAOInstance;
	}
}
