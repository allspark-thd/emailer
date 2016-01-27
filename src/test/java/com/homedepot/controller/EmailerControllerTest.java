package com.homedepot.controller;

import com.homedepot.entity.EmailDetails;
import com.homedepot.service.EmailerService;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EmailerControllerTest {

    @Mock
    EmailerService emailService;

    MockMvc mockMvc;

    JSONObject json;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(
                new EmailerController(emailService)).build();
        json = new JSONObject();
        json.put("spaceGuid", "123456-space");
        json.put("appId", "123452-the-app");
        json.put("accessRequirementsRepo", "https://some/github/repo");
        json.put("desiredVaultUrl", "/vault_url/v1/secret/service-instance-id");

    }
    @Test
    public void itShouldReturnBAD_REQUESTWithNoEmailPayload() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/send").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void itShouldReturnACCEPTEDWhenItGetsAPayload() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/send").contentType(MediaType.APPLICATION_JSON)
                .content(json.toString())).andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    public void itShouldAskTheServiceToSendAnEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/send").contentType(MediaType.APPLICATION_JSON)
                .content(json.toString()));

        verify(emailService, times(1)).sendEmail(any(EmailDetails.class));
    }
}