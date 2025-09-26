package claudiopostiglione.dao;

import claudiopostiglione.entities.CatalogoBibliografico;
import claudiopostiglione.entities.Libri;
import claudiopostiglione.entities.Riviste;
import claudiopostiglione.exceptions.IdNotFoundException;
import claudiopostiglione.exceptions.NameAuthorNotFoundException;
import claudiopostiglione.exceptions.TitleElementNotFoundException;
import claudiopostiglione.exceptions.YearNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

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

    public CatalogoBibliografico findById(long idElemento) {
        CatalogoBibliografico found = entityManager.find(CatalogoBibliografico.class, idElemento);
        if (found == null) throw new IdNotFoundException(idElemento);
        return found;
    }

    public Libri findLibroById(long idLibro) {
        Libri found = entityManager.find(Libri.class, idLibro);
        if (found == null) throw new IdNotFoundException(idLibro);
        return found;
    }

    public Riviste findRivisteById(long idRivista) {
        Riviste found = entityManager.find(Riviste.class, idRivista);
        if (found == null) throw new IdNotFoundException(idRivista);
        return found;
    }

    public void findElementoFromCatalogoAndDelete(long idElemento) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("DELETE FROM CatalogoBibliografico cb WHERE cb.codiceISBN = :idElemento");
        if (query == null) throw new IdNotFoundException(idElemento);
        query.setParameter("idElemento", idElemento);

        int numDeleted = query.executeUpdate();

        transaction.commit();
        System.out.println("|- Numero di elementi cancellati: " + numDeleted);
    }

    public CatalogoBibliografico findCatalogoByISBN(long idElemento) {
        TypedQuery<CatalogoBibliografico> query = entityManager.createQuery("SELECT ca FROM CatalogoBibliografico ca WHERE ca.codiceISBN = :idElemento", CatalogoBibliografico.class);
        if (query == null) throw new IdNotFoundException(idElemento);
        query.setParameter("idElemento", idElemento);
        return query.getSingleResult();
    }

    public List<CatalogoBibliografico> findCatalogoByAnnoDiPubblicazione(int annoDiPubblicazione) {
        TypedQuery<CatalogoBibliografico> query = entityManager.createQuery("SELECT ca FROM CatalogoBibliografico ca WHERE  EXTRACT(YEAR FROM ca.annoDiPubblicazione) = :annoDiPubblicazione", CatalogoBibliografico.class);
        if(query == null) throw new YearNotFoundException(annoDiPubblicazione);
        query.setParameter("annoDiPubblicazione", annoDiPubblicazione);
        return query.getResultList();
    }

    public List<Libri> findLibroByAutore(String nomeAutore) {
        TypedQuery<Libri> query = entityManager.createQuery("SELECT li FROM Libri li WHERE li.autore = :nomeAutore", Libri.class);
        if (query == null) throw new NameAuthorNotFoundException(nomeAutore);
        query.setParameter("nomeAutore", nomeAutore);
        return query.getResultList();
    }

    public List<CatalogoBibliografico> findCatalogoByTitolo(String titolo) {
        TypedQuery<CatalogoBibliografico> query = entityManager.createQuery("SELECT ca FROM CatalogoBibliografico ca WHERE ca.titolo = :titolo", CatalogoBibliografico.class);
        if(query == null) throw new TitleElementNotFoundException(titolo);
        query.setParameter("titolo", titolo);
        return query.getResultList();
    }
}
