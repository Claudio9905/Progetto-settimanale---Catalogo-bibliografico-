package claudiopostiglione.dao;

import claudiopostiglione.entities.Prestito;
import claudiopostiglione.entities.Utente;
import claudiopostiglione.exceptions.IdNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UtenteDAO {

    private final EntityManager entityManager;

    public UtenteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //Metodi per operazioni sul db
    public void save(Utente newUtente) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newUtente);
        transaction.commit();
        System.out.println("|- L'utente " + newUtente + " è stato aggiunto!");
    }

    public Utente findById(long idUtente) {
        Utente found = entityManager.find(Utente.class, idUtente);
        if (found == null) throw new IdNotFoundException(idUtente);
        return found;
    }

}
