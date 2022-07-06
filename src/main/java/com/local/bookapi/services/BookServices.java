package com.local.bookapi.services;


import com.local.bookapi.Constants;
import com.local.bookapi.entities.Book;
import com.local.bookapi.entities.KafkaMessage;
import com.local.bookapi.exceptions.BookNotFoundException;
import com.local.bookapi.repositories.BookRepository;
import com.local.bookapi.repositories.KafkaMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class BookServices {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private KafkaMessageRepository kafkaMessageRepository;

    public void addBook(KafkaMessage message) {
        message.setStatus(Constants.STATUS_PROCESSING);
        kafkaMessageRepository.save(message);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            this.bookRepository.save(message.getBook());
            message.setStatus(Constants.STATUS_COMPLETED);
        } catch (Exception e) {
            message.setStatus(Constants.STATUS_FAILED);
            message.setRemarks(e.getMessage());
        }

        kafkaMessageRepository.save(message);
    }

    public List<Book> getAllBooks(){
        return this.bookRepository.findAll();
    }

    public void getBookById(KafkaMessage kafkaMessage){
        kafkaMessage.setStatus(Constants.STATUS_PROCESSING);
        kafkaMessageRepository.save(kafkaMessage);

        try {
            this.bookRepository.findById(kafkaMessage.getBook().getId());
            kafkaMessage.setStatus(Constants.STATUS_COMPLETED);
            kafkaMessageRepository.save(kafkaMessage);
        } catch (BookNotFoundException e) {
            kafkaMessage.setStatus(Constants.STATUS_FAILED);
            kafkaMessage.setRemarks(Constants.BOOKOTFOUND);
            kafkaMessageRepository.save(kafkaMessage);
        }

    }

    public boolean deleteBookById(KafkaMessage kafkaMessage) {
        return this.bookRepository.deleteById(kafkaMessage.getBook().getId());
    }
}
