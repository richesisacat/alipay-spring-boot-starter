package pers.pete.alipay.enums;

public enum Charset {

  GBK("GBK"),

  UTF_8("UTF-8");

  private String value;

  private Charset(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
