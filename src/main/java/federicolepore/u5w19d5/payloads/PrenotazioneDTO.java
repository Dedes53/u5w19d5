package federicolepore.u5w19d5.payloads;

import federicolepore.u5w19d5.entities.Dipendente;
import federicolepore.u5w19d5.entities.Viaggio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PrenotazioneDTO(
        @NotBlank(message = "C'è bisogno di qualcuno per prenotare per qualcuno")
        Dipendente dipendente,
        @NotBlank(message = "Esponi qui le tue preferenze su viaggio e pernotto, se non ne hai dì pure nessuna")
        @Size(min = 2, max = 250, message = "Adesso va bene avere delle preferenze, ma non esagerare, sii più coinciso")
        String preferenze,
        @NotBlank(message = "C'è bisogno di dove andare, per decidere dove andare")
        Viaggio viaggio
) {
}
