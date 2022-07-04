package com.local.bookapi.repositories;

import com.local.bookapi.entities.Book;
import com.local.bookapi.exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    private static final String KEY = "BOOK";
    @Autowired
    private RedisTemplate redisTemplate;

    public boolean save(Book book) {
        try {
            redisTemplate.opsForHash().put(KEY, book.getId(), book);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Book> findAll() {
        return redisTemplate.opsForHash().values(KEY);
    }

    public Book findById(int id) {
         Book b = (Book) redisTemplate.opsForHash().get(KEY,id);
        if(b != null) {
            return b;
        } else {
            throw new BookNotFoundException("Book with id " + id + " not found");
        }

    }

    public boolean deleteById(int id) {
        try {
            redisTemplate.opsForHash().delete(KEY,id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
