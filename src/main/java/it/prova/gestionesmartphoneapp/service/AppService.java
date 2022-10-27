package it.prova.gestionesmartphoneapp.service;

import java.util.List;

import it.prova.gestionesmartphoneapp.dao.app.AppDAO;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public interface AppService {

	public List<App> listAll() throws Exception;

	public App caricaSingola(Long id) throws Exception;

	public void aggiorna(App daAggiornare) throws Exception;

	public void inserisciNuova(App daAggiungere) throws Exception;

	public void rimuovi(App daEliminare) throws Exception;

	public App caricaEager(Long id) throws Exception;

	public void installaApp(App daInstallare, Smartphone doveInstallare) throws Exception;

	public void disinstallaApp(App daDisinstallare, Smartphone doveDisinstallare) throws Exception;

	public void aggiornaVersioneEAggiornaDataUltimoUpdate(App daAggiornare, String versione) throws Exception;

	public void setAppDAO(AppDAO dao);

}
