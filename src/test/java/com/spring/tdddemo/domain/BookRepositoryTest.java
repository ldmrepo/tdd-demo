package com.spring.tdddemo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    
    @Test
    public void 등록_Test() {
        // given (데이터 준비)
        String title = "junit5";
        String author = "TDD Project Start";
        Book book = Book.builder()
                        .title(title)
                        .author(author)
                        .build();
        // when (테스트 실행)
        Book bookPS = bookRepository.save(book);
        // then (검증)
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    }

    @Test
    public void 목록_Test(){
        // given
        // when
        List<Book> books = bookRepository.findAll();
        // then
        System.out.println(books.size());
    }
}
