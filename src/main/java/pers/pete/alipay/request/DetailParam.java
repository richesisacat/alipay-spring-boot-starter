package pers.pete.alipay.request;

public class DetailParam extends CommonParam {

  /**
   * 支付宝订单号
   */
  private String authNo;

  /**
   * 支付宝流水号
   */
  private String operationId;

  /**
   * 商户订单号
   */
  private String outOrderNo;

  /**
   * 商户流水号
   */
  private String outRequestNo;

  public String getAuthNo() {
    return authNo;
  }

  public void setAuthNo(String authNo) {
    this.authNo = authNo;
  }

  public String getOperationId() {
    return operationId;
  }

  public void setOperationId(String operationId) {
    this.operationId = operationId;
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
}
