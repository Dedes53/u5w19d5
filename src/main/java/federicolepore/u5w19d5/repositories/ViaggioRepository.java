package federicolepore.u5w19d5.repositories;

import federicolepore.u5w19d5.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, UUID> {
    // eventuale controllo per verificare non si creino viaggi duplicati (destinazione e data)
    boolean existsByDestinazioneAndData(String destinazione, LocalDate data);
}
