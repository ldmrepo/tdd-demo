package com.spring.tdddemo.web.dto;

import com.spring.tdddemo.domain.Book;

import lombok.Setter;

@Setter // Controller에서 Setter가 호출되며서 Dto에 값이 채워짐.
public class BookRespDto {
    private String title;
    private String author;

    public Book toEntity() {
        return Book.builder()
        .title(title)
        .author(author)
        .build();
    }
}
