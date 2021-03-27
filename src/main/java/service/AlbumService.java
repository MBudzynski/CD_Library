package service;


import dto.AlbumDto;
import entity.Album;
import lombok.RequiredArgsConstructor;
import mapper.AlbumMapper;
import repository.AlbumsRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AlbumService {

    private AlbumsRepository repository;
    private AlbumMapper mapper;


    public AlbumDto createAlbum(Album album){
        Album createdAlbum = repository.create(album);
        return mapper.toDto(createdAlbum);
    }

    public List<AlbumDto> getAlbums(){
        return repository.getAll().stream().map(album -> mapper.toDto(album)).collect(Collectors.toList());
    }

    public Optional<AlbumDto> findAlbumById(Integer id){
        return repository.findById(id).map(album -> mapper.toDto(album));
    }

    public List<AlbumDto> findAlbumsByName(String name){
        return repository.findAlbumByName(name).stream().map(album -> mapper.toDto(album)).collect(Collectors.toList());
    }

    public List<AlbumDto> findAlbumsByArtist(String name){
        return repository.findByAuthor(name).stream().map(album -> mapper.toDto(album)).collect(Collectors.toList());
    }

    public void deleteAlbum(Integer id){
        repository.delete(id);
    }


}
