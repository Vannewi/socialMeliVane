package com.example.be_java_hisp_w25_g10.dtos;

import com.example.be_java_hisp_w25_g10.entities.Product;

public record PostPromoDto(
    int user_id,
    String date,
    Product product,
    int category,
    Double price,
    boolean has_promo,
    Double discount

    ){
}
