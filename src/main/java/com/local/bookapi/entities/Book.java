package com.local.bookapi.entities;

import javax.persistence.*;

@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_ID")
    private int Id;

    @Column(name = "BOOK_NAME")
    private String bookName;

    @Column(name = "AUTHOR_NAME")
    private String authorName;

    public Book() {
    }

    public Book(int id, String bookName, String authorName) {
        Id = id;
        this.bookName = bookName;
        this.authorName = authorName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "Id=" + Id +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
