package pers.pete.alipay.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayFundAuthOperationDetailQueryModel;
import com.alipay.api.domain.AlipayFundAuthOrderFreezeModel;
import com.alipay.api.domain.AlipayFundAuthOrderUnfreezeModel;
import com.alipay.api.domain.AlipayFundAuthOrderVoucherCreateModel;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayFundAuthOperationDetailQueryRequest;
import com.alipay.api.request.AlipayFundAuthOrderFreezeRequest;
import com.alipay.api.request.AlipayFundAuthOrderUnfreezeRequest;
import com.alipay.api.request.AlipayFundAuthOrderVoucherCreateRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayFundAuthOperationDetailQueryResponse;
import com.alipay.api.response.AlipayFundAuthOrderFreezeResponse;
import com.alipay.api.response.AlipayFundAuthOrderUnfreezeResponse;
import com.alipay.api.response.AlipayFundAuthOrderVoucherCreateResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import pers.pete.alipay.request.FundAuthOrderFreezeParam;
import pers.pete.alipay.request.FundAuthOrderUnFreezeParam;
import pers.pete.alipay.request.FundAuthOrderVoucherCreateParam;
import pers.pete.alipay.request.FundAuthQueryParam;
import pers.pete.alipay.request.FundAuthTradePayParam;
import pers.pete.alipay.request.TradeRefundParam;

@Slf4j
public class FundAuthService {

  private AlipayClient alipayClient;

  public FundAuthService(AlipayClient alipayClient) {
    this.alipayClient = alipayClient;
  }

  /**
   * 资金授权冻结接口.
   * <p>
   * https://docs.open.alipay.com/api_28/alipay.fund.auth.order.freeze
   */
  public AlipayFundAuthOrderFreezeResponse fundAuthOrderFreeze(FundAuthOrderFreezeParam param) throws AlipayApiException {
    AlipayFundAuthOrderFreezeRequest request = new AlipayFundAuthOrderFreezeRequest();
    AlipayFundAuthOrderFreezeModel model = new AlipayFundAuthOrderFreezeModel();
    model.setAuthCode(param.getAuthCode());         //  填写用户支付宝钱包中的付款码
    model.setAuthCodeType("bar_code");              //  填写固定值bar_code
    model.setOutOrderNo(param.getOutOrderNo());     //  预授权冻结交易商户订单号，商户系统唯一标识一笔预授权
    model.setOutRequestNo(param.getOutRequestNo());
    model.setOrderTitle(param.getOrderTitle());     //  预授权标题,会在支付宝账单中显示
    model.setAmount(param.getAmount());             //  预授权金额，注意需要大于等于结算支付金额
    // model.setPayeeUserId(appid);                 //  收款方的支付宝唯一用户号,以2088开头的16位纯数字组成，收款方支付宝登录号(payee_logon_id)和用户号(payee_user_id)不能同时为空。
    model.setPayTimeout(param.getPayTimeOut());     //  预授权冻结超时时间，超时则自动关闭冻结交易
    model.setProductCode("PRE_AUTH");               //  填写固定值PRE_AUTH
    request.setBizModel(model);
    if (!StringUtils.isEmpty(param.getAppAuthToken())) {
      request.putOtherTextParam("app_auth_token", param.getAppAuthToken());
    }
    AlipayFundAuthOrderFreezeResponse response = alipayClient.execute(request);
    log.info("response:  {}" + response.getBody());
    return response;
  }

  /**
   * 资金授权发码.
   * <p>
   * https://docs.open.alipay.com/api_28/alipay.fund.auth.order.voucher.create
   */
  public AlipayFundAuthOrderVoucherCreateResponse fundAuthOrderVoucherCreate(FundAuthOrderVoucherCreateParam param) throws AlipayApiException {
    AlipayFundAuthOrderVoucherCreateRequest request = new AlipayFundAuthOrderVoucherCreateRequest();
    AlipayFundAuthOrderVoucherCreateModel model = new AlipayFundAuthOrderVoucherCreateModel();
    model.setOutOrderNo(param.getOutOrderNo());
    model.setOutRequestNo(param.getOutRequestNo());
    model.setOrderTitle(param.getOrderTitle());
    model.setAmount(param.getAmount());
    model.setPayTimeout(param.getPayTimeOut());
    model.setExtraParam("{\"category\":\"HOTEL\"}");
    model.setProductCode("PRE_AUTH");
    model.setTransCurrency("CNY");
    model.setSettleCurrency("CNY");
    model.setEnablePayChannels("[{\"payChannelType\":\"MONEY_FUND\"},{\"payChannelType\":\"PCREDIT_PAY\"}]");
    request.setBizModel(model);
    if (!StringUtils.isEmpty(param.getAppAuthToken())) {
      request.putOtherTextParam("app_auth_token", param.getAppAuthToken());
    }
    if (!StringUtils.isEmpty(param.getNotifyUrl())) {
      request.setNotifyUrl(param.getNotifyUrl());
    }
    AlipayFundAuthOrderVoucherCreateResponse response = alipayClient.execute(request);
    log.info("response:  {}" + response.getBody());
    return response;
  }


