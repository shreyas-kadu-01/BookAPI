package com.local.bookapi.controllers;

import com.local.bookapi.Constants;
import com.local.bookapi.entities.AcceptedResponse;
import com.local.bookapi.entities.Book;
import com.local.bookapi.entities.KafkaMessage;
import com.local.bookapi.kafka.BookKafkaProducer;
import com.local.bookapi.repositories.KafkaMessageRepository;
import com.local.bookapi.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class BookController {

    public static final String RESPONSE_ACCEPTED = "Response Accepted";
    @Autowired
    private BookServices bookService;
    @Autowired
    private BookKafkaProducer bookKafkaProducer;

    @Autowired
    private KafkaMessageRepository kafkaMessageRepository;

    @PostMapping(value = "/book")
    public ResponseEntity<AcceptedResponse> addBook(@RequestBody Book book) {
        String uniqueID = UUID.randomUUID().toString();
        KafkaMessage message = new KafkaMessage(uniqueID, Constants.STATUS_INIT, Constants.SAVE, book, Constants.REMARK_ACCEPTED);

        this.bookKafkaProducer.sendMessage(message);

        AcceptedResponse response = new AcceptedResponse(uniqueID, RESPONSE_ACCEPTED);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
//        this.bookService.addBook(book);
    }

    @GetMapping(value = "/book")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(value = "/book/{id}")
    public ResponseEntity<AcceptedResponse> getBookById(@PathVariable("id") int id){
        String uniqueID = UUID.randomUUID().toString();
        Book book = new Book();
        book.setId(id);
        KafkaMessage message = new KafkaMessage(uniqueID, Constants.STATUS_INIT, Constants.GET_ID, book, Constants.REMARK_ACCEPTED);

        this.bookKafkaProducer.sendMessage(message);

        AcceptedResponse response = new AcceptedResponse(uniqueID, RESPONSE_ACCEPTED);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
//        return this.bookService.getBookById(id);
    }

    @PostMapping(value = "/book/{id}")
    public ResponseEntity<AcceptedResponse> updateBookById(@PathVariable("id") int id, @RequestBody Book book){
        String uniqueID = UUID.randomUUID().toString();
        book.setId(id);
        KafkaMessage message = new KafkaMessage(uniqueID, Constants.STATUS_INIT, Constants.SAVE, book, Constants.REMARK_ACCEPTED);

        this.bookKafkaProducer.sendMessage(message);

        AcceptedResponse response = new AcceptedResponse(uniqueID, RESPONSE_ACCEPTED);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
//        return this.bookService.updateBookById(id, book);
    }

    @DeleteMapping(value = "/book/{id}")
    public ResponseEntity<AcceptedResponse> deleteBookById(@PathVariable("id") int id){
        String uniqueID = UUID.randomUUID().toString();
        Book book = new Book();
        book.setId(id);
        KafkaMessage message = new KafkaMessage(uniqueID, Constants.STATUS_INIT, Constants.DELETE, book, Constants.REMARK_ACCEPTED);

        this.bookKafkaProducer.sendMessage(message);

        AcceptedResponse response = new AcceptedResponse(uniqueID, RESPONSE_ACCEPTED);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
    @GetMapping(value = "/status")
    public List<KafkaMessage> getAllMessage(){
        return kafkaMessageRepository.getAllMessage();
    }

    @GetMapping(value = "/status/{uid}")
    @ResponseBody
    public KafkaMessage getMessage(@PathVariable("uid") String uid){
        return kafkaMessageRepository.getMessage(uid);
    }
}
