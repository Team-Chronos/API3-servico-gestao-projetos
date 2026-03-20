package com.api.projeto.repository;

import com.api.projeto.entity.ProjetoUsuario;
import com.api.projeto.entity.ProjetoUsuarioId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjetoUsuarioRepository extends JpaRepository<ProjetoUsuario, ProjetoUsuarioId> {

    // Busca todos os membros de um projeto específico
    List<ProjetoUsuario> findByIdProjetoId(Integer projetoId);
}
