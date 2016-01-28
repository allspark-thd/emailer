package com.homedepot.service;

import com.homedepot.entity.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailerService {

    private MailSender sender;
    private EmailGenerator generator;
    @Value("$recipient")
    private String recipientEmail;

    @Autowired
    public EmailerService(MailSender sender) {
        this.sender = sender;
        this.generator = new EmailGenerator();
    }

    public boolean sendEmail(EmailDetails details) {
        sender.send(generator.generateEmail(details, recipientEmail));
        return true;
    }

}
