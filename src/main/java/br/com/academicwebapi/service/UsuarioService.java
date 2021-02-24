package br.com.academicwebapi.service;

import br.com.academicwebapi.models.Usuario;
import br.com.academicwebapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario usuarioPorLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }
}
