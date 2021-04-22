package service;


import dto.AlbumDto;
import entity.Album;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.AlbumsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class AlbumServiceTest {

    @Mock
    AlbumsRepository repository;

    @InjectMocks
    AlbumService albumService;


    @Test
    public void shouldGetAllAlbums(){
        //given
        given(repository.getAll()).willReturn(preparMockData());
        //when
        List<AlbumDto> albumDtoList = albumService.getAlbums();
        //than
        Assert.assertEquals(3, albumDtoList.size());
        Assert.assertEquals("Ride the Lightning", albumDtoList.get(0).getAlbumName());
        Assert.assertEquals("Celine Dion", albumDtoList.get(1).getAuthor());
        Assert.assertEquals("Thriller", albumDtoList.get(2).getAlbumName());
        Assert.assertEquals("Michael Jacson", albumDtoList.get(2).getAuthor());
        }


    @Test
    public void shouldAddAlbumToRepository(){
        //given
        Album createAlbum = new Album(1, "Ride the Lightning", "Metallica");
        given(repository.create(createAlbum)).willReturn(createAlbum);
        //when
         AlbumDto albumDto = albumService.createAlbum(createAlbum);
        //than
        Assert.assertEquals("Metallica", albumDto.getAuthor());
        Assert.assertEquals("Ride the Lightning",albumDto.getAlbumName());
    }

    @Test
    public void shouldReturnAlbumWithId1(){
        //given
        Album album = new Album(1, "Ride the Lightning", "Metallica");
        given(repository.findById(1)).willReturn(java.util.Optional.of(album));
        //when
        Optional<AlbumDto> albumDto = albumService.findAlbumById(1);
        //than
        Assert.assertEquals("Metallica", albumDto.get().getAuthor());
        Assert.assertEquals("Ride the Lightning",albumDto.get().getAlbumName());
    }

    @Test
    public void shouldFindAlbumByName(){
        //given
        List<Album> albumList = new ArrayList<>();
        albumList.add(new Album(1,"Ride the Lightning", "Metallica"));
        given(repository.findAlbumByName("Ride the Lightning")).willReturn(albumList);
        //when
        List<AlbumDto> albumDto = albumService.findAlbumsByName("Ride the Lightning");
        //than
        Assert.assertEquals("Metallica", albumDto.get(0).getAuthor());
        Assert.assertEquals("Ride the Lightning",albumDto.get(0).getAlbumName());
    }

    @Test
    public void shouldFindAlbumByAuthor(){
        //given
        List<Album> albumList = new ArrayList<>();
        albumList.add(new Album(1,"Ride the Lightning", "Metallica"));
        given(repository.findByAuthor("Metallica")).willReturn(albumList);
        //when
        List<AlbumDto> albumDto = albumService.findAlbumsByArtist("Metallica");
        //than
        Assert.assertEquals("Metallica", albumDto.get(0).getAuthor());
        Assert.assertEquals("Ride the Lightning",albumDto.get(0).getAlbumName());
    }



    private List<Album> preparMockData(){
        List<Album> albums = new ArrayList<>();
        albums.add(new Album(1,"Ride the Lightning", "Metallica"));
        albums.add(new Album(2,"Alive", "Celine Dion"));
        albums.add(new Album(3,"Thriller", "Michael Jacson"));
        return albums;
    }



}