package com.distribuida.rest;

import com.distribuida.db.Book;
import com.distribuida.services.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
@Path("/books")
public class BookRest {
    @Inject
    private BookService bookService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book findById(@PathParam("id") String id) {
        return bookService.getBookById(new ObjectId(id));
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> findAll (){
        return bookService.getBooks();
    }
    @DELETE
    @Path("/{id}")
    public Response delete (@PathParam("id") String id) {
        bookService.delete(new ObjectId(id));
        return Response.status((Response.Status.NO_CONTENT) ).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBooks(){
        return Book.builder()
                .title("asd").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Book b) {
        bookService.createBook(b);
        return Response.status(Response.Status.CREATED).build();
    }
    @PUT @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update (Book b, @PathParam("id") String id) {
        bookService.updateBook(new ObjectId(id),b);
        return Response.status((Response.Status.OK) ).build();
    }

}
