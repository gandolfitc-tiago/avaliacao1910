package com.example.senac.avaliacao.repository;

import com.example.senac.avaliacao.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    boolean existsByNumeroPatrimonio(String numeroPatrimonio);
}