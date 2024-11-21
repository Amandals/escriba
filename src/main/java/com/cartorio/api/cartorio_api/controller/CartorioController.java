package com.cartorio.api.cartorio_api.controller;

import com.cartorio.api.cartorio_api.entity.Cartorio;
import com.cartorio.api.cartorio_api.service.CartorioService;
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
@RequestMapping("/api/cartorios")
public class CartorioController {

    @Autowired
    private CartorioService cartorioService;

    @GetMapping("/{id}")
    public ResponseEntity<Cartorio> get(@PathVariable Integer id) {
        return ResponseEntity.ok(cartorioService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Cartorio>> list(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(cartorioService.findAll(page, size));
    }

    @PostMapping
    public ResponseEntity<Cartorio> create(@RequestBody Cartorio cartorio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartorioService.save(cartorio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        cartorioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
