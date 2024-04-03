package dev.patika.library.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "publishers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id",columnDefinition = "serial")
    private int id;

    @Column(name = "publisher_name",nullable = false)
    @NotNull
    private String name;

    @Column(name = "publisher_year",nullable = false)
    @NotNull
    private LocalDate establishmentYear;

    @Column(name = "publisher_address")
    @NotNull
    private String address;
}
