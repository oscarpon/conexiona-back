package com.opbaquero.conexionaback.models.service.interfaces;

import com.opbaquero.conexionaback.models.service.dto.EmailDTO;

public interface IEmailService {

    void sendMail();

    void sendMailTemplate(EmailDTO dto);

}
