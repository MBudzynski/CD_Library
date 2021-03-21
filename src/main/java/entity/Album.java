package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class Album {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 64, nullable = false)
  private String albumName;

  @Column(length = 64, nullable = false)
  private String author;


  @Builder(toBuilder = true)
  public Album(Integer id, String albumName, String author) {
    this.id = id;
    this.albumName = albumName;
    this.author = author;
  }

  @Override
  public String toString() {
    return id + ") " + "album name: " + albumName + ", author: " + author ;
  }
}
