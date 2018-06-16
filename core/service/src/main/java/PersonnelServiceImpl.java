import java.util.List;
import java.util.stream.*;
import java.util.Set;
import java.util.Comparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class PersonnelServiceImpl implements PersonnelService {

    private Dao dao;

    private static Logger logger = LoggerFactory.getLogger(PersonnelServiceImpl.class);

    public void setDao(Dao dao){
      this.dao = dao;
    }

    public Personnel findById(Long id, String object) {
        logger.info("PersonnelService findById method");
        return (Personnel) dao.findById(id, "Personnel");
    }

    public void deletePersonnel(Long id, String object) {
        logger.info("PersonnelService deletePersonnel method");
        dao.delete(id, "Personnel");
    }

    public void addPersonnel(Personnel p) {
        logger.info("PersonnelService addPersonnel method");
		dao.add(p);
    }

    public void updatePersonnel(Personnel p) {
        logger.info("PersonnelService updatePersonnel method");
    	dao.update(p);
    }

    public List<Personnel> listPersonnel() {
        logger.info("PersonnelService listPersonnel method");
    	return dao.getList("Personnel");
    }

    public List<Personnel> searchFor(String str) {
        System.out.println("HERE");
        logger.info("PersonnelService searchFor method");
        return dao.searchFor(str);
    }
}