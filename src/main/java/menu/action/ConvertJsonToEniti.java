package menu.action;


import entity.Album;
import entity.Song;
import lombok.RequiredArgsConstructor;
import menu.MenuActionContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import repository.AlbumsRepository;
import repository.SongsRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class ConvertJsonToEniti implements MenuAction {


    private final MenuActionContext ctx;
    private final SongsRepository songsRepository;
    private final AlbumsRepository albumsRepository;

    @Override
    public void execute() throws IOException {

        System.out.println("0) Przejdź do poprzedniego menu");

        ObjectMapper mapper = new ObjectMapper();

        List<Song> listSongs = mapper.readValue(new File("zapisanePiosenki.json"), new TypeReference<List<Song>>(){});

        List<Album> listAlbums = mapper.readValue(new File("zapisaneAlbumu.json"), new TypeReference<List<Album>>(){});

    System.out.println(listSongs);
    System.out.println(listAlbums);

        songsRepository.addSongFromList(listSongs);

        albumsRepository.addAlbummFromList(listAlbums);

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
