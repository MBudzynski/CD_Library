package dto;


import entity.Album;
import entity.Song;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@Builder
public class SongDto {

    private Integer id;


    @NotBlank
    @Size(max = 64)
    private String title;

    @NotBlank
    @Size(max = 64)
    private String author;

    private Album album;

    public Song toEntity(){
        return Song.builder().id(this.id).title(this.title).author(this.author).album(this.album).build();
    }

    public static SongDto from(Song entity){
        return SongDto.builder().id(entity.getId()).title(entity.getTitle()).author(entity.getAuthor()).album(entity.getAlbum()).build();
    }

    @Override
    public String toString() {
        return id + ")" + " title= " + title + ", " + "author=" + author + ", album: " + album.getAlbumName();
    }
}
