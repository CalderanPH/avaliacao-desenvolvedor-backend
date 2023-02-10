package paulocalderan.avaliacaodesenvolvedorbackend.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class PessoaRequest {

    private Long id;

    @NotNull(message = "O nome deve ser informado.")
    private String nome;

    @NotNull(message = "A data de nascimento deve ser informado.")
    private LocalDate dataDeNascimento;

    @NotNull(message = "O id do endereço deve ser informado.")
    private EnderecoRequest endereco;

}
