package com.example.fingoal.mappers.impl;

import com.example.fingoal.dto.UserDto;
import com.example.fingoal.mappers.Mapper;
import com.example.fingoal.model.User;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User , UserDto> {

    private final ModelMapper modelMapper;

    @Override
    public UserDto mapTo(User user) {
        return modelMapper.map(user , UserDto.class);
    }

    @Override
    public User mapFrom(UserDto userDto) {
        return  modelMapper.map(userDto , User.class);
    }
}
