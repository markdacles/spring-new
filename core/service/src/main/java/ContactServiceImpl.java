import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactServiceImpl implements ContactService {

    private static Dao dao;
    private static Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

    public void setDao(Dao dao){
      this.dao = dao;
    }

    public void addContactToPersonnel(Personnel p) {
        logger.info("ContactService addContactToPersonnel method");
        dao.update(p);
    }

    public void updateContact(Personnel p) {
        logger.info("ContactService updateContact method");
        dao.update(p);
    }

    public void removeContact(Long id, Long cid) {
        logger.info("ContactService removeContact method");
        Personnel p = (Personnel) dao.findById(id, "Personnel");
        for(Contact c : p.getContact()) {
            if(c.getContactId() == cid) {
                p.getContact().remove(c);
                break;
            }
        }
        dao.update(p);
    }
}