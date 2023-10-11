package com.example.knjige.controller;

import com.example.knjige.DTO.BookDTO;
import com.example.knjige.model.Book;
import com.example.knjige.repository.BookRepository;
import com.example.knjige.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiv2")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BookController {



    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;


    @PostMapping("/addBook/{id}")
    ResponseEntity<?> addBook(@PathVariable Long id, @RequestBody BookDTO bookDTO){
        Book book = bookService.addBook(id,bookDTO);

        if(book!=null) {
            return new ResponseEntity<>(book,HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/books-of-person")
    ResponseEntity<?> getPersonBooks(@RequestParam("personId") Long id,@RequestParam (required = true,name="pageNumber") Integer pageNumber, @RequestParam(required = true,name="recordCount") Integer recordCount, @RequestParam(required=false, name="sorted") String sorted){
        Page<BookDTO> books = bookService.getBooksForPerson(pageNumber,recordCount,sorted,id);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books-from-person")
    ResponseEntity<?> getBooksFromPerson(@RequestParam("personId") Long id){
        List<BookDTO> books = bookService.getBooksOfPerson(id);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }
}
