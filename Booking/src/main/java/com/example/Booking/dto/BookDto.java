package com.example.Booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class BookDto {
    private int bookID;
    private String bookName;
    private String bookType;
    private String bookLocation;

}
