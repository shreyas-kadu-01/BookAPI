package com.local.bookapi.controllers;

import com.local.bookapi.entities.Book;
import com.local.bookapi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/book")
    public Book addBook(@RequestBody Book book) {
        return this.bookService.addBook(book);
    }

    @GetMapping(value = "/book")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping(value = "/book/{id}")
    public Book getBookById(@PathVariable("id") int id){
        return this.bookService.getBookById(id);
    }

    @PostMapping(value = "/book/{id}")
    public Book updateBookById(@PathVariable("id") int id, @RequestBody Book book){
        return this.bookService.updateBookById(id, book);
    }

    @DeleteMapping(value = "/book/{id}")
    public void deleteBookById(@PathVariable("id") int id){
        if(this.bookService.getBookById(id) != null) {
            this.bookService.deleteBookById(id);
        }
    }

}
