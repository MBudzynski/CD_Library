package config;

import org.hibernate.cfg.Environment;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JpaUtil {

  private final DataSource dataSource;
  private final Class[] entityClasses;

  private JpaUtil(DataSource dataSource, Class[] entityClasses) {
    this.dataSource = dataSource;
    this.entityClasses = entityClasses;
  }

  public static EntityManagerFactory getEntityManagerFactory(
      DataSource dataSource, Class[] entityClasses) {
    return new JpaUtil(dataSource, entityClasses).getFactory();
  }

  private EntityManagerFactory getFactory() {
    var persistenceUnitInfo = getPersistenceUnitInfo(getClass().getSimpleName());
    var configuration = Map.of();

    return new EntityManagerFactoryBuilderImpl(
            new PersistenceUnitInfoDescriptor(persistenceUnitInfo), configuration)
        .build();
  }

  private HibernatePersistenceUnitInfo getPersistenceUnitInfo(String name) {
    return new HibernatePersistenceUnitInfo(name, getEntityClassNames(), getProperties());
  }

  private List<String> getEntityClassNames() {
    return Stream.of(entityClasses).map(Class::getName).collect(Collectors.toUnmodifiableList());
  }

  private Properties getProperties() {
    var props = new Properties();

    props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
    props.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
    props.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
    props.put(Environment.DATASOURCE, dataSource);
    props.put(Environment.SHOW_SQL, "true");
    props.put(Environment.HBM2DDL_AUTO, "create-drop");

    return props;
  }
}
