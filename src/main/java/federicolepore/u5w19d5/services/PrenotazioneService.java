package federicolepore.u5w19d5.services;

import federicolepore.u5w19d5.entities.Dipendente;
import federicolepore.u5w19d5.entities.Prenotazione;
import federicolepore.u5w19d5.exceptions.BadRequestException;
import federicolepore.u5w19d5.payloads.PrenotazioneDTO;
import federicolepore.u5w19d5.repositories.PrenotazioneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class PrenotazioneService {

    private PrenotazioneRepository prenotazioneRepository;

    // servono dipendenteService e viaggioService per controllo sulla prenotazione
    private DipendenteService dipendenteService;
    private ViaggioService viaggioService;

    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository, DipendenteService dipendenteService, ViaggioService viaggioService) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.dipendenteService = dipendenteService;
        this.viaggioService = viaggioService;
    }


    //1
    public Prenotazione save(PrenotazioneDTO body) {
        // richiamo il dipendente e il viaggio poer i controlli
        Dipendente d = body.dipendente();
        LocalDate dataV = body.viaggio().getData();

        if (this.prenotazioneRepository.existsByDipendenteAndViaggio_Data(d, dataV)) {
            throw new BadRequestException("Esiste già una prenotazione per il dipendente " + body.dipendente().getDipendenteID() + " per la data " + dataV);
        }

        Prenotazione p = new Prenotazione(body.dipendente(), body.preferenze(), body.viaggio());
        Prenotazione savedP = this.prenotazioneRepository.save(p);
        log.info("La prenotazione " + savedP.getPrenotazioneID() + " è avvenuta con successo");
        return savedP;
    }


}
