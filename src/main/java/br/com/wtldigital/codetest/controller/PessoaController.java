package br.com.wtldigital.codetest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wtldigital.codetest.model.Automovel;
import br.com.wtldigital.codetest.model.Pessoa;
import br.com.wtldigital.codetest.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = pessoaService.criarPessoa(pessoa);
        return new ResponseEntity<>(novaPessoa, HttpStatus.CREATED);
    }

    @PostMapping("/{pessoaId}/automoveis")
    public ResponseEntity<Automovel> associarAutomovel(@PathVariable Long pessoaId, @RequestBody Automovel automovel) {
        Automovel novoAutomovel = pessoaService.associarAutomovel(pessoaId, automovel);
        return new ResponseEntity<>(novoAutomovel, HttpStatus.CREATED);
    }
}