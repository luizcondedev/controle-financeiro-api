package dev.luizconde.controlefinanceiroapi.controller;

import dev.luizconde.controlefinanceiroapi.dto.AccountRequestDTO;
import dev.luizconde.controlefinanceiroapi.dto.AccountResponseDTO;
import dev.luizconde.controlefinanceiroapi.dto.AccountUpdateRequestDTO;
import dev.luizconde.controlefinanceiroapi.dto.UpdateStatusAccountDTO;
import dev.luizconde.controlefinanceiroapi.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AccountController {
    private final AccountService service;

    @PostMapping("/users/{userId}/accounts")
    public ResponseEntity<AccountResponseDTO> createAccount(@Valid @RequestBody AccountRequestDTO requestDTO,
                                                            @PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAccount(requestDTO, userId));
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountResponseDTO>> findAllAccounts(){
        return ResponseEntity.ok(service.findAllAccounts());
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountResponseDTO> findAccountById(@PathVariable Long id){
        return ResponseEntity.ok(service.findAccountById(id));
    }

    @GetMapping("/users/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDTO>> findAccountsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(service.findAccountsByUserId(userId));
    }

    @PatchMapping("/accounts/{id}")
    public ResponseEntity<AccountResponseDTO> updateAccount(@PathVariable Long id,
                                                            @Valid @RequestBody AccountUpdateRequestDTO dto){
        return ResponseEntity.ok(service.updateAccount(dto, id));
    }

    @PatchMapping("/accounts/{id}/status")
    public ResponseEntity<AccountResponseDTO> updateStatusAccount(@PathVariable Long id,
                                                                  @RequestBody UpdateStatusAccountDTO dto){
        return ResponseEntity.ok(service.updateStatusAccount(dto, id));
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        return ResponseEntity.ok(service.deleteAccount(id));
    }
}
