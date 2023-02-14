package paulocalderan.avaliacaodesenvolvedorbackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import paulocalderan.avaliacaodesenvolvedorbackend.request.PessoaRequest;
import paulocalderan.avaliacaodesenvolvedorbackend.response.PessoaListResponse;
import paulocalderan.avaliacaodesenvolvedorbackend.response.PessoaResponse;
import paulocalderan.avaliacaodesenvolvedorbackend.service.PessoaService;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Slf4j
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
    public ResponseEntity<PessoaResponse> save(@RequestBody @Valid PessoaRequest pessoaRequest) {
        PessoaResponse pessoaResponse = pessoaService.save(pessoaRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pessoaResponse.getId())
                .toUri();
        log.info("Criado novo endereço com id: {}", pessoaResponse.getId());
        return ResponseEntity.created(location).build();
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