package it.prova.gestionesmartphoneapp.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionesmartphoneapp.dao.app.AppDAO;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public class AppServiceImpl implements AppService {
	
	AppDAO appDAOInstance; 
	
	@Override
	public List<App> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public App caricaSingola(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(App daAggiornare) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void inserisciNuova(App daAggiungere) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void rimuovi(App daEliminare) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void installaApp(App daInstallare, Smartphone doveInstallare) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void disinstallaApp(App daDisinstallare, Smartphone doveInstallare) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void aggiornaVersioneEAggiornaDataUltimoUpdate(String versione) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAppDAO(AppDAO dao) {
		this.appDAOInstance = dao;
		
	}

}
