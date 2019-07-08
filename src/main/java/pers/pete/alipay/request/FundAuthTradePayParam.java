package pers.pete.alipay.request;

public class FundAuthTradePayParam extends CommonParam {

  /**
   * 必填 商户交易流水号与冻结不同
   */
  private String outTradeNo;

  /**
   * 必填 支付宝资金授权订单号，在授权冻结成功时返回需要入库保存
   */
  private String authNo;

  /**
   * 必填 订单标题
   */
  private String subject;

  /**
   * 必填 买家id 预授权冻结接口返回 查询接口返回
   */
  private String buyerId;

  /**
   * 选填 订单描述
   */
  private String body;

  /**
   * 必填 订单金额
   */
  private String totalAmount;


  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getAuthNo() {
    return authNo;
  }

  public void setAuthNo(String authNo) {
    this.authNo = authNo;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getBuyerId() {
    return buyerId;
  }

  public void setBuyerId(String buyerId) {
    this.buyerId = buyerId;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(String totalAmount) {
    this.totalAmount = totalAmount;
  }
}
