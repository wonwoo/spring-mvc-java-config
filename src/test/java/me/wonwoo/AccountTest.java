package me.wonwoo;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.wonwoo.domain.Account;
import me.wonwoo.config.MvcConfiguration;
import me.wonwoo.config.RootConfiguration;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by wonwoo on 2016. 3. 19..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfiguration.class, MvcConfiguration.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.JVM)
@Transactional
public class AccountTest {


  MockMvc mockMvc;

  @Autowired
  private WebApplicationContext wac;


  @Autowired
  private ObjectMapper objectMapper;

  @Before
  public void before() {
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  public void accounts() throws Exception {
    mockMvc.perform(get("/account/"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.[0].name", is("wonwoo")))
      .andExpect(jsonPath("$.[1].name", is("kevin")));
  }

  @Test
  public void account() throws Exception {
    mockMvc.perform(get("/account/1"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.name", is("wonwoo")));
  }

  @Test
  public void createAccount() throws Exception {
    Account account = new Account();
    account.setId(3L);
    account.setName("wonwoo123");
    mockMvc.perform(post("/account/").contentType(MediaType.APPLICATION_JSON_UTF8).content(objectMapper.writeValueAsString(account)))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.name", is("wonwoo123")))
      .andExpect(jsonPath("$.id", is(3)));
  }

  @Test
  public void updateAccount() throws Exception {
    Account account = new Account();
    account.setName("wonwoo1010");
    mockMvc.perform(patch("/account/{id}", 3).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(account)))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.name", is("wonwoo1010")));
  }

  @Test
  public void deleteAccount() throws Exception {
    mockMvc.perform(delete("/account/{id}", 2))
      .andDo(print())
      .andExpect(status().isOk());
  }
}
