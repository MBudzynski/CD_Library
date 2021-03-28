package menu.action;


import entity.Album;
import entity.Song;
import lombok.RequiredArgsConstructor;
import menu.MenuActionContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import service.AlbumService;
import service.SongService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class ConvertJsonToEniti implements MenuAction {


    private final MenuActionContext ctx;
    private final SongService songService;
    private final AlbumService albumService;

    @Override
    public void execute() throws IOException {

        System.out.println("0) Przejdź do poprzedniego menu");

        ObjectMapper mapper = new ObjectMapper();

        List<Song> listSongs = mapper.readValue(new File("savedSongs.json"), new TypeReference<List<Song>>(){});

        List<Album> listAlbums = mapper.readValue(new File("savedAlbums.json"), new TypeReference<List<Album>>(){});

        songService.addSongsFromList(listSongs);

        albumService.addAlbumsFromList(listAlbums);

       ctx.use(MainAction.class).execute();


    }

}
