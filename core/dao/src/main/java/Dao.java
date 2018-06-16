import java.util.List;

public interface Dao {

    public <T> void add(T object);

    public Object findById(long id, String object);

    public <T> void update(T object);

    public <T> void delete(long id, String object);

    public List getList(String object);

   	public List<Personnel> searchFor(String str);  
}