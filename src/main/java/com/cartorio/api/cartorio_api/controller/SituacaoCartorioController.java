package com.cartorio.api.cartorio_api.controller;

import com.cartorio.api.cartorio_api.entity.SituacaoCartorio;
import com.cartorio.api.cartorio_api.service.SituacaoCartorioService;
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
@RequestMapping("/api/situacao")
public class SituacaoCartorioController {

    @Autowired
    private SituacaoCartorioService situacaoCartorioService;

    @GetMapping("/{id}")
    public ResponseEntity<SituacaoCartorio> get(@PathVariable String id) {
        return ResponseEntity.ok(situacaoCartorioService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SituacaoCartorio>> list(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(situacaoCartorioService.findAll(page, size));
    }

    @PostMapping
    public ResponseEntity<SituacaoCartorio> create(@RequestBody SituacaoCartorio situacaoCartorio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(situacaoCartorioService.save(situacaoCartorio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        situacaoCartorioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
