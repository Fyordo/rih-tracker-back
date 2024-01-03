package com.rentinhand.rihtracker.services.implementations;

import com.rentinhand.rihtracker.dto.requests.user.UserCreateRequest;
import com.rentinhand.rihtracker.dto.requests.user.UserDataRequest;
import com.rentinhand.rihtracker.dto.requests.user.UserUpdateRequest;
import com.rentinhand.rihtracker.entities.User;
import com.rentinhand.rihtracker.repos.UserRepository;
import com.rentinhand.rihtracker.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public User createUser(UserCreateRequest userData) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userData, User.class);
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(User user, UserUpdateRequest userData) {
        user.setUsername(userData.getUsername());
        user.setLogin(userData.getLogin());
        user.setAvatar(userData.getAvatar());
        userRepository.save(user);
        return user;
    }

    @Override
    public boolean deleteUser(User user) {
        userRepository.delete(user);
        return false;
    }
}