package br.ifsc.edu.fln.logixpraias.services;

import br.ifsc.edu.fln.logixpraias.model.RecebimentoMaterial;
import br.ifsc.edu.fln.logixpraias.model.RetiradaMaterial;
import br.ifsc.edu.fln.logixpraias.repository.MaterialReceiptRepository;
import br.ifsc.edu.fln.logixpraias.repository.MaterialWithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class RetiradaService {
    @Autowired
    private EstoqueService estoqueService;
    @Autowired
    private MaterialWithdrawalRepository materialWithdrawalRepository;


    public void save(RetiradaMaterial retirada) throws SQLException {
        try{
            materialWithdrawalRepository.save(retirada);
            estoqueService.retirar(retirada.getMaterial().getEstoque().getId(), retirada.getQuantidade());
        }catch (Exception e){
            materialWithdrawalRepository.delete(retirada);
            throw new SQLException("Não foi possível registrar o recebimento: ", e);
        }
    }
}
