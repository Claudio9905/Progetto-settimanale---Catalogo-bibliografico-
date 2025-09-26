package claudiopostiglione.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Utente")
public class Utente {

    //Attributi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numTessera;
    @Column(name = "Nome", nullable = false)
    private String nome;
    @Column(name = "Cognome", nullable = false)
    private String cognome;
    @Column(name = "Data di nascita", nullable = false)
    private LocalDate dataDiNascita;


    @OneToMany(mappedBy = "utente")
    private List<Prestito> prestiti;


    //Costruttori
    public Utente() {
    }

    public Utente(String nome, String cognome, LocalDate dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
    }

    //Metodi
    public long getNumTessera() {
        return numTessera;
    }

    public List<Prestito> getPrestiti() {
        return prestiti;
    }

    public void setPrestiti(List<Prestito> prestiti) {
        this.prestiti = prestiti;
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

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    @Override
    public String toString() {
        return "|-- Utente " +
                "Numero della tessera= " + numTessera + '\'' +
                ", Nome= " + nome + '\'' +
                ", Cognome= " + cognome + '\'' +
                ", Data di nascita= " + dataDiNascita +
               ", Lista prestiti= " + prestiti
                + " --|";
    }
}
