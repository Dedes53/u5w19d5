package federicolepore.u5w19d5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DipendenteDTO(
        @NotBlank(message = "Dai sceglilo anche stupido, tutti in ufficio ne hanno uno, basta che metti qualcosa...")
        String username,

        @NotBlank(message = "Bisogna che ci dici chi sei, non puoi lasciare una stringa vuota")
        @Size(min = 2, max = 30, message = "Il nome proprio deve essere compreso tra i 2 e i 30 caratteri")
        String nome,

        @NotBlank(message = "Sai quanti omonimi ci sono in azienda?! Metti il cognome, dai")
        @Size(min = 2, max = 30, message = "Il cognome deve essere compreso tra i 2 e i 30 caratteri")
        String cognome,

        @NotBlank(message = "E come pensi che dovremmo contattarti per il viaggio? Ci vuoi andare o no?! Allora non lasciare una stringa vuota")

        @Email(regexp = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$",
                message = "L'email inserita non è nel formato corretto, inseriscila bene")
        // @Email(message = "L'email inserita non è nel formato corretto, inseriscila bene")
        String email
) {
}
