package it.prova.gestionesocieta.service.progetto;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Progetto;
import it.prova.gestionesocieta.reporitory.DipendenteRepository;
import it.prova.gestionesocieta.reporitory.ProgettoRepository;
import it.prova.gestionesocieta.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class ProgettoServiceImpl implements ProgettoService
{
    @Autowired
    private ProgettoRepository progettoRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private Utils utils;

    public List<Progetto> findAll(){
        return StreamSupport.stream(progettoRepository.findAll().spliterator(), false)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    @Transactional
    public List<Progetto> listAll() {
        return StreamSupport.stream(progettoRepository.findAll().spliterator(), false)
                .collect(Collectors.toCollection(ArrayList::new));
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

    @Override
    @Transactional
    public void collegaProgettoADipendenti(Long idProgetto, List<Long> idsDipendenti)
    {
        Progetto progettoInstance = progettoRepository.findById(idProgetto).orElseThrow(() -> new RuntimeException("Progetto non trovato"));

        List<Dipendente> dipendenti = (List<Dipendente>) dipendenteRepository.findAllById(idsDipendenti);
        Set<Dipendente> dipendentiDaCollegare = dipendenti
                .stream()
                .peek(d -> utils.valida(d, progettoInstance))
                .collect(Collectors.toSet());

        for (Dipendente dipendenteItem : dipendentiDaCollegare) {
            dipendenteItem.getProgetti().add(progettoInstance);
            progettoInstance.getDipendenti().add(dipendenteItem);
        }
    }

    @Override
    public List<String> listClientiBySocieta(Long idSocieta) {
        return progettoRepository.findClientiBySocietaId(idSocieta);
    }

    @Override
    public List<String> listRagioniSocialiSocietaConProgettiDurataMaggioreDiUnAnno() {
        return progettoRepository.findRagioniSocialiSocietaConProgettiDurataMaggioreDiUnAnno();
    }

    @Override
    public List<Progetto> listProgettiConAlmenoUnDipendenteConRalMaggioreOUguale(Integer reddito) {
        return progettoRepository.findAllProgettiConAlmenoUnDipendenteConRalMaggioreOUguale(reddito);
    }

    @Override
    public List<Progetto> listProgettiAnomaliConAlmenoUnDipendenteDiSocietaChiusa() {
        return progettoRepository.findAllProgettiAnomaliConAlmenoUnDipendenteDiSocietaChiusa();
    }
}
