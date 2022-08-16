package dev.ahernandez.checkoutresourceapi.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration file.
 */
@Configuration
public class SecurityConfig {

  /**
   * SecurityFilter chain implementation.
   *
   * @param http security
   * @return SecurityFilterChain
   * @throws Exception general
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("/actuator/health").permitAll()
            .antMatchers("/api/product/get/all").permitAll()
            .anyRequest().authenticated().and().oauth2ResourceServer().jwt();
    return http.build();
  }
}
