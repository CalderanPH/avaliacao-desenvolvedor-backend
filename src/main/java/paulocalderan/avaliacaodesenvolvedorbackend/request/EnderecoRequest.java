package paulocalderan.avaliacaodesenvolvedorbackend.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EnderecoRequest {

    private Long id;

    @NotNull(message = "O logradouro deve ser informado.")
    private String logradouro;

    @NotNull(message = "O cep deve ser informado.")
    private String cep;

    @NotNull(message = "O número deve ser informado.")
    private int numero;

    @NotNull(message = "A cidade deve ser informado.")
    private String cidade;

}