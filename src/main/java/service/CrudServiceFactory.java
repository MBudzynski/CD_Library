package service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CrudServiceFactory {

    private final EntityManagerFactory emFactory;
    private Map<Class<? extends CrudService>, CrudService> serviceMap = new HashMap<>();

    @SneakyThrows
    public <T extends CrudService> T get(Class<T> repositoryClass) {
        if (serviceMap.get(repositoryClass) == null) {
            T repository =
                    repositoryClass
                            .getConstructor(EntityManager.class)
                            .newInstance(emFactory.createEntityManager());
            serviceMap.put(repositoryClass, repository);
        }

        return (T) serviceMap.get(repositoryClass);

    }
}
