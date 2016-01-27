package com.homedepot.service;

import com.homedepot.entity.EmailDetails;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class EmailGeneratorTest {

    SimpleMailMessage msg;
    String spaceGuid = "space-12345-guid";
    private String repoUrl = "https://github.com/user/repo/db-access.text";
    private String appId = "the-app-id-12442";
    private String vaultUrl = "https://vault.io/secret/thing";

    @Before
    public void setUp() {
        EmailGenerator emailGenerator = new EmailGenerator();

        EmailDetails deets = new EmailDetails();
        deets.setSpaceGuid(spaceGuid);
        deets.setAccessRequirementsRepo(repoUrl);
        deets.setAppId(appId);
        deets.setDesiredVaultUrl(vaultUrl);
        msg = emailGenerator.generateEmail(deets);
    }

    @Test
    public void theSubjectShouldSayNewRequest() {
        assertThat(
                msg.getSubject(),
                containsString("New Request")
        );
    }

    @Test
    public void theEmailShouldContainTheSpaceGuid() {
        assertThat(msg.getText(), containsString(spaceGuid));
    }

    @Test
    public void theEmailShouldContainTheRequirementsURL() {
        assertThat(msg.getText(), containsString(repoUrl));
    }

    @Test
    public void theEmailShouldContainTheAppId() {
        assertThat(msg.getText(), containsString(appId));
    }

    @Test
    public void theEmailShouldContainTheVaultURL() {
        assertThat(msg.getText(), containsString(vaultUrl));
    }
}