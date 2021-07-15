package model;

import java.util.Comparator;

public class RegionIdComparator implements Comparator<Region> {


    public int compare(Region region, Region t1) {
        return (int) (region.getId()-t1.getId());
    }
}

