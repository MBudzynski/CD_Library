package repository;


import entity.Song;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


public class SongsRepository extends AbstractRepository<Song, Integer> {


  public SongsRepository(EntityManager em) {
    super(em, Song.class);
  }

  public List<Song> findByAuthor(String author) {
    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(Song.class);
    var root = criteriaQuery.from(Song.class);
    var predicate = criteriaBuilder.like(root.get("author"), "%" + author + "%");
    return em.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
  }


  public List<Song> findByTitle(String title) {
    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(Song.class);
    var root = criteriaQuery.from(Song.class);
    var predicate = criteriaBuilder.like(root.get("title"), "%" + title + "%");
    return em.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
  }

  public List<Song> findByAlbumId(Integer id) {
      CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
      CriteriaQuery<Song> criteriaQuery = criteriaBuilder.createQuery(Song.class);
      Root<Song> root = criteriaQuery.from(Song.class);
      Predicate predicate = criteriaBuilder.equal(root.get("album"), id);
      return em.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
  }

  public void addSongFromList (List<Song> listSongs){
    Song song;
    for (int i = 0; i <listSongs.size() ; i++) {
      em.getTransaction().begin();
      song = listSongs.get(i);
      em.merge(song);
      em.getTransaction().commit();
    }
  }
}
