package controller;

import model.Post;
import model.Writer;
import service.PostService;
import service.RegionService;
import service.WriterService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WriterController {
    WriterService writerService = new WriterService();
    RegionService regionService = new RegionService();
    PostService postService = new PostService();

    public List<Writer> getAllWriters() {
        return writerService.getAllWriters();
    }

    public Writer updateWriter(Long id, String firstName, String lastName, Long idPost, Long idRegion) {
        if (writerService.getWriter(id) != null) {
            return writerService.updateWriter(new Writer(id, firstName, lastName, Collections.singletonList(postService.getPost(idPost)), regionService.getRegion((idRegion))));
        }
        return null;
    }

    public Writer getByIdWriter(Long id) {
        return writerService.getWriter(id);
    }

    public boolean deleteByIdWriter(Long id) {
        writerService.deleteWriter(id);
        return true;
    }


    public Writer saveWriter(Long id, String firstName, String lastName, String idPost, Long idRegion) {
        if (writerService.getWriter(id) == null) {
            return writerService.saveWriter(new Writer(id, firstName, lastName, getPostsById(idPost), regionService.getRegion(idRegion)));
        }
        return null;
    }

    public List<Post> getPostsById(String posts) {
        List<Post> resultPost = new ArrayList<>();
        List<Post> postList = postService.getAllPosts();

        String items[] = posts.split(",");
        Long ent[] = new Long[items.length];
        for (int i = 0; i < items.length; i++) {
            try {
                ent[i] = Long.parseLong(items[i]);
            } catch (NumberFormatException e) {
            }
        }

        for (int i = 0; i < ent.length; i++) {
            resultPost.add(postService.getPost(ent[i]));
        }
        return resultPost;
    }
}





