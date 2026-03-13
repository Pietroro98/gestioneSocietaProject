package it.prova.gestionesocieta.reporitory;
import it.prova.gestionesocieta.model.Societa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface SocietaRepository extends CrudRepository<Societa, Long>, QueryByExampleExecutor<Societa>
{
    public Societa findByRagioneSociale(String ragioneSociale);
}
