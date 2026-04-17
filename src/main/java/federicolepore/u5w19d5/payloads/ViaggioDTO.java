package federicolepore.u5w19d5.payloads;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ViaggioDTO(
        @NotBlank(message = "Dovrà pur esserci dove si vuole andare, tipo Pizzo Calabro")
        String destinazione,

        @NotBlank(message = "Devi inserire una data, e attento che tra 21 ottobre e 21 novembre c'è un mese di differenza, attento anche a non confondere giugno con ferragosto ")
        @Future
        LocalDate data,
        @NotBlank(message = "Devi dire solo se è in programma o se è già completato")
        @Size(min = 2, max = 15)
        String stato
) {
}
