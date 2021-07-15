package controller;

import model.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import service.WriterService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static prototype.PostPrototype.postPrototype;
import static prototype.RegionPrototype.regionPrototype;
import static prototype.WriterPrototype.writerPrototype;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class WriterControllerTest {

    @InjectMocks
    WriterController writerController;

    @Mock
    WriterService writerService;
    @Mock
    RegionController regionController;

    @Mock
    PostController postController;
    @Captor
    ArgumentCaptor<Writer> requestCaptorWriter = ArgumentCaptor.forClass(Writer.class);

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllWriters() {
        List<Writer> list = new ArrayList<>();
        list.add(0, writerPrototype());
        when(writerController.getAllWriters()).thenReturn(list);
        assertNotNull(writerController.getAllWriters().get(0));
        verify(writerService, times(1)).getAllWriters();
    }

    @Test
    void updateWriter() {
        Writer writer = new Writer(444L,"asd","dsa",postController.getAllPosts(),regionPrototype());
        when(writerController.updateWriter(writerPrototype().getId(), writerPrototype().getFirstName(), writerPrototype().getLastName(), postPrototype().getId(), regionPrototype().getId())).thenReturn(writer);
        verify(writerService, times(0)).updateWriter(writer);
        assertNotNull(writer);
        assertNotEquals(writerPrototype().getFirstName(), writer.getFirstName());
    }

    @Test
    void getByIdWriter() {
        when(writerController.getByIdWriter(anyLong())).thenReturn(writerPrototype());
        Writer writer = writerController.getByIdWriter(writerPrototype().getId());
        assertNotNull(writer);
        assertEquals(writerPrototype().getId(), writer.getId());
        assertEquals(writerPrototype().getFirstName(), writer.getFirstName());
        assertEquals(writerPrototype().getLastName(), writer.getLastName());
        assertEquals(writerPrototype().getRegion().getName(), writer.getRegion().getName());
        assertEquals(writerPrototype().getPosts().get(0).getContent(), writer.getPosts().get(0).getContent());
        verify(writerService, times(1)).getWriter(anyLong());
    }

    @Test
    void deleteByIdWriter() {
        when(writerController.deleteByIdWriter(writerPrototype().getId())).thenReturn(true);
        boolean b = writerController.deleteByIdWriter(writerPrototype().getId());
        assertTrue(b);
        verify(writerService, times(1)).deleteWriter(any());
    }

    @Test
    void saveWriter() {
        when(writerService.saveWriter(any())).thenReturn(writerPrototype());
        writerService.saveWriter(writerPrototype());
        verify(writerService, times(1)).saveWriter(requestCaptorWriter.capture());
        assertNotNull(requestCaptorWriter.getValue());
        assertEquals(writerPrototype().getFirstName(), requestCaptorWriter.getValue().getFirstName());
    }
}