package com.healthy.dto;

import com.healthy.model.enums.ResourceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResourceDTO {

    private Integer id;
    @NotBlank(message = "El titulo debe ser obligatorio")
    @Size(max =50, message = "El titulo debe tener 50 caracteres o menos")
    private String title;
    private String description;
    private ResourceType resourceType;
    private String content;
    private String price;
    private String firstName;
    private String lastName;
    private String expertise;

}