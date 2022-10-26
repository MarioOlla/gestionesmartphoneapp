package it.prova.gestionesmartphoneapp.test;

import java.util.Date;
import java.util.List;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;
import it.prova.gestionesmartphoneapp.service.AppService;
import it.prova.gestionesmartphoneapp.service.MyServiceFactory;
import it.prova.gestionesmartphoneapp.service.SmartphoneService;

public class TestGestione {

	public static void main(String[] args) {
		AppService appServiceInstance = MyServiceFactory.getAppServiceInstance();
		SmartphoneService smartphoneServiceInstance = MyServiceFactory.getSmartphoneServiceInstance();

		try {
			stampaContenutoDB(appServiceInstance, smartphoneServiceInstance);

			testInserisciNuovoSmartphone(smartphoneServiceInstance);
			stampaContenutoDB(appServiceInstance, smartphoneServiceInstance);

			testAggiornaSmartphone(smartphoneServiceInstance);
			stampaContenutoDB(appServiceInstance, smartphoneServiceInstance);

			testCaricaSingoloSmartphone(smartphoneServiceInstance);
			stampaContenutoDB(appServiceInstance, smartphoneServiceInstance);

			testInserisciNuovaApp(appServiceInstance);
			stampaContenutoDB(appServiceInstance, smartphoneServiceInstance);

			testAggiornaApp(appServiceInstance);
			stampaContenutoDB(appServiceInstance, smartphoneServiceInstance);

			testCaricaSingolaApp(appServiceInstance);
			stampaContenutoDB(appServiceInstance, smartphoneServiceInstance);

			testInstallaApp(appServiceInstance, smartphoneServiceInstance);
			stampaContenutoDB(appServiceInstance, smartphoneServiceInstance);

			testCaricaSingoloSmartphoneEager(smartphoneServiceInstance);
			stampaContenutoDB(appServiceInstance, smartphoneServiceInstance);

			testCaricaSingolaAppEager(appServiceInstance);
			stampaContenutoDB(appServiceInstance, smartphoneServiceInstance);

			testDisinstallaApp(appServiceInstance, smartphoneServiceInstance);
			stampaContenutoDB(appServiceInstance, smartphoneServiceInstance);

			testRimuoviSmartphone(smartphoneServiceInstance);
			stampaContenutoDB(appServiceInstance, smartphoneServiceInstance);

			testRimuoviApp(appServiceInstance);
			stampaContenutoDB(appServiceInstance, smartphoneServiceInstance);

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}
	}

	private static void testInserisciNuovoSmartphone(SmartphoneService smartphoneService) throws Exception {
		System.out.println("\n.....inizio testInserisciNuovoSmartphone ");
		Smartphone nuovoSmartphone = new Smartphone("Huawei", "Mate 10", 200, "4.6.3");
		smartphoneService.inserisciNuovo(nuovoSmartphone);
		if (smartphoneService.listAll().isEmpty())
			throw new RuntimeException("testInserisciNuovoSmartphone : FAILED, l' inserimento non e' avvenuto.");
		System.out.println(".....fine testInserisciNuovoSmartphone : PASSED");
	}

	private static void testAggiornaSmartphone(SmartphoneService smartphoneServiceInstance) throws Exception {
		System.out.println("\n.....inizio testAggiornaSmartphone ");
		List<Smartphone> tuttiInDB = smartphoneServiceInstance.listAll();
		if (tuttiInDB.isEmpty())
			throw new RuntimeException("testAggiornaSmartphone : FAILED, il DB non contiene smartphones.");
		Smartphone conValoriAggiornati = new Smartphone("Huawei", "Mate 10 lite", 174, "4.8.0");
		conValoriAggiornati.setId(tuttiInDB.get(0).getId());
		smartphoneServiceInstance.aggiorna(conValoriAggiornati);
		if (smartphoneServiceInstance.caricaSingolo(tuttiInDB.get(0).getId()).getPrezzo() != 174)
			throw new RuntimeException("testAggiornaSmartphone : FAILED, la voce non e' stata aggiornata.");
		System.out.println(".....fine testAggiornaSmartphone : PASSED");
	}

