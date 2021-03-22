package menu.action;


import entity.Album;
import entity.Song;
import lombok.RequiredArgsConstructor;
import menu.MenuActionContext;
import org.codehaus.jackson.map.ObjectMapper;
import repository.AlbumsRepository;
import repository.SongsRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class WriteLibraryCDToJason implements MenuAction  {

    private final MenuActionContext ctx;
    private final AlbumsRepository albumRepository;
    private final SongsRepository songsRepository;

    @Override
    public void execute() {

        System.out.println("0) Przejdź do poprzedniego menu");

        ObjectMapper mapper = new ObjectMapper();

        List<Album> listAlbums = albumRepository.getAll();
        List<Song> listSongs = songsRepository.getAll();

        try {
            mapper.writeValue(new File("zapisaneAlbumu.json"), listAlbums);
            mapper.writeValue(new File("zapisanePiosenki.json"), listSongs);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ctx.use(MainAction.class).execute();


}
}

