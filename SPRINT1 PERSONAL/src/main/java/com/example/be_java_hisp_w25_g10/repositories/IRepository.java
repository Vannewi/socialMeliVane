package com.example.be_java_hisp_w25_g10.repositories;

import com.example.be_java_hisp_w25_g10.dtos.DiscountProductsNumberDto;
import com.example.be_java_hisp_w25_g10.dtos.PostPromoDto;
import com.example.be_java_hisp_w25_g10.entities.Follower;
import com.example.be_java_hisp_w25_g10.entities.Post;
import com.example.be_java_hisp_w25_g10.entities.User;

import java.util.List;
import java.util.Optional;

public interface IRepository {
    List<Post> getPosts();

    public Optional<Follower> follow(int userId, int followedId);

    public Optional<User> findUser(int id);

    public void unFollow(int userId, int followedId);
    List<User> getFollowersList(int userId);
    List<User> getFollowedList(int userId);

    List<Post> getFollowedPosts(int userId, String sortOrder);
    public Post addPost(Post newPost);


    public List<Post> verPost();
    List<User> getSellers();
    boolean validatePost (int id);
    int getCountDiscountProducts(int user_id);

    String getSellerNameById (int user_id);
    List <Post> getPromoPost ();



}