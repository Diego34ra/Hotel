package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.model.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario create(Usuario usuario);
    List<Usuario> findAll(Long id);
    Usuario findById(Long id);
    Usuario update(Long id,Usuario usuario);
    void delete(Long id);
}
