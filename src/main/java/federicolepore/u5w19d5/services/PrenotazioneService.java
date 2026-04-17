package federicolepore.u5w19d5.services;

import federicolepore.u5w19d5.entities.Dipendente;
import federicolepore.u5w19d5.entities.Prenotazione;
import federicolepore.u5w19d5.entities.Viaggio;
import federicolepore.u5w19d5.exceptions.BadRequestException;
import federicolepore.u5w19d5.exceptions.NotFoundException;
import federicolepore.u5w19d5.payloads.PrenotazioneDTO;
import federicolepore.u5w19d5.repositories.PrenotazioneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;

    // servono dipendenteService e viaggioService per controllo sulla prenotazione
    private final DipendenteService dipendenteService;
    private final ViaggioService viaggioService;

    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository, DipendenteService dipendenteService, ViaggioService viaggioService) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.dipendenteService = dipendenteService;
        this.viaggioService = viaggioService;
    }


    //1
    public Prenotazione save(PrenotazioneDTO body) {
        // richiamo il dipendente e il viaggio poer i controlli

        Dipendente d = dipendenteService.findById(body.dipendente());
        Viaggio v = viaggioService.findById(body.viaggio());

        if (prenotazioneRepository.existsByDipendenteAndViaggio_Data(d, v.getData())) {
            throw new BadRequestException("Esiste già una prenotazione per il dipendente " + d + " per la data " + v.getData());
        }

        Prenotazione p = new Prenotazione(d, body.preferenze(), v);
        Prenotazione savedP = this.prenotazioneRepository.save(p);
        log.info("La prenotazione " + savedP.getPrenotazioneID() + " è avvenuta con successo");
        return savedP;
    }

    // 2
    public Page<Prenotazione> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 10;
        if (size < 0) size = 0;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.prenotazioneRepository.findAll(pageable);

    }

    //3
    public Prenotazione findById(UUID prenotazioneId) {
        return this.prenotazioneRepository.findById(prenotazioneId).orElseThrow(() -> new NotFoundException(prenotazioneId));
    }

    //4
    public Prenotazione findByIdAndUpdate(UUID prenotazioneId, PrenotazioneDTO body) {
        Prenotazione p = this.findById(prenotazioneId);

        p.setDipendente(dipendenteService.findById(body.dipendente()));
        p.setPreferenze(body.preferenze());
        p.setViaggio(viaggioService.findById(body.viaggio()));

        Prenotazione updatedP = this.prenotazioneRepository.save(p);

        log.info("La prenotazione " + updatedP.getPrenotazioneID() + " è stata modificata con successo");
        return updatedP;
    }


    //5
    public void getByIdAndDelete(UUID prenotazioneId) {
        Prenotazione p = this.findById(prenotazioneId);
        this.prenotazioneRepository.delete(p);
    }


}
