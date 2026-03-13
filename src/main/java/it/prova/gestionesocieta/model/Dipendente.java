package it.prova.gestionesocieta.model;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dipendente")
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dipendente")
    private Long id_dipendente;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "dataAssunzione")
    private LocalDate dataAssunzione;

    @Column(name = "redditoAnnuoLordo")
    private Integer redditoAnnuoLordo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "societa_id", nullable = false)
    private Societa societa;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(name = "dipendente_progetto", joinColumns = @JoinColumn(name = "dipendente_id"), inverseJoinColumns = @JoinColumn(name = "progetto_id"))
    private Set<Progetto> progetti = new HashSet<Progetto>();

    public Dipendente() {}

    public Dipendente(Long id_dipendente, String nome, String cognome, LocalDate dataAssunzione, Integer redditoAnnuoLordo) {
        this.id_dipendente = id_dipendente;
        this.nome = nome;
        this.cognome = cognome;
        this.dataAssunzione = dataAssunzione;
        this.redditoAnnuoLordo = redditoAnnuoLordo;
    }

    public Long getId_dipendente() {
        return id_dipendente;
    }

    public void setId_dipendente(Long id_dipendente) {
        this.id_dipendente = id_dipendente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataAssunzione() {
        return dataAssunzione;
    }

    public void setDataAssunzione(LocalDate dataAssunzione) {
        this.dataAssunzione = dataAssunzione;
    }

    public Integer getRedditoAnnuoLordo() {
        return redditoAnnuoLordo;
    }

    public void setRedditoAnnuoLordo(Integer redditoAnnuoLordo) {
        this.redditoAnnuoLordo = redditoAnnuoLordo;
    }

    public Societa getSocieta() {
        return societa;
    }

    public void setSocieta(Societa societa) {
        this.societa = societa;
    }

    public Set<Progetto> getProgetti() {
        return progetti;
    }

    public void setProgetti(Set<Progetto> progetti) {
        this.progetti = progetti;
    }

    @Override
    public String toString()
    {
        String dataAssunzioneString = dataAssunzione != null ? DateTimeFormatter.ofPattern("dd-MM-yyyy").format(dataAssunzione) : " N.D.";

        return "Dipendente{" +
                "id_dipendente=" + id_dipendente +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataAssuzione="+ dataAssunzioneString +
                ", redditoAnnuoLordo=" + redditoAnnuoLordo +
                '}';
    }
}
