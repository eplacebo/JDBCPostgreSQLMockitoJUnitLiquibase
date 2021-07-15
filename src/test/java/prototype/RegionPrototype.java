package prototype;

import model.Region;

public class RegionPrototype {

    public static Region regionPrototype(){
        return new Region(666L,"test");
    }

}
