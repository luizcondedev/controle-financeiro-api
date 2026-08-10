package dev.luizconde.controlefinanceiroapi.service;

import dev.luizconde.controlefinanceiroapi.dto.UserRequestDTO;
import dev.luizconde.controlefinanceiroapi.dto.UserResponseDTO;
import dev.luizconde.controlefinanceiroapi.entity.User;
import dev.luizconde.controlefinanceiroapi.exception.ConflictException;
import dev.luizconde.controlefinanceiroapi.exception.ResourceNotFoundException;
import dev.luizconde.controlefinanceiroapi.mapper.UserMapper;
import dev.luizconde.controlefinanceiroapi.mapper.UserUpdateMapper;
import dev.luizconde.controlefinanceiroapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserUpdateMapper userUpdateMapper;

    public UserResponseDTO createUser(UserRequestDTO requestDTO){
        if(userRepository.existsByEmail(requestDTO.email())){
            throw new ConflictException("Email already in use");
        }
        User entity = userMapper.toEntity(requestDTO);
        entity.setCreateDate(LocalDateTime.now());

        return userMapper.toResponseDto(userRepository.save(entity));
    }

    public List<UserResponseDTO> findAllUsers(){
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(userMapper::toResponseDto)
                .toList();
    }

    public UserResponseDTO findUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not Found by Id: " + id)
        );

        return userMapper.toResponseDto(user);
    }

    public UserResponseDTO updateUser(UserRequestDTO dto,
                                      Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not Found by Id: " + id)
        );

        userUpdateMapper.userUpdate(dto, user);
        return userMapper.toResponseDto(
                userRepository.save(user)
        );
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not Found by Id: " + id)
        );

        userRepository.delete(user);
    }
}
