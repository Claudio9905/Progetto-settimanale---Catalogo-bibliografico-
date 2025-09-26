package claudiopostiglione.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Prestito")
public class Prestito {

    //Attributi
    @Id
    @GeneratedValue
    private UUID idPrestito;

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
    @JoinColumn(name = "elementoPrestato", nullable = false)
    private CatalogoBibliografico elementoPrestato;

    //Costruttori
    public Prestito() {
    }

    public Prestito(LocalDate dataInizioPrestito, LocalDate dataRestituzione30gg, LocalDate dataRestituzioneEffettiva) {

        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzione30gg = dataRestituzione30gg;
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    //Metodi
    public UUID getIdPrestito() {
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
