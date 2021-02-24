package br.com.academicwebapi.service;

import br.com.academicwebapi.models.AlunoDisciplina;
import br.com.academicwebapi.repository.AlunoDisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoDisciplinaService {

    @Autowired
    private AlunoDisciplinaRepository alunoDisciplinaRepository;

    public void salvarAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
        alunoDisciplinaRepository.save(alunoDisciplina);
    }

}
