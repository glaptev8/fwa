package edu.school21.cinema.util;

import edu.school21.cinema.validation.MessageService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Error {
  private String msgCode;
  private String message;
  private List<Object> args;

  public Error(String msgCode) {
    this.msgCode = msgCode;
    this.message = toString();
  }

  public Error(String msgCode, Object ... args) {
    this.msgCode = msgCode;
    this.args = new ArrayList<>(Arrays.asList(args));
    this.message = toString();
  }

  public Error addArg(Object arg) {
    if (this.args == null) {
      this.args = new ArrayList<>();
    }
    this.args.add(arg);
    this.message = toString();
    return this;
  }

  public Error addArgs(Object ... args) {
    if (this.args == null) {
      this.args = new ArrayList<>();
    }
    this.args.addAll(Arrays.asList(args));
    this.message = toString();
    return this;
  }

  public String getMessage() {
    return message;
  }

  public String getMsgCode() {
    return msgCode;
  }

  public List<Object> getArgs() {
    return args;
  }

  @Override
  public String toString() {
    if (CollectionUtils.isNonEmpty(args)) {
      return MessageService.getMessage(msgCode, args.toArray());
    }
    return MessageService.getMessage(msgCode);
  }
}
