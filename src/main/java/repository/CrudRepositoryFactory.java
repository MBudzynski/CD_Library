package repository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CrudRepositoryFactory {

  private final EntityManagerFactory emFactory;
  private Map<Class<? extends CrudRepository>, CrudRepository> repositoryMap = new HashMap<>();

  @SneakyThrows
  public <T extends CrudRepository> T get(Class<T> repositoryClass) {
    if (repositoryMap.get(repositoryClass) == null) {
      T repository =
          repositoryClass
              .getConstructor(EntityManager.class)
              .newInstance(emFactory.createEntityManager());
      repositoryMap.put(repositoryClass, repository);
    }

    return (T) repositoryMap.get(repositoryClass);
  }
}
