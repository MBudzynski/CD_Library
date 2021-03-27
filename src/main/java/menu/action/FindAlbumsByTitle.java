package menu.action;


import lombok.RequiredArgsConstructor;
import menu.CustomScanner;
import menu.MenuActionContext;
import service.AlbumService;

@RequiredArgsConstructor
public class FindAlbumsByTitle implements MenuAction {

    private final MenuActionContext ctx;
    private final CustomScanner scanner;
    private final AlbumService albumService;

    @Override
    public void execute() {

        System.out.println("0) Przejdź do poprzedniego menu");

        System.out.println("Podaj nezwe szukanego albumu");

        var input = scanner.nextLine();

        if (pressedZero(input)) return;

        var albums = albumService.findAlbumsByName(input);


        if (albums.isEmpty()) {
            System.out.println("Brak danych do wyświetlenia");
        } else {
            System.out.println("\n");
            albums.forEach(System.out::println);
            System.out.println("\n");
        }

        ctx.use(MainAction.class).execute();

    }

    private boolean pressedZero(String input) {
        if (input.equals("0")) {
            ctx.use(MainAction.class).execute();
            return true;
        }
        return false;
    }

}
