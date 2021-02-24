package br.com.academicwebapi.exceptions;

public class AlunoNaoEncontradoException extends RuntimeException {

    public AlunoNaoEncontradoException(String message) {
        super(message);
    }

    public AlunoNaoEncontradoException(String message, Throwable reason) {
        super(message, reason);
    }

}
