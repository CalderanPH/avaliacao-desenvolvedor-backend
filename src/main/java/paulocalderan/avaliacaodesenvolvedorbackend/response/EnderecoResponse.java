package paulocalderan.avaliacaodesenvolvedorbackend.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import paulocalderan.avaliacaodesenvolvedorbackend.domain.endereco.Endereco;

@Data
@NoArgsConstructor
public class EnderecoResponse {

    private String logradouro;
    private String cep;
    private int numero;
    private String cidade;

    public EnderecoResponse(Endereco endereco) {
        this.logradouro = endereco.getLogradouro();
        this.cep = endereco.getCep();
        this.numero = endereco.getNumero();
        this.cidade = endereco.getCidade();
    }

}