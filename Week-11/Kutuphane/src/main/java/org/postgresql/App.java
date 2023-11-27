package org.postgresql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;


public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("kutuphane");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();


        transaction.begin();

        /*


        entityManager.persist(entityManager.find(Category.class,1));

        //Publisher Add
        Publisher publisherIthaki = new Publisher();
        publisherIthaki.setAddress("Kadıköy / İstanbul");
        publisherIthaki.setName("İthaki Yayınları");
        publisherIthaki.setEstablishmentYear(LocalDate.of(1996, 1, 1));
        entityManager.persist(publisherIthaki);


        entityManager.persist(publisherIthaki);

        //Author Add
        Author authorAsimov = new Author();
        authorAsimov.setCountry("ABD");
        authorAsimov.setName("Isaac Asimov");
        authorAsimov.setBirthDate(LocalDate.of(1920, 1, 2));
        entityManager.persist(authorAsimov);



        //Book Add
        Book bookFirstRobot = new Book();
        bookFirstRobot.setName("Robot Serisi: İlk Robot");
        bookFirstRobot.setStock(25);
        bookFirstRobot.setPublicationYear(LocalDate.of(1950, 1, 1));
        bookFirstRobot.setAuthor(authorAsimov);
        bookFirstRobot.setPublisher(publisherIthaki);
        entityManager.persist(bookFirstRobot);



        //Borrower Add
        BookBorrowing borrowerMehmet = new BookBorrowing();
        borrowerMehmet.setBorrowingDate(LocalDate.of(2023, 11, 10));
        borrowerMehmet.setReturnDate(LocalDate.of(2023, 11, 15));
        borrowerMehmet.setName("Mehmet Yıldız");
        borrowerMehmet.setBook(bookFirstRobot);
        entityManager.persist(borrowerMehmet);

        */





        transaction.commit();


    }
}