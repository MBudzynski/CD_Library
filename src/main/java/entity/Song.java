package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Song {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 64, nullable = false)
  private String title;

  @Column(length = 64, nullable = false)
  private String author;

  @ManyToOne(cascade = CascadeType.MERGE)
  private Album album;

  @Builder(toBuilder = true)
  public Song(Integer id, String title, String author, Album album) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.album = album;
  }

}
