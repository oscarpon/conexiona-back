package com.opbaquero.conexionaback.models.service.impl;

import com.opbaquero.conexionaback.models.service.dto.EmailDTO;
import com.opbaquero.conexionaback.models.service.interfaces.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    private String url;


    @Override
    public void sendMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("oscarponte97@gmail.com");
        message.setTo(email);
        message.setSubject("Usuario kanbansense");
        message.setText("Esto es el contenido del email");

        javaMailSender.send(message);
    }

    @Override
    public void sendMailTemplate(EmailDTO dto) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            model.put("userName", dto.getUserName());
            model.put("newPassword", dto.getNewPassword());
            context.setVariables(model);
            String htmlText = templateEngine.process("forgot-password", context);
            helper.setFrom(dto.getFrom());
            helper.setTo(dto.getTo());
            helper.setSubject(dto.getSubject());
            helper.setText(htmlText, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
