package pers.pete.alipay.request;

public class FundAuthUnfreezeParam extends CommonParam {

  /**
   * 必选 支付宝资金授权订单号，在授权冻结成功时返回需要入库保存
   */
  private String authNo;

  /**
   * 必选 商户本次资金操作的请求流水号
   */
  private String outRequestNo;

  /**
   * 必选 本次操作解冻的金额，单位为：元（人民币），精确到小数点后两位，取值范围：[0.01,100000000.00]
   */
  private String amount;

  /**
   * 必选 商户对本次解冻操作的附言描述，长度不超过100个字母或50个汉字
   */
  private String remark;

  public String getAuthNo() {
    return authNo;
  }

  public void setAuthNo(String authNo) {
    this.authNo = authNo;
  }

  public String getOutRequestNo() {
    return outRequestNo;
  }

  public void setOutRequestNo(String outRequestNo) {
    this.outRequestNo = outRequestNo;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
