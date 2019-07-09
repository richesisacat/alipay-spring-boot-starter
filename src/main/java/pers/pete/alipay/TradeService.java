package pers.pete.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import pers.pete.alipay.request.TradePayParam;
import pers.pete.alipay.request.TradePrecreateParam;

@Slf4j
public class TradeService {

  private AlipayClient alipayClient;

  public TradeService(AlipayClient alipayClient) {
    this.alipayClient = alipayClient;
  }

  /**
   * 统一收单线下交易预创建.
   * <p>
   * https://docs.open.alipay.com/api_1/alipay.trade.precreate
   */
  public AlipayTradePrecreateResponse tradePercreate(TradePrecreateParam param) throws AlipayApiException {
    AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
    AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
    model.setOutTradeNo(param.getOutOrderNo());
    model.setTotalAmount(param.getTotalAmount());
    model.setSubject(param.getSubject());
    model.setBody(param.getBody());
    model.setProductCode("FACE_TO_FACE_PAYMENT");
    model.setTimeoutExpress(param.getTimeoutExpress());
    request.setBizModel(model);
    if (!StringUtils.isEmpty(param.getAppAuthToken())) {
      request.putOtherTextParam("app_auth_token", param.getAppAuthToken());
    }
    if (!StringUtils.isEmpty(param.getNotifyUrl())) {
      request.setNotifyUrl(param.getNotifyUrl());
    }
    AlipayTradePrecreateResponse response = alipayClient.execute(request);
    log.info("response:  {}" + response.getBody());
    return response;
  }

  /**
   * 交易结果接口.
   * <p>
   * https://docs.open.alipay.com/api_1/alipay.trade.query
   */
  public AlipayTradeQueryResponse tradeQuery(String outTradeNo, String appAuthToken) throws AlipayApiException {
    AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
    AlipayTradeQueryModel model = new AlipayTradeQueryModel();
    model.setOutTradeNo(outTradeNo);
    request.setBizModel(model);
    if (!StringUtils.isEmpty(appAuthToken)) {
      request.putOtherTextParam("app_auth_token", appAuthToken);
    }
    AlipayTradeQueryResponse response = alipayClient.execute(request);
    log.info("response: {}" + response.getBody());
    return response;
  }

  /**
   * 交易关闭接口.
   * <p>
   * https://docs.open.alipay.com/api_1/alipay.trade.close/
   */
  public AlipayTradeCloseResponse tradeClose(String tradeNo, String outTradeNo, String appAuthToken) throws AlipayApiException {
    AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
    AlipayTradeCloseModel model = new AlipayTradeCloseModel();
    model.setTradeNo(tradeNo);
    model.setOutTradeNo(outTradeNo);
    request.setBizModel(model);
    if (!StringUtils.isEmpty(appAuthToken)) {
      request.putOtherTextParam("app_auth_token", appAuthToken);
    }
    AlipayTradeCloseResponse response = alipayClient.execute(request);
    log.info("response: {}" + response.getBody());
    return response;
  }

  /**
   * 统一收单交易支付.
   * <p>
   * https://docs.open.alipay.com/api_1/alipay.trade.pay
   */
  public AlipayTradePayResponse tradePay(TradePayParam param) throws AlipayApiException {
    AlipayTradePayRequest request = new AlipayTradePayRequest();
    AlipayTradePayModel model = new AlipayTradePayModel();
    model.setOutTradeNo(param.getOutTradeNo());
    model.setScene("bar_code");
    model.setProductCode("FACE_TO_FACE_PAYMENT");
    model.setSubject(param.getSubject());
    model.setTotalAmount(param.getTotalAmount());
    model.setTransCurrency("CNY");
    model.setBody(param.getBody());
    model.setAuthCode(param.getAuthCode());
    if (!StringUtils.isEmpty(param.getAppAuthToken())) {
      request.putOtherTextParam("app_auth_token", param.getAppAuthToken());
    }
    if (!StringUtils.isEmpty(param.getNotifyUrl())) {
      request.setNotifyUrl(param.getNotifyUrl());
    }
    AlipayTradePayResponse response = alipayClient.execute(request);
    log.info("response:  {}" + response.getBody());
    return response;
  }

}
