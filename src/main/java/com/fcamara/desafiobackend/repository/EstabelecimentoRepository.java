package com.fcamara.desafiobackend.repository;

import com.fcamara.desafiobackend.model.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento,Long> {

}
