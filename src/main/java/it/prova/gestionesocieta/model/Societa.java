package it.prova.gestionesocieta.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "societa")
public class Societa
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_societa")
    private Long id_societa;

    @Column(name = "ragioneSociale")
    private String ragioneSociale;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name = "dataFondazione")
    private LocalDate dataFondazione;

    @Column(name = "dataChiusura")
    private LocalDate dataChiusura;

    @OneToMany(mappedBy = "societa", fetch = FetchType.LAZY)
    private Set<Dipendente> dipendenti = new HashSet<Dipendente>();

    public Societa() {}

    public Societa(Long id_societa, String ragioneSociale, String indirizzo, LocalDate dataFondazione, LocalDate dataChiusura) {
        this.id_societa = id_societa;
        this.ragioneSociale = ragioneSociale;
        this.indirizzo = indirizzo;
        this.dataFondazione = dataFondazione;
        this.dataChiusura = dataChiusura;
    }

    public Long getId_societa() {
        return id_societa;
    }

    public void setId_societa(Long id_societa) {
        this.id_societa = id_societa;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public LocalDate getDataFondazione() {
        return dataFondazione;
    }

    public void setDataFondazione(LocalDate dataFondazione) {
        this.dataFondazione = dataFondazione;
    }

    public LocalDate getDataChiusura() {
        return dataChiusura;
    }

    public void setDataChiusura(LocalDate dataChiusura) {
        this.dataChiusura = dataChiusura;
    }

    public Set<Dipendente> getDipendenti() {
        return dipendenti;
    }

    public void setDipendenti(Set<Dipendente> dipendenti) {
        this.dipendenti = dipendenti;
    }

    public void addDipendente(Dipendente dipendente) {
        this.dipendenti.add(dipendente);
        dipendente.setSocieta(this);
    }

    public void removeDipendente(Dipendente dipendente) {
        this.dipendenti.remove(dipendente);
        if (dipendente != null) {
            dipendente.setSocieta(null);
        }
    }

    @Override
    public String toString()
    {
        String dataFondazioneString = dataFondazione != null ? DateTimeFormatter.ofPattern("dd-MM-yyyy").format(dataFondazione) : " N.D.";
        String dataChiusuraString   = dataChiusura   != null ? DateTimeFormatter.ofPattern("dd-MM-yyyy").format(dataChiusura)   : " N.D.";
        return "Societa{" +
                "id_societa=" + id_societa +
                ", ragioneSociale='" + ragioneSociale + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", dataFondazione=" + dataFondazioneString +
                ", dataChiusura=" + dataChiusuraString +
                '}';
    }
}
