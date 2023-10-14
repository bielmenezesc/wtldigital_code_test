package br.com.wtldigital.codetest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.wtldigital.codetest.model.Automovel;
import br.com.wtldigital.codetest.model.Pessoa;
import br.com.wtldigital.codetest.repository.AutomovelRepository;
import br.com.wtldigital.codetest.repository.PessoaRepository;

public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private AutomovelRepository automovelRepository;

    public List<Pessoa> listarTodasAsPessoas() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> getPessoaPorId(Long cpf) {
        return pessoaRepository.findById(cpf);
    }

    public Pessoa criarPessoa(Pessoa pessoa) {
        // Validação: Verificar se o nome da pessoa não está vazio.
        if (pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome da pessoa não pode estar vazio");
        }

        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) {
        // Adicione aqui a lógica para atualizar a pessoa, se necessário.
        return pessoaRepository.save(pessoaAtualizada);
    }

    public void deletarPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }

    public Automovel associarAutomovel(Long cpf, Automovel automovel) {
        // Verifique se a pessoa com o ID fornecido existe no banco de dados.
        Optional<Pessoa> pessoa = pessoaRepository.findById(cpf);
         //   .orElseThrow(() -> new NoSuchElementException("Pessoa não encontrada com ID: " + pessoaId));

        // Defina a pessoa como proprietária do automóvel.
        automovel.setProprietario(pessoa);
        automovelRepository.save(automovel);

        return automovel;
    }
}
