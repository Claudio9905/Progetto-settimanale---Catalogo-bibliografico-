package claudiopostiglione.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Catalogo Bibliografico")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CatalogoBibliografico {

    //Attributi
    @Id
    @GeneratedValue
    protected UUID codiceISBN;
    protected String titolo;
    protected LocalDate annoDiPubblicazione;
    protected int numeroPagine;


    @OneToOne(mappedBy = "elementoPrestato")
    private Prestito prestito;

    //Costruttori
    public CatalogoBibliografico() {
    }

    public CatalogoBibliografico(String titolo, LocalDate annoDiPubblicazione, int numeroPagine) {
        this.titolo = titolo;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    //Metodi
    public UUID getCodiceISBN() {
        return codiceISBN;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }

    public void setAnnoDiPubblicazione(LocalDate annoDiPubblicazione) {
        this.annoDiPubblicazione = annoDiPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    @Override
    public String toString() {
        return "|-- CatalogoBibliografico" +
                "codiceISBN= " + codiceISBN +
                ", titolo= " + titolo + '\'' +
                ", annoDiPubblicazione= " + annoDiPubblicazione +
                ", numeroPagine= " + numeroPagine +
                " --|";
    }
}
