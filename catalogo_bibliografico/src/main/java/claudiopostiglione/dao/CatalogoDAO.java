package claudiopostiglione.dao;

import claudiopostiglione.entities.CatalogoBibliografico;
import claudiopostiglione.entities.Libri;
import claudiopostiglione.entities.Riviste;
import claudiopostiglione.exceptions.IdNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class CatalogoDAO {

    private final EntityManager entityManager;

    public CatalogoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //Metodi per operazioni sul db
    public void save(CatalogoBibliografico newElemento) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newElemento);
        transaction.commit();
        System.out.println("|- L'elemento " + newElemento + " è stato salvato!");
    }

    public CatalogoBibliografico findById(String idElemento) {
        CatalogoBibliografico found = entityManager.find(CatalogoBibliografico.class, idElemento);
        if (found == null) throw new IdNotFoundException(idElemento);
        return found;
    }

    public Libri findLibroById(String idLibro) {
        Libri found = entityManager.find(Libri.class, idLibro);
        if (found == null) throw new IdNotFoundException(idLibro);
        return found;
    }

    public Riviste findRivisteById(String idRivista) {
        Riviste found = entityManager.find(Riviste.class, idRivista);
        if (found == null) throw new IdNotFoundException(idRivista);
        return found;
    }

    public void findElementoFromCatalogoAndDelete(String idElemento){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("DELETE FROM CatalogoBibliografico cb WHERE cb.codiceISBN = :idElemento");
        query.setParameter("idElemento", idElemento);

        int numDeleted = query.executeUpdate();

        transaction.commit();
        System.out.println("|- Numero di elementi cancellati: " + numDeleted);
    }

    public CatalogoBibliografico findCatalogoByISBN(String idElemento){
        TypedQuery<CatalogoBibliografico> query = entityManager.createQuery("SELECT ca FROM CatalogoBibliografico ca WHERE ca.codiceISBN = :idElemento", CatalogoBibliografico.class);
        if(query == null) throw new IdNotFoundException(idElemento);
        query.setParameter("idElemento", idElemento);
        return query.getSingleResult();
    }

    public List<CatalogoBibliografico> findCatalogoByAnnoDiPubblicazione(LocalDate annoDiPubblicazione){
        TypedQuery<CatalogoBibliografico> query = entityManager.createQuery("SELECT ca FROM CatalogoBibliografico ca WHERE ca.annoDiPubblicazione = :annoDiPubblicazione", CatalogoBibliografico.class);
        query.setParameter("annoDiPubblicazione", annoDiPubblicazione);
        return  query.getResultList();
    }

    public List<Libri> findLibroByAutore(String nomeAutore){
        TypedQuery<Libri> query = entityManager.createQuery("SELECT li FROM Libri li WHERE li.autore = :nomeAutore", Libri.class);
        query.setParameter("nomeAutore", nomeAutore);
        return  query.getResultList();
    }

    public List<CatalogoBibliografico> findCatalogoByTitolo(String titolo){
        TypedQuery<CatalogoBibliografico> query = entityManager.createQuery("SELECT ca FROM CatalogoBibliografico ca WHERE ca.titolo = :titolo", CatalogoBibliografico.class);
        query.setParameter("titolo", titolo);
        return  query.getResultList();
    }
}
