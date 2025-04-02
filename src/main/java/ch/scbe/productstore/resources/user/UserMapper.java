package ch.scbe.productstore.resources.user;

import ch.scbe.productstore.resources.user.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "roles", target = "roles")
    public abstract Users toEntity(UserCreateDto userCreateDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "roles", target = "roles")
    public abstract UserShowDto toShowDto(Users users);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    public abstract void update(UserCreateDto userCreateDto, @MappingTarget Users usersToUpdate);
}
