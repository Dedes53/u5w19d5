package federicolepore.u5w19d5.exceptions;

import java.util.List;

public class ValidationException extends RuntimeException {

    private List<String> errors;

    public ValidationException(List<String> errors) {
        super("Si è verificato un errore di validazione");
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }


}
