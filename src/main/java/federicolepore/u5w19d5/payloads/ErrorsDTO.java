package federicolepore.u5w19d5.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime timeStamp) {
}
