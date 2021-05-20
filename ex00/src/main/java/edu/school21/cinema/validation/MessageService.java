package edu.school21.cinema.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;

public class MessageService {
  private static MessageSource messageSource;

  static {
    ResourceBundleMessageSource messageSource2 = new ResourceBundleMessageSource();
    messageSource2.setBasename("i18n/messages");
    messageSource2.setDefaultEncoding(StandardCharsets.UTF_8.name());
    messageSource = messageSource2;
  }

  public static String getMessage(String code, Object ... args) {
    return getMessageWithDefault(code, code, args);
  }

  public static String getMessageWithDefault(String code, String defaultMessage, Object ... args) {
    return messageSource.getMessage(code, args, defaultMessage, LocaleContextHolder.getLocale());
  }
}
