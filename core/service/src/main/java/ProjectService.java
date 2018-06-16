import java.util.List;
import java.util.Set;

public interface ProjectService {
    
    public List<Project> listProject();

    public Project findById(Long id, String object);

    public void addProject(Project p);

    public void updateProject(Project r);

    public void deleteProject(Long id, String object);

}