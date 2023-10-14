package br.com.wtldigital.codetest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wtldigital.codetest.model.Automovel;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, Long> {
    
}