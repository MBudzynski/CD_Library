package menu.action;


import lombok.RequiredArgsConstructor;
import menu.CustomScanner;
import menu.MenuActionContext;
import repository.SongsRepository;

@RequiredArgsConstructor
public class DisplaySongsForAlbum implements MenuAction {
  private final MenuActionContext ctx;
  private final CustomScanner scanner;
  private final SongsRepository songsRepository;

  @Override
  public void execute() {

    System.out.println("0) Przejdź do poprzedniego menu");

    System.out.println("Podaj numer albumu do wyświtlenia");

    var input = scanner.nextLine();

    if (pressedZero(input)) return;

    var songs = songsRepository.findByAlbumId(Integer.valueOf(input));

    if (songs.isEmpty()) {
      System.out.println("Brak danych do wyświetlenia");
    } else {
      System.out.println("\n");
      songs.forEach(System.out::println);
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