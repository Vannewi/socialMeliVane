package com.example.be_java_hisp_w25_g10.utils;

import com.example.be_java_hisp_w25_g10.dtos.PostCreatedDto;
import com.example.be_java_hisp_w25_g10.dtos.PostPromoDto;
import com.example.be_java_hisp_w25_g10.entities.Post;
import com.example.be_java_hisp_w25_g10.entities.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Boolean.FALSE;

public class PostMapper {
    public static Post fromDto(PostCreatedDto postDto, User user){
        Random random = new Random();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = LocalDate.parse(postDto.date(), formatter);
        return new Post(
                random.nextInt(),
                user,
                fecha,
                postDto.product(),
                false,
                0.0

        );
    }
    public static Post fromPostPromoDto(PostPromoDto postDto, User user){
        Random random = new Random();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = LocalDate.parse(postDto.date(), formatter);
        return new Post(
                random.nextInt(),
                user,
                fecha,
                postDto.product(),
                postDto.has_promo(),
                postDto.discount()
        );
    }
    public static PostCreatedDto toDto(Post post){
        return new PostCreatedDto(
                post.getUser().getId(),
                post.getDate().toString(),
                post.getProduct(),
                post.getProduct().getCategory(),
                post.getProduct().getPrice()


        );
    }
    public static PostPromoDto toPromoDto(Post post){
        return new PostPromoDto(
                post.getUser().getId(),
                post.getDate().toString(),
                post.getProduct(),
                post.getProduct().getCategory(),
                post.getProduct().getPrice(),
                post.isHas_promo(),
                post.getDiscount()

        );
    }

    public static List<PostCreatedDto> ListToDto(List<Post> listPost){
        List<PostCreatedDto> listPostDto = new ArrayList<>();
        for (Post post: listPost){
            PostCreatedDto postDto = new PostCreatedDto(
                    post.getUser().getId(),
                    post.getDate().toString(),
                    post.getProduct(),
                    post.getProduct().getCategory(),
                    post.getProduct().getPrice()
            );
            listPostDto.add(postDto);
        }
        return listPostDto;
    }
    public static List<PostPromoDto> ListToPromoDto(List<Post> listPost){
        List<PostPromoDto> listPostDto = new ArrayList<>();
        for (Post post: listPost){
            PostPromoDto postDto = new PostPromoDto(
                    post.getUser().getId(),
                    post.getDate().toString(),
                    post.getProduct(),
                    post.getProduct().getCategory(),
                    post.getProduct().getPrice(),
                    post.isHas_promo(),
                    post.getDiscount()
            );
            listPostDto.add(postDto);
        }
        return listPostDto;
    }
}

