package com.challenge.disney.common.mail;

public interface IMail {

  String getSubject();

  IContent getContent();

  String getTo();
}
