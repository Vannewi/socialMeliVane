package com.example.be_java_hisp_w25_g10.controllers;

import com.example.be_java_hisp_w25_g10.dtos.PostCreatedDto;
import com.example.be_java_hisp_w25_g10.dtos.PostPromoDto;
import com.example.be_java_hisp_w25_g10.dtos.PostsDto;
import com.example.be_java_hisp_w25_g10.exceptions.BadRequestException;
import com.example.be_java_hisp_w25_g10.services.posts.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class PostsController {
    @Autowired
    IPostService postService;

    @GetMapping("products/followed/{userId}/list")
    ResponseEntity<PostsDto> getPostsFollowed(@PathVariable int userId, @RequestParam(required = false) String sortOrder){
        return new ResponseEntity<>(postService.getPostsFollowed(userId, sortOrder), HttpStatus.OK);
    }
    @PostMapping("/products/post")
    public ResponseEntity<?> registerPost(@RequestBody PostCreatedDto newPost){

        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate fecha = LocalDate.parse(newPost.date(), formatter);

        }catch(RuntimeException e){
            throw new BadRequestException("Ingresar una fecha valida");
        }


        return new ResponseEntity<>(postService.createPost(newPost), HttpStatus.CREATED);
    }
    @GetMapping("/posts")
    public ResponseEntity<?> verPost(){

        return new ResponseEntity<>(postService.verPosts(), HttpStatus.CREATED);
    }
    @PostMapping("/products/promo-post")
    public ResponseEntity<?> registerPromoPost(@RequestBody PostPromoDto newPost){

        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate fecha = LocalDate.parse(newPost.date(), formatter);

        }catch(RuntimeException e){
            throw new BadRequestException("Ingresar una fecha valida");
        }


        return new ResponseEntity<>(postService.createPromoPost(newPost), HttpStatus.CREATED);
    }
    @GetMapping("/products/promo-post/count/{userId}")
    public ResponseEntity<?> getCountDiscountProducts (@PathVariable int userId){

        return new ResponseEntity<>(postService.getCountDiscountProducts(userId), HttpStatus.CREATED);
    }


}
