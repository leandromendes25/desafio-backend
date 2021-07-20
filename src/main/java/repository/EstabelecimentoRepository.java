package repository;

import model.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento,Long> {
    List<Estabelecimento> findByNomeEstabelecimento(String nomeEstabelecimento);
}
