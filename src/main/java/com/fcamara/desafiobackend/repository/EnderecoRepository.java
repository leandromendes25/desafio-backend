package com.fcamara.desafiobackend.repository;

import com.fcamara.desafiobackend.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
