package pers.pete.alipay.request;

public class TradePayParam extends CommonParam {

  /**
   * 必填 商户交易流水号与冻结不同
   */
  private String outTradeNo;

  /**
   * 必填 支付授权码
   */
  private String authCode;

  /**
   * 必填 订单标题
   */
  private String subject;

  /**
   * 必选 订单总金额 单位为元，精确到小数点后两位
   */
  private String totalAmount;

  /**
   * 可选 对交易或商品的描述
   */
  private String body;

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getAuthCode() {
    return authCode;
  }

  public void setAuthCode(String authCode) {
    this.authCode = authCode;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(String totalAmount) {
    this.totalAmount = totalAmount;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}
