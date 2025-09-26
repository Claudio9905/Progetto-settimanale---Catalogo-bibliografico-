package claudiopostiglione.exceptions;

public class YearNotFoundException extends RuntimeException {
    public YearNotFoundException(int anno) {
        super("L'anno " + anno + "non è stato trovato");
    }
}
