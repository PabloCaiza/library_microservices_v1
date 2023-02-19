package com.distribuida.services;

import com.distribuida.db.Book;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BookServiceImpl implements BookService {

    @Inject
    private MongoDatabase mongoDatabase;

    private MongoCollection<Document> mongoCollection;

    @PostConstruct
    void init() {
        mongoCollection = mongoDatabase.getCollection("books");
    }

    @Override
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        var documents = mongoCollection.find()
                .map(document -> Book.builder()
                        ._id(document.getObjectId("_id").toString())
                        .title(document.getString("title"))
                        .author(document.getString("author"))
                        .price(document.getDouble("price"))
                        .isbn(document.getString("isbn"))
                        .build()
                );
        for (Book b : documents) {
            books.add(b);

        }
        return books;
    }

    @Override
    public void createBook(Book book) {
        Document newBook = new Document();
        newBook.append("isbn", book.getIsbn())
                .append("title", book.getTitle())
                .append("author", book.getAuthor())
                .append("price", book.getPrice());
        mongoCollection.insertOne(newBook);
    }

    @Override
    public Book getBookById(ObjectId objectId) {
        Document query = new Document("_id", objectId);
       return mongoCollection.find(query)
                .map(d -> Book.builder()
                        ._id(d.getObjectId("_id").toString())
                        .title(d.getString("title"))
                        .author(d.getString("author"))
                        .price(d.getDouble("price"))
                        .isbn(d.getString("isbn"))
                        .build()
                ).first();

    }

    @Override
    public void updateBook(ObjectId objectId, Book book) {
        Document query=new Document("_id",objectId);
        Document updatedBook = new Document();
        updatedBook.append("isbn", book.getIsbn())
                .append("title", book.getTitle())
                .append("author", book.getAuthor())
                .append("price", book.getPrice());
        mongoCollection.replaceOne(query,updatedBook);



    }

    @Override
    public void delete(ObjectId id) {
        Document query=new Document("_id",id);
        mongoCollection.deleteOne(query);

    }
}
