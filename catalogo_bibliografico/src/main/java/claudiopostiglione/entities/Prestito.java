package claudiopostiglione.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Prestito")
public class Prestito {

    //Attributi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPrestito;

    @Column(name = "Data inizio prestito", nullable = false)
    private LocalDate dataInizioPrestito;
    @Column(name = "Data restituzione prevista", nullable = false)
    private LocalDate dataRestituzione30gg;
    @Column(name = "Data restituzione effettiva", nullable = false)
    private LocalDate dataRestituzioneEffettiva;

    @ManyToOne
    @JoinColumn(name = "idUtente",nullable = false)
    private Utente utente;

    @OneToOne
    @JoinColumn(name = "idElementoPrestato", nullable = false, unique = true)
    private CatalogoBibliografico elementoPrestato;

    //Costruttori
    public Prestito() {
    }

    public Prestito(LocalDate dataInizioPrestito, LocalDate dataRestituzione30gg, LocalDate dataRestituzioneEffettiva, Utente utente, CatalogoBibliografico elementoPrestato) {

        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzione30gg = dataRestituzione30gg;
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
    }

    //Metodi

    public CatalogoBibliografico getElementoPrestato() {
        return elementoPrestato;
    }

    public void setElementoPrestato(CatalogoBibliografico elementoPrestato) {
        this.elementoPrestato = elementoPrestato;
    }

    public long getIdPrestito() {
        return idPrestito;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }

    public LocalDate getDataRestituzione30gg() {
        return dataRestituzione30gg;
    }

    public void setDataRestituzione30gg(LocalDate dataRestituzione30gg) {
        this.dataRestituzione30gg = dataRestituzione30gg;
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    @Override
    public String toString() {
        return "|-- Prestito " +
                " ID Prestito= " + idPrestito +
                ", Data inizio prestito= " + dataInizioPrestito +
                ", Data restituzione 30gg= " + dataRestituzione30gg +
                ", Data restituzione effettiva= " + dataRestituzioneEffettiva +
                " --|";
    }
}
