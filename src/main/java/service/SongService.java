package service;


import dto.SongDto;
import entity.Song;
import mapper.DtoMapper;
import repository.CrudRepository;

public class SongService extends AbstractService<Song, SongDto, Integer> {

    public SongService(CrudRepository<Song, Integer> repository, DtoMapper<Song, SongDto> mapper){
        super(repository, mapper);
    }

}
