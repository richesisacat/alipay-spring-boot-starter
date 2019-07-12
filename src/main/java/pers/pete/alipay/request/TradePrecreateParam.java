package pers.pete.alipay.request;

public class TradePrecreateParam extends CommonParam {

  /**
   * 必选 商户授权资金订单号
   */
  private String outOrderNo;

  /**
   * 必选 订单总金额 单位为元，精确到小数点后两位
   */
  private String totalAmount;

  /**
   * 必选 订单标题
   */
  private String subject;

  /**
   * 可选 对交易或商品的描述
   */
  private String body;

  /**
   * 可选 该笔订单允许的最晚付款时间，逾期将关闭该笔订单，如果为空，默认15m
   */
  private String timeoutExpress;

  /**
   * 支付宝服务器主动通知商户服务器里指定的页面http/https路径。
   */
  private String notifyUrl;

  /**
   * 可选 门店ID
   */
  private String storeId;

  public String getOutOrderNo() {
    return outOrderNo;
  }

  public void setOutOrderNo(String outOrderNo) {
    this.outOrderNo = outOrderNo;
  }

  public String getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(String totalAmount) {
    this.totalAmount = totalAmount;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
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

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }

  public String getStoreId() {
    return storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }
}
