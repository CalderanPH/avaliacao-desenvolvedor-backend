package paulocalderan.avaliacaodesenvolvedorbackend.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import paulocalderan.avaliacaodesenvolvedorbackend.domain.pessoa.Pessoa;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PessoaResponse {

    private String nome;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDeNascimento;

    private EnderecoResponse endereco;

    public PessoaResponse(Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.dataDeNascimento = pessoa.getDataDeNascimento();
    }

}