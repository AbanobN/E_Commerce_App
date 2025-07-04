package com.javaFullStackProject.e_commerce.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CouponDto {

    private Long id;

    private String name;

    private String code;

    private Long discount;

    private Date expirationDate;
}
