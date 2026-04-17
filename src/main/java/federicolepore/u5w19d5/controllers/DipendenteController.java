package federicolepore.u5w19d5.controllers;


import federicolepore.u5w19d5.entities.Dipendente;
import federicolepore.u5w19d5.exceptions.ValidationException;
import federicolepore.u5w19d5.payloads.DipendenteDTO;
import federicolepore.u5w19d5.payloads.NewDipendenteDTO;
import federicolepore.u5w19d5.services.DipendenteService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    private final DipendenteService dipendenteService;


    public DipendenteController(DipendenteService dipendenteService) {
        this.dipendenteService = dipendenteService;
    }


    // 1 - POST http://localhost:3002/dipendenti + @RequestBody
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //quindi status 201
    public NewDipendenteDTO newDipendente(@RequestBody @Validated DipendenteDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(e -> e.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        }
        Dipendente newD = this.dipendenteService.save(body);
        return new NewDipendenteDTO(newD.getDipendenteID());
    }

    // 2 - GET  http://localhost:3002/dipendenti
    @GetMapping
    public Page<Dipendente> getDipendenti(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "nome") String sortBy) {
        return this.dipendenteService.findAll(page, size, sortBy);
    }

    // 3 - GET http://localhost:3002/dipendenti/{dipendentiId}
    @GetMapping("/{dipendenteId}")
    public Dipendente getById(@PathVariable UUID dipendenteId) {
        return this.dipendenteService.findById(dipendenteId);
    }

    // 4 - PUT http://localhost:3002/dipendenti/{dipendentiId} + @RequestBody
    @PutMapping("/{dipendenteId}")
    public Dipendente getByIdAndUpdate(@PathVariable UUID dipendenteId, @RequestBody DipendenteDTO body) {
        return this.dipendenteService.findByIdAndUpdate(dipendenteId, body);
    }
}























































