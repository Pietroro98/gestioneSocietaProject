package it.prova.gestionesocieta.reporitory;

import it.prova.gestionesocieta.model.Dipendente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.time.LocalDate;

public interface DipendenteRepository extends CrudRepository<Dipendente, Long>, QueryByExampleExecutor<Dipendente>
{
    @Query(value = """
            select d.*
            from dipendente d
            join societa s on s.id_societa = d.societa_id
            join dipendente_progetto dp on dp.dipendente_id = d.id_dipendente
            join progetto p on p.id_progetto = dp.progetto_id
            where s.dataFondazione < :dataFondazioneLimite
            and p.durataInMesi >= :durataMinima
            order by d.dataAssunzione asc, d.id_dipendente asc
            limit 1
            """, nativeQuery = true)
    Dipendente findTopDipendentePiuAnzianoPerDataFondazioneLimiteEProgettoDurataMinima(
            @Param("dataFondazioneLimite") LocalDate dataFondazioneLimite,
            @Param("durataMinima") Integer durataMinima);
}
