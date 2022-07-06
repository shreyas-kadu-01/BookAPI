package com.local.bookapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaMessage implements Serializable {

    String UID;
    String status;
    int operation;
    Book book;
    String Remarks;

}
