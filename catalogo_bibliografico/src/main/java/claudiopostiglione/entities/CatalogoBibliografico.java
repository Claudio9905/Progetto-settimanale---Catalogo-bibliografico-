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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Codice ISBN", nullable = false)
    protected long codiceISBN;
    @Column(name = "Titolo", nullable = false)
    protected String titolo;
    @Column(name = "Anno di pubblicazione", nullable = false)
    protected LocalDate annoDiPubblicazione;
    @Column(name = "Numero delle pagine", nullable = false)
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
    public long getCodiceISBN() {
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
