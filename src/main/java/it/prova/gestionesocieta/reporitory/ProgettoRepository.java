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

    @Query(value = """
          select distinct s.ragioneSociale
          from societa s
          join dipendente d on d.societa_id = s.id_societa
          join dipendente_progetto dp on dp.dipendente_id = d.id_dipendente
          join progetto p on p.id_progetto = dp.progetto_id
          where p.durataInMesi > 12
          order by s.ragioneSociale
            """, nativeQuery = true)
    List<String> findRagioniSocialiSocietaConProgettiDurataMaggioreDiUnAnno();

    @Query(value = """
      select distinct p.*
      from progetto p
      join dipendente_progetto dp on dp.progetto_id = p.id_progetto
      join dipendente d on d.id_dipendente = dp.dipendente_id
      where d.redditoAnnuoLordo >= 30000
      order by p.nome
        """, nativeQuery = true)
    List<Progetto> findAllProgettiConAlmenoUnDipendenteConRalMaggioreOUgualeA30000();
}
