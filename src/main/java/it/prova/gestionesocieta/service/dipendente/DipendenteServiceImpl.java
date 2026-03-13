package it.prova.gestionesocieta.service.dipendente;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.reporitory.DipendenteRepository;
import it.prova.gestionesocieta.reporitory.SocietaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class DipendenteServiceImpl implements DipendenteService
{
    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private SocietaRepository societaRepository;


    @Override
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
}
