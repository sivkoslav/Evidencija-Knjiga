package com.example.knjige.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {

    private Long id;

    private String bookName;

    private String bookAuthor;

    private Date date;

    private PersonDTO personDto;
}
