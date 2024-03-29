package pers.pete.alipay.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.domain.ZolozAuthenticationCustomerSmilepayInitializeModel;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.ZolozAuthenticationCustomerSmilepayInitializeRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.ZolozAuthenticationCustomerSmilepayInitializeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import pers.pete.alipay.request.FaceInitializeParam;
import pers.pete.alipay.request.TradePayParam;

@Slf4j
public class FaceService {

  private AlipayClient alipayClient;

  public FaceService(AlipayClient alipayClient) {
    this.alipayClient = alipayClient;
  }

  /**
   * 人脸初始化唤起zim.
   * <p>
   * https://docs.open.alipay.com/api_46/zoloz.authentication.customer.smilepay.initialize
   */
  public ZolozAuthenticationCustomerSmilepayInitializeResponse faceInitialize(FaceInitializeParam param) throws AlipayApiException {
    ZolozAuthenticationCustomerSmilepayInitializeRequest request = new ZolozAuthenticationCustomerSmilepayInitializeRequest();
    ZolozAuthenticationCustomerSmilepayInitializeModel model = new ZolozAuthenticationCustomerSmilepayInitializeModel();
    model.setZimmetainfo(param.getZimmetainfo());
    request.setBizModel(model);
    if (!StringUtils.isEmpty(param.getAppAuthToken())) {
      request.putOtherTextParam("app_auth_token", param.getAppAuthToken());
    }
    ZolozAuthenticationCustomerSmilepayInitializeResponse response = alipayClient.execute(request);
    log.info("response:  {}" + response.getBody());
    return response;
  }

  /**
   * 统一收单交易支付.
   * <p>
   * https://docs.open.alipay.com/api_1/alipay.trade.pay
   */
  public AlipayTradePayResponse faceTradePay(TradePayParam param) throws AlipayApiException {
    AlipayTradePayRequest request = new AlipayTradePayRequest();
    AlipayTradePayModel model = new AlipayTradePayModel();
    model.setOutTradeNo(param.getOutTradeNo());
    model.setAuthCode(param.getAuthCode());
    model.setScene("security_code");
    model.setSubject(param.getSubject());
    model.setTotalAmount(param.getTotalAmount());
    model.setBody(param.getBody());
    model.setTimeoutExpress(param.getTimeoutExpress());
    model.setStoreId(param.getStoreId());
    request.setBizModel(model);

    if (!StringUtils.isEmpty(param.getAppAuthToken())) {
      request.putOtherTextParam("app_auth_token", param.getAppAuthToken());
    }
    AlipayTradePayResponse response = alipayClient.execute(request);
    log.info("response:  {}" + response.getBody());
    return response;
  }
}
