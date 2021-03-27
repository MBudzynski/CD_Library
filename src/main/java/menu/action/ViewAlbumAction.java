package menu.action;


import lombok.RequiredArgsConstructor;
import menu.MenuActionContext;
import service.AlbumService;

@RequiredArgsConstructor
public class ViewAlbumAction implements MenuAction {

    private final MenuActionContext ctx;
    private final AlbumService albumService;


    @Override

    public void execute() {

        var albums = albumService.getAlbums();

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
