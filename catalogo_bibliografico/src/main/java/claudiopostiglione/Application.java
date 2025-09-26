package claudiopostiglione;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogoBibliografico");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();


        System.out.println("Connessione al database effetuata!");

        em.close();
        emf.close();
    }
}
