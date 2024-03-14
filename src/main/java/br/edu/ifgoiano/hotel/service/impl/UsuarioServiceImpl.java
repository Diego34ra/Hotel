package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.controller.exception.ResourceNotFoundException;
import br.edu.ifgoiano.hotel.model.Endereco;
import br.edu.ifgoiano.hotel.model.Usuario;
import br.edu.ifgoiano.hotel.model.UsuarioPapel;
import br.edu.ifgoiano.hotel.repository.UsuarioRepository;
import br.edu.ifgoiano.hotel.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public Usuario create(Usuario usuario) {
        if(usuario.getPapel() == null)
            usuario.setPapel(UsuarioPapel.getPadrao());
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum cliente com esse Id."));
    }

    @Override
    public Usuario update(Long id, Usuario usuario) {
        return null;
    }

    @Override
    public void delete(Long id) {
        var usuarioDelete = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum cliente com esse Id."));
        usuarioRepository.delete(usuarioDelete);
    }
}
