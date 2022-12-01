package com.spring.tdddemo.web.dto;

import com.spring.tdddemo.domain.Book;
import lombok.Getter;

@Getter // Controller에서 Setter가 호출되며서 Dto에 값이 채워짐.
public class BookRespDto {

  private Long id;
  private String title;
  private String author;

  public BookRespDto toDto(Book bookPS) {
    this.id = bookPS.getId();
    this.title = bookPS.getTitle();
    this.author = bookPS.getAuthor();
    return this;
  }
}
