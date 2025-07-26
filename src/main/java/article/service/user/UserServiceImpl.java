package article.service.user;

import article.dto.user.UserRegistrationRequestDto;
import article.dto.user.UserResponseDto;
import article.exeption.RegistrationException;
import article.mapper.UserMapper;
import article.model.User;
import article.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegistrationException(
                    "User with email " + requestDto.getEmail()
                            + " already exists."
            );
        }
        User user = userMapper.toEntity(requestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.CUSTOMER);

        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }
}
