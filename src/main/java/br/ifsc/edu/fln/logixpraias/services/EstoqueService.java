package br.ifsc.edu.fln.logixpraias.services;

import br.ifsc.edu.fln.logixpraias.model.Estoque;
import br.ifsc.edu.fln.logixpraias.model.RecebimentoMaterial;
import br.ifsc.edu.fln.logixpraias.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class EstoqueService {
    @Autowired
    EstoqueRepository estoqueRepository;

    public boolean isAvailable(Estoque estoque){return false;}


    public int findQuatindadeById(Long id) throws SQLException {
        try{
            return estoqueRepository.findQuatindadeById(id);
        }catch (Exception e){
            throw new SQLException(e);
        }
    }

    public void acrescentar(Long estoqueId, Integer quantidade) throws SQLException {
        Estoque es = estoqueRepository.getEstoqueById(estoqueId);
        es.setQuantidade(es.getQuantidade() + quantidade);
        estoqueRepository.save(es);
    }

    public void retirar(Long estoqueId, Integer quantidade) throws Exception {
        Estoque es = estoqueRepository.getEstoqueById(estoqueId);
        if (es.getQuantidade() >= quantidade) {
            es.setQuantidade(es.getQuantidade() - quantidade);
            estoqueRepository.save(es);
        }else{
            throw new Exception("O valor informado é maior que a quantidade em estoque");
        }
    }
}
