package claudiopostiglione.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "Libri")
public class Libri extends CatalogoBibliografico {

    //Attributi
    private String autore;
    @Enumerated(EnumType.STRING)
    private GenereLibro genere;

    //Costruttori
    public Libri() {
    }

    public Libri(String titolo, LocalDate annoDiPubblicazione, int numeroPagine, String autore, GenereLibro genere) {
        super(titolo, annoDiPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    //Metodi
    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public GenereLibro getGenere() {
        return genere;
    }

    public void setGenere(GenereLibro genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "|-- Libri" +
                "Autore= " + autore + '\'' +
                ", Genere= " + genere +
                ", CodiceISBN= " + codiceISBN +
                ", Titolo= " + titolo + '\'' +
                ", Anno di pubblicazione= " + annoDiPubblicazione +
                ", Numero pagine= " + numeroPagine +
                " --|";
    }
}
