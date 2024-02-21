package com.example.be_java_hisp_w25_g10.services.posts;

import com.example.be_java_hisp_w25_g10.dtos.*;
import com.example.be_java_hisp_w25_g10.DTO.ResponseDto;

import java.util.List;

public interface IPostService {
    PostsDto getPostsFollowed(int userId, String sortOrder);
    PostCreatedDto createPost(PostCreatedDto post);
    List<PostPromoDto> verPosts();
    ResponseDto createPromoPost(PostPromoDto post);
    DiscountProductsNumberDto getCountDiscountProducts(int user_id);
    List<PostPromoDto> getPromoPost ();
}
