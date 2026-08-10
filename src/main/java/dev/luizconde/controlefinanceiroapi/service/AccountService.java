package dev.luizconde.controlefinanceiroapi.service;

import dev.luizconde.controlefinanceiroapi.dto.AccountRequestDTO;
import dev.luizconde.controlefinanceiroapi.dto.AccountResponseDTO;
import dev.luizconde.controlefinanceiroapi.entity.Account;
import dev.luizconde.controlefinanceiroapi.entity.User;
import dev.luizconde.controlefinanceiroapi.enums.AccountStatusEnum;
import dev.luizconde.controlefinanceiroapi.exception.ResourceNotFoundException;
import dev.luizconde.controlefinanceiroapi.exception.UserNotFoundException;
import dev.luizconde.controlefinanceiroapi.mapper.AccountMapper;
import dev.luizconde.controlefinanceiroapi.repository.AccountRepository;
import dev.luizconde.controlefinanceiroapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper mapper;

    @Transactional
    public AccountResponseDTO createAccount(AccountRequestDTO requestDTO,
                                            Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found by id: " + userId)
        );

        Account account = mapper.toEntity(requestDTO);
        account.setAccountStatus(AccountStatusEnum.ATIVA);
        account.setUser(user);
        account.setCurrentBalance(account.getInitialBalance());
        account.setCreateDate(LocalDateTime.now());

        return mapper.toResponse(accountRepository.save(account));
    }

    @Transactional(readOnly = true)
    public List<AccountResponseDTO> findAllAccounts(){
        return accountRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public AccountResponseDTO findAccountById(Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Not Found Account by id: " + id)
                );

        return mapper.toResponse(account);
    }

    @Transactional(readOnly = true)
    public List<AccountResponseDTO> findAccountsByUserId(Long userId){
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("Not found user by id: " + userId);
        }

        List<Account> accountList = accountRepository.findByUserId(userId);

        return accountList
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
