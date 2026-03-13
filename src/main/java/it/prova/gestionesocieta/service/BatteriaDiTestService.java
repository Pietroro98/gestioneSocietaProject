package it.prova.gestionesocieta.service;
import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Progetto;
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

        List<Societa> societaList = List.of(
                new Societa("ACME SPA", "Via Roma 10", LocalDate.of(1985,3,12)),
                new Societa("Tech Solutions SRL", "Via Milano 45", LocalDate.of(1995,7,20)),
                new Societa("Global Software SPA", "Via Napoli 8", LocalDate.of(2005,11,3))
        );

        for (Societa s : societaList) {
            societaService.inserisciNuovoConControlloDuplicato(s);
        }
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

    public void testRimozioneSocietaConDipendenti() {
        Societa societa = societaService.listAll().get(1);
        Societa societaInstance = societaService.findByExample(societa).stream().findFirst().orElse(null);

        assert societaInstance != null;
        societaService.rimozioneSocietaConControllo(societaInstance.getId_societa());

    }

    public void testInserisciDipendenteConSocieta()
    {
        Societa societa = societaService.listAll().get(0);

        Societa societaInstance = societaService.findByExample(societa).stream().findFirst().orElse(null);

        Dipendente dipendenteInstance = new Dipendente();
        dipendenteInstance.setNome("Mario");
        dipendenteInstance.setCognome("Rossi");
        dipendenteInstance.setDataAssunzione(LocalDate.of(1990, 1, 10));
        dipendenteInstance.setRedditoAnnuoLordo(32000);

        assert societaInstance != null;
        dipendenteService.inserisciNuovoDipendenteConSocieta(societaInstance.getId_societa(), dipendenteInstance);
    }

    public void testInserisciProgetto() {
        Progetto progettoInstance = new Progetto();
        progettoInstance.setNome("Migrazione su Spring");
        progettoInstance.setCliente("Cliente test uno");
        progettoInstance.setDurataInMesi(3);

        progettoService.inserisciNuovo(progettoInstance);
    }

    public void testCollegaDipendenteAProgetti() {
        Dipendente dipendenteInstance = dipendenteService.listAll().stream().findFirst().orElseThrow();
        List<Long> idsProgetti = progettoService.listAll().stream().map(Progetto::getId_progetto).toList();

        dipendenteService.collegaDipendenteAProgetti(dipendenteInstance.getId_dipendente(), idsProgetti);
    }

    public void testCollegaProgettoADipendenti() {
        Progetto progettoInstance = progettoService.listAll().stream().findFirst().orElseThrow();
        List<Long> idsDipendenti = dipendenteService.listAll().stream().map(Dipendente::getId_dipendente).toList();

        progettoService.collegaProgettoADipendenti(progettoInstance.getId_progetto(), idsDipendenti);
    }

    public void testListaClientiDeiProgettiPerSocieta() {
        Societa societaInstance = societaService.listAll().stream().findFirst().orElseThrow();
        List<String> clienti = progettoService.listClientiBySocieta(societaInstance.getId_societa());

        System.out.println("Clienti dei progetti per societa :" + societaInstance.getRagioneSociale() + ": " + clienti);
    }


}
