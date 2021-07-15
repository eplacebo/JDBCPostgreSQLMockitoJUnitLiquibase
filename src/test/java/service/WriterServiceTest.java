package service;

import model.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.dbwJDBCImpl.WriterRepositoryJDBCImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static prototype.RegionPrototype.regionPrototype;
import static prototype.WriterPrototype.writerPrototype;

@ExtendWith(MockitoExtension.class)
class WriterServiceTest {

    @InjectMocks
    WriterService writerService;

    @Mock
    WriterRepositoryJDBCImpl writerRepository;

    @Mock
    List<Writer> listWriters;

    @Captor
    ArgumentCaptor<Writer> requestCaptorWriter = ArgumentCaptor.forClass(Writer.class);

    @BeforeEach
    void setUp() {
        listWriters.add(writerPrototype());
    }


    @Test
    void testGetWriter() {
        when(writerService.getWriter(anyLong())).thenReturn(writerPrototype());
        Writer writer = writerService.getWriter(444L);
        assertNotNull(writer);
        assertEquals("test", writerService.getWriter(444L).getFirstName());
        verify(writerRepository, times(2)).getById(any());
    }

    @Test
    void saveWriter() {
        when(writerService.saveWriter(any())).thenReturn(writerPrototype());
        writerService.saveWriter(writerPrototype());
        verify(writerRepository, times(1)).save(requestCaptorWriter.capture());
        assertNotNull(requestCaptorWriter.getValue());
        assertEquals(writerPrototype().getFirstName(), requestCaptorWriter.getValue().getFirstName());
    }

    @Test
    void deleteWriter() {
        when(writerService.deleteWriter((anyLong()))).thenReturn(true);
        boolean b = writerService.deleteWriter(444L);
        assertTrue(b);
        verify(writerRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void getAllWriters() {
        when(writerService.getAllWriters()).thenReturn(listWriters);
        assertNotNull(writerService.getAllWriters());
        verify(writerRepository, times(1)).getAll();
    }

    @Test
    void updateWriter() {
        when(writerService.updateWriter(any())).thenReturn(writerPrototype());
        PostService postService = new PostService();
        Writer writer = new Writer(123l, "D","ASD",postService.getAllPosts(),regionPrototype());
        writer = writerService.updateWriter(writerPrototype());
        assertNotEquals("D", writer.getFirstName());
        verify(writerRepository, times(1)).update(any());
    }
}
