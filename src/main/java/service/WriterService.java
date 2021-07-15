package service;

import model.Writer;
import repository.WriterRepository;
import repository.dbwJDBCImpl.WriterRepositoryJDBCImpl;

import java.util.List;

public class WriterService {
    WriterRepository writerService = new WriterRepositoryJDBCImpl();

    public Writer getWriter(Long id) {
        return writerService.getById(id);
    }

    public Writer saveWriter(Writer writer) {

        return writerService.save(writer);
    }

    public boolean deleteWriter(Long id) {
        writerService.deleteById(id);
        return true;
    }


    public List<Writer> getAllWriters() {
        return writerService.getAll();
    }

    public Writer updateWriter(Writer writer) {
        return writerService.update(writer);
    }
}
