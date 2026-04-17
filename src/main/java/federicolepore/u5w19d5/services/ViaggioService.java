package federicolepore.u5w19d5.services;

import federicolepore.u5w19d5.entities.Viaggio;
import federicolepore.u5w19d5.exceptions.BadRequestException;
import federicolepore.u5w19d5.exceptions.NotFoundException;
import federicolepore.u5w19d5.payloads.ViaggioDTO;
import federicolepore.u5w19d5.repositories.ViaggioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ViaggioService {

    private final ViaggioRepository viaggioRepository;

    public ViaggioService(ViaggioRepository viaggioRepository) {
        this.viaggioRepository = viaggioRepository;
    }


    // METODI CRUD
    //1
    public Viaggio save(ViaggioDTO body) {
        if (this.viaggioRepository.existsByDestinazioneAndData(body.destinazione(), body.data()))
            throw new BadRequestException("La proposta di viaggio che stai provando ad inserire esiste già: luogo: " + body.destinazione() + ", data: " + body.data());
        Viaggio v = new Viaggio(body.destinazione(), body.data(), body.stato());
        Viaggio savedV = this.viaggioRepository.save(v);
        log.info("Il viaggio " + savedV.getViaggioID() + " è stato salvato con successo");
        return savedV;
    }

    // 2
    public Page<Viaggio> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 10;
        if (size < 0) size = 0;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.viaggioRepository.findAll(pageable);
    }

    //3
    public Viaggio findById(UUID viaggioID) {
        return this.viaggioRepository.findById(viaggioID).orElseThrow(() -> new NotFoundException(viaggioID));
    }

    //4
    public Viaggio findByIdAndUpdate(UUID viaggioId, ViaggioDTO body) {
        Viaggio v = this.findById(viaggioId);

        if (!v.getData().equals(body.data()) || !v.getDestinazione().equals(body.destinazione())) {
            if (this.viaggioRepository.existsByDestinazioneAndData(body.destinazione(), body.data()))
                throw new BadRequestException("La proposta di viaggio che stai provando ad inserire esiste già: luogo: " + body.destinazione() + ", data: " + body.data());
        }

        v.setData(body.data());
        v.setDestinazione(body.destinazione());
        v.setStato(body.stato());

        Viaggio updatedV = this.viaggioRepository.save(v);

        log.info("Il viaggio " + updatedV.getViaggioID() + " è stato salvato con successo");
        return updatedV;
    }

    //5
    public void getByIdAndDelete(UUID viaggioId) {
        Viaggio v = this.findById(viaggioId);
        this.viaggioRepository.delete(v);
    }

}
