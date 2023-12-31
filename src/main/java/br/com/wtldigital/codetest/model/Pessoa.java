package br.com.wtldigital.codetest.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa")
public class Pessoa implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String cpf;

    private String password;

    private String nome;

    private String estado;

    @OneToMany(mappedBy = "proprietario", cascade = CascadeType.ALL)
    @Column
    private List<Automovel> automoveis;

    public Pessoa() {

    }

    public Pessoa(String cpf, String password, String nome, String estado) {
        this.cpf = cpf;
        this.password = password;
        this.nome = nome;
        this.estado = estado;
    }

    // Getter para o campo id
    public Integer getId() {
        return id;
    }

    // Setter para o campo id
    public void setId(Integer id) {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return cpf;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}