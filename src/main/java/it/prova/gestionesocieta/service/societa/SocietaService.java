package it.prova.gestionesocieta.service.societa;
import it.prova.gestionesocieta.model.Societa;
import java.util.List;

public interface SocietaService
{
    public List<Societa> listAll();

    public Societa caricaSingolo(Long id);

    public void aggiorna(Societa societaInstance);

    public void inserisciNuovo(Societa societaInstance);

    public void rimuovi(Long idSocieta);

}
