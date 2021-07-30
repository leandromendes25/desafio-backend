package com.fcamara.desafiobackend.repository;

import com.fcamara.desafiobackend.model.Estabelecimento;
import com.fcamara.desafiobackend.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {

  Optional<Veiculo> findByPlaca(String placa);
  Optional<Veiculo> findByPlacaAndEstabelecimento(String placa, Estabelecimento estabelecimento);
}
