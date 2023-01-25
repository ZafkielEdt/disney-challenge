package com.challenge.disney.common.mail.template;

import com.challenge.disney.common.mail.IContent;
import com.challenge.disney.common.mail.IMail;
import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterEmailTemplate implements IMail, IContent {

  private static final String TYPE = "text/plain";
  private static final String SUBJECT = "Register Successfully";
  private static final String WELCOME_TEXT = "Welcome to {0}";

  private final String organizationName;

  private final String emailTo;



  @Override
  public String getBody() {
    return MessageFormat.format(WELCOME_TEXT, organizationName);
  }

  @Override
  public String getContentType() {
    return TYPE;
  }

  @Override
  public String getSubject() {
    return SUBJECT;
  }

  @Override
  public IContent getContent() {
    return this;
  }

  @Override
  public String getTo() {
    return emailTo;
  }
}
