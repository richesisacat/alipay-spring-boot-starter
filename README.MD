# 对支付宝支付接口的简单封装

## 更新记录
[CHANGELOG](./CHANGELOG.MD)

## 配置说明
### maven
```
<dependency>
    <groupId>pers.pete</groupId>
    <artifactId>alipay-spring-boot-starter</artifactId>
    <version>0.0.2</version>
</dependency>
```

### 配置文件application.yml
```
alipay:
  appid: 
  charset: utf_8
  alipay-public-key: 
  app-private-key: 
  sign-type: rsa2
```
也可不配置，手动注册bean
```
@Configuration
public class xxxConfig {

    public AlipayTemplate alipayTemplate() {
        return new AlipayTemplate(appid, appPrivateKey, charset, alipayPublicKey, signType);
    }
}

```

## 流程说明
### 一. 预售权流程
#### 1. 支付
- 商家扫用户
  1. "auth-1 fundAuthOrderFreeze 资金授权冻结"
- 用户扫商家
  1. "auth-2 fundAuthOrderVoucherCreate 资金授权发码"，若参数包含回调则不用执行下步
  1. 轮询"auth-5 fundAuthQuery 资金授权操作查询"，判断用户是否支付，判断标准：status="SUCCESS"

#### 2. 解冻
> 当用户无消费时需要将冻结的金额全部返还用户 
- "auth-3 fundAuthOrderUnFreeze 资金授权解冻"

注意:入参中<br>
buyerId (买家id) 来源:在预授权冻结接口、查询接口中均有返回.<br>
sellerId (卖家支付宝uid) 来源:在支付宝商户账户管理-角色身份中的PID.

#### 3. 转消费
> 当用户消费时，将部分或全部金额转消费，并返还剩余金额
- "auth-4 fundAuthTradePay 交易创建并支付"

#### 4. 退款
> 当转消费后，可将消费的金额退款给用户
- "trade-4 tradeRefund 交易同步退款"

#### 5. 查询
> 过程中随时可调查询接口，查询流程相关信息
- "auth-5 fundAuthQuery 资金授权操作查询"

#### 6. 接口[调用示例](./FundAuthExample.MD)

### 二. 当面付流程
#### 1. 支付
- 商家扫用户
  1. "trade-2 tradePay 交易支付"
- 用户扫商家
  1. "trade-3 tradePercreate 线下交易预创建"，若参数包含回调则不用执行下步
  1. 轮询"trade-1 tradeQuery 交易结果查询"，判断用户是否支付，判断标准：tradeStatus="TRADE_SUCCESS"

#### 2. 退款
- "trade-4 tradeRefund 交易同步退款"

#### 3. 交易关闭
- "trade-5 tradeClose 交易关闭"

#### 6. 接口[调用示例](./TradeExample.MD)

### 三、刷脸付

#### 流程
移动端获取设备信息-->face-1 人脸初始化-->移动端唤起人脸识别-->face-2 扫脸统一收单交易支付

#### face-1
入参：zolozGetMetaInfo接口返回的metainfo对象中加入业务参数

#### face-2
trade-2类似，authCode值换成移动端获得的ftoken

