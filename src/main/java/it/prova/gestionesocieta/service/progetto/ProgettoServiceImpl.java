package it.prova.gestionesocieta.service.progetto;

import it.prova.gestionesocieta.model.Progetto;
import it.prova.gestionesocieta.reporitory.ProgettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProgettoServiceImpl implements ProgettoService
{
    @Autowired
    private ProgettoRepository progettoRepository;

    public List<Progetto> findAll(){
        return (List<Progetto>) progettoRepository.findAll();
    }

    @Override
    public List<Progetto> listAll() {
        return (List<Progetto>) progettoRepository.findAll();
    }

    @Override
    public Progetto caricaSingolo(Long id) {
        return progettoRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void aggiorna(Progetto progettoInstance) {
        progettoRepository.save(progettoInstance);
    }

    @Override
    @Transactional
    public void inserisciNuovo(Progetto progettoInstance) {
        progettoRepository.save(progettoInstance);
    }


}
