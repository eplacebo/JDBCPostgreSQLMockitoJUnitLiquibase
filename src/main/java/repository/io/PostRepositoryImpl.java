package repository.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Post;
import repository.PostRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PostRepositoryImpl implements PostRepository {

    private final String postFile = "src/main/resources/posts.json";

    private static PostRepositoryImpl postRepositoryImpl;

    public static PostRepositoryImpl getPostRepositoryImpl() {
        if (postRepositoryImpl == null) {
            postRepositoryImpl = new PostRepositoryImpl();
        }
        return postRepositoryImpl;
    }


    @Override
    public Post getById(Long id) {
        return readJSON().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Post> getAll() {
        return readJSON();
    }

    @Override
    public Post update(Post postUpdate) {
        List<Post> posts = getAll().stream()
                .peek(s -> {
                    if (s.getId().equals(postUpdate.getId())) {
                        s.setContent(postUpdate.getContent());
                        s.setUpdated(new Date());
                    }
                })
                .collect(Collectors.toList());
        writeJSON(posts);
        return postUpdate;
    }

    @Override
    public boolean deleteById(Long id) {
        List<Post> posts = null;
        posts = readJSON();
        posts.removeIf(s -> s.getId().equals(id));
        writeJSON(posts);
        return true;

    }

    @Override
    public Post save(Post postSave) {
        List<Post> posts = null;
        posts = readJSON();
        Post post = new Post(postSave.getId(), postSave.getContent(), postSave.getCreated(), postSave.getUpdated());
        posts.add(post);
        writeJSON(posts);
        return post;
    }

    private void writeJSON(List<Post> posts) {
        try (Writer writer = Files.newBufferedWriter(Path.of(postFile), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            gson.toJson(posts, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Post> readJSON() {
        try (BufferedReader reader = new BufferedReader(new FileReader(postFile))) {
            List<Post> posts = new ArrayList<>();
            posts = new GsonBuilder().create().fromJson(reader, new TypeToken<List<Post>>() {
            }.getType());
            return posts;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

