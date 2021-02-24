package br.com.academicwebapi.exceptions;

public class ProfessorNaoEncontradoException extends RuntimeException {

    public ProfessorNaoEncontradoException(String message) {
        super(message);
    }

    public ProfessorNaoEncontradoException(String message, Throwable reason) {
        super(message, reason);
    }

}
