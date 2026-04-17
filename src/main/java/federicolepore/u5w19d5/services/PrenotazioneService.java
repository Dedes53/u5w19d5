package federicolepore.u5w19d5.services;

import federicolepore.u5w19d5.repositories.PrenotazioneRepository;
import org.springframework.stereotype.Service;

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

//    public Prenotazione save(PrenotazioneDTO body) {
//        // richiamo il dipendente e il viaggio poer i controlli
//        Dipendente d = dipendenteService.findByID(body.dipendenteID());
//    }
}
