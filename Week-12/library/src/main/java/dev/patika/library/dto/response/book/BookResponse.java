package dev.patika.library.dto.response.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private int id;
    private String name;
    private LocalDate publicationYear;
    private int stock;
}
