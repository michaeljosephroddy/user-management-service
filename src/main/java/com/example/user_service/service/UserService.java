package com.example.user_service.service;

import com.example.user_service.exception.UserAlreadyExistsException;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.model.User;
import com.example.user_service.model.UserDTO;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.util.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO register(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists.");
        }

        user.setLocation(user.getLongitude(), user.getLatitude());

        System.out.println("Latitude: " + user.getLatitude());
        System.out.println("Longitude: " + user.getLongitude());
        System.out.println("Location: " + user.getLocation().toText());

        User registeredUser = userRepository.save(user);
        return UserConverter.convertToDTO(registeredUser);
    }

    public void login(User user) {
        // Implement login logic
    }

    public void logout(User user) {
        // Implement logout logic
    }

    public UserDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }
        return UserConverter.convertToDTO(user.get());
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }
        User user = UserConverter.convertToEntity(userDTO);
        user.setUserId(id);

        // Convert location string to Point object
        user.setLocation(userDTO.getLongitude(), userDTO.getLatitude());

        User updatedUser = userRepository.save(user);
        return UserConverter.convertToDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }
        userRepository.deleteById(id);
    }

    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User with username " + username + " not found.");
        }
        return UserConverter.convertToDTO(user);
    }
}