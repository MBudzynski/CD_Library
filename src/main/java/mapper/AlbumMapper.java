package mapper;


import dto.AlbumDto;
import entity.Album;

public class AlbumMapper implements DtoMapper<Album, AlbumDto> {


    @Override
    public Album toEntity(AlbumDto dto) {
        return dto.toEntity();
    }

    @Override
    public AlbumDto toDto(Album album) {
        return AlbumDto.from(album);
    }
}
