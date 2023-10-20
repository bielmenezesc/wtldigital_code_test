package br.com.wtldigital.codetest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import br.com.wtldigital.codetest.model.Pessoa;
import br.com.wtldigital.codetest.repository.PessoaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    @Override
    public Pessoa buscarPessoaPorId(Integer id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isPresent()) {
            return pessoaOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
        }
    }

    @Override
    public Pessoa criarPessoa(String cpf, String nome, String estado) {
        Optional<Pessoa> pessoaExistente = pessoaRepository.findByCpf(cpf);
    
        if (pessoaExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Pessoa já existe");
        } else {
            Pessoa pessoa = new Pessoa(cpf, nome, estado);
            return pessoaRepository.save(pessoa);
        }
    }

    @Override
    public Pessoa atualizarPessoa(Integer id, String cpf, String nome, String estado) {
        Optional<Pessoa> pessoaExistenteOptional = pessoaRepository.findById(id);

        if (pessoaExistenteOptional.isPresent()) {
            Pessoa pessoaExistente = pessoaExistenteOptional.get();
            // Atualize os campos relevantes da pessoa existente
            pessoaExistente.setNome(nome);
            pessoaExistente.setCpf(cpf);
            pessoaExistente.setEstado(estado);
            return pessoaRepository.save(pessoaExistente);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
        }
    }

    @Override
    public void deletarPessoa(Integer id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);

        if (pessoaOptional.isPresent()) {
            pessoaRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
        }
    }
}