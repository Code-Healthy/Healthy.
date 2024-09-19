package com.healthy.dto;

import com.healthy.model.entity.Expert;
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
    private String type;
    private String content;
    private String price;




}
