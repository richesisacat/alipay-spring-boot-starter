# 对支付宝支付接口的简单封装

## 配置说明
### 依赖
```
<dependency>
    <groupId>pers.pete</groupId>
    <artifactId>alipay-spring-boot-starter</artifactId>
    <version>0.0.1</version>
</dependency>
```

### 配置文件application.yml，若参数包含回调则不用执行下步
```
alipay:
  appid: 
  charset: utf_8
  alipay-public-key: 
  app-private-key: 
  sign-type: rsa2
```

## 流程说明
### 一. 预售权流程
#### 1. 支付
- 用户扫商家
  1. "auth-2 fundAuthOrderVoucherCreate 资金授权发码"，若参数包含回调则不用执行下步
  1. 轮询"auth-5 fundAuthQuery 资金授权操作查询"，判断用户是否支付
- 商家扫用户
  1. "auth-1 fundAuthOrderFreeze 资金授权冻结"
  
#### 2. 解冻
> 当用户无消费时需要将冻结的金额全部返还用户 
- "auth-3 fundAuthOrderUnFreeze 资金授权解冻"

#### 3. 转消费
> 当用户消费时，将部分或全部金额转消费，并返还剩余金额
- "auth-4 fundAuthTradePay 交易创建并支付"

#### 4. 退款
> 当转消费后，可将消费的金额退款给用户
- "trade-4 tradeRefund 交易同步退款"

#### 5. 查询
> 过程中随时可调查询接口，查询流程相关信息
- "auth-5 fundAuthQuery 资金授权操作查询"

#### 6. 预售权相关接口[调用示例](./FundAuthExample.MD)

### 二. 当面付流程
#### 1. 支付
- 用户扫商家
  1. "trade-2 tradePercreate 线下交易预创建"，若参数包含回调则不用执行下步
  1. 轮询"trade-1 tradeQuery 交易结果查询"，判断用户是否支付
- 商家扫用户
  1. "trade-3 tradePay 交易支付"
  1. 轮询"trade-1 tradeQuery 交易结果查询"，判断用户是否支付

#### 2. 退款
- "trade-4 tradeRefund 交易同步退款"

#### 3. 交易关闭
- "trade-5 tradeClose 交易关闭"
