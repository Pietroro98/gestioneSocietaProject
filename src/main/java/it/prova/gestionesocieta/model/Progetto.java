package it.prova.gestionesocieta.model;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "progetto")
public class Progetto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_progetto")
    private Long id_progetto;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cliente")
    private String cliente;

    @Column(name = "durataInMesi")
    private Integer durataInMesi;

    @ManyToMany(mappedBy = "progetti", fetch = FetchType.LAZY)
    private Set<Dipendente> dipendenti = new HashSet<Dipendente>();

    public Progetto() {}

    public Progetto(String nome, String cliente, Integer durataInMesi) {
        this.nome = nome;
        this.cliente = cliente;
        this.durataInMesi = durataInMesi;
    }

    public Long getId_progetto() {
        return id_progetto;
    }

    public void setId_progetto(Long id_progetto) {
        this.id_progetto = id_progetto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Integer getDurataInMesi() {
        return durataInMesi;
    }

    public void setDurataInMesi(Integer durataInMesi) {
        this.durataInMesi = durataInMesi;
    }

    public Set<Dipendente> getDipendenti() {
        return dipendenti;
    }

    public void setDipendenti(Set<Dipendente> dipendenti) {
        this.dipendenti = dipendenti;
    }

    @Override
    public String toString() {
        return "Progetto{" +
                "id_progetto=" + id_progetto +
                ", nome='" + nome + '\'' +
                ", cliente='" + cliente + '\'' +
                ", durataInMesi=" + durataInMesi +
                '}';
    }
}
