package pers.pete.alipay.request;

public class CommonParam {

  /**
   * 选填 B商户授权A商户做资金操作的token，详见应用授权概述
   */
  protected String appAuthToken;

  /**
   * 支付宝服务器主动通知商户服务器里指定的页面http/https路径。
   */
  protected String notifyUrl;

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }

  public String getAppAuthToken() {
    return appAuthToken;
  }

  public void setAppAuthToken(String appAuthToken) {
    this.appAuthToken = appAuthToken;
  }
}
