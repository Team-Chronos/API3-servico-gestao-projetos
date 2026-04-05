package com.api.projeto.controller;

import com.api.projeto.dto.request.ProjetoRequest;
import com.api.projeto.dto.request.ProjetoUsuarioRequest;
import com.api.projeto.dto.response.ProjetoResponse;
import com.api.projeto.dto.response.ProjetoUsuarioResponse;
import com.api.projeto.service.ProjetoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins ="http://localhost:5173")
@RestController
@RequestMapping("/projetos")
@RequiredArgsConstructor
public class ProjetoController {

    private final ProjetoService projetoService;

    @GetMapping
    public ResponseEntity<List<ProjetoResponse>> listarTodos() {
        return ResponseEntity.ok(projetoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoResponse> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(projetoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProjetoResponse> criar(@RequestBody @Valid ProjetoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoService.criar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetoResponse> atualizar(@PathVariable Integer id,
                                                      @RequestBody @Valid ProjetoRequest request) {
        return ResponseEntity.ok(projetoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        projetoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Gerenciamento de membros do projeto

    @GetMapping("/{id}/usuarios")
    public ResponseEntity<List<ProjetoUsuarioResponse>> listarUsuarios(@PathVariable Integer id) {
        return ResponseEntity.ok(projetoService.listarUsuarios(id));
    }

    @PostMapping("/{id}/usuarios")
    public ResponseEntity<ProjetoUsuarioResponse> adicionarUsuario(
            @PathVariable Integer id,
            @RequestBody @Valid ProjetoUsuarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoService.adicionarUsuario(id, request));
    }

    @DeleteMapping("/{projetoId}/usuarios/{usuarioId}")
    public ResponseEntity<Void> removerUsuario(@PathVariable Integer projetoId,
                                               @PathVariable Integer usuarioId) {
        projetoService.removerUsuario(projetoId, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
