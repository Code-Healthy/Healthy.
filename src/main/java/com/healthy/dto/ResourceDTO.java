package com.healthy.dto;

import com.healthy.model.entity.Expert;
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

    @NotBlank(message = "La descripcion debe de ser obligatoria")
    private String description;


    @NotBlank(message = "El tipo de recurso es obligatorio")
    private ResourceType resourceType;


    @NotBlank(message = "El contenido es obligatorio")
    private String content;


    @NotBlank(message = "El nombre del experto debe ser obligatorio")
    private String firstName;


    @NotBlank(message = "El apellido del experto es obligatorio")
    private String lastName;



    private String expertise;

}
