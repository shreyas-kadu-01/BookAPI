package com.local.bookapi.services;


import com.local.bookapi.entities.Book;
import com.local.bookapi.exceptions.BookNotFoundException;
import com.local.bookapi.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServices {

    @Autowired
    private BookRepository bookRepository;

    public boolean addBook(Book book) {
        return this.bookRepository.save(book);
    }

    public List<Book> getAllBooks(){
        return this.bookRepository.findAll();
    }

    public Book getBookById(int id){
        return this.bookRepository.findById(id);
    }

    public boolean updateBookById(int id, Book newBook) {
        Book book = getBookById(id);

        if(newBook.getBook_name() != null) {
            book.setBook_name(newBook.getBook_name());
        }

        if(newBook.getBook_author() != null) {
            book.setBook_author(newBook.getBook_author());
        }

        return this.bookRepository.save(book);
    }

    public boolean deleteBookById(int id) {
        return this.bookRepository.deleteById(id);
    }
}
