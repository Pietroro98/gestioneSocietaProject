package it.prova.gestionesocieta.service.dipendente;

import it.prova.gestionesocieta.model.Dipendente;

import java.util.List;

public interface DipendenteService
{
   public List<Dipendente> listAll();

   public Dipendente caricaSingolo(Long id);

   public void aggiorna(Dipendente dipendenteInstance);

   public void inserisciDipendente(Dipendente dipendenteInstance);

   void inserisciNuovoDipendenteConSocieta(Long idSocieta, Dipendente dipendenteInstance);

}
