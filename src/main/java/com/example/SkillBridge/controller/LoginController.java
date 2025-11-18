package com.example.SkillBridge.controller;

import com.example.SkillBridge.model.Usuario;
import com.example.SkillBridge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final UsuarioRepository userRepository;

    // Injeção do repositório através do construtor
    @Autowired
    public LoginController(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Login (simples, sem autenticação)
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Usuario user) {
        // Usar a instância para chamar o metodo
        Optional<Usuario> foundUser = userRepository.findByEmail(user.getEmail());

        // Verificar se o usuário existe
        if (foundUser.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuário não encontrado!");
        }

        // Verificar a senha
        if (!foundUser.get().getSenhaHash().equals(user.getSenhaHash())) {
            return ResponseEntity.badRequest().body("Senha incorreta!");
        }

        return ResponseEntity.ok("Login bem-sucedido!");
    }
}