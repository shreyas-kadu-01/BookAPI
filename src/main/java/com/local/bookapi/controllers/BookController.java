package com.local.bookapi.controllers;

import com.local.bookapi.entities.Book;
import com.local.bookapi.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookServices bookService;

    @PostMapping(value = "/book")
    public boolean addBook(@RequestBody Book book) {
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
    public boolean updateBookById(@PathVariable("id") int id, @RequestBody Book book){
        return this.bookService.updateBookById(id, book);
    }

    @DeleteMapping(value = "/book/{id}")
    public boolean deleteBookById(@PathVariable("id") int id){
        if(this.bookService.getBookById(id) != null) {
            return this.bookService.deleteBookById(id);
        }
        return false;
    }
}
