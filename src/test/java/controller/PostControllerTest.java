package controller;

import model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.PostService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;
import static prototype.PostPrototype.postPrototype;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @InjectMocks
    PostController postController;

    @Mock
    PostService postService;

    final Long POST_ID_PROTOTYPE = 555L;


    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllPosts() {
        List<Post> list = new ArrayList<>();
        list.add(0, postPrototype());
        when(postController.getAllPosts()).thenReturn(list);
        assertNotNull(postController.getAllPosts().get(0));
        verify(postService, times(1)).getAllPosts();
    }

    @Test
    void getByIdPost() {
        when(postController.getByIdPost(anyLong())).thenReturn(postPrototype());
        Post post = postController.getByIdPost(POST_ID_PROTOTYPE);
        assertNotNull(post);
        assertEquals(POST_ID_PROTOTYPE, post.getId());
        assertEquals(postPrototype().getContent(), post.getContent());
        verify(postService, times(1)).getPost(anyLong());
    }

    @Test
    void deleteByIdPost() {
        when(postController.deleteByIdPost(postPrototype().getId())).thenReturn(true);
        boolean b = postController.deleteByIdPost(POST_ID_PROTOTYPE);
        assertTrue(b);
        verify(postService, times(1)).deletePost(anyLong());
    }

    @Test
    void savePost() {
        when(postController.savePost(POST_ID_PROTOTYPE, any())).thenReturn(postPrototype());
        Post post = new Post(123l, "D", new Date(), null);
        post = postController.savePost(POST_ID_PROTOTYPE, postPrototype().getContent());
        assertNotEquals("D", post.getContent());
        verify(postService, times(1)).savePost(any());
    }

    @Test
    void update() {
        Post post = new Post(555L, "asd", postPrototype().getCreated(), new Date());
        when(postController.update(POST_ID_PROTOTYPE, any())).thenReturn(post);
        postController.update(postPrototype().getId(), post.getContent());
        assertNotEquals(postPrototype().getContent(), post.getContent());
        verify(postService, times(1)).updatePost(any());
    }
}