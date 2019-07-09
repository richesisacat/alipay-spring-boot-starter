package pers.pete.alipay.request;

public class FundAuthOrderFreezeParam extends CommonParam {

  /**
   * 必选 用户支付宝钱包中的付款码
   */
  private String authCode;

  /**
   * 必选 商户授权资金订单号
   */
  private String outOrderNo;
  /**
   * 必选 商户本次资金操作的请求流水号
   */
  private String outRequestNo;
  /**
   * 必选 业务订单的简单描述
   */
  private String orderTitle;
  /**
   * 必选 需要冻结的金额
   */
  private String amount;

  /**
   * 可选 该笔订单允许的最晚付款时间，逾期将关闭该笔订单，如果为空，默认15m
   */
  private String payTimeOut;

  /**
   * 支付宝服务器主动通知商户服务器里指定的页面http/https路径。
   */
  private String notifyUrl;

  public String getAuthCode() {
    return authCode;
  }

  public void setAuthCode(String authCode) {
    this.authCode = authCode;
  }

  public String getOutOrderNo() {
    return outOrderNo;
  }

  public void setOutOrderNo(String outOrderNo) {
    this.outOrderNo = outOrderNo;
  }

  public String getOutRequestNo() {
    return outRequestNo;
  }

  public void setOutRequestNo(String outRequestNo) {
    this.outRequestNo = outRequestNo;
  }

  public String getOrderTitle() {
    return orderTitle;
  }

  public void setOrderTitle(String orderTitle) {
    this.orderTitle = orderTitle;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getPayTimeOut() {
    return payTimeOut;
  }

  public void setPayTimeOut(String payTimeOut) {
    this.payTimeOut = payTimeOut;
  }

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }
}
