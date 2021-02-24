package br.com.academicwebapi.service;

import br.com.academicwebapi.models.Disciplina;
import br.com.academicwebapi.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Cacheable(value = "disciplinas")
    public List<Disciplina> findAll() {
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> buscarDisciplina(Long idDisciplina) {
        return disciplinaRepository.findById(idDisciplina);
    }

    public Optional<Disciplina> buscarDisciplinaNome(String nomeDisciplina) {
        return disciplinaRepository.findByNome(nomeDisciplina);
    }
}
