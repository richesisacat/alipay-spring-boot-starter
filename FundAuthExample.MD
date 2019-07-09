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
        Map<String, String> params = convertRequestParamsToMap(request); // 将异步通知中收到的待验证所有参数都存放到map中
        log.info("map= {}", params);
        /*
         * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号；
         * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）；
         * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）；
         * 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明同步校验结果是无效的，只有全部验证通过后，才可以认定买家付款成功。
         * */
        return "success";
    }

    @GetMapping("/0")
    @ApiOperation("资金授权操作查询")
    public AlipayFundAuthOperationDetailQueryResponse fundAuthQuery(@ModelAttribute DetailParam param) throws AlipayApiException {
        return alipayTemplate.fundAuthQuery(param);
    }

    @PostMapping("/1_1")
    @ApiOperation("资金授权发码-用户扫商户")
    public AlipayFundAuthOrderVoucherCreateResponse fundAuthOrderVoucherCreate() throws AlipayApiException {
        String orderNo = String.valueOf(System.currentTimeMillis());
        VoucherParam param = new VoucherParam();
        param.setOutOrderNo("out_order_no_" + orderNo);
        param.setOutRequestNo("out_request_no_" + orderNo);
        param.setOrderTitle("voucher-" + orderNo);
        param.setAmount("0.01");
        param.setPayTimeOut("5m");
        // param.setNotifyUrl("http://ip:host/test/anotify");
        return alipayTemplate.fundAuthOrderVoucherCreate(param);
    }

    @GetMapping("1_1_1")
    @ApiOperation("用户扫商户时，若未指定回调，可通过此接口主动查询用户支付状态")
    public AlipayTradeQueryResponse tradeQuery(@RequestParam String outTradeNo, @RequestParam String appAuthToken) throws AlipayApiException {
        return alipayTemplate.tradeQuery(outTradeNo, appAuthToken);
    }

    @PostMapping("/1_2")
    @ApiOperation("资金授权冻结接口-商户扫用户")
    public AlipayFundAuthOrderFreezeResponse fundAuthOrderFreeze(@RequestParam String authCode) throws AlipayApiException {
        String orderNo = String.valueOf(System.currentTimeMillis());
        OrderFreezeParam param = new OrderFreezeParam();
        param.setOutOrderNo("out_order_no_" + orderNo);
        param.setOutRequestNo("out_request_no_" + orderNo);
        param.setAmount("0.01");
        param.setOrderTitle("freeze-" + orderNo);
        param.setAuthCode(authCode);
        return alipayTemplate.fundAuthOrderFreeze(param);
    }

    @PostMapping("/2_1")
    @ApiOperation("资金授权解冻")
    public AlipayFundAuthOrderUnfreezeResponse fundAuthOrderUnFreeze(@RequestBody UnfreezeParam param) throws AlipayApiException {
        return alipayTemplate.fundAuthOrderUnFreeze(param);
    }

    @PostMapping("/2_2")
    @ApiOperation("预售权转消费")
    public AlipayTradePayResponse tradePay(@RequestBody TradePayParam param) throws AlipayApiException {
        return alipayTemplate.tradePay(param);
    }

    @PostMapping("/3")
    @ApiOperation("交易同步退款接口")
    public AlipayTradeRefundResponse tradeRefund(@RequestBody TradeRefundParam param) throws AlipayApiException {
        return alipayTemplate.tradeRefund(param);
    }

    @PostMapping("/4")
    @ApiOperation("交易关闭接口")
    public AlipayTradeCloseResponse tradeClose(@RequestParam String outTradeNo, @RequestParam String appAuthToken) throws AlipayApiException {
        return alipayTemplate.tradeClose(outTradeNo, appAuthToken);
    }

    // 将request中的参数转换成Map
    private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<>();
        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();
        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }
        return retMap;
    }

}
```