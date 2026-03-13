package it.prova.gestionesocieta.service.societa;
import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.reporitory.SocietaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class SocietaServiceImpl implements SocietaService
{
    @Autowired
    private SocietaRepository societaRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Societa> listAll() {
        return (List<Societa>) societaRepository.findAll();
    }

    @Override
    public Societa caricaSingolo(Long id) {
        return societaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void aggiorna(Societa societaInstance) {
        societaRepository.save(societaInstance);
    }

    @Override
    @Transactional
    public void inserisciNuovo(Societa societaInstance) {
        societaRepository.save(societaInstance);
    }

    @Override
    @Transactional
    public void rimuovi(Long idSocieta) {
        societaRepository.deleteById(idSocieta);
    }
}
