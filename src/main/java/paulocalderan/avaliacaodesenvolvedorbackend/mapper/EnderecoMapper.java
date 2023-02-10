package paulocalderan.avaliacaodesenvolvedorbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import paulocalderan.avaliacaodesenvolvedorbackend.domain.endereco.Endereco;
import paulocalderan.avaliacaodesenvolvedorbackend.request.EnderecoRequest;
import paulocalderan.avaliacaodesenvolvedorbackend.response.EnderecoResponse;

import java.util.function.Function;

@Mapper
public interface EnderecoMapper extends Function<EnderecoRequest, Endereco> {

    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    @Mapping(source = "logradouro", target = "logradouro")
    @Mapping(source = "cep", target = "cep")
    @Mapping(source = "numero", target = "numero")
    @Mapping(source = "cidade", target = "cidade")
    EnderecoResponse toGetResponse(Endereco endereco);

    void update(EnderecoRequest enderecoRequest, @MappingTarget Endereco endereco);

}