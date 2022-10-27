package it.prova.gestionesmartphoneapp.service;

import java.util.Date;
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
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {

			entityManager.getTransaction().begin();
			appDAOInstance.setEntityManager(entityManager);
			daInstallare.setDataInstallazione(new Date());
			daInstallare = entityManager.merge(daInstallare);
			doveInstallare = entityManager.merge(doveInstallare);

			daInstallare.addAppToSmartphone(doveInstallare);

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
	public void disinstallaApp(App daDisinstallare, Smartphone doveDisinstallare) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			appDAOInstance.setEntityManager(entityManager);

			daDisinstallare = entityManager.merge(daDisinstallare);
			doveDisinstallare = entityManager.merge(doveDisinstallare);

			daDisinstallare.removeAppFromSmartphone(doveDisinstallare);

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
	public void aggiornaVersioneEAggiornaDataUltimoUpdate(App daAggiornare, String versione) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			appDAOInstance.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			daAggiornare.setVersione(versione);
			daAggiornare.setDataUltimoAggiornamento(new Date());
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
	public void setAppDAO(AppDAO dao) {
		this.appDAOInstance = dao;

	}

	@Override
	public App caricaEager(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			appDAOInstance.setEntityManager(entityManager);
			return appDAOInstance.getAppEager(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

}
