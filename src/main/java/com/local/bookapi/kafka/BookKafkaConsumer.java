package com.local.bookapi.kafka;

import com.local.bookapi.Constants;
import com.local.bookapi.entities.Book;
import com.local.bookapi.entities.KafkaMessage;
import com.local.bookapi.repositories.KafkaMessageRepository;
import com.local.bookapi.services.BookServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookKafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookKafkaConsumer.class);
    @Autowired
    private BookServices bookServices;

    @Autowired
    private KafkaMessageRepository kafkaMessageRepository;

    @KafkaListener(topics = "books", groupId = "myGroup")
    public void consume(KafkaMessage kafkaMessage){
        LOGGER.info(String.format("Message received -> %s", kafkaMessage.toString()));
        this.kafkaMessageRepository.save(kafkaMessage);
        if(kafkaMessage.getOperation() == Constants.SAVE){
            bookServices.addBook(kafkaMessage);
        } else if(kafkaMessage.getOperation() == Constants.DELETE){
            bookServices.deleteBookById(kafkaMessage);
        } else if(kafkaMessage.getOperation() == Constants.GET_ID){
            bookServices.getBookById(kafkaMessage);
        }
    }
}
