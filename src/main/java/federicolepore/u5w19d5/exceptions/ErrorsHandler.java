package federicolepore.u5w19d5.exceptions;

import federicolepore.u5w19d5.payloads.ErrorsDTO;
import federicolepore.u5w19d5.payloads.ErrorsListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleBadRequest(BadRequestException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsListDTO handleValidationErrors(ValidationException ex) {
        return new ErrorsListDTO(ex.getMessage(), LocalDateTime.now(), ex.getErrors());
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO handleNotFound(NotFoundException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsDTO handleGenerric(Exception ex) {
        ex.printStackTrace();
        return new ErrorsDTO("Scusa, stavolta colpa mia, vedo di sistemare", LocalDateTime.now());
    }

}
