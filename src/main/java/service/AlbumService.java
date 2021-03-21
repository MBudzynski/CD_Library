package service;


import dto.AlbumDto;
import entity.Album;
import mapper.DtoMapper;
import repository.CrudRepository;

public class AlbumService extends AbstractService<Album, AlbumDto, Integer> {


    public AlbumService(CrudRepository<Album, Integer> repository, DtoMapper<Album, AlbumDto> mapper){
        super(repository,mapper);
    }


}
