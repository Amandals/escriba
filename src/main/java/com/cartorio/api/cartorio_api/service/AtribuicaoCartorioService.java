package com.cartorio.api.cartorio_api.service;

import com.cartorio.api.cartorio_api.entity.AtribuicaoCartorio;
import com.cartorio.api.cartorio_api.repository.AtribuicaoCartorioRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AtribuicaoCartorioService {

    private final AtribuicaoCartorioRepository atribuicaoCartorioRepository;

    public AtribuicaoCartorioService(AtribuicaoCartorioRepository atribuicaoCartorioRepository) {
        this.atribuicaoCartorioRepository = atribuicaoCartorioRepository;
    }

    public void validaNomeDuplicado(AtribuicaoCartorio atribuicaoCartorio) {
        atribuicaoCartorioRepository.findByNome(atribuicaoCartorio.getNome())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Nome já informado no registro com código " + existing.getId());
                });
    }

    public void validaIdDuplicado(AtribuicaoCartorio atribuicaoCartorio) {
        if (atribuicaoCartorioRepository.existsById(atribuicaoCartorio.getId())) {
            throw new IllegalArgumentException("Registro já cadastrado");
        }
    }

    @Transactional
    public void delete(String id) {
        AtribuicaoCartorio atribuicaoCartorio = atribuicaoCartorioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cartório não encontrado"));
        if (!atribuicaoCartorio.getNome().isEmpty()) {
            throw new IllegalArgumentException("Registro utilizado em outro cadastro.");
        }
        atribuicaoCartorioRepository.delete(atribuicaoCartorio);
    }

    @Transactional
    public AtribuicaoCartorio save(AtribuicaoCartorio atribuicaoCartorio) {
        validaNomeDuplicado(atribuicaoCartorio);
        validaIdDuplicado(atribuicaoCartorio);
        return atribuicaoCartorioRepository.save(atribuicaoCartorio);
    }

    @Transactional(readOnly = true)
    public AtribuicaoCartorio findById(String id) {
        return atribuicaoCartorioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cartório não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<AtribuicaoCartorio> findAll(int pagina, int size) {
        Pageable pageable = PageRequest.of(pagina, 10);
        return atribuicaoCartorioRepository.findAll(pageable).getContent();
    }

}
