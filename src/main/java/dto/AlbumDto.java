package dto;


import entity.Album;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Value
@Builder
public class AlbumDto {

    private Integer id;

    @NotBlank
    @Size(max = 64)
    private String albumName;

    @NotBlank
    @Size(max = 64)
    private String author;

    public Album toEntity(){
        return Album.builder().id(this.id).albumName(this.albumName).author(this.albumName).build();
    }

    public static AlbumDto from(Album entity){
        return AlbumDto.builder().id(entity.getId()).albumName(entity.getAlbumName()).author(entity.getAuthor()).build();
    }
}
