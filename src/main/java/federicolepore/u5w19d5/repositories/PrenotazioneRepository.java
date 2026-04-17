package federicolepore.u5w19d5.repositories;

import federicolepore.u5w19d5.entities.Dipendente;
import federicolepore.u5w19d5.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
    // anche qui eventuali controlli per prenotazioni duplicate / fatte dallo stesso utente per lo stesso giorno
    boolean existsByDipendenteAndViaggio_Data(Dipendente dipendente, LocalDate data);
}
