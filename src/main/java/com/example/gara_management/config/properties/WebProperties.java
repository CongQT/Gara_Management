package com.example.gara_management.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "web-config")
public class WebProperties {

  private String baseUrl;
  private String verificationPath;

  public String buildVerificationUrl(String key) {
    return baseUrl + verificationPath + "?key=" + key;
  }

}