  /**
   * 资金授权解冻.
   * <p>
   * https://docs.open.alipay.com/api_28/alipay.fund.auth.order.unfreeze
   */
  public AlipayFundAuthOrderUnfreezeResponse fundAuthOrderUnFreeze(FundAuthOrderUnFreezeParam param) throws AlipayApiException {
    AlipayFundAuthOrderUnfreezeRequest request = new AlipayFundAuthOrderUnfreezeRequest();
    AlipayFundAuthOrderUnfreezeModel model = new AlipayFundAuthOrderUnfreezeModel();
    model.setAuthNo(param.getAuthNo());               // 支付宝资金授权订单号，在授权冻结成功时返回需要入库保存
    model.setOutRequestNo(param.getOutRequestNo());   // 同一商户每次不同的资金操作请求，商户请求流水号不能重复,且与与冻结流水号不同
    model.setAmount(param.getAmount());               // 本次操作解冻的金额，单位为：元（人民币），精确到小数点后两位
    model.setRemark(param.getRemark());               // 商户对本次解冻操作的附言描述，长度不超过100个字母或50个汉字
    model.setExtraParam("{\"unfreezeBizInfo\":\"{\\\"bizComplete\\\":\\\"true\\\"}\"}");
    request.setBizModel(model);
    if (!StringUtils.isEmpty(param.getAppAuthToken())) {
      request.putOtherTextParam("app_auth_token", param.getAppAuthToken());
    }
    AlipayFundAuthOrderUnfreezeResponse response = alipayClient.execute(request);
    log.info("response:  {}" + response.getBody());
    return response;
  }

  /**
   * 资金授权操作查询接口.
   * <p>
   * https://docs.open.alipay.com/api_28/alipay.fund.auth.operation.detail.query
   */
  public AlipayFundAuthOperationDetailQueryResponse fundAuthQuery(FundAuthQueryParam param) throws AlipayApiException {
    AlipayFundAuthOperationDetailQueryRequest request = new AlipayFundAuthOperationDetailQueryRequest();
    AlipayFundAuthOperationDetailQueryModel model = new AlipayFundAuthOperationDetailQueryModel();
    model.setAuthNo(param.getAuthNo());             // 支付宝资金授权订单号，在授权冻结成功时返回参数中获得
    model.setOperationId(param.getOperationId());   // 支付宝的授权资金操作流水号，冻结成功同步返回
    model.setOutOrderNo(param.getOutOrderNo());     // 商户的授权资金订单号，与支付宝的授权资金订单号不能同时为空
    model.setOutRequestNo(param.getOutRequestNo()); // 商户的授权资金操作流水号，与支付宝的授权资金操作流水号不能同时为空
    request.setBizModel(model);
    if (!StringUtils.isEmpty(param.getAppAuthToken())) {
      request.putOtherTextParam("app_auth_token", param.getAppAuthToken());
    }
    AlipayFundAuthOperationDetailQueryResponse response = alipayClient.execute(request);
    log.info("response: {}" + response.getBody());
    return response;
  }

  /**
   * 交易创建并支付接口.
   * <p>
   * alipay.trade.pay
   */
  public AlipayTradePayResponse tradePay(FundAuthTradePayParam param) throws AlipayApiException {
    AlipayTradePayRequest request = new AlipayTradePayRequest();
    AlipayTradePayModel model = new AlipayTradePayModel();
    model.setOutTradeNo(param.getOutTradeNo());
    model.setScene("bar_code");
    model.setProductCode("PRE_AUTH");
    model.setSubject(param.getSubject());
    model.setBuyerId(param.getBuyerId());
    model.setSellerId(param.getSellerId());
    model.setTotalAmount(param.getTotalAmount());
    model.setBody(param.getBody());
    model.setAuthNo(param.getAuthNo());
    // ExtendParams extendParams = new ExtendParams();
    // extendParams.setSysServiceProviderId("此处填写ISV签约返佣协议账户的PID为2088开头的16位数字");
    // model.setExtendParams(extendParams);   // 系统商开发需要传入正确的返佣参数才能拿到返佣,无返佣协议不需要传入该参数。
    model.setAuthConfirmMode("COMPLETE");     // 自动解冻时取值COMPLETE,不传该参数默认剩余资金不会自动解冻NOT_COMPLETE。
    request.setBizModel(model);
    if (!StringUtils.isEmpty(param.getAppAuthToken())) {
      request.putOtherTextParam("app_auth_token", param.getAppAuthToken());
    }
    AlipayTradePayResponse response = alipayClient.execute(request);
    log.info("response: {}" + response.getBody());
    return response;
  }

}

