package com.cartorio.api.cartorio_api.controller;

import com.cartorio.api.cartorio_api.entity.AtribuicaoCartorio;
import com.cartorio.api.cartorio_api.service.AtribuicaoCartorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/atribuicao")
public class AtribuicaoCartorioController {

    @Autowired
    private AtribuicaoCartorioService atribuicaoCartorioService;

    @GetMapping("/{id}")
    public ResponseEntity<AtribuicaoCartorio> findById(@PathVariable String id) {
        return ResponseEntity.ok(atribuicaoCartorioService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AtribuicaoCartorio>> findAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(atribuicaoCartorioService.findAll(page, size));
    }

    @PostMapping
    public ResponseEntity<AtribuicaoCartorio> save(@RequestBody AtribuicaoCartorio atribuicaoCartorio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(atribuicaoCartorioService.save(atribuicaoCartorio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        atribuicaoCartorioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
