import config.Configuration;
import config.JpaUtil;
import menu.CustomScanner;
import menu.MenuActionContext;
import menu.action.MainAction;
import repository.CrudRepositoryFactory;

public class Application {


    public static void main(String... args) {
        var emFactory =
                JpaUtil.getEntityManagerFactory(
                        Configuration.getDataSource(), Configuration.getEntityClass());

        var repositoryFactory = new CrudRepositoryFactory(emFactory);
        var scanner = new CustomScanner();

        new MenuActionContext(scanner, repositoryFactory).use(MainAction.class).execute();
    }
}