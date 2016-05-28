package me.wonwoo.controller;

import lombok.extern.slf4j.Slf4j;
import me.wonwoo.domain.Account;
import me.wonwoo.domain.OrderType;
import me.wonwoo.repository.AccountRepository;
import me.wonwoo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by wonwoo on 2016. 3. 19..
 */
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private AccountService accountService;

  @RequestMapping("/")
  public List<Account> accounts() {
    List<Account> accounts = accountRepository.findAll();
    accounts.stream().map(x -> x.toString()).forEach(log::debug);
    return accounts;
  }

  @RequestMapping("/{id}")
  public Account account(@PathVariable Long id) {
    return accountService.findOne(id);
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public Account createAccount(@RequestBody @Valid Account account, BindingResult result) {
    if(result.hasErrors()){
      throw new IllegalArgumentException();
    }
    return accountService.save(account);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
  public Account updateAccount(@PathVariable Long id, @RequestBody Account account) {
    return accountService.update(id, account);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteAccount(@PathVariable Long id) {
    accountService.delete(id);
  }

  @RequestMapping(value = "/test")
  public void converterTest(@RequestParam OrderType orderType) {
    System.out.println(orderType);
  }
}
