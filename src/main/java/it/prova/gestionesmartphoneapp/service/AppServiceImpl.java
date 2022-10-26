package it.prova.gestionesmartphoneapp.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.dao.app.AppDAO;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public class AppServiceImpl implements AppService {
	
	AppDAO appDAOInstance; 
	
	@Override
	public List<App> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			appDAOInstance.setEntityManager(entityManager);
			return appDAOInstance.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public App caricaSingola(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			appDAOInstance.setEntityManager(entityManager);
			return appDAOInstance.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(App daAggiornare) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			appDAOInstance.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			appDAOInstance.update(daAggiornare);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void inserisciNuova(App daAggiungere) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			appDAOInstance.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			appDAOInstance.insert(daAggiungere);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(App daEliminare) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			appDAOInstance.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			appDAOInstance.delete(daEliminare);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

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
