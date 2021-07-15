package prototype;

import model.Post;

import java.util.Date;

public class PostPrototype {

    public static Post postPrototype(){
        return new Post(555L,"test",new Date(), null);
    }
}
