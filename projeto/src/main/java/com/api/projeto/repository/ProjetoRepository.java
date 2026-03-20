package com.api.projeto.repository;

import com.api.projeto.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

    // Verifica duplicidade de código ao criar
    boolean existsByCodigo(String codigo);

    // Verifica duplicidade de código ao atualizar (ignora o próprio registro)
    boolean existsByCodigoAndIdNot(String codigo, Integer id);
}
