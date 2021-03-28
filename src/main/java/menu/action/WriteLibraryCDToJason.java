package menu.action;


import dto.AlbumDto;
import dto.SongDto;
import lombok.RequiredArgsConstructor;
import menu.MenuActionContext;
import org.codehaus.jackson.map.ObjectMapper;
import service.AlbumService;
import service.SongService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class WriteLibraryCDToJason implements MenuAction  {

    private final MenuActionContext ctx;
    private final AlbumService albumService;
    private final SongService songService;

    @Override
    public void execute() {

        System.out.println("0) Przejdź do poprzedniego menu");

        ObjectMapper mapper = new ObjectMapper();

        List<AlbumDto> listAlbums = albumService.getAlbums();
        List<SongDto> listSongs = songService.getAllSong();

        try {
            mapper.writeValue(new File("savedAlbums.json"), listAlbums);
            mapper.writeValue(new File("savedSongs.json"), listSongs);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ctx.use(MainAction.class).execute();


}
}

