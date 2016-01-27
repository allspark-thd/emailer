package com.homedepot.service;

import com.homedepot.entity.EmailDetails;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EmailerServiceTest{

    private EmailDetails details;
    private EmailerService emailService;

    @Mock
    MailSender sender;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        details = new EmailDetails();
        details.setAccessRequirementsRepo("https://github.com/foo/repo/file");
        details.setAppId("the-id");
        details.setDesiredVaultUrl("https://vault.io/secret/key");
        details.setSpaceGuid("the-guid");

        emailService = new EmailerService(sender);
    }
    @Test
    public void itShouldSendAnEmailWithValidDetails() {
        assertTrue(emailService.sendEmail(details));
    }

    @Test
    public void itShouldUseTheSenderToSendAMessage() {
        emailService.sendEmail(details);
        verify(sender, times(1)).send(any(SimpleMailMessage.class));
    }
}