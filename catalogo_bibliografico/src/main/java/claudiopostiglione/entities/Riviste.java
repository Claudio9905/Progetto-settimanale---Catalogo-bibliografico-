package claudiopostiglione.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Riviste")
public class Riviste extends CatalogoBibliografico {

    //Attributi
    @Enumerated(EnumType.STRING)
    @Column(name = "Periodicità", nullable = false)
    private TipoPeriodicità periodicità;

    //Costruttori
    public Riviste() {
    }

    public Riviste(String titolo, LocalDate annoDiPubblicazione, int numeroPagine, TipoPeriodicità periodicità) {
        super(titolo, annoDiPubblicazione, numeroPagine);
        this.periodicità = periodicità;
    }

    //Metodi
    public TipoPeriodicità getPeriodicità() {
        return periodicità;
    }

    public void setPeriodicità(TipoPeriodicità periodicità) {
        this.periodicità = periodicità;
    }

    @Override
    public String toString() {
        return "|-- Riviste " +
                " Periodicità= " + periodicità +
                ", Codice ISBN= " + codiceISBN +
                ", Titolo= " + titolo + '\'' +
                ", Anno di pubblicazione= " + annoDiPubblicazione +
                ", Numero pagine=" + numeroPagine +
                " --|";
    }
}
