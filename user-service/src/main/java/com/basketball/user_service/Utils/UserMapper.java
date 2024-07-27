package com.basketball.user_service.Utils;

import com.basketball.codegen_service.codegen.types.User;
import com.basketball.user_service.Models.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(User user);

    User toDto(UserEntity userEntity);
}

