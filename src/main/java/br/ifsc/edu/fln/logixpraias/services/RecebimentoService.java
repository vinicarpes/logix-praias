package br.ifsc.edu.fln.logixpraias.services;

import br.ifsc.edu.fln.logixpraias.model.RecebimentoMaterial;
import br.ifsc.edu.fln.logixpraias.repository.EstoqueRepository;
import br.ifsc.edu.fln.logixpraias.repository.MaterialReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class RecebimentoService {
    @Autowired
    private EstoqueService estoqueService;
    @Autowired
    private MaterialReceiptRepository  materialReceiptRepository;


    public void save(RecebimentoMaterial recebimento) throws SQLException {
        try{
            materialReceiptRepository.save(recebimento);
            estoqueService.acrescentar(recebimento.getMaterial().getEstoque().getId(), recebimento.getQuantidade());
        }catch (Exception e){
            materialReceiptRepository.delete(recebimento);
            throw new SQLException("Não foi possível registrar o recebimento: ", e);
        }
    }
}
