package br.com.academicwebapi.exceptions;

public class DisciplinaNaoEncontradaException extends RuntimeException {

    public DisciplinaNaoEncontradaException(String message) {
        super(message);
    }

    public DisciplinaNaoEncontradaException(String message, Throwable reason) {
        super(message, reason);
    }

}
