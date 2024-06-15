package com.example.Booking.service;

import com.example.Booking.dto.BookDto;
import com.example.Booking.entity.Book;
import com.example.Booking.repo.BookRepo;
import com.example.Booking.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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
    public String updateBook(BookDto bookDto) {
        if(bookRepo.existsById(bookDto.getBookID())){
            bookRepo.save(modelMapper.map(bookDto, Book.class));
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<BookDto> getAllBooks() {
        List<Book> bookList = bookRepo.findAll();
        return modelMapper.map(bookList,new TypeToken<ArrayList<BookDto>>(){}.getType());
    }

    public BookDto searchBook(int bookID){
        if(bookRepo.existsById(bookID)){
            Book book = bookRepo.findById(bookID).orElse(null);
            return modelMapper.map(book ,BookDto.class);
        }else{
            return null;
        }
    }

    public String deleteBook(int bookID){
        if(bookRepo.existsById(bookID)){
            bookRepo.deleteById(bookID);
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }


}
