package claudiopostiglione.exceptions;

public class TitleElementNotFoundException extends RuntimeException {
    public TitleElementNotFoundException(String titolo) {
        super("il titolo " + titolo + " non è stato trovato");
    }
}
