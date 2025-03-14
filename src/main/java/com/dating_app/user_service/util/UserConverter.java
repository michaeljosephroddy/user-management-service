package com.dating_app.user_service.util;

import com.dating_app.user_service.model.User;
import com.dating_app.user_service.model.UserDTO;

public class UserConverter {

    public static UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getFullName(),
                user.getGender(),
                user.getDateOfBirth(),
                user.getBio(),
                user.getLocation().toText(),
                user.getLongitude(),
                user.getLatitude());
    }

    public static User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setFullName(userDTO.getFullName());
        user.setGender(userDTO.getGender());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setBio(userDTO.getBio());
        user.setLocation(userDTO.getLongitude(), userDTO.getLatitude());
        return user;
    }
}