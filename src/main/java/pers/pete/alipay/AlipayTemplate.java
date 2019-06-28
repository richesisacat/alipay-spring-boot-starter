package pers.pete.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayFundAuthOperationDetailQueryResponse;
import com.alipay.api.response.AlipayFundAuthOrderFreezeResponse;
import com.alipay.api.response.AlipayFundAuthOrderUnfreezeResponse;
import com.alipay.api.response.AlipayFundAuthOrderVoucherCreateResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
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
    alipayAuthService = new AlipayAuthService(appid, appPrivateKey, charset, alipayPublicKey, signType);
  }

  /**
   * 资金授权冻结接口(商户扫用户).
   */
  public AlipayFundAuthOrderFreezeResponse fundAuthOrderFreeze(OrderFreezeParam param) throws AlipayApiException {
    return alipayAuthService.fundAuthOrderFreeze(param);
  }

  /**
   * 资金授权发码(用户扫商户).
   */
  public AlipayFundAuthOrderVoucherCreateResponse fundAuthOrderVoucherCreate(VoucherParam param) throws AlipayApiException {
    return alipayAuthService.fundAuthOrderVoucherCreate(param);
  }

  /**
   * 资金授权解冻.
   */
  public AlipayFundAuthOrderUnfreezeResponse fundAuthOrderUnFreeze(UnfreezeParam param) throws AlipayApiException {
    return alipayAuthService.fundAuthOrderUnFreeze(param);
  }

  /**
   * 交易创建并支付接口(预售权转消费).
   */
  public AlipayTradePayResponse tradePay(TradePayParam param) throws AlipayApiException {
    return alipayAuthService.tradePay(param);
  }

  /**
   * 资金授权操作查询.
   */
  public AlipayFundAuthOperationDetailQueryResponse fundAuthQuery(DetailParam param) throws AlipayApiException {
    return alipayAuthService.fundAuthQuery(param);
  }

  /**
   * 交易同步退款接口.
   */
  public AlipayTradeRefundResponse tradeRefund(TradeRefundParam param) throws AlipayApiException {
    return alipayAuthService.tradeRefund(param);
  }

  /**
   * 交易结果接口.
   */
  public AlipayTradeQueryResponse tradeQuery(String outTradeNo, String appAuthToken) throws AlipayApiException {
    return alipayAuthService.tradeQuery(outTradeNo, appAuthToken);
  }

  /**
   * 交易关闭接口.
   */
  public AlipayTradeCloseResponse tradeClose(String outTradeNo, String appAuthToken) throws AlipayApiException {
    return alipayAuthService.tradeClose(outTradeNo, appAuthToken);
  }
}
