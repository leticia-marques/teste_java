package com.safeway.teste.domain.service.notification;


import com.safeway.teste.domain.dto.notification.EmailDto;
import com.safeway.teste.domain.dto.notification.Message;
import com.safeway.teste.domain.dto.notification.MessageDto;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import javax.swing.text.AbstractDocument;
import java.io.IOException;

@Service
public class EmailNotificationService implements NotificationService{


    @Value("${safeway.teste.notification.email}")
    private String email;

    @Value("${safeway.teste.notification.api-key}")
    private String apiKey;

    //TODO configurar recebimento de email do cliente na criação de clienteInputDto
    // TODO TESTE refatoração melhorar documentação README DOCKER e docker-compose
    @Override
    public void notification(Message message) {
        EmailDto newEmail =  new EmailDto(message.client().getName(), message.transactionValue(),
                "bc71a578-2756-49dd-900c-d46e9e20c315@email.webhook.site");
        this.composeEmail("Movimentação bancária", message.transactionType(),  newEmail);
    }

    public void depositEmail(EmailDto data) {
        composeEmail("Depósito recebido", "Depósito", data);
    }

    public void withdrawEmail(EmailDto data) {
        composeEmail("Saque efetuado com sucesso!", "Saque", data);
    }

    public void transferEmail(EmailDto data) {
        composeEmail("Transferência realizada com sucesso!", "Transferência", data);
    }

    private void composeEmail(String subject, String operation, EmailDto data) {
        Email from = new Email(this.email);
        String message = String.format("Olá %s,\n\n%s no valor de R$: %s realizado com sucesso!", data.name(), operation, data.amount());
        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, new Email(data.to()), content);
        sendEmail(mail);
    }

    public void sendEmail(Mail mail)  {
        SendGrid sg = new SendGrid(this.apiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
        } catch (IOException ex) {
            throw new RuntimeException("Email não envidado");
        }
    }
}
