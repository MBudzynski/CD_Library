package service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CrudeServiceFactory {

    private final EntityManagerFactory emFactory;
    private Map<Class<? extends CrudService>, CrudService> serviceMap = new HashMap<>();

    @SneakyThrows
    public <T extends CrudService> T get(Class<T> serviceClass) {
        if (serviceMap.get(serviceClass) == null) {
            T repository =
                    serviceClass
                            .getConstructor(EntityManager.class)
                            .newInstance(emFactory.createEntityManager());
            serviceMap.put(serviceClass, repository);
        }

        return (T) serviceMap.get(serviceClass);
    }

}
