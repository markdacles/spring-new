import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectServiceImpl implements ProjectService {
    
    private Dao dao;
    private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    public void setDao(Dao dao){
      this.dao = dao;
    }

    public List<Project> listProject() {
        logger.info("ProjectService listProject method");
        return dao.getList("Project");
    }

    public Project findById(Long id, String object) {
        logger.info("ProjectService listProject method");
        return (Project) dao.findById(id, "Project");
    }

    public void addProject(Project p) {
        logger.info("ProjectService addProject method");
        dao.add(p);
    }

    public void updateProject(Project p) {
        logger.info("ProjectService updateProject method");
        dao.update(p);
    }

    public void deleteProject(Long id, String object) {
        logger.info("ProjectService deleteProject method");
        dao.delete(id, "Project");
    }

}