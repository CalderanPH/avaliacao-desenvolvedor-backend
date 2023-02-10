package paulocalderan.avaliacaodesenvolvedorbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import paulocalderan.avaliacaodesenvolvedorbackend.request.EnderecoRequest;
import paulocalderan.avaliacaodesenvolvedorbackend.response.EnderecoListResponse;
import paulocalderan.avaliacaodesenvolvedorbackend.response.EnderecoResponse;
import paulocalderan.avaliacaodesenvolvedorbackend.service.EnderecoService;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@AllArgsConstructor
@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private EnderecoService enderecoService;

    @GetMapping("/{id}")
    public EnderecoResponse findById(@PathVariable("id") Long id) {
        return enderecoService.getEndereco(id);
    }

    @GetMapping
    public EnderecoListResponse findAll() {
        return enderecoService.getAllEndereco();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public EnderecoResponse save(@RequestBody @Valid EnderecoRequest enderecoRequest) {
        return enderecoService.save(enderecoRequest);
    }

    @PutMapping
    @ResponseStatus(NO_CONTENT)
    public void update(@RequestBody @Valid EnderecoRequest enderecoRequest) {
        enderecoService.update(enderecoRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        enderecoService.delete(id);
    }

}
