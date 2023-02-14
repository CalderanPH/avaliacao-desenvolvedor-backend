package paulocalderan.avaliacaodesenvolvedorbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import paulocalderan.avaliacaodesenvolvedorbackend.domain.pessoa.Pessoa;
import paulocalderan.avaliacaodesenvolvedorbackend.mapper.PessoaMapper;
import paulocalderan.avaliacaodesenvolvedorbackend.repository.PessoaRepository;
import paulocalderan.avaliacaodesenvolvedorbackend.request.PessoaRequest;
import paulocalderan.avaliacaodesenvolvedorbackend.response.PessoaListResponse;
import paulocalderan.avaliacaodesenvolvedorbackend.response.PessoaResponse;

import java.util.stream.Collectors;

@Service
@Transactional
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa findById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada."));
    }

    public PessoaResponse getPerson(Long id) {
        Pessoa pessoa = findById(id);

        return PessoaMapper.INSTANCE.toGetResponse(pessoa);
    }

    public PessoaListResponse getAllPerson() {
        return PessoaListResponse.builder()
                .pessoas(pessoaRepository.findAll().stream()
                        .map(PessoaMapper.INSTANCE::toGetResponse).collect(Collectors.toList())
                ).build();
    }

    public PessoaResponse save(PessoaRequest pessoaRequest) {
        Pessoa pessoa = PessoaMapper.INSTANCE.apply(pessoaRequest);
        pessoa = this.newSave(pessoa);
        return PessoaMapper.INSTANCE.toGetResponse(pessoa);
    }

    public Pessoa newSave(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public void update(Long id, PessoaRequest pessoaRequest) {
        Pessoa pessoa = getById(id);
        PessoaMapper.INSTANCE.update(pessoaRequest, pessoa);
        pessoaRepository.save(pessoa);
    }

    public Pessoa getById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada!"));
    }

    public void delete(Long id) {
        Pessoa pessoa = getById(id);
        pessoaRepository.delete(pessoa);
    }

}