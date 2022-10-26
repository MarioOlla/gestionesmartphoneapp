package it.prova.gestionesmartphoneapp.service;

import java.util.List;

import it.prova.gestionesmartphoneapp.dao.MyDaoFactory;
import it.prova.gestionesmartphoneapp.dao.smartphone.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public class SmartphoneServiceImpl implements SmartphoneService {

	SmartphoneDAO smartphoneDAOInstance;
	
	@Override
	public List<Smartphone> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Smartphone caricaSingolo(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Smartphone daAggiornare) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void inserisciNuovo(Smartphone daAggiungere) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void rimuovi(Smartphone daEliminare) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void aggiornaOS(String uovaVersione) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void rimuoviSmartphoneConAppInstallate(Smartphone daEliminare) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSmartphoneDAO(SmartphoneDAO smartphoneDAOInstance) {
		this.smartphoneDAOInstance = MyDaoFactory.getSmartphoneDAOInstance();
		
	}



}
