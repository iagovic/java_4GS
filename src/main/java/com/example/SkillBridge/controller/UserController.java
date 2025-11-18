package com.example.SkillBridge.controller;

import com.example.SkillBridge.dto.VagaCompatibilidadeDTO;
import com.example.SkillBridge.model.Usuario;
import com.example.SkillBridge.repository.CompatibilidadeRepository;
import com.example.SkillBridge.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/user")
public class UserController {

    private final UsuarioService usuarioService;
    private final CompatibilidadeRepository compatibilidadeRepository;

    public UserController(UsuarioService usuarioService,
                          CompatibilidadeRepository compatibilidadeRepository) {
        this.usuarioService = usuarioService;
        this.compatibilidadeRepository = compatibilidadeRepository;
    }

    @GetMapping("/dashboard/{usuarioId}")
    public String dashboard(@PathVariable Long usuarioId, Model model) {

        Usuario usuario = usuarioService.buscarPorId(usuarioId);

        if (usuario == null) {
            return "error/404";
        }

        // Todas compatibilidades do usuário
        var compatibilidades = compatibilidadeRepository.findByUsuarioId(usuarioId);

        // Melhor vaga
        var melhorVaga = compatibilidades.stream().findFirst().orElse(null);

        model.addAttribute("usuario", usuario);
        model.addAttribute("compatibilidades", compatibilidades);
        model.addAttribute("melhorVaga", melhorVaga);

        return "user/dashboard";
    }

    @GetMapping("/perfil/{usuarioId}")
    public String perfil(@PathVariable Long usuarioId, Model model) {

        Usuario usuario = usuarioService.buscarPorId(usuarioId);

        if (usuario == null) {
            return "error/404";
        }

        model.addAttribute("usuario", usuario);
        return "user/perfil";
    }
}