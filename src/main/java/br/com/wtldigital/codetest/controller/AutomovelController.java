package br.com.wtldigital.codetest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wtldigital.codetest.model.Automovel;
import br.com.wtldigital.codetest.service.AutomovelService;

@RestController
@RequestMapping("/automoveis")
public class AutomovelController {
    @Autowired
    private AutomovelService automovelService;

    @GetMapping
    public ResponseEntity<List<Automovel>> listarAutomoveis() {
        List<Automovel> automoveis = automovelService.listarTodosOsAutomoveis();
        return new ResponseEntity<>(automoveis, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Automovel> getAutomovelPorId(@PathVariable Long id) {
        Optional<Automovel> automovel = automovelService.getAutomovelPorId(id);
        return automovel.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Automovel> criarAutomovel(@RequestBody Automovel automovel) {
        Automovel novoAutomovel = automovelService.criarAutomovel(automovel);
        return new ResponseEntity<>(novoAutomovel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Automovel> atualizarAutomovel(@PathVariable Long id, @RequestBody Automovel automovel) {
        Automovel automovelAtualizado = automovelService.atualizarAutomovel(id, automovel);
        return new ResponseEntity<>(automovelAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutomovel(@PathVariable Long id) {
        automovelService.deletarAutomovel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

