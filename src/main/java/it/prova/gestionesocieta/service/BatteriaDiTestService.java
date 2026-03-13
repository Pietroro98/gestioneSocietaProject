package it.prova.gestionesocieta.service;
import it.prova.gestionesocieta.service.dipendente.DipendenteService;
import it.prova.gestionesocieta.service.progetto.ProgettoService;
import it.prova.gestionesocieta.service.societa.SocietaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatteriaDiTestService {

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private ProgettoService progettoService;

    @Autowired
    private SocietaService societaService;


}
