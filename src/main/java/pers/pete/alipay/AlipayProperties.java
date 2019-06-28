package pers.pete.alipay;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pers.pete.alipay.enums.Charset;
import pers.pete.alipay.enums.SignType;

@ConfigurationProperties("alipay")
public class AlipayProperties {

  /**
   * 应用唯一标识.
   */
  private String appid;

  /**
   * 编码格式.
   *
   * @see pers.pete.alipay.enums.Charset
   */
  private Charset charset;

  /**
   * 支付宝公钥.
   */
  private String alipayPublicKey;

  /**
   * 应用私钥.
   */
  private String appPrivateKey;

  /**
   * 签名.
   */
  private SignType signType;

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public Charset getCharset() {
    return charset;
  }

  public void setCharset(Charset charset) {
    this.charset = charset;
  }

  public String getAlipayPublicKey() {
    return alipayPublicKey;
  }

  public void setAlipayPublicKey(String alipayPublicKey) {
    this.alipayPublicKey = alipayPublicKey;
  }

  public String getAppPrivateKey() {
    return appPrivateKey;
  }

  public void setAppPrivateKey(String appPrivateKey) {
    this.appPrivateKey = appPrivateKey;
  }

  public SignType getSignType() {
    return signType;
  }

  public void setSignType(SignType signType) {
    this.signType = signType;
  }
}
