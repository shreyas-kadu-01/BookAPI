package com.local.bookapi.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "notifications")
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "NOTIFICATION_ID")
    private int id;
    @Column(name = "NOTIFICATION_MESSAGE")
    private String message;
    @Column(name = "NOTIFICATION_TIME")
    private String timeStamp;
    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    public Notification() {
    }

    public Notification(int id, String message, String timeStamp, Book book) {
        this.id = id;
        this.message = message;
        this.timeStamp = timeStamp;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", book=" + book +
                '}';
    }
}
