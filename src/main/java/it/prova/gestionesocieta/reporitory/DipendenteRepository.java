package it.prova.gestionesocieta.reporitory;

import it.prova.gestionesocieta.model.Dipendente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;


public interface DipendenteRepository extends CrudRepository<Dipendente, Long>, QueryByExampleExecutor<Dipendente>
{
    @Query(value = """
            select d.*
            from dipendente d
            join societa s on s.id_societa = d.societa_id
            join dipendente_progetto dp on dp.dipendente_id = d.id_dipendente
            join progetto p on p.id_progetto = dp.progetto_id
            where s.dataFondazione < '1990-01-01'
            and p.durataInMesi >= 6
            order by d.dataAssunzione asc, d.id_dipendente asc
            limit 1
            """, nativeQuery = true)
    Dipendente findTopDipendentePiuAnzianoPerSocietaFondataPrimaDel1990EProgettoDiAlmenoSeiMesi();
}
