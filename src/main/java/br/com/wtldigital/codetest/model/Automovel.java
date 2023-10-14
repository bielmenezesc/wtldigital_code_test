package br.com.wtldigital.codetest.model;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import jakarta.persistence.Column;

@Entity
public class Automovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String placa;
    
    private String veiculo;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Optional<Pessoa> proprietario;
    
    // Getter para o campo id
    public Long getId() {
        return id;
    }

    // Setter para o campo id
    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public Optional<Pessoa> getProprietario() {
        return proprietario;
    }

    public void setProprietario(Optional<Pessoa> proprietario) {
        this.proprietario = proprietario;
    }
}
