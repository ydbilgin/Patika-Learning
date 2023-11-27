package org.postgresql;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "book_borrowing")

public class BookBorrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id" , columnDefinition = "serial")
    private int id;
    @Column(name = "borrower_name" , length = 100,nullable = false)
    private String name;
    @Temporal(TemporalType.DATE)
    @Column(name =  "borrowing_date")
    private LocalDate borrowingDate;
    @Temporal(TemporalType.DATE)
    @Column(name =  "return_date")
    private LocalDate returnDate;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_borrow_id" , referencedColumnName = "book_id")
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(LocalDate borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BookBorrowing() {
    }
}
