package com.local.bookapi;

import com.local.bookapi.entities.Book;
import com.local.bookapi.repositories.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BookApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApiApplication.class, args);

//        ApplicationContext context = SpringApplication.run(BookApiApplication.class, args);
//        BookRepository bookRepository = context.getBean(BookRepository.class);
//
//        Book book = new Book();
//
//        book.setBookName("Java");
//        book.setAuthorName("Shreyas Kadu");
//
//        bookRepository.save(book);
//        shsh
    }

}
