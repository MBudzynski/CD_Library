package menu.action;


import lombok.RequiredArgsConstructor;
import menu.MenuActionContext;
import repository.AlbumsRepository;
import service.AlbumService;

@RequiredArgsConstructor
public class ViewAlbumAction implements MenuAction {

    private final MenuActionContext ctx;
    private final AlbumsRepository repository;


    @Override

    public void execute() {

        var albums = repository.getAll();

        if (albums.isEmpty()) {
            System.out.println("Brak danych do wyświetlenia");
        } else {
            System.out.println("\n");
            albums.forEach(System.out::println);
            System.out.println("\n");
        }

        ctx.use(MainAction.class).execute();
    }
}
