package com.opbaquero.conexionaback.models.service.interfaces;

import com.opbaquero.conexionaback.models.service.dto.EmailDTO;

public interface IEmailService {

    void sendMail(String email);

    void sendMailTemplate(EmailDTO dto);

}
