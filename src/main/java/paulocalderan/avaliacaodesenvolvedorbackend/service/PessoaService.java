package paulocalderan.avaliacaodesenvolvedorbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paulocalderan.avaliacaodesenvolvedorbackend.domain.pessoa.Pessoa;
import paulocalderan.avaliacaodesenvolvedorbackend.exception.ErroException;
import paulocalderan.avaliacaodesenvolvedorbackend.mapper.PessoaMapper;
import paulocalderan.avaliacaodesenvolvedorbackend.repository.EnderecoRepository;
import paulocalderan.avaliacaodesenvolvedorbackend.repository.PessoaRepository;
import paulocalderan.avaliacaodesenvolvedorbackend.request.PessoaRequest;
import paulocalderan.avaliacaodesenvolvedorbackend.response.PessoaListResponse;
import paulocalderan.avaliacaodesenvolvedorbackend.response.PessoaResponse;

import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class PessoaService {

    private PessoaRepository pessoaRepository;
    private EnderecoRepository enderecoRepository;

    public Pessoa findById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ErroException("Pessoa não encontrada."));
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

    public void update(PessoaRequest pessoaRequest) {
        Pessoa pessoa = getById(pessoaRequest.getId());
        PessoaMapper.INSTANCE.update(pessoaRequest, pessoa);
        pessoaRepository.save(pessoa);
    }

    public Pessoa getById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ErroException("Pessoa não encontrada!"));
    }

    public void delete(Long id) {
        Pessoa pessoa = getById(id);
        pessoaRepository.delete(pessoa);
    }

}