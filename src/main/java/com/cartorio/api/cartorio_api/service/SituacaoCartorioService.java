package com.cartorio.api.cartorio_api.service;

import com.cartorio.api.cartorio_api.entity.SituacaoCartorio;
import com.cartorio.api.cartorio_api.exception.BusinessException;
import com.cartorio.api.cartorio_api.exception.ErrorCode;
import com.cartorio.api.cartorio_api.repository.CartorioRepository;
import com.cartorio.api.cartorio_api.repository.SituacaoCartorioRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SituacaoCartorioService {

    private final SituacaoCartorioRepository situacaoCartorioRepository;
    private CartorioRepository cartorioRepository;

    public SituacaoCartorioService(SituacaoCartorioRepository situacaoCartorioRepository, CartorioRepository cartorioRepository) {
        this.situacaoCartorioRepository = situacaoCartorioRepository;
        this.cartorioRepository = cartorioRepository;
    }

    @Transactional(readOnly = true)
    public void validaNomeDuplicado(SituacaoCartorio situacaoCartorio) {
        situacaoCartorioRepository.findByNome(situacaoCartorio.getNome())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Nome já informado no registro com código " + existing.getId());
                });
    }

    @Transactional(readOnly = true)
    public void validaIdDuplicado(SituacaoCartorio situacaoCartorio) {
        if (situacaoCartorioRepository.existsById(situacaoCartorio.getId())) {
            throw new BusinessException(ErrorCode.REGISTRO_JA_CADASTRADO);
        }
    }

    @Transactional
    public void delete(String id) {
        SituacaoCartorio situacaoCartorio = situacaoCartorioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Situação não encontrada"));

        boolean isReferenced = cartorioRepository.isReferencedInCartorio(situacaoCartorio.getId());

        if (isReferenced) {
            throw new BusinessException(ErrorCode.REGISTRO_JA_USADO);
        }

        situacaoCartorioRepository.delete(situacaoCartorio);
    }

    @Transactional
    public SituacaoCartorio save(SituacaoCartorio cartorio) {
        validaNomeDuplicado(cartorio);
        validaIdDuplicado(cartorio);
        return situacaoCartorioRepository.save(cartorio);
    }

    @Transactional(readOnly = true)
    public SituacaoCartorio findById(String id) {
        return situacaoCartorioRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.REGISTRO_NAO_ENCONTRADO));
    }

    @Transactional(readOnly = true)
    public List<SituacaoCartorio> findAll(int pagina, int size) {
        Pageable pageable = PageRequest.of(pagina, 10);
        return situacaoCartorioRepository.findAll(pageable).getContent();
    }

}
