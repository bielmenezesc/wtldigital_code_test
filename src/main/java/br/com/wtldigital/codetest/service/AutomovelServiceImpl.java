package br.com.wtldigital.codetest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.wtldigital.codetest.model.Automovel;
import br.com.wtldigital.codetest.model.Pessoa;
import br.com.wtldigital.codetest.repository.AutomovelRepository;
import br.com.wtldigital.codetest.repository.PessoaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AutomovelServiceImpl implements AutomovelService {
    @Autowired
    private AutomovelRepository automovelRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public List<Automovel> listarAutomoveis() {
        return automovelRepository.findAll();
    }

    @Override
    public List<Automovel> listarAutomoveisPorPessoaId(Integer pessoaId) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId);
        return automovelRepository.findByProprietario(pessoa.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada")));
    }

    @Override
    public Automovel criarAutomovel(String placa, String veiculo, String pessoaCpf) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(pessoaCpf);
        if (pessoa.isPresent()) {
            Automovel automovel = new Automovel(placa, veiculo, pessoa.get());
            return automovelRepository.save(automovel);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
        }
    }

    @Override
    public Automovel buscarAutomovelPorId(Integer id) {
        Optional<Automovel> automovel = automovelRepository.findById(id);
        return automovel.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Automóvel não encontrado"));
    }

    @Override
    public Automovel atualizarAutomovel(Integer id, String placa, String veiculo, String pessoaCpf) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(pessoaCpf);
        Optional<Automovel> automovelOptional = automovelRepository.findById(id);

        if (automovelOptional.isPresent() && pessoa.isPresent()) {
            Automovel automovel = automovelOptional.get();
            automovel.setPlaca(placa);
            automovel.setVeiculo(veiculo);
            automovel.setProprietario(pessoa.get());
            return automovelRepository.save(automovel);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Automóvel ou Pessoa não encontrados");
        }
    }

    @Override
    public void deletarAutomovel(Integer id) {
        Optional<Automovel> automovelOptional = automovelRepository.findById(id);
        if (automovelOptional.isPresent()) {
            automovelRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Automóvel não encontrado");
        }
    }
}
