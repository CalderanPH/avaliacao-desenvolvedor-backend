package paulocalderan.avaliacaodesenvolvedorbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paulocalderan.avaliacaodesenvolvedorbackend.domain.endereco.Endereco;
import paulocalderan.avaliacaodesenvolvedorbackend.exception.ErroException;
import paulocalderan.avaliacaodesenvolvedorbackend.mapper.EnderecoMapper;
import paulocalderan.avaliacaodesenvolvedorbackend.repository.EnderecoRepository;
import paulocalderan.avaliacaodesenvolvedorbackend.request.EnderecoRequest;
import paulocalderan.avaliacaodesenvolvedorbackend.response.EnderecoListResponse;
import paulocalderan.avaliacaodesenvolvedorbackend.response.EnderecoResponse;

import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    public Endereco findById(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new ErroException("Endereco não encontrada."));
    }

    public EnderecoResponse getEndereco(Long id) {
        Endereco endereco = findById(id);

        return EnderecoMapper.INSTANCE.toGetResponse(endereco);
    }

    public EnderecoListResponse getAllEndereco() {
        return EnderecoListResponse.builder()
                .enderecos(enderecoRepository.findAll().stream()
                        .map(EnderecoMapper.INSTANCE::toGetResponse).collect(Collectors.toList())
                ).build();
    }

    public EnderecoResponse save(EnderecoRequest enderecoRequest) {
        Endereco endereco = EnderecoMapper.INSTANCE.apply(enderecoRequest);
        endereco = this.newSave(endereco);
        return EnderecoMapper.INSTANCE.toGetResponse(endereco);
    }

    public Endereco newSave(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void update(EnderecoRequest enderecoRequest) {
        Endereco endereco = getById(enderecoRequest.getId());
        EnderecoMapper.INSTANCE.update(enderecoRequest, endereco);
        enderecoRepository.save(endereco);
    }

    public Endereco getById(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new ErroException("Endereco não encontrada!"));
    }

    public void delete(Long id) {
        Endereco endereco = getById(id);
        enderecoRepository.delete(endereco);
    }

}