package claudiopostiglione.exceptions;

import java.util.UUID;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(UUID id) {
        super("Il record con id " + id + " non è stato trovato");
    }
}
