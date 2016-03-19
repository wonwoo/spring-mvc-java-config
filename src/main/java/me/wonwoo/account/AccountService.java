package me.wonwoo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by wonwoo on 2016. 3. 19..
 */
@Service
@Transactional
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  public Account save(Account account){
    return accountRepository.save(account);
  }

  public Account update(Long id, Account account){
    Account oldAccount = accountRepository.findOne(id);
    if(!StringUtils.hasText(account.getName())){
      account.setName(oldAccount.getName());
    }
    return accountRepository.save(account);
  }
  public void delete(Long id){
    accountRepository.delete(id);
  }
}
