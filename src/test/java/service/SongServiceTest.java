package service;

import dto.AlbumDto;
import dto.SongDto;
import entity.Album;
import entity.Song;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.SongsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class SongServiceTest {

    @Mock
    SongsRepository repository;

    @InjectMocks
    SongService songService;

    @Test
    public void shouldGetAllSongs(){
        //given
        given(repository.getAll()).willReturn(preparMockData());
        //when
        List<SongDto> songsDtoList = songService.getAllSong();
        //than
        Assert.assertEquals(3, songsDtoList.size());
        Assert.assertEquals("Darniere Dense", songsDtoList.get(0).getTitle());
        Assert.assertEquals("Indila", songsDtoList.get(1).getAuthor());
        Assert.assertEquals("The girl is mine", songsDtoList.get(2).getTitle());
        Assert.assertEquals("Michael Jacson", songsDtoList.get(2).getAuthor());
    }

    @Test
    public void shouldAddSongToRepository(){
        //given
        Song createSong = new Song(2, "Darniere Dense" , "Indila" , new Album(1, "Mini World" , "Indila"));
        given(repository.create(createSong)).willReturn(createSong);
        //when
        SongDto songDto = songService.createSong(createSong);
        //than
        Assert.assertEquals("Indila", songDto.getAuthor());
        Assert.assertEquals("Darniere Dense",songDto.getTitle());
        Assert.assertEquals(java.util.Optional.of(1).get(),songDto.getAlbum().getId());
    }

    @Test
    public void shouldReturnSongWithId1(){
        //given
        Song song = new Song(1, "Darniere Dense" , "Indila" , new Album(1, "Mini World" , "Indila"));
        given(repository.findById(1)).willReturn(Optional.of(song));
        //when
        List<SongDto> songDto = songService.findByAlbumId(1);
        //than
        Assert.assertEquals("Indila", songDto.get(0).getAuthor());
        Assert.assertEquals("Darniere Dense",songDto.get(0).getTitle());
    }

    @Test
    public void shouldFindSongsByTitle(){
        //given
        List<Song> songList = new ArrayList<>();
        songList.add(new Song(3, "The girl is mine", "Michael Jacson", new Album(4,"Thriller", "Michael Jacson")));
        given(repository.findByTitle("The girl is mine")).willReturn(songList);
        //when
        List<SongDto> songDto = songService.findSongsByTitle("The girl is mine");
        //than
        Assert.assertEquals("Michael Jacson", songDto.get(0).getAuthor());
        Assert.assertEquals("The girl is mine",songDto.get(0).getTitle());
    }

    @Test
    public void shouldFindSongByArtist(){
        //given
        List<Song> songsList = new ArrayList<>();
        songsList.add(new Song(1, "Darniere Dense" , "Indila" , new Album(1, "Mini World" , "Indila")));
        songsList.add(new Song(2, "Love Story", "Indila", new Album(1, "Mini World" , "Indila")));
        given(repository.findByAuthor("Indila")).willReturn(songsList);
        //when
        List<SongDto> songDto = songService.findSongsByArtist("Indila");
        //than
        Assert.assertEquals("Darniere Dense", songDto.get(0).getTitle());
        Assert.assertEquals("Love Story", songDto.get(1).getTitle());
    }


    private List<Song> preparMockData(){
        List<Song> songs = new ArrayList<>();
        songs.add(new Song(1, "Darniere Dense" , "Indila" , new Album(1, "Mini World" , "Indila")));
        songs.add(new Song(2, "Love Story", "Indila", new Album(1, "Mini World" , "Indila")));
        songs.add( new Song(3, "The girl is mine", "Michael Jacson", new Album(4,"Thriller", "Michael Jacson")) );
        return songs;
    }
}
