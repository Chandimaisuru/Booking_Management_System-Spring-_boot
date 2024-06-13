package com.example.Booking.controller;


import com.example.Booking.dto.BookDto;
import com.example.Booking.dto.ResponseDto;
import com.example.Booking.service.BookService;
import com.example.Booking.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
