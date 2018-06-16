import java.util.*;

public interface PersonnelService {

    public void addPersonnel(Personnel p);

    public void updatePersonnel(Personnel p);

    public List<Personnel> listPersonnel();

 	public void deletePersonnel(Long id, String object);

	public Personnel findById(Long id, String object);

	public List<Personnel> searchFor(String str);
}