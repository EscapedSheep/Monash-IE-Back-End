package com.treepal.config;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements ApplicationContextAware {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.requiresChannel()
      .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
      .requiresSecure();
    
    http
    .csrf().disable();
  }
  
  @Override
  public void configure(WebSecurity web) throws Exception {

      web.ignoring().antMatchers("/index/*","/map/*, /assets/*, /gameAssets/*, /CSS/*, /JS/*");
  }
  
  

}
