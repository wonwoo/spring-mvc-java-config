package me.wonwoo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.wonwoo.account.Account;
import me.wonwoo.account.AccountRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

/**
 * Created by wonwoo on 2016. 3. 19..
 */
@Configuration
@EnableWebMvc
@ComponentScan(
  basePackages = "me.wonwoo.account"
)
public class MvcConfiguration extends WebMvcConfigurerAdapter {

  @Bean
  @Primary
  public ObjectMapper jacksonObjectMapper() {
    return new ObjectMapper();
  }

  @Autowired
  private AccountRepository accountRepository;

  @Bean
  public InitializingBean initializingBean() {
    return () -> {
      Arrays.asList(
        new Account(1L, "wonwoo"),
        new Account(2L, "kevin")
      ).forEach(accountRepository::save);
    };
  }
}
