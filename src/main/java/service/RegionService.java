package service;

import model.Region;
import repository.dbwJDBCImpl.RegionRepositoryJDBCImpl;

import java.util.List;

public class RegionService {

    RegionRepositoryJDBCImpl regionRepository = new RegionRepositoryJDBCImpl();

    public Region getRegion(Long id) {
        return regionRepository.getById(id);
    }

    public Region saveRegion(Region region) {
        return regionRepository.save(region);
    }

    public boolean deleteRegion(Long id) {
        regionRepository.deleteById(id);
        return true;
    }

    public List<Region> getAllRegions() {
        return regionRepository.getAll();
    }

    public Region updateRegion(Region region) {
        return regionRepository.update(region);
    }

}

