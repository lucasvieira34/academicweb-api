package br.com.academicwebapi.handler;

import br.com.academicwebapi.exceptions.*;
import br.com.academicwebapi.models.DetalhesErro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(AlunoNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handleAlunoNaoEncontradoException(AlunoNaoEncontradoException e, HttpServletRequest request) {

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404l);
        erro.setTitulo(e.getMessage());
        erro.setMensagemDesenvolvedor("http://erros.academicweb.com/404");
        erro.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(ProfessorNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handleProfessorNaoEncontradoException(ProfessorNaoEncontradoException e, HttpServletRequest request) {

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404l);
        erro.setTitulo(e.getMessage());
        erro.setMensagemDesenvolvedor("http://erros.academicweb.com/404");
        erro.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(ProfessorNaoMinistraDisciplinaException.class)
    public ResponseEntity<DetalhesErro> handleProfessorNaoMinistraDisciplinaException(ProfessorNaoMinistraDisciplinaException e, HttpServletRequest request) {

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(406l);
        erro.setTitulo(e.getMessage());
        erro.setMensagemDesenvolvedor("http://erros.academicweb.com/406");
        erro.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(erro);
    }

    @ExceptionHandler(DisciplinaNaoEncontradaException.class)
    public ResponseEntity<DetalhesErro> handleDisciplinaNaoEncontradaException(DisciplinaNaoEncontradaException e, HttpServletRequest request) {

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404l);
        erro.setTitulo(e.getMessage());
        erro.setMensagemDesenvolvedor("http://erros.academicweb.com/404");
        erro.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(AlunoExistenteException.class)
    public ResponseEntity<DetalhesErro> handleAlunoExistenteException(AlunoExistenteException e, HttpServletRequest request) {

        var erro = new DetalhesErro();
        erro.setStatus(409l);
        erro.setTitulo(e.getMessage());
        erro.setMensagemDesenvolvedor("http://erros.academicweb.com/409");
        erro.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(ProfessorExistenteException.class)
    public ResponseEntity<DetalhesErro> handleProfessorExistenteException(ProfessorExistenteException e, HttpServletRequest request) {

        var erro = new DetalhesErro();
        erro.setStatus(409l);
        erro.setTitulo(e.getMessage());
        erro.setMensagemDesenvolvedor("http://erros.academicweb.com/409");
        erro.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

}
