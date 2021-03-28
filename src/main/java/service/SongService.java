package service;


import dto.SongDto;
import entity.Song;
import mapper.SongMapper;
import repository.SongsRepository;
import java.util.List;
import java.util.stream.Collectors;

public class SongService {

    private SongsRepository repository;
    protected final SongMapper mapper = new SongMapper();

    public SongService(SongsRepository repository) {
        this.repository = repository;
    }

    public List<SongDto> findSongsByArtist(String author){
        return repository.findByAuthor(author).stream().map(song -> mapper.toDto(song)).collect(Collectors.toList());
    }

    public List<SongDto> findSongsByTitle(String title){
        return repository.findByTitle(title).stream().map(song -> mapper.toDto(song)).collect(Collectors.toList());
    }

    public SongDto createSong(Song song){
        Song createSong = repository.create(song);
        return mapper.toDto(createSong);
    }

    public List<SongDto> findByAlbumId(Integer id){
       return repository.findByAlbumId(id).stream().map(song -> mapper.toDto(song)).collect(Collectors.toList());
    }

    public List<SongDto> getAllSong(){
        return repository.getAll().stream().map(song -> mapper.toDto(song)).collect(Collectors.toList());
    }

    public void addSongsFromList(List<Song> listSongs){
        repository.addSongFromList(listSongs);
    }

}
