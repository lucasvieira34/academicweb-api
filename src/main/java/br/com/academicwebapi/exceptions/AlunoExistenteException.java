package br.com.academicwebapi.exceptions;

public class AlunoExistenteException extends RuntimeException {

    public AlunoExistenteException(String message) {
        super(message);
    }

    public AlunoExistenteException(String message, Throwable reason) {
        super(message, reason);
    }

}
