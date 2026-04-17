package federicolepore.u5w19d5.controllers;

import federicolepore.u5w19d5.entities.Viaggio;
import federicolepore.u5w19d5.exceptions.ValidationException;
import federicolepore.u5w19d5.payloads.NewViaggioDTO;
import federicolepore.u5w19d5.payloads.ViaggioDTO;
import federicolepore.u5w19d5.services.ViaggioService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    private final ViaggioService viaggioService;

    public ViaggioController(ViaggioService viaggioService) {
        this.viaggioService = viaggioService;
    }


    // 1 - POST http://localhost:3002/viaggi + @RequestBody
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //quindi status 201
    public NewViaggioDTO newViaggio(@RequestBody @Validated ViaggioDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(e -> e.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        }
        Viaggio newV = this.viaggioService.save(body);
        return new NewViaggioDTO(newV.getViaggioID());
    }

    // 2 - GET  http://localhost:3002/viaggi
    @GetMapping
    public Page<Viaggio> getViaggi(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "nome") String sortBy) {
        return this.viaggioService.findAll(page, size, sortBy);
    }

    // 3 - GET http://localhost:3002/viaggi/{viaggioId}
    @GetMapping("/{viaggioId}")
    public Viaggio getById(@PathVariable UUID viaggioId) {
        return this.viaggioService.findById(viaggioId);
    }

    // 4 - PUT http://localhost:3002/viaggi/{viaggioId} + @RequestBody
    @PutMapping("/{viaggioId}")
    public Viaggio getByIdAndUpdate(@PathVariable UUID viaggioId, @RequestBody ViaggioDTO body) {
        return this.viaggioService.findByIdAndUpdate(viaggioId, body);
    }

    // 5 - DELETE http://localhost:3002/dipendenti/{dipendentiId}
    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable UUID viaggioId) {
        this.viaggioService.getByIdAndDelete(viaggioId);
    }


}
