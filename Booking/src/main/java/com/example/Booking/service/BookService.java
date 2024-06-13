package com.example.Booking.service;

import com.example.Booking.dto.BookDto;
import com.example.Booking.entity.Book;
import com.example.Booking.repo.BookRepo;
import com.example.Booking.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional


public class BookService {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private ModelMapper modelMapper;


    public String saveBook(BookDto bookDto) {
        if (bookRepo.existsById(bookDto.getBookID())) {
            return VarList.RSP_DUPLICATED;
        } else {
            bookRepo.save(modelMapper.map(bookDto, Book.class));
            return VarList.RSP_SUCCESS;
        }

    }
}
