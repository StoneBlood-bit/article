package article.service.user;

import article.dto.user.UserRegistrationRequestDto;
import article.dto.user.UserResponseDto;
import article.exeption.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
