package com.local.bookapi.repositories;

import com.local.bookapi.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
