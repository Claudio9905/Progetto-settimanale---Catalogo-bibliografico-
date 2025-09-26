package claudiopostiglione.dao;

import claudiopostiglione.entities.CatalogoBibliografico;
import claudiopostiglione.entities.Libri;
import claudiopostiglione.entities.Prestito;
import claudiopostiglione.exceptions.IdNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PrestitoDAO {
    private final EntityManager entityManager;

    public PrestitoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //Metodi per operazioni sul db
    public void save(Prestito newPrestito) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newPrestito);
        transaction.commit();
        System.out.println("|- Il prestito " + newPrestito + " è stato salvato!");
    }

    public Prestito findById(long idPrestito) {
        Prestito found = entityManager.find(Prestito.class, idPrestito);
        if (found == null) throw new IdNotFoundException(idPrestito);
        return found;
    }

    public List<Prestito> findElementoByPrestito(long idUtente){
        TypedQuery<Prestito> query = entityManager.createQuery("SELECT p.elementoPrestato FROM Prestito p WHERE p.utente = :idUtente", Prestito.class);
        if(query == null) throw new IdNotFoundException(idUtente);
        query.setParameter("idUtente", idUtente);
        return  query.getResultList();
    }
}
