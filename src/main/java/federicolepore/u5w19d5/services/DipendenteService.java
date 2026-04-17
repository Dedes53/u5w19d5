package federicolepore.u5w19d5.services;

import com.cloudinary.Cloudinary;
import federicolepore.u5w19d5.entities.Dipendente;
import federicolepore.u5w19d5.exceptions.BadRequestException;
import federicolepore.u5w19d5.exceptions.NotFoundException;
import federicolepore.u5w19d5.payloads.DipendenteDTO;
import federicolepore.u5w19d5.repositories.DipendenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class DipendenteService {

    private final DipendenteRepository dipendenteRepository;
    private final Cloudinary cloudinaryUploader;

    public DipendenteService(DipendenteRepository dipendenteRepository, Cloudinary cloudinaryUploader) {
        this.dipendenteRepository = dipendenteRepository;
        this.cloudinaryUploader = cloudinaryUploader;
    }


    //    METODI CRUD

    // 1
    public Dipendente save(DipendenteDTO body) {
        if (this.dipendenteRepository.existsByEmail((body.email())))
            throw new BadRequestException("L'indirizzo email " + body.email() + " è già associato ad un altro dipendente");
        Dipendente d = new Dipendente(body.username(), body.nome(), body.cognome(), body.email());
        Dipendente savedD = this.dipendenteRepository.save(d);
        log.info("Il nuovo dipendente con id: " + savedD.getDipendenteID() + " è stato inserito con sucesso");
        return savedD;
    }

    // 2
    public Page<Dipendente> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 10;
        if (size < 0) size = 0;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.dipendenteRepository.findAll(pageable);

    }

    //3
    public Dipendente findById(UUID dipendenteID) {
        return this.dipendenteRepository.findById(dipendenteID).orElseThrow(() -> new NotFoundException(dipendenteID));
    }

}
























