package com.cartorio.api.cartorio_api.repository;

import com.cartorio.api.cartorio_api.entity.SituacaoCartorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SituacaoCartorioRepository extends JpaRepository<SituacaoCartorio, String> {

    Optional<SituacaoCartorio> findByNome(String nome);
    Optional<SituacaoCartorio> findById(String id);

}
