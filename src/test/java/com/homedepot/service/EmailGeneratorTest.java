package com.homedepot.service;

import com.homedepot.entity.EmailDetails;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class EmailGeneratorTest {

    private SimpleMailMessage emailToAdmin;
    private SimpleMailMessage emailToNonAdmin;

    private String spaceGuid = "space-12345-guid";
    private String repoUrl = "https://github.com/user/repo/db-access.text";
    private String appId = "the-app-id-12442";
    private String vaultUrl = "https://vault.io/secret/thing";
    private String adminAddress = "admin@example.com";
    private String nonAdminAddress = "not-admin@example.com";

    @Before
    public void setUp() {
        EmailGenerator emailGenerator = new EmailGenerator();
        EmailDetails deets = new EmailDetails();
        deets.setSpaceGuid(spaceGuid);
        deets.setAccessRequirementsRepo(repoUrl);
        deets.setAppId(appId);
        deets.setDesiredVaultUrl(vaultUrl);
        emailToAdmin = emailGenerator.generateEmail(deets, adminAddress);
        emailToNonAdmin = emailGenerator.generateEmail(deets, nonAdminAddress);
    }

    @Test
    public void theSubjectShouldSayNewRequest() {
        assertThat(
                emailToAdmin.getSubject(),
                containsString("New Request")
        );
    }

    @Test
    public void theEmailShouldContainTheSpaceGuid() {
        assertThat(emailToAdmin.getText(), containsString(spaceGuid));
    }

    @Test
    public void theEmailShouldContainTheRequirementsURL() {
        assertThat(emailToAdmin.getText(), containsString(repoUrl));
    }

    @Test
    public void theEmailShouldContainTheAppId() {
        assertThat(emailToAdmin.getText(), containsString(appId));
    }

    @Test
    public void theEmailShouldContainTheVaultURL() {
        assertThat(emailToAdmin.getText(), containsString(vaultUrl));
    }

    @Test
    public void itShouldSendTheEmailToTheRightRecipicent() {
        assertThat(emailToAdmin.getTo()[0], is(equalTo(adminAddress)));
        assertThat(emailToNonAdmin.getTo()[0], is(equalTo(nonAdminAddress)));
    }
}