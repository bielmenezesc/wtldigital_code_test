package br.com.wtldigital.codetest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import br.com.wtldigital.codetest.model.Pessoa;
import br.com.wtldigital.codetest.service.PessoaService;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public String criarAutomovel() {
        return "pessoa/create";
    }

    @GetMapping("/listar")
    public String listarPessoas(Model model) {
        List<Pessoa> pessoas = pessoaService.listarPessoas();
        model.addAttribute("pessoas", pessoas);
        return "pessoa/showAll";
    }

    @GetMapping("/{id}")
    public String getPessoaPorId(@PathVariable Integer id, Model model) {
        Pessoa pessoa = pessoaService.buscarPessoaPorId(id);
        model.addAttribute("pessoa", pessoa);
        return "pessoa/show";
    }

    @PostMapping
    public String criarPessoa(@RequestParam("cpf") String cpf,
            @RequestParam("nome") String nome,
            @RequestParam("estado") String estado) {
        Pessoa novaPessoa = pessoaService.criarPessoa(cpf, nome, estado);
        return "redirect:/pessoas/" + novaPessoa.getId();
    }

    @GetMapping("/edit/{id}")
    public String editarAutomovelPorId(@PathVariable Integer id, Model model) {
        Pessoa pessoa = pessoaService.buscarPessoaPorId(id);
        model.addAttribute("pessoa", pessoa);
        return "pessoa/edit";
    }


    @PostMapping("/update/{id}")
    public String atualizarPessoa(@PathVariable Integer id,
            @RequestParam("cpf") String cpf,
            @RequestParam("nome") String nome,
            @RequestParam("estado") String estado) {
        pessoaService.atualizarPessoa(id, cpf, nome, estado);
        return "redirect:/pessoas/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deletarPessoa(@PathVariable Integer id) {
        pessoaService.deletarPessoa(id);
        return "redirect:/pessoas";
    }
}