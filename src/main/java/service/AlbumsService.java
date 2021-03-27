package service;

import dto.AlbumDto;
import entity.Album;
import mapper.DtoMapper;
import repository.CrudRepository;

public class AlbumsService extends AbstractService<Album, AlbumDto, Integer> {

    public AlbumsService(CrudRepository<Album, Integer> repository, DtoMapper<Album, AlbumDto> mapper){
        super(repository,mapper);
    }



}
