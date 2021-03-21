package menu.action;


import entity.Album;
import lombok.RequiredArgsConstructor;
import menu.CustomScanner;
import menu.MenuActionContext;
import repository.AlbumsRepository;

@RequiredArgsConstructor
public class CreateAlbumAction implements MenuAction {
  private final CustomScanner scanner;
  private final MenuActionContext ctx;
  private final AlbumsRepository repository;

  @Override
  public void execute() {

    System.out.println("0) Przejdź do poprzedniego menu");
    System.out.println("Podaj nazwę albumu:");

    var input = scanner.nextLine();

    if (pressedZero(input)) return;

    Album.AlbumBuilder builder = Album.builder().albumName(input);

    System.out.println("Podaj autora:");

    input = scanner.nextLine();

    if (pressedZero(input)) return;

    var album = builder.author(input).build();

    repository.create(album);
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
