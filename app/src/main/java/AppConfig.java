import com.mycompany.backendhibernatejpaannotation.daoImpl.AbonneDaoImpl;
    import com.mycompany.backendhibernatejpaannotation.daoInterface.IAbonneDao;
    import com.mycompany.backendhibernatejpaannotation.serviceImpl.AbonneServiceImpl;
    import com.mycompany.backendhibernatejpaannotation.serviceInterface.IAbonneService;
    import javax.sql.DataSource;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.ComponentScan;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
    import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
    import org.springframework.jdbc.datasource.DriverManagerDataSource;
    import org.springframework.orm.jpa.JpaTransactionManager;
    import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
    import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
    import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
    import org.springframework.web.servlet.config.annotation.EnableWebMvc;

    /**
    *
    * @author vivien saa
    */
    @Configuration
    @EnableWebMvc
    @ComponentScan(basePackages = "com.mycompany.backendhibernatejpaannotation")
    public class RestConfiguration {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/personneldb");
        dataSource.setUsername("postgres");
        dataSource.setPassword("ex!stgl0bal");
        return dataSource;
    }

    public LocalSessionFactoryBean sessionFactory() {
      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
      sessionFactory.setDataSource(dataSource());
      sessionFactory.setHibernateProperties(hibernateProperties());
      return sessionFactory;
   }

    Properties hibernateProperties() {
      return new Properties() {
         {
            setProperty("hibernate.hbm2ddl.auto",
              "org.hibernate.dialect.PostgreSQLDialect");
            setProperty("hibernate.show_sql",
              "false");
            setProperty("hibernate.cache.region.factory_class",
              "org.hibernate.cache.ehcache.EhCacheRegionFactory");
            setProperty("hibernate.cache.use_second_level_cache",
              "true");
            setProperty("hibernate.cache.use_query_cache",
              "true");  
         }
      };
   }
}