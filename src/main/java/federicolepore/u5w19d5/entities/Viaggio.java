package federicolepore.u5w19d5.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "viaggi")
public class Viaggio {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID viaggioID;

    private String destinazione;
    private LocalDate data;
    private String stato;   // in_programma/completato

    public Viaggio(String destinazione, LocalDate data, String stato) {
        this.destinazione = destinazione;
        this.data = data;
        this.stato = stato;
    }


}
