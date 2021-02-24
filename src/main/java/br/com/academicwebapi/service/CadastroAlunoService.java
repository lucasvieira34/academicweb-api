package br.com.academicwebapi.service;

import br.com.academicwebapi.dto.CadastroAlunoDto;
import br.com.academicwebapi.exceptions.AlunoExistenteException;
import br.com.academicwebapi.models.*;
import br.com.academicwebapi.repository.AlunoRepository;
import br.com.academicwebapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CadastroAlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    AlunoDisciplinaService alunoDisciplinaService;

    public CadastroAlunoDto salvarAluno(CadastroAlunoDto alunoDto) {

        Role role = roleService.buscarRolePeloNome("ROLE_ALUNO");

        var passwordEncoder = new BCryptPasswordEncoder();
        String encodedPass = passwordEncoder.encode(alunoDto.getSenha());

        var usuario = new Usuario();
        usuario.setEmail(alunoDto.getEmail());
        usuario.setLogin(alunoDto.getLogin());
        usuario.setSenha(encodedPass);
        usuario.setRole(role);
        usuario = usuarioRepository.save(usuario);

        var aluno = new Aluno();
        aluno.setMatricula(alunoDto.getMatricula());
        aluno.setNome(alunoDto.getNome());
        aluno.setSobrenome(alunoDto.getSobrenome());
        aluno.setCpf(alunoDto.getCpf());
        aluno.setDataNascimento(alunoDto.getDataNascimento());
        aluno.setNomeResponsavel(alunoDto.getNomeResponsavel());
        aluno.setEmailResponsavel(alunoDto.getEmailResponsavel());
        aluno.setMensalidade(alunoDto.getMensalidade());
        aluno.setUsuario(usuario);

        if(aluno.getId() != null) {
            if(alunoRepository.findById(aluno.getId()).isPresent()){
                throw new AlunoExistenteException("O aluno já existe.");
            }
        }
        if (aluno.getCpf() != null) {
            if(alunoRepository.findByCpf(aluno.getCpf()).isPresent()){
                throw new AlunoExistenteException("O CPF do aluno já existe.");
            }
        }

        aluno = alunoRepository.save(aluno);

        var alunoDisciplina = new AlunoDisciplina();
        for (int i = 1; i < 13; i++) {
            var disciplina = new Disciplina();
            disciplina.setId((long) i);

            alunoDisciplina.setAluno(aluno);
            alunoDisciplina.setDisciplina(disciplina);

            var alunoDisciplinaPk = new AlunoDisciplinaPK(aluno.getId(), disciplina.getId());
            alunoDisciplina.setId(alunoDisciplinaPk);

            alunoDisciplinaService.salvarAlunoDisciplina(alunoDisciplina);
        }
        return alunoDto;
    }

}
