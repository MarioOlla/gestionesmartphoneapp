package it.prova.gestionesmartphoneapp.test;

import javax.swing.text.html.parser.Entity;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.service.AppService;
import it.prova.gestionesmartphoneapp.service.MyServiceFactory;
import it.prova.gestionesmartphoneapp.service.SmartphoneService;

public class TestGestione {

	public static void main(String[] args) {
		AppService appServiceInstance = MyServiceFactory.getAppServiceInstance();
		SmartphoneService smartphoneServiceInstance = MyServiceFactory.getSmartphoneServiceInstance();
		
		try {
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		finally {
			EntityManagerUtil.shutdown();
		}
	}
//	Inserimento nuovo Smartphone
//	Aggiornamento versioneOS di uno Smartphone esistente
//	Inserimento nuova App
//	Aggiornamento versione APP con relativa data
//	Installazione di App esistente su Smartphone esistente
//	Disinstallazione di una App da uno Smartphone
//	Rimozione completa di uno Smartphone associato a due App
}
