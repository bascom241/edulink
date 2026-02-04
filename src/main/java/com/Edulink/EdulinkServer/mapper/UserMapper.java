package com.Edulink.EdulinkServer.mapper;

import com.Edulink.EdulinkServer.dto.user.UserResponseDTO;
import com.Edulink.EdulinkServer.model.User;

public class UserMapper {

    public static UserResponseDTO toUserDto (User user ){
         UserResponseDTO userResponseDTO = new UserResponseDTO();

         userResponseDTO.setFirstName(user.getFirstName());
         userResponseDTO.setLastName(user.getLastName());
         userResponseDTO.setNoOfSessions(user.getNoOfSessions());
         userResponseDTO.setShortBio(user.getShortBio());
         userResponseDTO.setEmail(user.getEmail());
         userResponseDTO.setBankAccount(user.getBankAccount());
         userResponseDTO.setPhoneNumber(user.getPhoneNumber());
         userResponseDTO.setBankCode(user.getBankCode());
         userResponseDTO.setBankName(user.getBankName());
         userResponseDTO.setSubAccountCode(user.getSubAccountCode());

         return userResponseDTO;

    }
}
