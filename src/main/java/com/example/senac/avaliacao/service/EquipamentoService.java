package com.example.senac.avaliacao.service;

import com.example.senac.avaliacao.exception.ConflictException;
import com.example.senac.avaliacao.exception.ResourceNotFoundException;
import com.example.senac.avaliacao.model.Equipamento;
import com.example.senac.avaliacao.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository repository;

    public List<Equipamento> listarTodos() {
        return repository.findAll();
    }

    public Equipamento buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipamento não encontrado com o id " + id));
    }

    public Equipamento criar(Equipamento equipamento) {
        if (repository.existsByNumeroPatrimonio(equipamento.getNumeroPatrimonio())) {
            throw new ConflictException("Já existe equipamento cadastrado com o número de patrimônio fornecido.");
        }
        return repository.save(equipamento);
    }

    public Equipamento atualizar(Long id, Equipamento equipamentoAtualizado) {
        Equipamento existente = buscarPorId(id);

        if (!existente.getNumeroPatrimonio().equals(equipamentoAtualizado.getNumeroPatrimonio()) &&
                repository.existsByNumeroPatrimonio(equipamentoAtualizado.getNumeroPatrimonio())) {
            throw new ConflictException("Já existe equipamento cadastrado com o número de patrimônio fornecido.");
        }

        existente.setNome(equipamentoAtualizado.getNome());
        existente.setNumeroPatrimonio(equipamentoAtualizado.getNumeroPatrimonio());
        existente.setCategoria(equipamentoAtualizado.getCategoria());
        existente.setQuantidade(equipamentoAtualizado.getQuantidade());

        return repository.save(existente);
    }

    public void excluir(Long id) {
        Equipamento existente = buscarPorId(id);
        repository.delete(existente);
    }
}