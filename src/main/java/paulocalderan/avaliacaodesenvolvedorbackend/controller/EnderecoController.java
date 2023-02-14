package paulocalderan.avaliacaodesenvolvedorbackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import paulocalderan.avaliacaodesenvolvedorbackend.request.EnderecoRequest;
import paulocalderan.avaliacaodesenvolvedorbackend.response.EnderecoListResponse;
import paulocalderan.avaliacaodesenvolvedorbackend.response.EnderecoResponse;
import paulocalderan.avaliacaodesenvolvedorbackend.service.EnderecoService;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Slf4j
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
    public ResponseEntity<EnderecoResponse> save(@RequestBody @Valid EnderecoRequest enderecoRequest) {
        EnderecoResponse enderecoResponse = enderecoService.save(enderecoRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(enderecoResponse.getId())
                .toUri();
        log.info("Criado novo endereço com id: {}", enderecoResponse.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Long id,
                       @RequestBody @Valid EnderecoRequest enderecoRequest) {
        enderecoService.update(id, enderecoRequest);
        log.info("Endereço alterado com o id: {}", id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        enderecoService.delete(id);
        log.info("Endereço deletado com o id: {}", id);
    }

}
