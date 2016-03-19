package me.wonwoo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wonwoo on 2016. 3. 19..
 */
@RestController
@RequestMapping("/account")
public class AccountController {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private AccountService accountService;

  @RequestMapping("/")
  public List<Account> accounts() {
    return accountRepository.findAll();
  }

  @RequestMapping("/{id}")
  public Account account(@PathVariable Long id) {
    return accountRepository.findOne(id);
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
