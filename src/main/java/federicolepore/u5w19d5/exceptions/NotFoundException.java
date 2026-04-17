package federicolepore.u5w19d5.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("Non è stato possibile trovare il dipendente corrispondente all'id: " + id);
    }
}
