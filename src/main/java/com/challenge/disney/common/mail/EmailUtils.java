package com.challenge.disney.common.mail;

import com.challenge.disney.exception.ExternalServiceException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailUtils {

  @Value("${app.sendgrid.key}")
  private String apiKey;

  @Value("${app.from.email}")
  private String from;

  public void send(IMail IMail) throws ExternalServiceException {
    Email to = new Email(IMail.getTo());
    Content content = new Content(IMail.getContent().getContentType(),
        IMail.getContent().getBody());

    Mail sendGridMail = new Mail(new Email(from), IMail.getSubject(), to, content);
    SendGrid sendGrid = new SendGrid(apiKey);
    Request request = new Request();

    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(sendGridMail.build());

      Response response = sendGrid.api(request);
      if (response.getStatusCode() != 202) {
        throw new ExternalServiceException("Fails to send email: " + response.getBody());
      }
    } catch (IOException | RuntimeException e) {
      throw new ExternalServiceException(e.getMessage());
    }
  }
}
