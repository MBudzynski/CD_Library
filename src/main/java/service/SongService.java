package service;


import dto.SongDto;
import entity.Song;
import lombok.RequiredArgsConstructor;
import mapper.SongMapper;
import repository.SongsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SongService {

    private SongsRepository repository;
    private SongMapper mapper;

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

    public Optional<SongDto> findSongById(Integer id){
        return repository.findById(id).map(song -> mapper.toDto(song));
    }

    public void deleteSong(Integer id){
        repository.delete(id);
    }

}
