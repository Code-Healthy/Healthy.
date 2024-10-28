package com.healthy.dto;

import com.healthy.model.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class UserRegistrationDTO {

    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String userName;

    //@Email(message = "El correo electrónico no es válido")
    //@NotBlank(message = "El correo electrónico es obligatorio")
    //private String email;

    @NotEmpty(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe contener mínimo 8 caracteres")
    private String password;

    //private String name;
    //private Float height;
    //private Float weight;

    //@NotNull(message = "La edad es obligatoria")
    //@Min(value = 18, message = "Tienes que ser mayor o igual de 18 años")
    //@Max(value = 100, message = "Tienes que ser menor o igual de 100 años")
    //private Integer age;
    //private Gender gender;
    //private String healthConditions;

    //private List<PlanDTO> plans;
    //private List<ProfileResourceDetailsDTO> resources;
    //private List<ProfileSubscriptionDTO> subPlans;
}