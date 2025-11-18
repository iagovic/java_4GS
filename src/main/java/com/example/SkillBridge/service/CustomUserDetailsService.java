package com.example.SkillBridge.service;

// Login via formulário /login.

// Só Usuário ADMIN pode criar/editar/excluir motos, USER só pode acessar páginas seguras mas não editar motos.

import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SkillBridge.repository.UsuarioRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService{
    private final UsuarioRepository userRepo;

    public CustomUserDetailsService (UsuarioRepository userRepo){
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("[INFO] Iniciando busca no banco de dados para o usuário: " + email);
        var usuario = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
        System.out.println("[SUCCESS] Usuário carregado com sucesso do banco: " + usuario.getEmail());

        // Log da senha e do hash armazenado
        System.out.println("Senha inserida: admin123");
        System.out.println("Hash armazenado no banco: " + usuario.getEmail());
        return new User(
                usuario.getEmail(),
                usuario.getSenhaHash(),
                List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRole().name()))
        );
    }
}