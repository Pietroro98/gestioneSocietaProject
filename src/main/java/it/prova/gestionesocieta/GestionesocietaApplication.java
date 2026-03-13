package it.prova.gestionesocieta;

import it.prova.gestionesocieta.service.BatteriaDiTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionesocietaApplication implements CommandLineRunner {

	@Autowired
	private BatteriaDiTestService batteriaDiTestService;

	public static void main(String[] args) {
		SpringApplication.run(GestionesocietaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("################ START   #################");
		System.out.println("################ eseguo i test  #################");

		// inserisco la batteria dei test che eseguo su BatteriaDiTestService.

		batteriaDiTestService.testInserisciNuovaSocieta();
		// batteriaDiTestService.testInserisciNuovaSocietaConControlloDuplicato();
		batteriaDiTestService.testFindByExampleSocieta();
		batteriaDiTestService.testInserisciDipendenteConSocieta();
		// batteriaDiTestService.testRimozioneSocietaConDipendenti();
		batteriaDiTestService.testInserisciProgetto();
		batteriaDiTestService.testCollegaDipendenteAProgetti();
		batteriaDiTestService.testCollegaProgettoADipendenti();

		System.out.println("################ FINE - PASSED  #################");
	}
}
