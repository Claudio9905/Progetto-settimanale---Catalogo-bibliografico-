package claudiopostiglione.exceptions;

public class NameAuthorNotFoundException extends RuntimeException {
    public NameAuthorNotFoundException(String nome) {
        super("l'autore " + nome + " non è stato trovato");
    }
}
