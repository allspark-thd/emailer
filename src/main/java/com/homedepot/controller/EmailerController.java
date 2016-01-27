package com.homedepot.controller;

import com.homedepot.entity.EmailDetails;
import com.homedepot.service.EmailerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailerController {
    private Logger log = Logger.getLogger(EmailerController.class);

    private final EmailerService emailService;

    @Autowired
    public EmailerController(EmailerService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ResponseEntity<String> sendEmail(@RequestBody EmailDetails details) {
        emailService.sendEmail(details);
        return new ResponseEntity("OK", HttpStatus.ACCEPTED);
    }
}
