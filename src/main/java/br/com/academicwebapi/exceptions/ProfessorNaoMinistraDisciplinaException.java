package br.com.academicwebapi.exceptions;

public class ProfessorNaoMinistraDisciplinaException extends RuntimeException {

    public ProfessorNaoMinistraDisciplinaException(String message) {
        super(message);
    }

    public ProfessorNaoMinistraDisciplinaException(String message, Throwable reason) {
        super(message, reason);
    }

}
