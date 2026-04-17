package federicolepore.u5w19d5.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsListDTO(String message, LocalDateTime timeStamp, List<String> errors) {
}
