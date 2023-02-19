package com.distribuida.db;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String _id;
    private String isbn;
    private String title;
    private String author;
    private Double price;
}
