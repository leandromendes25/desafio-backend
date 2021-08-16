package com.fcamara.desafiobackend.repository;

import com.fcamara.desafiobackend.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
