package br.com.wtldigital.codetest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.wtldigital.codetest.DTO.Login;
import br.com.wtldigital.codetest.model.Pessoa;
import br.com.wtldigital.codetest.service.TokenService;

@Controller
@RequestMapping("/account")
public class AuthController {

    public String storageSession;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public String criarPessoa() {
        return "pessoa/login";
    }

    @PostMapping("/login")
    public String login(Login login) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(login.cpf(),
                        login.password());

        Authentication authenticate = this.authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);

        var usuario = (Pessoa) authenticate.getPrincipal();

        this.storageSession = tokenService.gerarToken(usuario);

        return "redirect:/automoveis/create";
    }

    @GetMapping("/logout")
    public String logout() {
        this.storageSession = null;
        return "redirect:/account";
    }
}
