package com.healthy.service;

import com.healthy.dto.AuthResponseDTO;
import com.healthy.dto.LoginDTO;
import com.healthy.dto.UserProfileDTO;
import com.healthy.dto.UserRegistrationDTO;

public interface UserService {

    // Registra un Usuario
    UserRegistrationDTO registerUser(UserRegistrationDTO registrationDTO);

    // Actualiza el perfil de usuario
    UserProfileDTO updateUserProfile(UserProfileDTO userProfileDTO);

    // Obtener el perfil de usuario por ID
    UserProfileDTO getUserProfileById(Integer id);

    // Iniciar sesión en el sistema
    AuthResponseDTO login(LoginDTO loginDTO);
}
