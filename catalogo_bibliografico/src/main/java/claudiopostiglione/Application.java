package claudiopostiglione;

import claudiopostiglione.dao.CatalogoDAO;
import claudiopostiglione.entities.*;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class Application {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogoBibliografico");

    public static void main(String[] args) throws InterruptedException {

        EntityManager em = emf.createEntityManager();
        CatalogoDAO cd = new CatalogoDAO(em);

        System.out.println("Connessione in corso...");
        Thread.sleep(5000);
        System.out.println("Connessione al database effetuata!");


        Supplier<Libri> libriSupplier = () -> {
            Faker faker = new Faker();
            Random rdnNumber = new Random();
            int numPagine = rdnNumber.nextInt(100, 600);
            String[] genereLibro = {"AVVENTURA", "FANTASY", "HORROR", "ROMANTICO"};
            GenereLibro genere = GenereLibro.valueOf(genereLibro[rdnNumber.nextInt(0, 4)]);

            LocalDate randomDate = getRandomDate(LocalDate.of(1940, 1, 1), LocalDate.of(2025, 9, 26));
            return new Libri(faker.book().title(), randomDate, numPagine, faker.book().author(), genere);
        };

        Supplier<Riviste> rivisteSupplier = () -> {
            Faker faker = new Faker();
            LocalDate randomDate = getRandomDate(LocalDate.of(2020, 1, 1), LocalDate.of(2025, 9, 26));
            Random rdnNumber = new Random();
            int numPagine = rdnNumber.nextInt(10, 30);
            String[] tipoPeriodicità = {"SETTIMANALE", "MENSILE", "SEMESTRALE"};
            TipoPeriodicità periodicità = TipoPeriodicità.valueOf(tipoPeriodicità[rdnNumber.nextInt(0, 3)]);

            return new Riviste(faker.name().title(), randomDate, numPagine, periodicità);
        };

        for(int i = 0; i < 20; i++){
            cd.save(rivisteSupplier.get());
            cd.save(libriSupplier.get());
        }

        System.out.println("|--- Benvenuto su Epi-Books ---|");




        em.close();
        emf.close();
    }

    public static LocalDate getRandomDate(LocalDate start, LocalDate end) {
        long startEpochDay = start.toEpochDay();
        long endtEpochDay = end.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endtEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }
}
