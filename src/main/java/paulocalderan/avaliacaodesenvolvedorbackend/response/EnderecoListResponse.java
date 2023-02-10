package paulocalderan.avaliacaodesenvolvedorbackend.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class EnderecoListResponse {

    private List<EnderecoResponse> enderecos;

}
