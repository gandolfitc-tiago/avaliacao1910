package com.example.senac.avaliacao.controller;

import com.example.senac.avaliacao.model.Equipamento;
import com.example.senac.avaliacao.service.EquipamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoService service;

    // POST /equipamentos -> 201 Created
    @PostMapping
    public ResponseEntity<Equipamento> criar(@Valid @RequestBody Equipamento equipamento) {
        Equipamento novo = service.criar(equipamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    // GET /equipamentos -> 200 OK
    @GetMapping
    public ResponseEntity<List<Equipamento>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // GET /equipamentos/{id} -> 200 OK ou 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // PUT /equipamentos/{id} -> 200 OK, 404 ou 409
    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> atualizar(@PathVariable Long id, @Valid @RequestBody Equipamento equipamento) {
        Equipamento atualizado = service.atualizar(id, equipamento);
        return ResponseEntity.ok(atualizado);
    }

    // DELETE /equipamentos/{id} -> 204 No Content ou 404 Not Found
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}