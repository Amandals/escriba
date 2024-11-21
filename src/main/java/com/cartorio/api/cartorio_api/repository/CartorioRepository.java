package com.cartorio.api.cartorio_api.repository;

import com.cartorio.api.cartorio_api.entity.Cartorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartorioRepository extends JpaRepository<Cartorio, Integer> {

    Optional<Cartorio> findByNome(String nome);

    @Query("SELECT COUNT(c) > 0 FROM Cartorio c WHERE c.situacaoCartorio.id = :id")
    Boolean isReferencedInCartorio(String id);
}
