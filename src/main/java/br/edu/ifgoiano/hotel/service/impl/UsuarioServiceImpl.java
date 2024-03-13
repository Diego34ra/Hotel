package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.model.Endereco;
import br.edu.ifgoiano.hotel.model.Usuario;
import br.edu.ifgoiano.hotel.model.UsuarioPapel;
import br.edu.ifgoiano.hotel.repository.UsuarioRepository;
import br.edu.ifgoiano.hotel.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public Usuario create(Usuario usuario) {
        Endereco endereco = new Endereco();
        endereco.setBairro("teste bairro");
        endereco.setLogradouro("teste logradouro");
        Usuario usuarioCreate = new Usuario();
        usuarioCreate.setNome("Teste");
        usuarioCreate.setEmail("Teste@gmail.com");
//        usuarioCreate.setPapel();
        usuarioCreate.setEndereco(endereco);

        return usuarioRepository.save(usuarioCreate);
    }
}
