package br.com.academicwebapi.service;

import br.com.academicwebapi.models.Usuario;
import br.com.academicwebapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        var usuario = usuarioRepository.findByLogin(login);

        if(usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), true, true, true, usuario.getAuthorities());
    }
}
