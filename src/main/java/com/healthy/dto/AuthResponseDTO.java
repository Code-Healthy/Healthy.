package com.healthy.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {

    private String token;         // El token JWT

    private String userName;     // El username del usuario
    private String role;          // El rol del usuario (OLE_CUSTOMER)
}