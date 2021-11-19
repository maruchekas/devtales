package org.skillbox.devtales.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class InitResponse {
  @Value("${blog.title}")
  private String title;
  @Value("${blog.subtitle}")
  private String subtitle;
  @Value("${blog.phone}")
  private String phone;
  @Value("${blog.email}")
  @JsonProperty("e_mail")
  private String email;
  @Value("${blog.copyright}")
  private String copyright;
  @Value("${blog.copyrightFrom}")
  private String copyrightFrom;

}