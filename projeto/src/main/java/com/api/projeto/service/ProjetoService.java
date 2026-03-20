package com.api.projeto.service;

import com.api.projeto.dto.request.ProjetoRequest;
import com.api.projeto.dto.request.ProjetoUsuarioRequest;
import com.api.projeto.dto.response.ProjetoResponse;
import com.api.projeto.dto.response.ProjetoUsuarioResponse;
import java.util.List;

public interface ProjetoService {
    List<ProjetoResponse> listarTodos();
    ProjetoResponse buscarPorId(Integer id);
    ProjetoResponse criar(ProjetoRequest request);
    ProjetoResponse atualizar(Integer id, ProjetoRequest request);
    void deletar(Integer id);
    ProjetoUsuarioResponse adicionarUsuario(Integer projetoId, ProjetoUsuarioRequest request);
    void removerUsuario(Integer projetoId, Integer usuarioId);
    List<ProjetoUsuarioResponse> listarUsuarios(Integer projetoId);
}
