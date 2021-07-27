package com.fcamara.desafiobackend.repository;

import com.fcamara.desafiobackend.model.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento,Long> {

 Optional<Estabelecimento> findByCnpj(String cnpj);
}
