package pers.pete.alipay.request;

public class TradePayParam extends CommonParam {

  /**
   * 必填 商户交易流水号与冻结不同
   */
  private String outTradeNo;

  /**
   * 必填 支付授权码或扫脸成功后获取ftoken
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

  /**
   * 可选 门店ID
   */
  private String storeId;

  /**
   * 可选 该笔订单允许的最晚付款时间，逾期将关闭交易。
   * <p>
   * 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
   * <p>
   * 该参数数值不接受小数点， 如 1.5h，可转换为 90m
   */
  private String timeoutExpress;

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

  public String getTimeoutExpress() {
    return timeoutExpress;
  }

  public void setTimeoutExpress(String timeoutExpress) {
    this.timeoutExpress = timeoutExpress;
  }

  public String getStoreId() {
    return storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }
}
