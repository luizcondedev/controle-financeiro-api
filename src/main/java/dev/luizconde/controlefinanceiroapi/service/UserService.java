package dev.luizconde.controlefinanceiroapi.service;

import dev.luizconde.controlefinanceiroapi.dto.UserRequestDTO;
import dev.luizconde.controlefinanceiroapi.dto.UserResponseDTO;
import dev.luizconde.controlefinanceiroapi.dto.UserUpdateRequestDTO;
import dev.luizconde.controlefinanceiroapi.entity.User;
import dev.luizconde.controlefinanceiroapi.exception.ConflictException;
import dev.luizconde.controlefinanceiroapi.exception.ResourceNotFoundException;
import dev.luizconde.controlefinanceiroapi.exception.UserNotFoundException;
import dev.luizconde.controlefinanceiroapi.mapper.UserMapper;
import dev.luizconde.controlefinanceiroapi.mapper.UserUpdateMapper;
import dev.luizconde.controlefinanceiroapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserUpdateMapper userUpdateMapper;

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO requestDTO){
        if(userRepository.existsByEmail(requestDTO.email())){
            throw new ConflictException("Email already in use");
        }
        User entity = userMapper.toEntity(requestDTO);
        entity.setCreateDate(LocalDateTime.now());

        return userMapper.toResponseDto(userRepository.save(entity));
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAllUsers(){
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(userMapper::toResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not Found by Id: " + id)
        );

        return userMapper.toResponseDto(user);
    }

    @Transactional
    public UserResponseDTO updateUser(UserUpdateRequestDTO dto,
                                      Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not Found by Id: " + id)
        );

        if(dto.email() != null && userRepository.existsByEmailAndIdNot(dto.email(), id)){
            throw new ConflictException("Email already in use");
        }

        userUpdateMapper.userUpdate(dto, user);
        return userMapper.toResponseDto(user);
    }

    @Transactional
    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not Found by Id: " + id)
        );

        userRepository.delete(user);
    }
}
