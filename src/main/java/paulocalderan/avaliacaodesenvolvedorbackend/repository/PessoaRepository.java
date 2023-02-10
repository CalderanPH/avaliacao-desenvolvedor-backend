package paulocalderan.avaliacaodesenvolvedorbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import paulocalderan.avaliacaodesenvolvedorbackend.domain.pessoa.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
