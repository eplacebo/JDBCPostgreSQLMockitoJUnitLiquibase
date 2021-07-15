package service;

import model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.dbwJDBCImpl.PostRepositoryJDBCImpl;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static prototype.PostPrototype.postPrototype;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @InjectMocks
    PostService postService;

    @Mock
    PostRepositoryJDBCImpl postRepository;

    @Mock
    List<Post> listPost;

    @BeforeEach
    void setUp() {
        listPost.add(postPrototype());
    }

    @Captor
    ArgumentCaptor<Post> requestCaptorPost = ArgumentCaptor.forClass(Post.class);


    @Test
    void testGetPost() {
        when(postService.getPost(anyLong())).thenReturn(postPrototype());
        Post post = postService.getPost(555L);
        assertNotNull(post);
        assertEquals("test", postService.getPost(555L).getContent());
        verify(postRepository, times(2)).getById(any());
    }

    @Test
    void savePost() {
        when(postService.savePost(any())).thenReturn(postPrototype());
        postService.savePost(postPrototype());
        verify(postRepository, times(1)).save(requestCaptorPost.capture());
        assertNotNull(requestCaptorPost.getAllValues());
        assertEquals(postPrototype().getContent(), requestCaptorPost.getValue().getContent());
    }

    @Test
    void deletePost() {
        when(postService.deletePost(anyLong())).thenReturn(true);
        boolean b = postService.deletePost(555L);
        assertTrue(b);
        verify(postRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void getAllPost() {
        when(postService.getAllPosts()).thenReturn(listPost);
        assertNotNull(postService.getAllPosts());
        verify(postRepository, times(1)).getAll();
    }

    @Test
    void updatePost() {
        when(postService.updatePost(any())).thenReturn(postPrototype());
        Post post = new Post(123l, "D",new Date(),null);
        post = postService.updatePost(postPrototype());
        assertNotEquals("D", post.getContent());
        verify(postRepository, times(1)).update(any());
    }
}


