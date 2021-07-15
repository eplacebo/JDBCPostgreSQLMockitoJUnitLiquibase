package controller;


import model.Region;
import model.RegionIdComparator;
import repository.RegionRepository;
import repository.dbwJDBCImpl.RegionRepositoryJDBCImpl;
import service.RegionService;

import java.util.Comparator;
import java.util.List;

public class RegionController {
    RegionRepository regionRepository = new RegionRepositoryJDBCImpl();

    RegionService regionService = new RegionService();
    Comparator orderLong = new RegionIdComparator();

    public List<Region> getAllRegions() {
        return regionService.getAllRegions();
    }

    public Region getByIdRegion(Long id) {
        return regionService.getRegion(id);
    }

    public boolean deleteByIdRegion(Long id) {
        regionService.deleteRegion(id);
        return true;
    }

    public Region saveRegion(Long id, String name) {
        return regionService.saveRegion(new Region(id, name));
    }

    public Region updateRegion(Long id, String name) {
        return regionService.updateRegion(new Region(id, name));
    }

}




