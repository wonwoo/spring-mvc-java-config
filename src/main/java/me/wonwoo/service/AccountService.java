package me.wonwoo.service;

import me.wonwoo.domain.Account;
import me.wonwoo.domain.Product;
import me.wonwoo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by wonwoo on 2016. 3. 19..
 */
@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Transactional
  public Account save(Account account) {
    accountRepository.save(account);
    throw new RuntimeException();
  }

  @Transactional
  public Account update(Long id, Account account) {
    Account oldAccount = accountRepository.findOne(id);
    if (!StringUtils.hasText(account.getName())) {
      account.setName(oldAccount.getName());
    }
    return accountRepository.save(account);
  }

  @Transactional
  public void delete(Long id) {
    accountRepository.delete(id);
  }


  @Transactional(readOnly = true)
  public Account findOne(Long id) {
    Account one = accountRepository.findOne(id);
    Product order = one.getOrder();
    order.getOrderedName();
    return one;
  }
}
