package mapper;


import dto.SongDto;
import entity.Song;

public class SongMapper implements DtoMapper<Song, SongDto> {
    @Override
    public Song toEntity(SongDto dto) {
        return dto.toEntity();
    }

    @Override
    public SongDto toDto(Song song) {
        return SongDto.from(song);
    }
}
