package federicolepore.u5w19d5.controllers;

import federicolepore.u5w19d5.entities.Prenotazione;
import federicolepore.u5w19d5.exceptions.ValidationException;
import federicolepore.u5w19d5.payloads.NewPrenotazioneDTO;
import federicolepore.u5w19d5.payloads.PrenotazioneDTO;
import federicolepore.u5w19d5.services.PrenotazioneService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;


    public PrenotazioneController(PrenotazioneService prenotazioneService) {
        this.prenotazioneService = prenotazioneService;
    }


    // 1 - POST http://localhost:3002/prenotazioni + @RequestBody
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //quindi status 201
    public NewPrenotazioneDTO newPrenotazione(@RequestBody @Validated PrenotazioneDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(e -> e.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        }
        Prenotazione newP = this.prenotazioneService.save(body);
        return new NewPrenotazioneDTO(newP.getPrenotazioneID());
    }

    // 2 - GET  http://localhost:3002/prenotazioni
    @GetMapping
    public Page<Prenotazione> getPrenotazioni(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(defaultValue = "nome") String sortBy) {
        return this.prenotazioneService.findAll(page, size, sortBy);
    }


}
