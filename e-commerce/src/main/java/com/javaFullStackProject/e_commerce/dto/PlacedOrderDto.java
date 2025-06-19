package com.javaFullStackProject.e_commerce.dto;

import lombok.Data;

@Data
public class PlacedOrderDto {

    private Long userId;

    private String address;

    private String orderDescription;


}
