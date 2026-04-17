package federicolepore.u5w19d5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID prenotazioneID;

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    private String preferenze;

    @ManyToOne
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;

    public Prenotazione(Dipendente dipendente, String preferenze, Viaggio viaggio) {
        this.dipendente = dipendente;
        this.preferenze = preferenze;
        this.viaggio = viaggio;
    }

    
}
