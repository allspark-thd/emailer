package com.homedepot.service;

import com.homedepot.entity.EmailDetails;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailerService {
    private static Logger log = Logger.getLogger(EmailerService.class);
    private MailSender sender;
    private EmailGenerator generator;
    @Value("${recipient}")
    private String recipientEmail;

    @Autowired
    public EmailerService(MailSender sender) {
        this.sender = sender;
        this.generator = new EmailGenerator();
    }

    public boolean sendEmail(EmailDetails details) {
        log.info("Sending request to " + recipientEmail);
        sender.send(generator.generateEmail(details, recipientEmail));
        return true;
    }

}
