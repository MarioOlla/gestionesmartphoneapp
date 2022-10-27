package it.prova.gestionesmartphoneapp.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.dao.MyDaoFactory;
import it.prova.gestionesmartphoneapp.dao.smartphone.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public class SmartphoneServiceImpl implements SmartphoneService {

	SmartphoneDAO smartphoneDAOInstance;

	@Override
	public List<Smartphone> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			smartphoneDAOInstance.setEntityManager(entityManager);
			return smartphoneDAOInstance.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Smartphone caricaSingolo(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			smartphoneDAOInstance.setEntityManager(entityManager);
			return smartphoneDAOInstance.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Smartphone daAggiornare) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			smartphoneDAOInstance.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			daAggiornare.setCreateDateTime(smartphoneDAOInstance.get(daAggiornare.getId()).getCreateDateTime());
			smartphoneDAOInstance.update(daAggiornare);
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
	public void inserisciNuovo(Smartphone daAggiungere) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			smartphoneDAOInstance.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			smartphoneDAOInstance.insert(daAggiungere);
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
	public void rimuovi(Smartphone daEliminare) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			smartphoneDAOInstance.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			smartphoneDAOInstance.delete(daEliminare);
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
	public void aggiornaOS(Smartphone s, String nuovaVersione) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			smartphoneDAOInstance.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			s.setVersioneOS(nuovaVersione);
			smartphoneDAOInstance.update(s);
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
	public void rimuoviSmartphoneConAppInstallate(Smartphone daEliminare) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			smartphoneDAOInstance.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			smartphoneDAOInstance.uninstallAllApps(daEliminare);
			smartphoneDAOInstance.delete(daEliminare);
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
	public void setSmartphoneDAO(SmartphoneDAO smartphoneDAOInstance) {
		this.smartphoneDAOInstance = MyDaoFactory.getSmartphoneDAOInstance();

	}

	@Override
	public Smartphone caricaEager(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			smartphoneDAOInstance.setEntityManager(entityManager);
			return smartphoneDAOInstance.getSmartphoneEager(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

}
