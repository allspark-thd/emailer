package com.homedepot.service;

import com.homedepot.entity.EmailDetails;
import org.springframework.stereotype.Service;

@Service
public class EmailerService {

    public boolean sendEmail(EmailDetails details) {
        return true;
    }
}
