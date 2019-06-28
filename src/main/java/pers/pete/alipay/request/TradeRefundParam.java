package pers.pete.alipay.request;

public class TradeRefundParam extends CommonParam {

  /**
   * 必填 与预授权转支付商户订单号相同，代表对该笔交易退款
   */
  private String outTradeNo;

  /**
   * 必填 需要退款的金额
   */
  private String refundAmount;

  /**
   * 选填 退款的原因说明
   */
  private String refundReason;

  /**
   * 选填 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
   */
  private String outRequestNo;

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getRefundAmount() {
    return refundAmount;
  }

  public void setRefundAmount(String refundAmount) {
    this.refundAmount = refundAmount;
  }

  public String getRefundReason() {
    return refundReason;
  }

  public void setRefundReason(String refundReason) {
    this.refundReason = refundReason;
  }

  public String getOutRequestNo() {
    return outRequestNo;
  }

  public void setOutRequestNo(String outRequestNo) {
    this.outRequestNo = outRequestNo;
  }
}
