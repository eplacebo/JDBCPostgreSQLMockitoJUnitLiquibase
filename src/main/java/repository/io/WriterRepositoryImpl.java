package repository.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Writer;
import repository.WriterRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class WriterRepositoryImpl implements WriterRepository {

    private final String writerFile = "src/main/resources/writers.json";
    private static WriterRepositoryImpl writerRepositoryImpl;

    public static WriterRepositoryImpl getWriterRepositoryImpl() {
        if (writerRepositoryImpl == null) {
            writerRepositoryImpl = new WriterRepositoryImpl();
        }
        return writerRepositoryImpl;
    }

    @Override
    public Writer getById(Long id) {
        return readJSON().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Writer> getAll() {
        return readJSON();
    }

    @Override
    public Writer update(Writer writerUpdate) {
        List<Writer> writers = getAll().stream()
                .peek(s -> {
                    if (s.getId().equals(writerUpdate.getId())) {
                        s.setFirstName(writerUpdate.getFirstName());
                        s.setLastName(writerUpdate.getLastName());
                        s.setPosts(writerUpdate.getPosts());
                        s.setRegion(writerUpdate.getRegion());
                    }
                })
                .collect(Collectors.toList());
        writeJSON(writers);
        return writerUpdate;
    }

    @Override
    public boolean deleteById(Long id) {
        List<Writer> writers = readJSON();
        writers.removeIf(s -> s.getId().equals(id));
        writeJSON(writers);
        return true;

    }

    @Override
    public Writer save(Writer writerSave) {
        List<Writer> writers = null;
        writers = readJSON();
        Writer writer = new Writer(writerSave.getId(), writerSave.getFirstName(), writerSave.getLastName(), writerSave.getPosts(),writerSave.getRegion());
        writers.add(writer);
        writeJSON(writers);
        return writer;
    }

    private void writeJSON(List<Writer> writers) {
        try (java.io.Writer writer = Files.newBufferedWriter(Path.of(writerFile), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            gson.toJson(writers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<model.Writer> readJSON() {
        try (BufferedReader reader = new BufferedReader(new FileReader(writerFile))) {
            List<Writer> writers = new GsonBuilder().create().fromJson(reader, new TypeToken<List<Writer>>() {
            }.getType());
            return writers;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
