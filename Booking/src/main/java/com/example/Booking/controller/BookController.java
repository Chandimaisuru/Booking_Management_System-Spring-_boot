package com.example.Booking.controller;


import com.example.Booking.dto.BookDto;
import com.example.Booking.dto.ResponseDto;
import com.example.Booking.entity.Book;
import com.example.Booking.service.BookService;
import com.example.Booking.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")

public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private ResponseDto responseDto;

    @PostMapping(value = "/saveBook")
    public ResponseEntity saveBook(@RequestBody BookDto bookDto){
        try {
            String res = bookService.saveBook(bookDto);
            if (res.equals("00")){
               responseDto.setCode(VarList.RSP_SUCCESS);
               responseDto.setMessage("Success");
               responseDto.setContent(bookDto);
               return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);

            }else if(res.equals("06")){

                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Duplicate");
                responseDto.setContent(bookDto);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);

            }else {
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("Error");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateBook")
    public ResponseEntity updateBook(@RequestBody BookDto bookDto){
        try {
            String res = bookService.updateBook(bookDto);
            if (res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(bookDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);

            }else if(res.equals("01")){

                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Not a register book");
                responseDto.setContent(bookDto);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);

            }else {
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("Error");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAllBook")
    public ResponseEntity getAllBook(){
        try {
            List<BookDto> bookDtoList = bookService.getAllBooks();
            responseDto.setCode(VarList.RSP_SUCCESS);
            responseDto.setMessage("Success");
            responseDto.setContent(bookDtoList);
            return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchBook/{bookID}")
    public ResponseEntity searchBook(@PathVariable  int bookID){
        try {
            BookDto bookDto = bookService.searchBook(bookID);
            if (bookDto != null){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(bookDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);


            }else {
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("No such book");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(ex);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping (value = "/deleteBook/{bookID}")
    public ResponseEntity deleteBook(@PathVariable  int bookID){
        try {
            String res= bookService.deleteBook(bookID);
            if (res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);


            }else {
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("No such book");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(ex);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
