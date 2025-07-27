package article.mapper;

import article.config.MapperConfig;
import article.dto.user.UserRegistrationRequestDto;
import article.dto.user.UserResponseDto;
import article.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toUserResponse(User user);

    User toEntity(UserRegistrationRequestDto requestDto);
}
