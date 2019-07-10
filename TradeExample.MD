## 当面付相关接口调用示例

```
@RestController
@RequestMapping("/trade")
@Slf4j
@Api("支付宝交易")
public class TradeApi {

  @Autowired
  private AlipayTemplate alipayTemplate;

  @GetMapping("/trade-1")
  @ApiOperation("交易结果查询")
  public AlipayTradeQueryResponse tradeQuery(@RequestParam String outTradeNo,
                                             @RequestParam(required = false) String appAuthToken) throws AlipayApiException {
    return alipayTemplate.tradeQuery(outTradeNo, appAuthToken);
  }

  @PostMapping("/trade-2")
  @ApiOperation("交易支付-商户扫用户")
  public AlipayTradePayResponse tradePay(@RequestParam String authCode) throws AlipayApiException {
    String orderNo = String.valueOf(System.currentTimeMillis());
    TradePayParam param = new TradePayParam();
    param.setAuthCode(authCode);
    param.setTotalAmount("0.01");
    param.setOutTradeNo(orderNo);
    param.setSubject("trade-" + orderNo);
    return alipayTemplate.tradePay(param);
  }

  @PostMapping("/trade-3")
  @ApiOperation("线下交易预创建-用户扫商户")
  public AlipayTradePrecreateResponse tradePercreate() throws AlipayApiException {
    String orderNo = String.valueOf(System.currentTimeMillis());
    TradePrecreateParam param = new TradePrecreateParam();
    param.setOutOrderNo(orderNo);
    param.setBody("trade-" + orderNo);
    param.setSubject("trade-" + orderNo);
    param.setTotalAmount("0.01");
    param.setTimeoutExpress("5m");
    return alipayTemplate.tradePercreate(param);
  }

  @PostMapping("/trade-4")
  @ApiOperation("交易退款")
  public AlipayTradeRefundResponse tradeRefund(@RequestBody TradeRefundParam param) throws AlipayApiException {
    return alipayTemplate.tradeRefund(param);
  }

  @PostMapping("/trade-5")
  @ApiOperation("交易关闭接口")
  public AlipayTradeCloseResponse tradeClose(String tradeNo, String outTradeNo, String appAuthToken) throws AlipayApiException {
    return alipayTemplate.tradeClose(tradeNo, outTradeNo, appAuthToken);
  }
}

```