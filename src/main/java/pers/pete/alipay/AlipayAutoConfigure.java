package pers.pete.alipay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(AlipayTemplate.class)
@EnableConfigurationProperties(AlipayProperties.class)
public class AlipayAutoConfigure {

  private AlipayProperties properties;

  @Autowired
  public AlipayAutoConfigure(AlipayProperties properties) {
    this.properties = properties;
  }

  @Bean
  @ConditionalOnMissingBean
  public AlipayTemplate init() {
    return new AlipayTemplate(properties.getAppid(), properties.getAppPrivateKey(), properties.getCharset(),
      properties.getAlipayPublicKey(), properties.getSignType());
  }
}
