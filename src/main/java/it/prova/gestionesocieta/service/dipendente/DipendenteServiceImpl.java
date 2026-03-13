package it.prova.gestionesocieta.service.dipendente;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Progetto;
import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.reporitory.DipendenteRepository;
import it.prova.gestionesocieta.reporitory.ProgettoRepository;
import it.prova.gestionesocieta.reporitory.SocietaRepository;
import it.prova.gestionesocieta.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class DipendenteServiceImpl implements DipendenteService
{
    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private SocietaRepository societaRepository;
    @Autowired
    private ProgettoRepository progettoRepository;

    @Autowired
    private Utils utils;


    @Override
    @Transactional
    public List<Dipendente> listAll() {
        return (List<Dipendente>) dipendenteRepository.findAll();
    }

    @Override
    public Dipendente caricaSingolo(Long id) {
        return dipendenteRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void aggiorna(Dipendente dipendenteInstance) {
        dipendenteRepository.save(dipendenteInstance);
    }

    @Override
    @Transactional
    public void inserisciDipendente(Dipendente dipendenteInstance) {
        dipendenteRepository.save(dipendenteInstance);
    }


    /**
     * Inserimento dipendente (data una Società).
     * In fase di inserimento si verifica che la data assunzione non sia precedente alla data fondazione,
     * Eccezione se verificata.
     * @param idSocieta
     * @param dipendenteInstance
     */
    @Override
    @Transactional
    public void inserisciNuovoDipendenteConSocieta(Long idSocieta, Dipendente dipendenteInstance)
    {
        Societa societaInstance = societaRepository
                .findById(idSocieta)
                .orElseThrow(() -> new RuntimeException("Società non trovata"));

        if (dipendenteInstance.getDataAssunzione() != null
                && societaInstance.getDataFondazione() != null
                && dipendenteInstance.getDataAssunzione().isBefore(societaInstance.getDataFondazione()))
        {
            throw new RuntimeException("La data assunzione non puo precedere la fondazione");
        }

        societaInstance.addDipendente(dipendenteInstance);
        dipendenteRepository.save(dipendenteInstance);
    }

    @Override
    @Transactional
    public void collegaDipendenteAProgetti(Long idDipendente, List<Long> idsProgetti)
    {
        Dipendente dipendenteInstance = dipendenteRepository.findById(idDipendente)
                .orElseThrow(() -> new RuntimeException("Dipendente non trovato"));

        List<Progetto> progetti = (List<Progetto>) progettoRepository.findAllById(idsProgetti);
        Set<Progetto> progettoDaCollegare = progetti
                .stream()
                .peek(p -> utils.valida(dipendenteInstance, p))
                .collect(Collectors.toSet());

        dipendenteInstance.getProgetti().addAll(progettoDaCollegare);
    }
}
