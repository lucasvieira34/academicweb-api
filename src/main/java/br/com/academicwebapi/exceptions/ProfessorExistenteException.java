package br.com.academicwebapi.exceptions;

public class ProfessorExistenteException extends RuntimeException {

    public ProfessorExistenteException(String message) {
        super(message);
    }

    public ProfessorExistenteException(String message, Throwable reason) {
        super(message, reason);
    }

}
