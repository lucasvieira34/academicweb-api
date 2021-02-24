package br.com.academicwebapi.service;

import br.com.academicwebapi.dto.CadastroProfessorDto;
import br.com.academicwebapi.exceptions.AlunoExistenteException;
import br.com.academicwebapi.exceptions.ProfessorExistenteException;
import br.com.academicwebapi.models.Professor;
import br.com.academicwebapi.models.Role;
import br.com.academicwebapi.models.Usuario;
import br.com.academicwebapi.repository.ProfessorRepository;
import br.com.academicwebapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CadastroProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    UsuarioRepository usuarioRepository;

    public CadastroProfessorDto salvarProfessor(CadastroProfessorDto professorDto) {

        Role role = roleService.buscarRolePeloNome("ROLE_PROFESSOR");

        var passwordEncoder = new BCryptPasswordEncoder();
        String encodedPass = passwordEncoder.encode(professorDto.getSenha());

        var usuario = new Usuario();
        usuario.setEmail(professorDto.getEmail());
        usuario.setLogin(professorDto.getLogin());
        usuario.setSenha(encodedPass);
        usuario.setRole(role);
        usuario = usuarioRepository.save(usuario);

        var professor = new Professor();
        professor.setMatricula(professorDto.getMatricula());
        professor.setNome(professorDto.getNome());
        professor.setSobrenome(professorDto.getSobrenome());
        professor.setCpf(professorDto.getCpf());
        professor.setDataNascimento(professorDto.getDataNascimento());
        professor.setSalario(professorDto.getSalario());
        professor.setUsuario(usuario);

        if(professor.getId() != null) {
            if(professorRepository.findById(professor.getId()).isPresent()){
                throw new ProfessorExistenteException("O professor já existe.");
            }
        }
        if (professor.getCpf() != null) {
            if(professorRepository.findByCpf(professor.getCpf()).isPresent()){
                throw new ProfessorExistenteException("O CPF do professor já existe.");
            }
        }

        professor = professorRepository.save(professor);

        return professorDto;
    }

}
