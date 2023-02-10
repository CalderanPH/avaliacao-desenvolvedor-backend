package paulocalderan.avaliacaodesenvolvedorbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import paulocalderan.avaliacaodesenvolvedorbackend.domain.endereco.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
