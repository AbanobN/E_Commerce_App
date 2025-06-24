package com.javaFullStackProject.e_commerce.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReviewDto {

    private Long id;

    private Long rating;

    private String description;

    private byte[] img;

    private MultipartFile retunedImg;

    private Long user_id;

    private String user_name;

    private Long product_id;

}
