package controller;

import model.Post;
import service.PostService;

import java.util.Date;
import java.util.List;


public class PostController {

    PostService postService = new PostService();

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public Post getByIdPost(Long id) {
        return postService.getPost(id);
    }

    public boolean deleteByIdPost(Long id) {
        postService.deletePost(id);
        return true;
    }

    public Post savePost(Long id, String content) {
        return postService.savePost(new Post(id, content, new Date(), null));


    }

    public Post update(Long id, String content) {
        return postService.updatePost(new Post(id, content, new Date(), new Date()));
    }
}





