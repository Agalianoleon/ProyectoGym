package com.empresa.curso.ProyectoGym.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.empresa.curso.ProyectoGym.dto.LoginForm;
import com.empresa.curso.ProyectoGym.model.User;
import com.empresa.curso.ProyectoGym.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Mostrar formulario de login
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        if (!model.containsAttribute("login")) {
            model.addAttribute("login", new LoginForm());
        }
        return "login";
    }

    // Procesar login
    @PostMapping("/login")
    public String login(@ModelAttribute("login") LoginForm form,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        Optional<User> userOpt = userRepository.findByEmail(form.getEmail());

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // De momento comparación en texto plano
            if (user.getPassword().equals(form.getPassword())) {

                // Guardar en sesión
                session.setAttribute("user", user);

                // Redirigir a la home protegida o perfil
                return "redirect:/users/" + user.getId();
                
            }
            System.out.println("Login ok.Session id: " + session.getId());
        }
        
        redirectAttributes.addFlashAttribute("login", form);
        redirectAttributes.addFlashAttribute("loginError", "Email o contraseña incorrectos");
        return "redirect:/login";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:login?logout";
    }

}
