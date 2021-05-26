package edu.school21.cinema.exceptions;

import java.util.ArrayList;
import java.util.List;
import edu.school21.cinema.util.Error;

public class ValidationException extends Exception {
  private final List<Error> errors;

  public ValidationException() {
    this.errors = new ArrayList<>();
  }

  public ValidationException(String msgCode) {
    this(new Error(msgCode));
  }

  public ValidationException(String msgCode, Object ... args) {
    this(new Error(msgCode, args));
  }

  public ValidationException(Error error) {
    this.errors = new ArrayList<>();
    addError(error);
  }

  public ValidationException addError(String msgCode) {
    return addError(new Error(msgCode));
  }

  public ValidationException addError(String msgCode, Object ... args) {
    return addError(new Error(msgCode, args));
  }

  public ValidationException addError(Error error) {
    errors.add(error);
    return this;
  }

  public List<Error> getErrors() {
    return errors;
  }
}
