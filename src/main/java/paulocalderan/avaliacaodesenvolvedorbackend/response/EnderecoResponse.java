package paulocalderan.avaliacaodesenvolvedorbackend.response;

import lombok.Data;

@Data
public class EnderecoResponse {

    private String logradouro;
    private int cep;
    private int numero;
    private String cidade;

}