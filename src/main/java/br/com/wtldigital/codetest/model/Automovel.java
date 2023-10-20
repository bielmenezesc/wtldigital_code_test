package br.com.wtldigital.codetest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "automovel")
public class Automovel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String placa;

    private String veiculo;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa proprietario;

    public Automovel() {
    }

    public Automovel(String placa, String veiculo, Pessoa pessoa) {
        this.placa = placa;
        this.veiculo = veiculo;
        this.proprietario = pessoa;
    }
    
    // Getter para o campo id
    public Integer getId() {
        return id;
    }

    // Setter para o campo id
    public void setId(Integer id) {
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

    public Pessoa getProprietario() {
        return proprietario;
    }

    public void setProprietario(Pessoa pessoa) {
        this.proprietario = pessoa;
    }
}
