package com.cartorio.api.cartorio_api.repository;

import com.cartorio.api.cartorio_api.entity.AtribuicaoCartorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AtribuicaoCartorioRepository extends JpaRepository<AtribuicaoCartorio, String> {

    Optional<AtribuicaoCartorio> findByNome(String nome);

}
