package federicolepore.u5w19d5.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record PrenotazioneDTO(
        @NotNull(message = "C'è bisogno di qualcuno per prenotare per qualcuno")
        UUID dipendente,
        @NotBlank(message = "Esponi qui le tue preferenze su viaggio e pernotto, se non ne hai dì pure nessuna")
        @Size(min = 2, max = 250, message = "Adesso va bene avere delle preferenze, ma non esagerare, sii più coinciso")
        String preferenze,
        @NotNull(message = "C'è bisogno di dove andare, per decidere dove andare")
        UUID viaggio
) {
}
