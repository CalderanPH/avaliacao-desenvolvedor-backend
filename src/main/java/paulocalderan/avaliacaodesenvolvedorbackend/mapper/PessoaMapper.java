package paulocalderan.avaliacaodesenvolvedorbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import paulocalderan.avaliacaodesenvolvedorbackend.domain.pessoa.Pessoa;
import paulocalderan.avaliacaodesenvolvedorbackend.request.PessoaRequest;
import paulocalderan.avaliacaodesenvolvedorbackend.response.PessoaResponse;

import java.util.function.Function;

@Mapper
public interface PessoaMapper extends Function<PessoaRequest, Pessoa> {

    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "dataDeNascimento", target = "dataDeNascimento")
    @Mapping(source = "endereco.logradouro", target = "endereco.logradouro")
    @Mapping(source = "endereco.cep", target = "endereco.cep")
    @Mapping(source = "endereco.numero", target = "endereco.numero")
    @Mapping(source = "endereco.cidade", target = "endereco.cidade")
    PessoaResponse toGetResponse(Pessoa pessoa);

    void update(PessoaRequest pessoaRequest, @MappingTarget Pessoa pessoa);

    Pessoa apply(PessoaRequest pessoaRequest);

}
