package com.example.knjige.mapper;

import com.example.knjige.DTO.BookDTO;
import com.example.knjige.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapper implements IMapper<Book, BookDTO>{
    @Override
    public Book toModel(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setBookName(bookDTO.getBookName());
        book.setBookAuthor(bookDTO.getBookAuthor());
        book.setDate(bookDTO.getDate());

        return book;
    }

    @Override
    public List<Book> toModel(List<BookDTO> dto) {
        List<Book> bookList = new ArrayList<>();
        for(BookDTO bookDTO: dto){
            bookList.add(toModel(bookDTO));
        }
        return bookList;
    }

    @Override
    public BookDTO toDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookAuthor(book.getBookAuthor());
        bookDTO.setBookName(book.getBookName());
        bookDTO.setId(book.getId());
        bookDTO.setDate(book.getDate());
        return bookDTO;
    }

    @Override
    public List<BookDTO> toDTO(List<Book> model) {
        List<BookDTO> bookDTOS = new ArrayList<>();
        for(Book book: model){
            bookDTOS.add(toDTO(book));
        }
        return bookDTOS;
    }
}
