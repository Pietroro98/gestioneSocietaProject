package it.prova.gestionesocieta.service.dipendente;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.reporitory.DipendenteRepository;
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


    @Override
    public List<Dipendente> listAll() {
        return (List<Dipendente>) dipendenteRepository.findAll();
    }

    @Override
    public Dipendente caricaSingolo(Long id) {
        return dipendenteRepository.findById(id).get();
    }

    @Override
    public void aggiorna(Dipendente dipendenteInstance) {
        dipendenteRepository.save(dipendenteInstance);
    }

}
