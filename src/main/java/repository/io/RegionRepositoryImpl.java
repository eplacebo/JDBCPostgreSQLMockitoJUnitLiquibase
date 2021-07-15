package repository.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Region;
import repository.RegionRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegionRepositoryImpl implements RegionRepository {

    private final String regionFile = "src/main/resources/regions.json";

    private static RegionRepositoryImpl regionRepositoryImpl;

    public static RegionRepositoryImpl getRegionRepositoryImp() {
        if (regionRepositoryImpl == null) {
            regionRepositoryImpl = new RegionRepositoryImpl();
        }
        return regionRepositoryImpl;
    }

    @Override
    public Region getById(Long id) {
        return readJSON().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Region> getAll() {
        return readJSON();
    }

    @Override
    public Region save(Region regionSave) {
        List<Region> regions = readJSON();
        Region region = new Region(regionSave.getId(), regionSave.getName());
        regions.add(region);
        writeJSON(regions);
        return region;
    }

    @Override
    public Region update(Region region) {
        List<Region> regions = getAll().stream()
                .peek(s -> {
                    if (s.getId().equals(region.getId())) {
                        s.setName(region.getName());
                    }
                })
                .collect(Collectors.toList());
        writeJSON(regions);
        return region;
    }

    @Override
    public boolean deleteById(Long id) {
        List<Region> regions = readJSON();
        regions.removeIf(s -> s.getId().equals(id));
        writeJSON(regions);
        return true;
    }

    private void writeJSON(List<Region> regions) {
        try (Writer writer = Files.newBufferedWriter(Path.of(regionFile), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            gson.toJson(regions, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Region> readJSON() {
        try (BufferedReader reader = new BufferedReader(new FileReader(regionFile))) {
            List<Region> regions = new ArrayList<>();
            regions = new GsonBuilder().create().fromJson(reader, new TypeToken<List<Region>>() {
            }.getType());
            return regions;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}