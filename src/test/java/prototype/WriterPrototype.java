package prototype;

import model.Region;
import model.Writer;
import repository.dbwJDBCImpl.PostRepositoryJDBCImpl;

public class WriterPrototype {

    public static Writer writerPrototype() {
        PostRepositoryJDBCImpl post = new PostRepositoryJDBCImpl();
        return new Writer(444L, "test", "test", post.getAll(), new Region(123L, "ggg"));
    }

}
