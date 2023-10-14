package br.com.wtldigital.codetest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.wtldigital.codetest.model.Automovel;
import br.com.wtldigital.codetest.repository.AutomovelRepository;

public class AutomovelService {
    @Autowired
    private AutomovelRepository automovelRepository;

    public List<Automovel> listarTodosOsAutomoveis() {
        return automovelRepository.findAll();
    }

    public Optional<Automovel> getAutomovelPorId(Long id) {
        return automovelRepository.findById(id);
    }

    public Automovel criarAutomovel(Automovel automovel) {
        // Adicione aqui lógica de validação, se necessário.
        return automovelRepository.save(automovel);
    }

    public Automovel atualizarAutomovel(Long id, Automovel automovelAtualizado) {
        // Adicione aqui a lógica para atualizar o automóvel, se necessário.
        return automovelRepository.save(automovelAtualizado);
    }

    public void deletarAutomovel(Long id) {
        automovelRepository.deleteById(id);
    }
}
