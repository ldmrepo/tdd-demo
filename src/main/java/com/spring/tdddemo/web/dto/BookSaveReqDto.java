package com.spring.tdddemo.web.dto;

import com.spring.tdddemo.domain.Book;
import lombok.Setter;

@Setter
public class BookSaveReqDto {

  private String title;
  private String author;

  public Book toEntity() {
    return Book.builder().title(title).author(author).build();
  }
}
