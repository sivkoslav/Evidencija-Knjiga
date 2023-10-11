package com.example.knjige.service;

import com.example.knjige.DTO.BookDTO;
import com.example.knjige.mapper.BookMapper;
import com.example.knjige.model.Book;
import com.example.knjige.repository.BookRepository;
import com.example.knjige.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    PersonRepository personRepository;


    public Book addBook(Long id, BookDTO bookDTO){
        Book book = bookMapper.toModel(bookDTO);
        book.setPerson(personRepository.findById(id).get());
        book.setDate(bookDTO.getDate());
        bookRepository.save(book);

        return  book;

    }

    public Page<BookDTO> getBooksForPerson(int pageNumber, int recordCount, String sorted, Long id){
        if(sorted.equals("")){
            Pageable pageable = PageRequest.of(pageNumber,recordCount);
            List<Book> books = bookRepository.findBooksByPersonId(id,pageable).getContent();
            List<BookDTO> bookDTOS = bookMapper.toDTO(books);
            Page<BookDTO> bookDTOPage = new PageImpl<>(bookDTOS,pageable,bookRepository.findBooKCountByPersonId(id));
            return bookDTOPage;
        }
        Pageable pageable = PageRequest.of(pageNumber, recordCount, Sort.by(Sort.Direction.ASC,sorted));
        List<Book> books = bookRepository.findBooksByPersonId(id,pageable).getContent();
        List<BookDTO> bookDTOS = bookMapper.toDTO(books);
        Page<BookDTO> bookDTOPage = new PageImpl<>(bookDTOS,pageable,bookRepository.findBooKCountByPersonId(id));
        return bookDTOPage;

    }

    public List<BookDTO> getBooksOfPerson(Long id){
        List<Book> books = bookRepository.findBookByPerson(id);
        List<BookDTO> bookDTOS = bookMapper.toDTO(books);
        return bookDTOS;
    }


}
