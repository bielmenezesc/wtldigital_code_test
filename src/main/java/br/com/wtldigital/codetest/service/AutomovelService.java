package br.com.wtldigital.codetest.service;

import java.util.List;
import org.springframework.stereotype.Service;
import br.com.wtldigital.codetest.model.Automovel;

@Service
public interface AutomovelService {
    List<Automovel> listarAutomoveis();

    List<Automovel> listarAutomoveisPorPessoaId(Integer pessoaId);

    Automovel criarAutomovel(String placa, String veiculo, String pessoaCpf);

    Automovel buscarAutomovelPorId(Integer id);

    Automovel atualizarAutomovel(Integer id, String placa, String veiculo, String pessoaCpf);
    
    void deletarAutomovel(Integer id);
}