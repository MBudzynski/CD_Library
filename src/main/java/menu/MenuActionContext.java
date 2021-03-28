package menu;


import menu.action.*;
import repository.AlbumsRepository;
import repository.CrudRepositoryFactory;
import repository.SongsRepository;
import service.AlbumService;
import service.SongService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MenuActionContext {

  private MenuAction action;
  private Map<Class<? extends MenuAction>, MenuAction> holder = new HashMap<>();

  public MenuActionContext(CustomScanner scanner, CrudRepositoryFactory repositoryFactory) {
    AlbumService albumService = new AlbumService(repositoryFactory.get(AlbumsRepository.class));
    SongService songService = new SongService(repositoryFactory.get(SongsRepository.class));
    initHolder(scanner, albumService, songService);
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

  private void initHolder(CustomScanner scanner, AlbumService albumService, SongService songService) {
    holder.put(MainAction.class, new MainAction(scanner, this));
    holder.put(
        CreateAlbumAction.class,
        new CreateAlbumAction(scanner, this, albumService));
    holder.put(
        ViewAlbumAction.class,
        new ViewAlbumAction(this, albumService));
    holder.put(
        DisplaySongsForAlbum.class,
        new DisplaySongsForAlbum(this, scanner, songService));
    holder.put(
        AddSongToAlbum.class,
        new AddSongToAlbum(
            scanner,
            this,
                songService,
                albumService));
    holder.put(
            FindAlbumsByTitle.class,
            new FindAlbumsByTitle(this, scanner, albumService));
    holder.put(
            FindAlbumsByArtist.class,
            new FindAlbumsByArtist(this, scanner, albumService));
    holder.put(
            FindSongsByTitle.class,
            new FindSongsByTitle(this, scanner, songService));
    holder.put(
            FindSongsByArtist.class,
            new FindSongsByArtist(this, scanner, songService));
    holder.put(
            WriteLibraryCDToJason.class,
            new WriteLibraryCDToJason(this, albumService, songService));
    holder.put(
            ConvertJsonToEniti.class,
            new ConvertJsonToEniti(this, songService, albumService));
  }
}
