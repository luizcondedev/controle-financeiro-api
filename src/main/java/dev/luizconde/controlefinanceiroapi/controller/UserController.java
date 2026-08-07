package dev.luizconde.controlefinanceiroapi.controller;

import dev.luizconde.controlefinanceiroapi.dto.UserRequestDTO;
import dev.luizconde.controlefinanceiroapi.dto.UserResponseDTO;
import dev.luizconde.controlefinanceiroapi.entity.User;
import dev.luizconde.controlefinanceiroapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAllUsers(){
        return ResponseEntity.ok(service.findAllUsers());
    }
}
