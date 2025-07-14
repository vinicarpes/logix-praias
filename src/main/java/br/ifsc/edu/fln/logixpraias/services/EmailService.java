package br.ifsc.edu.fln.logixpraias.services;

import br.ifsc.edu.fln.logixpraias.model.Email;
import br.ifsc.edu.fln.logixpraias.model.RecebimentoMaterial;
import br.ifsc.edu.fln.logixpraias.model.RetiradaMaterial;
import br.ifsc.edu.fln.logixpraias.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final EmailUtils emailUtils;

    @Autowired
    public EmailService(JavaMailSender mailSender, EmailUtils emailUtils) {
        this.mailSender = mailSender;
        this.emailUtils = emailUtils;
    }

    public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@gmail.com");
        message.setTo(email.to());
        message.setSubject(email.subject());
        message.setText(email.body());

        mailSender.send(message);
    }

    public Email toEmail(RecebimentoMaterial recebimento) {
        String corpo = emailUtils.gerarCorpoEmail(recebimento);

        return new Email(
                recebimento.getUsuario().getEmail(),
                "Registro de Inserção no Estoque - " + recebimento.getMaterial().getNome(),
                corpo
        );
    }

    public Email toEmail(RetiradaMaterial retirada) {
        String corpo = emailUtils.gerarCorpoEmail(retirada);

        return new Email(
                retirada.getUsuario().getEmail(),
                "Confirmação de Retirada de Material - " + retirada.getMaterial().getNome(),
                corpo
        );
    }
}
