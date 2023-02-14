package paulocalderan.avaliacaodesenvolvedorbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import paulocalderan.avaliacaodesenvolvedorbackend.request.PessoaRequest;
import paulocalderan.avaliacaodesenvolvedorbackend.response.PessoaListResponse;
import paulocalderan.avaliacaodesenvolvedorbackend.response.PessoaResponse;
import paulocalderan.avaliacaodesenvolvedorbackend.service.PessoaService;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private PessoaService pessoaService;

    @GetMapping("/{id}")
    public PessoaResponse findById(@PathVariable("id") Long id) {
        return pessoaService.getPerson(id);
    }

    @GetMapping
    public PessoaListResponse findAll() {
        return pessoaService.getAllPerson();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public PessoaResponse save(@RequestBody @Valid PessoaRequest pessoaRequest) {
        return pessoaService.save(pessoaRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Long id,
                       @RequestBody @Valid PessoaRequest pessoaRequest) {
        pessoaService.update(id, pessoaRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        pessoaService.delete(id);
    }

}