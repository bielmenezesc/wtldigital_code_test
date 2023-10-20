package br.com.wtldigital.codetest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wtldigital.codetest.model.Automovel;
import br.com.wtldigital.codetest.model.Pessoa;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, Integer> {
    
    List<Automovel> findByProprietario(Pessoa pessoa);
}