## 预售权相关接口调用示例

```
@RestController
@RequestMapping("/fund_auth")
@Slf4j
@Api("支付宝预售权")
public class FundAuthApi {

  @Autowired
  private AlipayTemplate alipayTemplate;

  @PostMapping("/anotify")
  public String anotify(HttpServletRequest request) {
    /*
     * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号；
     * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）；
     * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）；
     * 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明同步校验结果是无效的，只有全部验证通过后，才可以认定买家付款成功。
     * */
    return "success";
  }

  @PostMapping("/auth-2")
  @ApiOperation("资金授权发码-用户扫商家")
  public AlipayFundAuthOrderVoucherCreateResponse fundAuthOrderVoucherCreate() throws AlipayApiException {
    String orderNo = String.valueOf(System.currentTimeMillis());
    FundAuthOrderVoucherCreateParam param = new FundAuthOrderVoucherCreateParam();
    param.setOutOrderNo(orderNo);
    param.setOutRequestNo(orderNo);
    param.setOrderTitle("voucher-" + orderNo);
    param.setAmount("0.01");
    param.setPayTimeOut("5m");
    // param.setNotifyUrl("http://ip:host/test/anotify");
    return alipayTemplate.fundAuthOrderVoucherCreate(param);
  }

  @PostMapping("/auth-1")
  @ApiOperation("资金授权冻结-商家扫用户")
  public AlipayFundAuthOrderFreezeResponse fundAuthOrderFreeze(@RequestParam String authCode) throws AlipayApiException {
    String orderNo = String.valueOf(System.currentTimeMillis());
    FundAuthOrderFreezeParam param = new FundAuthOrderFreezeParam();
    param.setOutOrderNo(orderNo);
    param.setOutRequestNo(orderNo);
    param.setAmount("0.01");
    param.setOrderTitle("freeze-" + orderNo);
    param.setAuthCode(authCode);
    return alipayTemplate.fundAuthOrderFreeze(param);
  }

  @PostMapping("/auth-3")
  @ApiOperation("资金授权解冻")
  public AlipayFundAuthOrderUnfreezeResponse fundAuthOrderUnFreeze(@RequestBody FundAuthOrderUnFreezeParam param) throws AlipayApiException {
    return alipayTemplate.fundAuthOrderUnFreeze(param);
  }

  @PostMapping("/auth-4")
  @ApiOperation("交易创建并支付")
  public AlipayTradePayResponse fundAuthTradePay(@RequestBody FundAuthTradePayParam param) throws AlipayApiException {
    return alipayTemplate.fundAuthTradePay(param);
  }

  @PostMapping("/trade-4")
  @ApiOperation("退款")
  public AlipayTradeRefundResponse tradeRefund(@RequestBody TradeRefundParam param) throws AlipayApiException {
    return alipayTemplate.tradeRefund(param);
  }

  @PostMapping("/trade-5")
  @ApiOperation("交易关闭")
  public AlipayTradeCloseResponse tradeClose(@RequestParam String outTradeNo, @RequestParam(required = false) String appAuthToken) throws AlipayApiException {
    return alipayTemplate.tradeClose(null, outTradeNo, appAuthToken);
  }

  @GetMapping("/auth-5")
  @ApiOperation("查询 用于auth-2判断用户是否支付")
  public AlipayFundAuthOperationDetailQueryResponse fundAuthQuery(@ModelAttribute FundAuthQueryParam param) throws AlipayApiException {
    return alipayTemplate.fundAuthQuery(param);
  }
}
```