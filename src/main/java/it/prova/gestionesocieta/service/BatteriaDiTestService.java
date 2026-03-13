package it.prova.gestionesocieta.service;
import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.service.dipendente.DipendenteService;
import it.prova.gestionesocieta.service.progetto.ProgettoService;
import it.prova.gestionesocieta.service.societa.SocietaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BatteriaDiTestService {

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private ProgettoService progettoService;

    @Autowired
    private SocietaService societaService;


    public void testInserisciNuovaSocieta() {
        Societa societaInstance = new Societa();
        societaInstance.setRagioneSociale("ACME SPA");
        societaInstance.setIndirizzo("Via Roma 10");
        societaInstance.setDataFondazione(LocalDate.of(1985, 3, 12));

        societaService.inserisciNuovoConControlloDuplicato(societaInstance);
    }

    public void testInserisciNuovaSocietaConControlloDuplicato() {
        Societa societaInstance = new Societa();
        societaInstance.setRagioneSociale("Solving Team SPA");
        societaInstance.setIndirizzo("Via Mosca 52");
        societaInstance.setDataFondazione(LocalDate.of(1985, 3, 12));

        societaService.inserisciNuovoConControlloDuplicato(societaInstance);
    }

    public void testFindByExampleSocieta() {
        Societa example = new Societa();
        example.setRagioneSociale("ACME");

        List<Societa> risultati = societaService.findByExample(example);
        System.out.println("Societa trovate: " + risultati.size());
    }

    public void testRimozioneSocietaConControlloSeDipendentiPresenti()
    {
        
    }
}
