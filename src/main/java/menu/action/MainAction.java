package menu.action;


import lombok.RequiredArgsConstructor;
import menu.CustomScanner;
import menu.MenuActionContext;

@RequiredArgsConstructor
public class MainAction implements MenuAction {

  private final CustomScanner scanner;
  private final MenuActionContext ctx;

  @Override
  public void execute() {
    System.out.println("0) Zamknij aplikację");
    System.out.println("1) Dodaj album");
    System.out.println("2) Wyswietl albumy");
    System.out.println("3) Dodaj piosenkę do albumu");
    System.out.println("4) Wyświetl utwory danego albumu");
    System.out.println("5) Wyszukaj album po nazwie");
    System.out.println("6) Wyszukaj album po wykonawcy");
    System.out.println("7) Wyszukaj piosenki po tytule");
    System.out.println("8) Wyszukaj piosenki po wykonawcy");
    System.out.println("9) Zapisz bibliotekę płyt cd");
    System.out.println("10) Wczytaj bibliotekę płyt cd");

    var input = scanner.nextLine();

    if (input.equals("0")) { 
      System.exit(0);
      return;
    }

    if (input.equals("1")) {
      ctx.use(CreateAlbumAction.class).execute();
      return;
    }

    if (input.equals("2")) {
      ctx.use(ViewAlbumAction.class).execute();
      return;
    }

    if (input.equals("3")) {
      ctx.use(AddSongToAlbum.class).execute();
      return;
    }

    if (input.equals("4")) {
      ctx.use(DisplaySongsForAlbum.class).execute();
      return;
    }

    if (input.equals("5")) {
      ctx.use(FindAlbumsByTitle.class).execute();
      return;
    }

    if (input.equals("6")) {
      ctx.use(FindAlbumsByArtist.class).execute();
      return;
    }

    if (input.equals("7")) {
      ctx.use(FindSongsByTitle.class).execute();
      return;
    }

    if (input.equals("8")) {
      ctx.use(FindSongsByArtist.class).execute();
      return;
    }

    if (input.equals("9")) {
      ctx.use(WriteLibraryCDToJason.class).execute();
      return;
    }

    if (input.equals("10")) {
      ctx.use(ConvertJsonToEniti.class).execute();
      return;
    }

    System.out.println("Wprowadzono nieprawidłowa wartość!");
    execute();
  }
}
