package com.cartorio.api.cartorio_api.service;

import com.cartorio.api.cartorio_api.entity.Cartorio;
import com.cartorio.api.cartorio_api.exception.BusinessException;
import com.cartorio.api.cartorio_api.exception.ErrorCode;
import com.cartorio.api.cartorio_api.repository.CartorioRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartorioService {

    private final CartorioRepository cartorioRepository;

    public CartorioService(CartorioRepository cartorioRepository) {
        this.cartorioRepository = cartorioRepository;
    }

    public void validaNomeDuplicado(Cartorio cartorio) {
        cartorioRepository.findByNome(cartorio.getNome())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Nome já informado no registro com código " + existing.getId());
                });
    }

    @Transactional
    public void delete(Integer id) {
        cartorioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cartório com ID " + id + " não encontrado."));

        cartorioRepository.deleteById(id);
    }

    @Transactional
    public Cartorio save(Cartorio cartorio) {
        if (cartorio.getId() == null) {
            validaNomeDuplicado(cartorio);
        } else {
            validaNomeDuplicado(cartorio);
            cartorioRepository.findById(cartorio.getId())
                    .orElseThrow(() -> new BusinessException("Cartório com ID " + cartorio.getId() + " não encontrado."));
        }
        return cartorioRepository.save(cartorio);
    }

    @Transactional(readOnly = true)
    public Cartorio findById (Integer id){
        return cartorioRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.REGISTRO_NAO_ENCONTRADO));
    }

    @Transactional(readOnly = true)
    public List<Cartorio> findAll ( int pagina, int size){
        Pageable pageable = PageRequest.of(pagina, 10);
        return cartorioRepository.findAll(pageable).getContent();
    }
}
