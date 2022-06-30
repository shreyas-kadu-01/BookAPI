package com.local.bookapi.services;

import com.local.bookapi.entities.Book;
import com.local.bookapi.exceptions.BookNotFoundException;
import com.local.bookapi.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return this.bookRepository.save(book);
    }

    public List<Book> getAllBooks(){
        return (List<Book>) this.bookRepository.findAll();
    }

    public Book getBookById(int id){
        Optional<Book> futBook = this.bookRepository.findById(id);

        if(futBook.isEmpty()){
            throw new BookNotFoundException("Book with id " + id + " not found");
        } else {
            return futBook.get();
        }
    }

    public Book updateBookById(int id, Book newBook) {
        Book book = getBookById(id);

        if(newBook.getBookName() != null) {
            book.setBookName(newBook.getBookName());
        }

        if(newBook.getAuthorName() != null) {
            book.setAuthorName(newBook.getAuthorName());
        }

        return this.bookRepository.save(book);
    }

    public void deleteBookById(int id) {
        this.bookRepository.deleteById(id);
    }

}
