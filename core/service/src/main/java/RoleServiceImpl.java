import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoleServiceImpl implements RoleService {
    
    private Dao dao;
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    public void setDao(Dao dao){
      this.dao = dao;
    }

    public List<Roles> listRoles() {
        logger.info("RoleService listProject method");
        return dao.getList("Roles");
    }

    public Roles findById(Long id, String object) {
        logger.info("RoleService findById method");
        return (Roles) dao.findById(id, "Roles");
    }

    public void addRole(Roles r) {
        logger.info("RoleService addRole method");
        dao.add(r);
    }

    public void updateRole(Roles r) {
        logger.info("RoleService updateRole method");
        dao.update(r);
    }

    public void deleteRole(Long id, String object) {
        logger.info("RoleService deleteRole method");
        dao.delete(id, "Roles");
    }

}