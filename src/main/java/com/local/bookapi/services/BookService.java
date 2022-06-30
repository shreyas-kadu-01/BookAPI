package com.local.bookapi.services;

import com.local.bookapi.entities.Book;
import com.local.bookapi.entities.Notification;
import com.local.bookapi.exceptions.BookNotFoundException;
import com.local.bookapi.kafka.BookKafkaProducer;
import com.local.bookapi.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookKafkaProducer bookKafkaProducer;

    public Book addBook(Book book) {
        Book newBook = this.bookRepository.save(book);
        Notification notification = new Notification();
        notification.setBook(newBook);
        notification.setMessage(String.format("Book of id %d added", newBook.getId()));
        notification.setTimeStamp(timeStamp());
        bookKafkaProducer.sendMessage(notification);
        return newBook;
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

        Book newBook1 = this.bookRepository.save(book);
        Notification notification = new Notification();
        notification.setBook(newBook1);
        notification.setMessage(String.format("Book of id %d updated", newBook1.getId()));
        notification.setTimeStamp(timeStamp());
        bookKafkaProducer.sendMessage(notification);
        return newBook1;
    }

    public void deleteBookById(int id) {
        Notification notification = new Notification();
        notification.setMessage(String.format("Book of id %d deleted", id));
        notification.setTimeStamp(timeStamp());
        bookKafkaProducer.sendMessage(notification);
        this.bookRepository.deleteById(id);
    }

    public String timeStamp(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
