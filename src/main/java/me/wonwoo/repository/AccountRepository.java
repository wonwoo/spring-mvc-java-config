package me.wonwoo.repository;

import me.wonwoo.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wonwoo on 2016. 3. 19..
 */
@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{
}
