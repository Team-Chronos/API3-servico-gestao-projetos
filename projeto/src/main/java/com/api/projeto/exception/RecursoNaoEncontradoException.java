package com.api.projeto.exception;

// Lançada quando um recurso buscado por ID não existe no banco
public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String message) {
        super(message);
    }
}
