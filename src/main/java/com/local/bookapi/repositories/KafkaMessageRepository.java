package com.local.bookapi.repositories;

import com.local.bookapi.entities.Book;
import com.local.bookapi.entities.KafkaMessage;
import com.local.bookapi.exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KafkaMessageRepository {
    private static final String KEY = "MESSAGE";
    @Autowired
    private RedisTemplate redisTemplate;

    public boolean save(KafkaMessage kafkaMessage) {
        try {
            redisTemplate.opsForHash().put(KEY, kafkaMessage.getUID(), kafkaMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<KafkaMessage> getAllMessage() {
        return redisTemplate.opsForHash().values(KEY);
    }

    public KafkaMessage getMessage(String uid) {
        KafkaMessage kafkaMessage = (KafkaMessage) redisTemplate.opsForHash().get(KEY, uid);
        if(kafkaMessage != null) {
            return kafkaMessage;
        } else {
            throw new BookNotFoundException("Message with uid " + uid + " not found");
        }
    }
}
