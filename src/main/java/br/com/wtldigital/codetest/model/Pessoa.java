package br.com.wtldigital.codetest.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import jakarta.persistence.Column;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cpf;
    
    private String nome;
    private String estado;

    @OneToMany(mappedBy = "proprietario", cascade = CascadeType.ALL)
    private List<Automovel> automoveis;

    // Getter para o campo id
    public Long getId() {
        return id;
    }

    // Setter para o campo id
    public void setId(Long id) {
        this.id = id;
    }

    // Getter para o campo cpf
    public String getCpf() {
        return cpf;
    }

    // Setter para o campo cpf
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Getter para o campo nome
    public String getNome() {
        return nome;
    }

    // Setter para o campo nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para o campo estado
    public String getEstado() {
        return estado;
    }

    // Setter para o campo estado
    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Getter para o campo automoveis
    public List<Automovel> getAutomoveis() {
        return automoveis;
    }

    // Setter para o campo automoveis
    public void setAutomoveis(List<Automovel> automoveis) {
        this.automoveis = automoveis;
    }
}