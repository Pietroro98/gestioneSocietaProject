package it.prova.gestionesocieta.service.progetto;

import it.prova.gestionesocieta.model.Progetto;

import java.util.List;

public interface ProgettoService {
    public List<Progetto> listAll();

    public Progetto caricaSingolo(Long id);

    public void aggiorna(Progetto progettoInstance);

    public void inserisciNuovo(Progetto progettoInstance);

}
