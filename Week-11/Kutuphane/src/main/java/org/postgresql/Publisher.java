package org.postgresql;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id" , columnDefinition = "serial")
    private int id;
    @Column(name = "publisher_name" , length = 100,nullable = false)
    private String name;
    @Temporal(TemporalType.DATE)
    @Column(name =  "publisher_establishment_year")
    private LocalDate establishmentYear;
    @Column(name = "publisher_address" , length = 300,nullable = false)
    private String address;

    @OneToMany(mappedBy = "publisher" , fetch = FetchType.LAZY , cascade = CascadeType.REMOVE)
    private List<Book> bookList;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
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

    public LocalDate getEstablishmentYear() {
        return establishmentYear;
    }

    public void setEstablishmentYear(LocalDate establishmentYear) {
        this.establishmentYear = establishmentYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Publisher() {
    }
}
