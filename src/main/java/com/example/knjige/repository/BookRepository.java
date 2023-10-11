package com.example.knjige.repository;

import com.example.knjige.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("select bk from Book bk where bk.person.id=:id order by bk.date desc ")
    Page<Book> findBooksByPersonId(@Param("id") Long id, Pageable pageable);

    @Query("select count(bk) from Book bk where bk.person.id=:id")
    long findBooKCountByPersonId(@Param("id")Long id);

    @Query("select bk from Book bk where bk.person.id=:id")
    List<Book> findBookByPerson(@Param("id")Long id);
}
