package it.prova.gestionesocieta.service.societa;
import io.micrometer.common.util.StringUtils;
import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.reporitory.SocietaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public void inserisciNuovoConControlloDuplicato(Societa societaInstance) {
        Societa societaGiaPresente = societaRepository.findByRagioneSociale(societaInstance.getRagioneSociale());
        if (societaGiaPresente != null)
        {
            throw new RuntimeException("Attenzione, impossibile eseguire l'operazione, società già presente");
        }
        societaRepository.save(societaInstance);
    }

    @Override
    public List<Societa> findByExample(Societa example)
    {
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        List<String> whereClauses = new ArrayList<String>();

        StringBuilder queryBuilder = new StringBuilder("select s from Societa s where s.id = s.id ");

        if (StringUtils.isNotEmpty(example.getRagioneSociale())) {
            whereClauses.add(" s.ragioneSociale  like :rag ");
            paramaterMap.put("rag", "%" + example.getRagioneSociale() + "%");
        }
        if (StringUtils.isNotEmpty(example.getIndirizzo())) {
            whereClauses.add(" s.indirizzo like :indirizzo ");
            paramaterMap.put("indirizzo", "%" + example.getIndirizzo() + "%");
        }

        queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
        queryBuilder.append(org.apache.commons.lang3.StringUtils.join(whereClauses, " and "));
        TypedQuery<Societa> typedQuery = entityManager.createQuery(queryBuilder.toString(), Societa.class);

        for (String key : paramaterMap.keySet()) {
            typedQuery.setParameter(key, paramaterMap.get(key));
        }

        return typedQuery.getResultList();
    }

    @Override
    public void rimozioneSocietaConControllo(Long idSocieta)
    {
        Societa societaInstance = caricaSingolo(idSocieta);
        if (societaInstance != null && !societaInstance.getDipendenti().isEmpty())
        {
            throw new RuntimeException("Impossibile rimuovere una società che ha dipendenti");
        }
        societaRepository.deleteById(idSocieta);
    }


    @Override
    @Transactional
    public void rimuovi(Long idSocieta) {
        societaRepository.deleteById(idSocieta);
    }
}
