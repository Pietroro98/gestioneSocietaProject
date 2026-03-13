package it.prova.gestionesocieta.reporitory;

import it.prova.gestionesocieta.model.Progetto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgettoRepository extends CrudRepository<Progetto, Long>, QueryByExampleExecutor<Progetto> {
    @Query("""
            select distinct p.cliente
            from Progetto p
            join p.dipendenti d
            where d.societa.id_societa = :idSocieta
            and p.cliente is not null
            order by p.cliente
            """)
    List<String> findClientiBySocietaId(@Param("idSocieta") Long idSocieta);
}