	private static void testCaricaSingoloSmartphone(SmartphoneService smartphoneServiceInstance) throws Exception {
		System.out.println("\n.....inizio testCaricaSingoloSmartphone ");
		List<Smartphone> tuttiInDB = smartphoneServiceInstance.listAll();
		if (tuttiInDB.isEmpty())
			throw new RuntimeException("testCaricaSingoloSmartphone : FAILED, il DB non contiene smartphones.");
		Smartphone result = smartphoneServiceInstance.caricaSingolo(tuttiInDB.get(0).getId());
		if (!result.getMarca().equals(tuttiInDB.get(0).getMarca()))
			throw new RuntimeException(
					"testCaricaSingoloSmartphone : FAILED, la ricerca non ha dato i risultati attesi.");
		System.out.println(".....fine testCaricaSingoloSmartphone : PASSED");
	}

	private static void testCaricaSingoloSmartphoneEager(SmartphoneService smartphoneServiceInstance) throws Exception {
		System.out.println("\n.....inizio testCaricaSingoloSmartphone ");
		List<Smartphone> tuttiInDB = smartphoneServiceInstance.listAll();
		if (tuttiInDB.isEmpty())
			throw new RuntimeException("testCaricaSingoloSmartphone : FAILED, il DB non contiene smartphones.");
		Smartphone result = smartphoneServiceInstance.caricaEager(tuttiInDB.get(0).getId());
		if (result.getApps().isEmpty())
			throw new RuntimeException("testCaricaSingoloSmartphone : FAILED, le app non sono state caricate.");
		System.out.println(".....fine testCaricaSingoloSmartphone : PASSED");
	}

	private static void testRimuoviSmartphone(SmartphoneService smartphoneServiceInstance) throws Exception {
		System.out.println("\n.....inizio testRimuoviSmartphone ");
		List<Smartphone> tuttiInDB = smartphoneServiceInstance.listAll();
		if (tuttiInDB.isEmpty())
			throw new RuntimeException("testRimuoviSmartphone : FAILED, il DB non contiene smartphones.");
		smartphoneServiceInstance.rimuovi(tuttiInDB.get(0));
		if (smartphoneServiceInstance.caricaSingolo(tuttiInDB.get(0).getId()) != null)
			throw new RuntimeException("testRimuoviSmartphone : FAILED, lo smartphone non e' stato cancellato.");
		System.out.println(".....fine testRimuoviSmartphone : PASSED");
	}

	private static void testInserisciNuovaApp(AppService appServiceInstance) throws Exception {
		System.out.println("\n.....inizio testInserisciNuovaApp ");
		App nuovaApp = new App("My App", null, new Date(), "2.0.11");
		appServiceInstance.inserisciNuova(nuovaApp);
		if (appServiceInstance.listAll().isEmpty())
			throw new RuntimeException("testInserisciNuovaApp : FAILED, l' inserimento non e' avvenuto.");
		System.out.println(".....fine testInserisciNuovaApp : PASSED");
	}

	private static void testAggiornaApp(AppService appServiceInstance) throws Exception {
		System.out.println("\n.....inizio testAggiornaApp ");
		List<App> tuttiInDB = appServiceInstance.listAll();
		if (tuttiInDB.isEmpty())
			throw new RuntimeException("testAggiornaApp : FAILED, il DB non contiene app.");
		App conValoriAggiornati = new App("My Epic App", null, new Date(), "2.3.7");
		conValoriAggiornati.setId(tuttiInDB.get(0).getId());
		appServiceInstance.aggiorna(conValoriAggiornati);
		if (appServiceInstance.caricaSingola(tuttiInDB.get(0).getId()).getNome().equals(tuttiInDB.get(0).getNome()))
			throw new RuntimeException("testAggiornaApp : FAILED, la voce non e' stata aggiornata.");
		System.out.println(".....fine testAggiornaApp : PASSED");
	}

	private static void testCaricaSingolaApp(AppService appServiceInstance) throws Exception {
		System.out.println("\n.....inizio testCaricaSingolaApp ");
		List<App> tuttiInDB = appServiceInstance.listAll();
		if (tuttiInDB.isEmpty())
			throw new RuntimeException("testCaricaSingolaApp : FAILED, il DB non contiene app.");
		App result = appServiceInstance.caricaSingola(tuttiInDB.get(0).getId());
		if (!result.getNome().equals(tuttiInDB.get(0).getNome()))
			throw new RuntimeException("testCaricaSingolaApp : FAILED, la ricerca non ha dato i risultati attesi.");
		System.out.println(".....fine testCaricaSingolaApp : PASSED");
	}

