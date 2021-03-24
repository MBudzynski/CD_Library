package repository;


import entity.Album;

import javax.persistence.EntityManager;
import java.util.List;


public class AlbumsRepository extends AbstractRepository<Album, Integer> {


  public AlbumsRepository(EntityManager em) {
    super(em, Album.class);
  }

  public void addAlbumFromList(List<Album> listAlbums){
    Album album;
    for (int i = 0; i <listAlbums.size() ; i++) {
      em.getTransaction().begin();
      album = listAlbums.get(i);
      em.merge(album);
      em.getTransaction().commit();
    }
  }


  public List<Album> findByAuthor(String author) {

    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(Album.class);
    var root = criteriaQuery.from(Album.class);
    var predicate = criteriaBuilder.like(root.get("author"), "%" + author + "%");
    return em.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
  }

  public List<Album> findAlbumByName(String albumName) {

    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(Album.class);
    var root = criteriaQuery.from(Album.class);
    var predicate = criteriaBuilder.like(root.get("albumName"), "%" + albumName + "%");
    return em.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
  }


}
