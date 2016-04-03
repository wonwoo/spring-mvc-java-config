package me.wonwoo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.wonwoo.domain.Account;
import me.wonwoo.repository.AccountRepository;
import me.wonwoo.domain.Product;
import me.wonwoo.repository.ProductRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

/**
 * Created by wonwoo on 2016. 3. 19..
 */
@Configuration
@EnableWebMvc
@ComponentScan(
  basePackages = "me.wonwoo.controller"
)
public class MvcConfiguration extends WebMvcConfigurerAdapter {

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private ProductRepository productRepository;

  @Bean
  public InitializingBean initializingBean() {
    return () -> {

      Product macbook = new Product(1L, "macbook");
      Product macbook2 = new Product(2L, "imac");
      Arrays.asList(macbook,macbook2).forEach(productRepository::save);

      Arrays.asList(
        new Account(1L, "wonwoo", macbook),
        new Account(2L, "kevin", macbook2)
      ).forEach(accountRepository::save);
    };
  }
}
