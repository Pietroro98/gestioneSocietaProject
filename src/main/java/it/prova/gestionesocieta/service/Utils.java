package it.prova.gestionesocieta.service;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Progetto;
import it.prova.gestionesocieta.reporitory.SocietaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Utils {

    @Autowired
    private SocietaRepository societaRepository;

    /**
     * Verifica che il progetto sia compatibile con l'eventuale data di chiusura
     * della societa' del dipendente.
     *
     * La data finale del progetto viene stimata sommando la durata del progetto
     * alla data di assunzione del dipendente. Se la societa' non ha una data di
     * chiusura impostata, il controllo non viene applicato. Se invece la data
     * finale stimata supera la data di chiusura, viene lanciata un'eccezione.
     *
     * @param dipendenteInstance il dipendente da associare al progetto
     * @param progettoInstance il progetto da verificare
     * @throws RuntimeException se il progetto termina dopo la chiusura della societa'
     */
    public void valida(Dipendente dipendenteInstance, Progetto progettoInstance) {
        LocalDate dataChiusura = societaRepository.findDataChiusuraByDipendenteId(dipendenteInstance.getId_dipendente());
        if (dataChiusura == null) {
            return;
        }

        LocalDate dataFineStimata = dipendenteInstance.getDataAssunzione().plusMonths(progettoInstance.getDurataInMesi());
        if (dataFineStimata.isAfter(dataChiusura)) {
            throw new RuntimeException("Il progetto supera la data chiusura della societa");
        }
    }
}
