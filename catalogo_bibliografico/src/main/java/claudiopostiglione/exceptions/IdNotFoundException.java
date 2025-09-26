package claudiopostiglione.exceptions;

import java.util.UUID;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String  id) {
        super("Il record con id " + id + " non è stato trovato");
    }
}
