package pers.pete.alipay.request;

public class CommonParam {

  /**
   * 选填 B商户授权A商户做资金操作的token，详见应用授权概述
   */
  protected String appAuthToken;

  public String getAppAuthToken() {
    return appAuthToken;
  }

  public void setAppAuthToken(String appAuthToken) {
    this.appAuthToken = appAuthToken;
  }
}
