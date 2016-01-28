package com.homedepot.service;

import com.homedepot.entity.EmailDetails;
import org.springframework.mail.SimpleMailMessage;

public class EmailGenerator {
    public SimpleMailMessage generateEmail(EmailDetails details,
                                           String recipentEmail) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(recipentEmail);
        msg.setSubject("New Request for access to a datasource");

        String body = String.format(
          "A request for access to a new datasource has been created\n" +
                  "db requirements url: %s\n" +
                  "app id: %s" +
                  "space guid: %s\n" +
                  "vault url: %s\n",
                details.getAccessRequirementsRepo(),
                details.getAppId(),
                details.getSpaceGuid(),
                details.getDesiredVaultUrl()
        );
        msg.setText(body);
        return msg;
    }
}
