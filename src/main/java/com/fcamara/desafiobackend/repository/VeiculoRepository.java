package com.fcamara.desafiobackend.repository;

import com.fcamara.desafiobackend.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {


}
