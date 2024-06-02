package ru.devolw.tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.devolw.tacos.repository.ClientRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private ClientRepository clientRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(ClientRepository clientRepo, PasswordEncoder passwordEncoder) {
        this.clientRepo = clientRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        clientRepo.save(form.toClient(passwordEncoder));
        return "redirect:/login";
    }
}