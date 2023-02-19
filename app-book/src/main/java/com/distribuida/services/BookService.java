package com.distribuida.services;

import com.distribuida.db.Book;
import org.bson.types.ObjectId;

import java.util.List;

public interface BookService {

    List<Book> getBooks();
    void createBook(Book book);
    Book getBookById(ObjectId objectId);
    void updateBook(ObjectId objectId,Book book);

    void delete(ObjectId id);

}
