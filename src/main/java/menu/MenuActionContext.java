package menu;



import menu.action.*;
import repository.AlbumsRepository;
import repository.CrudRepositoryFactory;
import repository.SongsRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MenuActionContext {

  private MenuAction action;
  private Map<Class<? extends MenuAction>, MenuAction> holder = new HashMap<>();

  public MenuActionContext(CustomScanner scanner, CrudRepositoryFactory repositoryFactory) {
    initHolder(scanner, repositoryFactory);
  }

  public MenuActionContext use(Class<? extends MenuAction> actionClass) {
    action = holder.get(actionClass);
    return this;
  }

  public void execute() {
    if (action == null) throw new RuntimeException("Menu action not set");
    try {
      action.execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void initHolder(CustomScanner scanner, CrudRepositoryFactory repositoryFactory ) {
    holder.put(MainAction.class, new MainAction(scanner, this));
    holder.put(
        CreateAlbumAction.class,
        new CreateAlbumAction(scanner, this, repositoryFactory.get(AlbumsRepository.class)));
    holder.put(
        ViewAlbumAction.class,
        new ViewAlbumAction(this, repositoryFactory.get(AlbumsRepository.class)));
    holder.put(
        DisplaySongsForAlbum.class,
        new DisplaySongsForAlbum(this, scanner, repositoryFactory.get(SongsRepository.class)));
    holder.put(
        AddSongToAlbum.class,
        new AddSongToAlbum(
            scanner,
            this,
            repositoryFactory.get(SongsRepository.class),
            repositoryFactory.get(AlbumsRepository.class)));
    holder.put(
            FindAlbumsByTitle.class,
            new FindAlbumsByTitle(this, scanner, repositoryFactory.get(AlbumsRepository.class)));
    holder.put(
            FindAlbumsByArtist.class,
            new FindAlbumsByArtist(this, scanner, repositoryFactory.get(AlbumsRepository.class)));
    holder.put(
            FindSongsByTitle.class,
            new FindSongsByTitle(this, scanner, repositoryFactory.get(SongsRepository.class)));
    holder.put(
            FindSongsByArtist.class,
            new FindSongsByArtist(this, scanner, repositoryFactory.get(SongsRepository.class)));
    holder.put(
            WriteLibraryCDToJason.class,
            new WriteLibraryCDToJason(this, repositoryFactory.get(AlbumsRepository.class), repositoryFactory.get(SongsRepository.class)));
    holder.put(
            ConvertJsonToEniti.class,
            new ConvertJsonToEniti(this, repositoryFactory.get(SongsRepository.class), repositoryFactory.get(AlbumsRepository.class)));
  }
}
