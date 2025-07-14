package br.ifsc.edu.fln.logixpraias.utils;

import br.ifsc.edu.fln.logixpraias.model.RecebimentoMaterial;
import br.ifsc.edu.fln.logixpraias.model.RetiradaMaterial;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class EmailUtils {

    public String gerarCorpoEmail(RecebimentoMaterial recebimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        StringBuilder sb = new StringBuilder();
        sb.append("Olá,\n\n");
        sb.append("Um novo item foi registrado no sistema com as seguintes informações:\n\n");
        sb.append("📦 ID: ").append(recebimento.getId()).append("\n");
        sb.append("📦 Quantidade: ").append(recebimento.getQuantidade()).append("\n");
        sb.append("📄 Descrição: ").append(recebimento.getDescricao()).append("\n");
        sb.append("📅 Data de Recebimento: ")
                .append(recebimento.getDataRecebimento().format(formatter)).append("\n");
        sb.append("🏢 Fornecedor: ").append(recebimento.getFornecedor()).append("\n");
        sb.append("👤 Usuário Responsável: ").append(recebimento.getUsuario().getNome()).append("\n");
        sb.append("🧾 Material: ").append(recebimento.getMaterial().getNome()).append("\n\n");
        sb.append("Atenciosamente,\n");
        sb.append("Sistema de Controle de Estoque");

        return sb.toString();
    }

    public String gerarCorpoEmail(RetiradaMaterial retirada) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        StringBuilder sb = new StringBuilder();
        sb.append("Olá,\n\n");
        sb.append("Uma nova retirada de material foi registrada no sistema:\n\n");
        sb.append("📦 ID da Retirada: ").append(retirada.getId()).append("\n");
        sb.append("📦 Quantidade: ").append(retirada.getQuantidade()).append("\n");
        sb.append("📝 Observação: ").append(retirada.getObservacao()).append("\n");
        sb.append("📅 Data da Retirada: ")
                .append(retirada.getDataRetirada().format(formatter)).append("\n");
        sb.append("👤 Usuário Responsável: ").append(retirada.getUsuario().getNome()).append("\n");
        sb.append("🧾 Material: ").append(retirada.getMaterial().getNome()).append("\n\n");
        sb.append("Atenciosamente,\n");
        sb.append("Sistema de Controle de Estoque");

        return sb.toString();
    }
}