	private static void testCaricaSingolaAppEager(AppService appServiceInstance) throws Exception {
		System.out.println("\n.....inizio testCaricaSingolaApp ");
		List<App> tuttiInDB = appServiceInstance.listAll();
		if (tuttiInDB.isEmpty())
			throw new RuntimeException("testCaricaSingolaApp : FAILED, il DB non contiene app.");
		App result = appServiceInstance.caricaEager(tuttiInDB.get(0).getId());
		if (!result.getNome().equals(tuttiInDB.get(0).getNome()))
			throw new RuntimeException("testCaricaSingolaApp : FAILED, la ricerca non ha dato i risultati attesi.");
		System.out.println(".....fine testCaricaSingolaApp : PASSED");
	}

	private static void testRimuoviApp(AppService appServiceInstance) throws Exception {
		System.out.println("\n.....inizio testRimuoviApp ");
		List<App> tuttiInDB = appServiceInstance.listAll();
		if (tuttiInDB.isEmpty())
			throw new RuntimeException("testRimuoviApp : FAILED, il DB non contiene app.");
		appServiceInstance.rimuovi(tuttiInDB.get(0));
		if (appServiceInstance.caricaSingola(tuttiInDB.get(0).getId()) != null)
			throw new RuntimeException("testRimuoviApp : FAILED, l' app non e' stata cancellata.");
		System.out.println(".....fine testRimuoviApp : PASSED");
	}

	private static void testInstallaApp(AppService appServiceInstance, SmartphoneService smartphoneServiceInstance)
			throws Exception {
		System.out.println("\n.....inizio testInstallaApp ");
		List<App> tutteLeApp = appServiceInstance.listAll();
		List<Smartphone> tuttiGliSmartphone = smartphoneServiceInstance.listAll();
		if (tutteLeApp.isEmpty() || tuttiGliSmartphone.isEmpty())
			throw new RuntimeException("testInstallaApp : FAILED, una o entrambe le tabelle del DB sono vuote.");
		appServiceInstance.installaApp(tutteLeApp.get(0), tuttiGliSmartphone.get(0));
		if (smartphoneServiceInstance.caricaEager(tuttiGliSmartphone.get(0).getId()).getApps().isEmpty())
			throw new RuntimeException("testInstallaApp : FAILED, l'app non risulta installata sullo smartphone.");
		System.out.println(".....fine testInstallaApp : PASSED");
	}

	private static void testDisinstallaApp(AppService appServiceInstance, SmartphoneService smartphoneServiceInstance)
			throws Exception {
		System.out.println("\n.....inizio testDisinstallaApp ");
		List<App> tutteLeApp = appServiceInstance.listAll();
		List<Smartphone> tuttiGliSmartphone = smartphoneServiceInstance.listAll();
		if (tutteLeApp.isEmpty() || tuttiGliSmartphone.isEmpty())
			throw new RuntimeException("testDisinstallaApp : FAILED, una o entrambe le tabelle del DB sono vuote.");
		Long idSmartphoneDaRipulire = tuttiGliSmartphone.get(0).getId();
		if (smartphoneServiceInstance.caricaEager(tuttiGliSmartphone.get(0).getId()).getApps().isEmpty())
			throw new RuntimeException("testDisinstallaApp : FAILED, l'app non risulta installata sullo smartphone.");
		appServiceInstance.disinstallaApp(appServiceInstance.caricaEager(tutteLeApp.get(0).getId()),
				smartphoneServiceInstance.caricaEager(tuttiGliSmartphone.get(0).getId()));
		Smartphone ripulito = smartphoneServiceInstance.caricaEager(idSmartphoneDaRipulire);
		if (ripulito == null)
			throw new RuntimeException(
					"testDisinstallaApp : FAILED, l'app risulta ancora installata sullo smartphone dopo la cancellazione.");
		System.out.println(".....fine testDisinstallaApp : PASSED");
	}

//	private static void test()throws Exception{
//		System.out.println("\n.....inizio test ");
//		if()
//			throw new RuntimeException("test : FAILED, ");
//		System.out.println(".....fine test : PASSED");
//	}

	private static void stampaContenutoDB(AppService appServiceInstance, SmartphoneService smartphoneServiceInstance)
			throws Exception {
		System.out.println("Nel database ci sono " + appServiceInstance.listAll().size() + " app e "
				+ smartphoneServiceInstance.listAll().size() + " smartphones.");
	}
//	Inserimento nuovo Smartphone
//	Aggiornamento versioneOS di uno Smartphone esistente
//	Inserimento nuova App
//	Aggiornamento versione APP con relativa data
//	Installazione di App esistente su Smartphone esistente
//	Disinstallazione di una App da uno Smartphone
//	Rimozione completa di uno Smartphone associato a due App
}
