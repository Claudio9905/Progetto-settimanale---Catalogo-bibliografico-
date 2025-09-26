package claudiopostiglione;

import claudiopostiglione.dao.CatalogoDAO;
import claudiopostiglione.dao.PrestitoDAO;
import claudiopostiglione.dao.UtenteDAO;
import claudiopostiglione.entities.*;
import claudiopostiglione.exceptions.IdNotFoundException;
import claudiopostiglione.exceptions.NameAuthorNotFoundException;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class Application {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogobibliografico");

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        EntityManager em = emf.createEntityManager();
        CatalogoDAO cd = new CatalogoDAO(em);
        PrestitoDAO pd = new PrestitoDAO(em);
        UtenteDAO ud = new UtenteDAO(em);


        System.out.println("Connessione in corso...");
        Thread.sleep(1000);
        System.out.println("Connessione al database effetuata!");


        // Supplier che mi generano oggetti di tipo Libri e Riviste
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

        for (int i = 0; i < 20; i++) {
            cd.save(rivisteSupplier.get());
            cd.save(libriSupplier.get());
        }

        // Supplier che mi generano oggetti di tipo Prestito e Utente
        Supplier<Utente> utenteSupplier = () ->{
            Faker faker = new Faker();
            return new Utente(faker.lordOfTheRings().character(),faker.name().lastName(),getRandomDate(LocalDate.of(1970,1,1),LocalDate.of(2025,9,26)));
        };

        Supplier<Prestito> prestitoSupplier = () ->{
            LocalDate date = getRandomDate(LocalDate.of(2025,1,1), LocalDate.of(2025,9,26));
            Random rdmNumber = new Random();
            Utente newUtente = utenteSupplier.get();
            CatalogoBibliografico newElemento = libriSupplier.get();
            ud.save(newUtente);
            cd.save(newElemento);
           return new Prestito(date,date.plusDays(30),date.plusDays(rdmNumber.nextInt(0,40)),newUtente,newElemento);
        };

        for(int i = 0; i < 5; i++ ){
            pd.save(prestitoSupplier.get());
            ud.save(utenteSupplier.get());
        }

        System.out.println("|--- Benvenuto su Epi-Books ---|");
        int scelta;

        do {
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("| Sono disponibili le seguenti funzioni:");
            System.out.println("|  - Aggiungere una rivista o un libro al catalogo ( premere 1 ) ");
            System.out.println("|  - Rimozione di un elemento(rivista/libro) del catalogo dato un codice ISBN ( premere 2 ) ");
            System.out.println("|  - Ricerca di un elemento(rivista/libro) per codice ISBN ( premere 3 ) ");
            System.out.println("|  - Ricerca di un elemento(rivista/libro) per anno di pubblicazione  ( premere 4 ) ");
            System.out.println("|  - Ricerca di un elemento/rivista/libro) per autore ( premere 5 ) ");
            System.out.println("|  - Ricerca di un elemento(rivista/libro) per titolo o parte di esso ( premere 6 ) ");
            System.out.println("|  - Ricerca degli elementi(rivista/libro) attualmente in prestito dato un numero di tessera utente ( premere 7 ) ");
            System.out.println("|  - Ricerca di tutti i prestiti scaduti e non ancora restituiti (premere 8) ");
            System.out.println("|  - EXIT (premere 0) ");
            scelta = Integer.parseInt(scanner.nextLine());

            switch (scelta) {
                case 0:
                    break;
                case 1:
                    System.out.println("Scegliere se è un libro o una rivista da inserire:");
                    System.out.println("| - Rivista (1)");
                    System.out.println("| - Libro (2)");
                    int sceltaElemento = Integer.parseInt(scanner.nextLine());

                    if (sceltaElemento == 1) {
                        System.out.println("| Inserisci il titolo:");
                        String titolo = scanner.nextLine();
                        System.out.println("| Inserisci l'anno di pubblicazione (year/month/day");
                        int year = Integer.parseInt(scanner.nextLine());
                        int month = Integer.parseInt(scanner.nextLine());
                        int day = Integer.parseInt(scanner.nextLine());
                        LocalDate annoDiPubblicazione = LocalDate.of(year, month, day);
                        System.out.println("| Inserisci il numero di pagine:");
                        int numPagine = Integer.parseInt(scanner.nextLine());
                        System.out.println("| Inserisci il tipo di periodicità (SETTIMANALE, MENSILE, SEMESTRALE):");
                        TipoPeriodicità per = TipoPeriodicità.valueOf(scanner.nextLine());
                        TipoPeriodicità periodicità = per;

                        cd.save(new Riviste(titolo, annoDiPubblicazione, numPagine, periodicità));
                    } else if (sceltaElemento == 2) {
                        System.out.println("| Inserisci il titolo:");
                        String titolo = scanner.nextLine();
                        System.out.println("| Inserisci l'anno di pubblicazione (year/month/day)");
                        int year = Integer.parseInt(scanner.nextLine());
                        int month = Integer.parseInt(scanner.nextLine());
                        int day = Integer.parseInt(scanner.nextLine());
                        LocalDate annoDiPubblicazione = LocalDate.of(year, month, day);
                        System.out.println("| Inserisci il numero di pagine:");
                        int numPagine = Integer.parseInt(scanner.nextLine());
                        System.out.println("Inserisci il nome dell'autore:");
                        String nomeAutore = scanner.nextLine();
                        System.out.println("| Inserisci il genere del libro (AVVENTURA, FANTASY, HORROR, ROMANTICO):");
                        GenereLibro gen = GenereLibro.valueOf(scanner.nextLine());
                        GenereLibro genereElemento = gen;
                        cd.save(new Libri(titolo, annoDiPubblicazione, numPagine, nomeAutore, genereElemento));
                    } else {
                        System.out.println("Errore, scelta non disponibile");
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Inserire il codice ISBN dell'elemento(rivista/libro):");
                        long codice = Long.parseLong(scanner.nextLine());
                        cd.findElementoFromCatalogoAndDelete(codice);
                    } catch (IdNotFoundException er) {
                        System.out.println(er.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Inserire il codice ISBN dell'elemento(rivista/libro):");
                        long codice = Long.parseLong(scanner.nextLine());
                        System.out.println(cd.findCatalogoByISBN(codice));
                    } catch (IdNotFoundException er) {
                        System.out.println(er.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Inserisci l'anno di pubblicazione");
                    int anno = Integer.parseInt(scanner.nextLine());
                    cd.findCatalogoByAnnoDiPubblicazione(anno).forEach(System.out::println);
                    break;
                case 5:
                    try {
                        System.out.println("Inserisci il nome dell'autore:");
                        String nomeAutore = scanner.nextLine();
                        cd.findLibroByAutore(nomeAutore).forEach(System.out::println);
                    } catch (NameAuthorNotFoundException er) {
                        System.out.println(er.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Inserisci il titolo dell'elemento(rivista/libro):");
                    String titoloElmento = scanner.nextLine();
                    cd.findCatalogoByTitolo(titoloElmento).forEach(System.out::println);
                    break;
                case 7:
                    System.out.println("Inserisci il numero della tessera utente");
                    long numTessera = Long.parseLong(scanner.nextLine());
                    pd.findElementoByPrestito(numTessera).forEach(System.out::println);
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Errore scelta non disponibile, prego, riprovare");
            }


            System.out.println("-----------------------------------------------------------------------");
        } while (scelta != 0);


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
