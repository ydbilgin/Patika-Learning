package org.postgresql;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id" , columnDefinition = "serial")
    private int id;
    @Column(name = "book_name" , length = 100,nullable = false)
    private String name;
    @Temporal(TemporalType.DATE)
    @Column(name =  "book_publication_year")
    private LocalDate publicationYear;
    @Column(name = "book_stock")
    private int stock;



    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_author_id" , referencedColumnName = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.MERGE)
    @JoinColumn ( name = "book_publisher_id" , referencedColumnName = "publisher_id")
    private Publisher publisher;

    @OneToMany(mappedBy = "book" ,fetch = FetchType.LAZY , cascade = CascadeType.REMOVE)
    private List<BookBorrowing> borrowList;

    @ManyToMany(mappedBy = "bookList",fetch = FetchType.EAGER , cascade = CascadeType.PERSIST)
    private List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<BookBorrowing> getBorrowList() {
        return borrowList;
    }

    public void setBorrowList(List<BookBorrowing> borrowList) {
        this.borrowList = borrowList;
    }

    public Book() {

    }


    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public LocalDate getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(LocalDate publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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
}
