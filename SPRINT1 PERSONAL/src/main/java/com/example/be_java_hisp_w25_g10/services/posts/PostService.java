package com.example.be_java_hisp_w25_g10.services.posts;

import com.example.be_java_hisp_w25_g10.dtos.*;
import com.example.be_java_hisp_w25_g10.DTO.ResponseDto;
import com.example.be_java_hisp_w25_g10.entities.Post;
import com.example.be_java_hisp_w25_g10.entities.Product;
import com.example.be_java_hisp_w25_g10.entities.User;
import com.example.be_java_hisp_w25_g10.exceptions.BadRequestException;
import com.example.be_java_hisp_w25_g10.exceptions.NotFoundException;
import com.example.be_java_hisp_w25_g10.repositories.IRepository;
import com.example.be_java_hisp_w25_g10.utils.PostMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.TRUE;

@Service
public class PostService implements IPostService {

    @Autowired
    IRepository repository;

    @Override
    public PostsDto getPostsFollowed(int userId, String sortOrder) {
        List<Post> posts = this.repository.getFollowedPosts(userId, sortOrder);
        List<PostDto> postsFollowed = new ArrayList<>();

        if (posts.isEmpty()) {
            throw new NotFoundException("No hay posts de los ultimos 20 dias de tus vendedores");
        }

        posts.forEach(p -> postsFollowed.add(new PostDto(
                p.getUser().getId(),
                p.getId(),
                p.getDate(),
                this.convertToProductDto(p.getProduct())
        )));

        postsFollowed.sort(Comparator.comparing(PostDto::date));

        return new PostsDto(userId, postsFollowed);
    }
    @Override
    public PostCreatedDto createPost(PostCreatedDto newPost){


        Post convertedPost;
        Optional<User> user= this.repository.findUser(newPost.user_id());
        if(user.isEmpty()){
            throw new BadRequestException("no existe ese vendedor");
        }

        convertedPost = PostMapper.fromDto(newPost, user.get());

        //Validar si existe ese vendedor
        if (repository.validatePost(convertedPost.getUser().getId()) == TRUE){
            repository.addPost(convertedPost);
        }else{
            throw new BadRequestException("Ese vendedor no existe");
        }



        return PostMapper.toDto(convertedPost) ;
    }

    @Override
    public List<PostPromoDto> verPosts() {
        List<Post> posts = repository.verPost();



        return PostMapper.ListToPromoDto(posts);
    }

    @Override
    public ResponseDto createPromoPost(PostPromoDto postDTO) {
        Post convertedPost;
        Optional<User> user= this.repository.findUser(postDTO.user_id());
        if(user.isEmpty()){
            throw new BadRequestException("no existe ese vendedor");
        }

        convertedPost = PostMapper.fromPostPromoDto(postDTO, user.get());

        //Validar si existe ese vendedor
        if (repository.validatePost(convertedPost.getUser().getId()) == TRUE){
            repository.addPost(convertedPost);
        }else{
            throw new BadRequestException("Ese vendedor no existe");
        }



        return new ResponseDto("Se creo correctamente") ;

    }

    @Override
    public DiscountProductsNumberDto getCountDiscountProducts(int user_id) {

        List<User> sellers = repository.getSellers();
        List<User> sellersSearch = sellers.stream()
                                .filter(v -> v.getId() == user_id)

                                .toList();
        if (sellersSearch.isEmpty())
            throw new NotFoundException("El vendedor que buscas no existe");


        String user_name = repository.getSellerNameById(user_id);
        int promo_products_count = repository.getCountDiscountProducts(user_id);
        return new DiscountProductsNumberDto(user_id, user_name, promo_products_count);
    }

    @Override
    public List<PostPromoDto> getPromoPost() {
        List<Post> posts = repository.getPromoPost();
        return PostMapper.ListToPromoDto(posts);
    }

    private ProductDto convertToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getType(),
                product.getBrand(),
                product.getColor(),
                product.getNotes(),
                product.getCategory(),
                product.getPrice()
        );
    }
}
