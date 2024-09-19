package com.healthy.dto;

import com.healthy.model.enums.ResourceType;
import lombok.Data;

@Data
public class ResourceCreateUpdateDTO {
    private  Integer id;
    private  String title;
    private  String description;
    private ResourceType resourceType;
    private String content;
    private float price;
    private Integer idExpert;

}
