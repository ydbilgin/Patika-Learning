package dev.patika.library.dto.request.book;

import dev.patika.library.entities.Author;
import dev.patika.library.entities.Publisher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class BookSaveRequest {
    @NotNull
    private String name;
    @NotNull
    private LocalDate publicationYear;
    @NotNull
    private int stock;
    @NotNull
    private Author author;
    @NotNull
    private Publisher publisher;
}
