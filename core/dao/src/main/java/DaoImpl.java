import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;
import java.util.*;
import java.util.stream.Collectors;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class DaoImpl implements Dao{

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
    }

    public Session getCurrentSession() {
      return sessionFactory.getCurrentSession();
    }
	
    public <T> void add(T object) {
    	getCurrentSession().save(object);
    }

  	public Object findById(long id, String object) {
  		Object o = getCurrentSession().get(object, id);
      return o;
    }
  	
  	public <T> void update(T object) {
  		getCurrentSession().update(object);
  	}

  	public <T> void delete(long id, String object) {
      Object o = getCurrentSession().get(object, id);
      if (o != null) {
          getCurrentSession().delete(o);
      }
  	}

  	public List getList(String object) {
      System.out.println("IN DAO");
      return (List<Object>) getCurrentSession().createQuery("from " + object).setCacheable(true).list();
  	}

    public List<Personnel> searchFor(String str) {
      System.out.println(":!231231");
      Criterion lastname = Restrictions.ilike("name.lname", str + "%");
      Criterion firstname = Restrictions.ilike("name.fname", str + "%");
      Criterion middlename = Restrictions.ilike("name.mname", str + "%");

      List<Personnel> resultList = (List<Personnel>) getCurrentSession()
            .createCriteria(Personnel.class)
            .add(lastname)
            .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
            .list();

      Set<Personnel> set1 = new HashSet<Personnel>(resultList);

      resultList.clear();

      resultList = (List<Personnel>) getCurrentSession()
            .createCriteria(Personnel.class)
            .add(firstname)
            .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
            .list();

      Set<Personnel> set2 = new HashSet<Personnel>(resultList);

      resultList.clear();

      resultList = (List<Personnel>) getCurrentSession()
            .createCriteria(Personnel.class)
            .add(middlename)
            .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
            .list();

      Set<Personnel> set3 = new HashSet<Personnel>(resultList);

      resultList.clear();

      set1.addAll(set2);
      set1.addAll(set3);

      return new ArrayList<Personnel>(set1);

    }
}