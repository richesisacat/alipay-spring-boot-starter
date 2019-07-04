package pers.pete.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.response.AlipayFundAuthOperationDetailQueryResponse;
import com.alipay.api.response.AlipayFundAuthOrderFreezeResponse;
import com.alipay.api.response.AlipayFundAuthOrderUnfreezeResponse;
import com.alipay.api.response.AlipayFundAuthOrderVoucherCreateResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import pers.pete.alipay.enums.Charset;
import pers.pete.alipay.enums.SignType;
import pers.pete.alipay.request.DetailParam;
import pers.pete.alipay.request.OrderFreezeParam;
import pers.pete.alipay.request.TradePayParam;
import pers.pete.alipay.request.TradeRefundParam;
import pers.pete.alipay.request.UnfreezeParam;
import pers.pete.alipay.request.VoucherParam;

@Slf4j
public class AlipayTemplate {

  private AlipayAuthService alipayAuthService;

  public AlipayTemplate(String appid, String appPrivateKey, Charset charset, String alipayPublicKey, SignType signType) {
    if (StringUtils.isEmpty(appid) || StringUtils.isEmpty(appPrivateKey) || StringUtils.isEmpty(alipayPublicKey)) {
      throw new RuntimeException("应用appid, 支付宝公钥, 应用私钥不可为空");
    }
    if (null == charset) {
      charset = Charset.UTF_8;
    }
    if (null == signType) {
      signType = SignType.RSA2;
    }
    AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", appid, appPrivateKey, "json", charset.getValue(), alipayPublicKey, signType.name());
    alipayAuthService = new AlipayAuthService(appid, alipayClient);
  }

  /**
   * auth-1 资金授权冻结(商户扫用户).
   */
  public AlipayFundAuthOrderFreezeResponse fundAuthOrderFreeze(OrderFreezeParam param) throws AlipayApiException {
    return alipayAuthService.fundAuthOrderFreeze(param);
  }

  /**
   * auth-2 资金授权发码(用户扫商户).
   */
  public AlipayFundAuthOrderVoucherCreateResponse fundAuthOrderVoucherCreate(VoucherParam param) throws AlipayApiException {
    return alipayAuthService.fundAuthOrderVoucherCreate(param);
  }

  /**
   * auth-3 资金授权解冻.
   */
  public AlipayFundAuthOrderUnfreezeResponse fundAuthOrderUnFreeze(UnfreezeParam param) throws AlipayApiException {
    return alipayAuthService.fundAuthOrderUnFreeze(param);
  }

  /**
   * auth-4 交易创建并支付(预售权转消费).
   */
  public AlipayTradePayResponse fundAuthTradePay(TradePayParam param) throws AlipayApiException {
    return alipayAuthService.fundAuthTradePay(param);
  }

  /**
   * auth-5 资金授权操作查询.
   */
  public AlipayFundAuthOperationDetailQueryResponse fundAuthQuery(DetailParam param) throws AlipayApiException {
    return alipayAuthService.fundAuthQuery(param);
  }

  /**
   * auth-6 交易同步退款.
   */
  public AlipayTradeRefundResponse fundAuthTradeRefund(TradeRefundParam param) throws AlipayApiException {
    return alipayAuthService.tradeRefund(param);
  }

  /**
   * auth-7 交易结果查询.
   */
  public AlipayTradeQueryResponse fundAuthTradeQuery(String outTradeNo, String appAuthToken) throws AlipayApiException {
    return alipayAuthService.tradeQuery(outTradeNo, appAuthToken);
  }

}
