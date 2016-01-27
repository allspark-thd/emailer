package com.homedepot.service;

import com.homedepot.entity.EmailDetails;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EmailerServiceTest{

    private EmailDetails details;
    private EmailerService emailService;

    @Before
    public void setUp() {
        details = new EmailDetails();
        details.setAccessRequirementsRepo("https://github.com/foo/repo/file");
        details.setAppId("the-id");
        details.setDesiredVaultUrl("https://vault.io/secret/key");
        details.setSpaceGuid("the-guid");

        emailService = new EmailerService();
    }
    @Test
    public void itShouldSendAnEmailWithValidDetails() {
        assertTrue(emailService.sendEmail(details));
    }
}