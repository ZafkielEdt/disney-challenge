package com.challenge.disney.services.impl;

import com.challenge.disney.services.MailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MailServiceImpl implements MailService {

    @Value("${disney.email.sender}")
    private String EMAIL_SENDER;

    @Value("${disney.email.enabled}")
    private Boolean ENABLED;

    private final Environment environment;

    @Autowired
    public MailServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void sendEmailTo(String to) {

        if(!ENABLED) {
            return;
        } else {

            String key = environment.getProperty("EMAIL_API_KEY");

            Email from = new Email(EMAIL_SENDER);
            String subject = "Disney Challenge";
            Email toEmail = new Email(to);
            Content content = new Content("text/plain", "Welcome to Disney Api");
            Mail mail = new Mail(from,subject,toEmail,content);

            SendGrid sendGrid = new SendGrid(key);

            Request request = new Request();

            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sendGrid.api(request);

                //return response.getBody();
            } catch (IOException e) {
                System.out.println("Error trying to send email");
            }
        }

    }
}
