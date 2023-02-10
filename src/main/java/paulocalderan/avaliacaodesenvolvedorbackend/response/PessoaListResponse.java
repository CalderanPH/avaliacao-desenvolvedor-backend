package paulocalderan.avaliacaodesenvolvedorbackend.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PessoaListResponse {

    private List<PessoaResponse> pessoas;

}
