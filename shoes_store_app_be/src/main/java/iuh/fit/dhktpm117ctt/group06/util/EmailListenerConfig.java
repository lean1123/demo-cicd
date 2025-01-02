package iuh.fit.dhktpm117ctt.group06.util;

import iuh.fit.dhktpm117ctt.group06.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EmailListenerConfig {
    @Autowired
    MailSenderService mailSenderService;

    @JmsListener(destination = "email_queue")
    public void receiveMessage(Map<String, Object> messageObject) {
        String type = (String) messageObject.get("type");
        String receiver = (String) messageObject.get("receiver");
        String content = (String) messageObject.get("content");
        try {
            switch (type) {
                case "registration":
                    mailSenderService.sendMail(receiver, content, "Your registration is successful");
                    break;
                case "ordering":
                    mailSenderService.sendMail(receiver, content, "Your order is successful");
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error when sending email");
        }
    }
}
