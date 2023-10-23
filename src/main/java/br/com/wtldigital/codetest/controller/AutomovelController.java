package br.com.wtldigital.codetest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import br.com.wtldigital.codetest.model.Automovel;
import br.com.wtldigital.codetest.service.AutomovelService;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
@RequestMapping("/automoveis")
public class AutomovelController {
    @Autowired
    private AutomovelService automovelService;

    @GetMapping("/create")
    public String criarAutomovel() {
        return "automovel/create";
    }

    @GetMapping
    public String mostrarAutomoveisDoUsuario(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String cpf = userDetails.getUsername();
        List<Automovel> automovel = automovelService.listarAutomoveisPorPessoaCpf(cpf);
        model.addAttribute("automoveis", automovel);
        return "automovel/showAll";
    }

    @GetMapping("/listar")
    public String listarAutomoveis(Model model) {
        List<Automovel> automoveis = automovelService.listarAutomoveis();
        model.addAttribute("automoveis", automoveis);
        return "automovel/showAll";
    }

    @PostMapping("/proprietario")
    public String listarAutomoveisPorPessoaId(@RequestParam("cpf") String pessoaCpf, Model model) {
        List<Automovel> automoveis = automovelService.listarAutomoveisPorPessoaCpf(pessoaCpf);
        model.addAttribute("automoveis", automoveis);
        return "automovel/showAll";
    }

    @PostMapping
    public String criarAutomovel(@RequestParam("placa") String placa,
            @RequestParam("veiculo") String veiculo,
            @RequestParam("pessoa_cpf") String pessoaCpf) {
        Automovel automovel = automovelService.criarAutomovel(placa, veiculo, pessoaCpf);
        return "redirect:/automoveis/" + automovel.getId();
    }

    @GetMapping("/{id}")
    public String mostrarAutomovelPorId(@PathVariable Integer id, Model model) {
        Automovel automovel = automovelService.buscarAutomovelPorId(id);
        model.addAttribute("automovel", automovel);
        return "automovel/show";
    }

    @GetMapping("/edit/{id}")
    public String editarAutomovelPorId(@PathVariable Integer id, Model model) {
        Automovel automovel = automovelService.buscarAutomovelPorId(id);
        model.addAttribute("automovel", automovel);
        return "automovel/edit";
    }

    @PostMapping("/update/{id}")
    public String atualizarAutomovel(@PathVariable Integer id,
            @RequestParam("placa") String placa,
            @RequestParam("veiculo") String veiculo,
            @RequestParam("pessoa_cpf") String pessoaCpf) {

        automovelService.atualizarAutomovel(id, placa, veiculo, pessoaCpf);

        return "redirect:/automoveis/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deletarAutomovel(@PathVariable Integer id) {
        automovelService.deletarAutomovel(id);
        return "redirect:/automoveis";
    }
}