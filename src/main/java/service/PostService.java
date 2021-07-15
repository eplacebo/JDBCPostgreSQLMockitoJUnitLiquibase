package service;

import model.Post;
import repository.dbwJDBCImpl.PostRepositoryJDBCImpl;

import java.util.List;

public class PostService {
    PostRepositoryJDBCImpl postRepository = new PostRepositoryJDBCImpl();


    public Post getPost(Long id) {
        return postRepository.getById(id);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public boolean deletePost(Long id) {
        postRepository.deleteById(id);
        return true;
    }

    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    public Post updatePost(Post post) {
        return postRepository.update(post);
    }
}
