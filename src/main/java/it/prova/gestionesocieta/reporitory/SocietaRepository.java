package it.prova.gestionesocieta.reporitory;
import it.prova.gestionesocieta.model.Societa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.time.LocalDate;

public interface SocietaRepository extends CrudRepository<Societa, Long>, QueryByExampleExecutor<Societa>
{
    public Societa findByRagioneSociale(String ragioneSociale);

    @Query("select s.dataChiusura from Dipendente d join d.societa s where d.id_dipendente = :idDipendente")
    LocalDate findDataChiusuraByDipendenteId(@Param("idDipendente") Long idDipendente);
}
