package dev.luizconde.controlefinanceiroapi.repository;

import dev.luizconde.controlefinanceiroapi.entity.Account;
import dev.luizconde.controlefinanceiroapi.enums.AccountStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserId(Long userId);

    List<Account> findAllByUserIdAndAccountStatus(Long userId, AccountStatusEnum accountStatus);
}
