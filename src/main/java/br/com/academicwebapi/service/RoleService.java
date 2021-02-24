package br.com.academicwebapi.service;

import br.com.academicwebapi.models.Role;
import br.com.academicwebapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role buscarRolePeloNome(String nome) {
        return roleRepository.findByNome(nome);
    }

}
