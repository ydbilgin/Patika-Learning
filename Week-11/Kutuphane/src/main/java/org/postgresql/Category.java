package org.postgresql;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id" , columnDefinition = "serial")
    private int id;
    @Column(name = "category_name" , length = 100,nullable = false)
    private String name;
    @Column(name = "category_description" , length = 300,nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "cat2book",
            joinColumns = {@JoinColumn(name = "cat2book_category_id" )},
            inverseJoinColumns = {@JoinColumn(name = "cat2book_book_id")}
    )
    private List<Book> bookList;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Category() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
