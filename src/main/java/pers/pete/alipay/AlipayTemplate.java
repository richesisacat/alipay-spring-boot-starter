package pers.pete.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.response.AlipayFundAuthOperationDetailQueryResponse;
import com.alipay.api.response.AlipayFundAuthOrderFreezeResponse;
import com.alipay.api.response.AlipayFundAuthOrderUnfreezeResponse;
import com.alipay.api.response.AlipayFundAuthOrderVoucherCreateResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import pers.pete.alipay.enums.Charset;
import pers.pete.alipay.enums.SignType;
import pers.pete.alipay.request.FundAuthQueryParam;
import pers.pete.alipay.request.FundAuthOrderFreezeParam;
import pers.pete.alipay.request.FundAuthTradePayParam;
import pers.pete.alipay.request.TradePayParam;
import pers.pete.alipay.request.TradePrecreateParam;
import pers.pete.alipay.request.TradeRefundParam;
import pers.pete.alipay.request.FundAuthOrderUnFreezeParam;
import pers.pete.alipay.request.FundAuthOrderVoucherCreateParam;

@Slf4j
public class AlipayTemplate {

  private FundAuthService fundAuthService;

  private TradeService tradeService;

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
    fundAuthService = new FundAuthService(appid, alipayClient);
    tradeService = new TradeService(alipayClient);
  }

  /**
   * auth-1 资金授权冻结(商户扫用户).
   */
  public AlipayFundAuthOrderFreezeResponse fundAuthOrderFreeze(FundAuthOrderFreezeParam param) throws AlipayApiException {
    return fundAuthService.fundAuthOrderFreeze(param);
  }

  /**
   * auth-2 资金授权发码(用户扫商户).
   */
  public AlipayFundAuthOrderVoucherCreateResponse fundAuthOrderVoucherCreate(FundAuthOrderVoucherCreateParam param) throws AlipayApiException {
    return fundAuthService.fundAuthOrderVoucherCreate(param);
  }

  /**
   * auth-3 资金授权解冻.
   */
  public AlipayFundAuthOrderUnfreezeResponse fundAuthOrderUnFreeze(FundAuthOrderUnFreezeParam param) throws AlipayApiException {
    return fundAuthService.fundAuthOrderUnFreeze(param);
  }

  /**
   * auth-4 交易创建并支付(预售权转消费).
   */
  public AlipayTradePayResponse fundAuthTradePay(FundAuthTradePayParam param) throws AlipayApiException {
    return fundAuthService.tradePay(param);
  }

  /**
   * auth-5 资金授权操作查询.
   */
  public AlipayFundAuthOperationDetailQueryResponse fundAuthQuery(FundAuthQueryParam param) throws AlipayApiException {
    return fundAuthService.fundAuthQuery(param);
  }

  /**
   * trade-1 交易结果查询.
   */
  public AlipayTradeQueryResponse tradeQuery(String outTradeNo, String appAuthToken) throws AlipayApiException {
    return tradeService.tradeQuery(outTradeNo, appAuthToken);
  }

  /**
   * trade-2 线下交易预创建.(用户扫商户)
   */
  public AlipayTradePrecreateResponse tradePercreate(TradePrecreateParam param) throws AlipayApiException {
    return tradeService.tradePercreate(param);
  }

  /**
   * trade-3 交易支付(商户扫用户).
   */
  public AlipayTradePayResponse tradePay(TradePayParam param) throws AlipayApiException {
    return tradeService.tradePay(param);
  }

  /**
   * trade-4 交易退款.
   */
  public AlipayTradeRefundResponse tradeRefund(TradeRefundParam param) throws AlipayApiException {
    return fundAuthService.tradeRefund(param);
  }

  /**
   * trade-5 交易关闭接口.
   */
  public AlipayTradeCloseResponse tradeClose(String tradeNo, String outTradeNo, String appAuthToken) throws AlipayApiException {
    return tradeService.tradeClose(tradeNo, outTradeNo, appAuthToken);
  }
}
