package com.EcommerceUserService.mapper;

import com.EcommerceUserService.dto.UserDTO;
import com.EcommerceUserService.model.User;

public class UserEntityDTOMaapper {
    public static UserDTO getUserDTOFromUserEntity(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }
}
