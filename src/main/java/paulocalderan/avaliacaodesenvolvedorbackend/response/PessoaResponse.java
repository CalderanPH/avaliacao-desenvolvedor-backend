package paulocalderan.avaliacaodesenvolvedorbackend.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaResponse {

    private String nome;
    private LocalDate dataDeNascimento;
    private EnderecoResponse endereco;

}