package pers.pete.alipay;

import com.alipay.api.AlipayClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlipayService {

  private AlipayClient alipayClient;

  public AlipayService(AlipayClient alipayClient) {
    this.alipayClient = alipayClient;
  }


}
