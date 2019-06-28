package pers.pete.alipay.request;

public class VoucherParam extends CommonParam {

  /**
   * 必选 商户授权资金订单号，创建后不能修改，需要保证在商户端不重复。
   */
  private String outOrderNo;

  /**
   * 必选 商户本次资金操作的请求流水号，用于标示请求流水的唯一性，需要保证在商户端不重复。
   */
  private String outRequestNo;

  /**
   * 必选 业务订单的简单描述，如商品名称等长度不超过100个字母或50个汉字
   */
  private String orderTitle;

  /**
   * 必选 需要冻结的金额，单位为：元（人民币），精确到小数点后两位取值范围：[0.01,100000000.00]
   */
  private String amount;

  /**
   * 可选 该笔订单允许的最晚付款时间，逾期将关闭该笔订单，如果为空，默认15m
   */
  private String payTimeOut;

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
}
