package dev.patika.library.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "bookBorrowings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id",columnDefinition = "serial")
    private int id;

    @Column(name = "borrow_borrower_name",nullable = false)
    @NotNull
    private String borrowerName;
    @Column(name = "borrow_borrower_mail",nullable = false)
    @NotNull
    private String borrowerEmail;

    @Column(name = "borrow_date",nullable = false)
    @NotNull
    private LocalDate borrowingDate;

    @Column(name = "borrow_return_date")
    @NotNull
    private LocalDate returnDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;
}
