package it.prova.gestionesocieta.reporitory;

import it.prova.gestionesocieta.model.Dipendente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;


public interface DipendenteRepository extends CrudRepository<Dipendente, Long>, QueryByExampleExecutor<Dipendente>
{

}
