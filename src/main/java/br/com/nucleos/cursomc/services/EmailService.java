package br.com.nucleos.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.nucleos.cursomc.domain.Pedido;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage msg);

}
