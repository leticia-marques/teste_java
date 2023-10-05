package com.safeway.teste.domain.service.notification;

import com.safeway.teste.domain.dto.notification.Message;
import com.safeway.teste.domain.dto.notification.MessageDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Value("${safeway.teste.notification.url}")
    private String url;

    @Override
    public void notification(Message message) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder stringBuilder = new StringBuilder();

        String transactionMessage = String.format("Sr(a) %s, o %s no valor de %.2f na empresa %s foi feito com sucesso.",
                message.client().getName(),message.transactionType(), message.transactionValue().floatValue(), message.company().getName());
        MessageDto messageDto = new MessageDto(transactionMessage);
        restTemplate.postForEntity(url, messageDto, String.class);
    }
}
