package it.prova.gestionesmartphoneapp.service;

import java.util.List;

import it.prova.gestionesmartphoneapp.dao.smartphone.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public interface SmartphoneService {
	
	public List<Smartphone> listAll() throws Exception;

	public Smartphone caricaSingolo(Long id) throws Exception;

	public void aggiorna(Smartphone daAggiornare) throws Exception;

	public void inserisciNuovo(Smartphone daAggiungere) throws Exception;

	public void rimuovi(Smartphone daEliminare) throws Exception;
	
	public void aggiornaOS(String uovaVersione)throws Exception;
	
	public void rimuoviSmartphoneConAppInstallate(Smartphone daEliminare)throws Exception;

	public void setSmartphoneDAO(SmartphoneDAO smartphoneDAOInstance);
	
}
