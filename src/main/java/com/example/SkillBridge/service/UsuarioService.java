package com.example.SkillBridge.service;

import com.example.SkillBridge.model.Usuario;
import com.example.SkillBridge.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}