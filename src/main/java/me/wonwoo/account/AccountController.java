package me.wonwoo.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
  public Account createAccount(@RequestBody Account account) {
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
}
