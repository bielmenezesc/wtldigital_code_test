package br.com.wtldigital.codetest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.wtldigital.codetest.model.Pessoa;

@Service
public interface PessoaService {
    List<Pessoa> listarPessoas();

    Pessoa buscarPessoaPorId(Integer id);

    Pessoa criarPessoa(String cpf, String nome, String estado);

    Pessoa atualizarPessoa(Integer id, String cpf, String nome, String estado);

    void deletarPessoa(Integer id);
}