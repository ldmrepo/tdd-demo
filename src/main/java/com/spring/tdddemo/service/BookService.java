package com.spring.tdddemo.service;

import com.spring.tdddemo.domain.Book;
import com.spring.tdddemo.domain.BookRepository;
import com.spring.tdddemo.web.dto.BookRespDto;
import com.spring.tdddemo.web.dto.BookSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookService {

  private final BookRepository bookRepository;

  // 1. 책등록
  public BookRespDto 책등록하기(BookSaveReqDto dto) {
    Book bookPS = bookRepository.save(dto.toEntity());
    return new BookRespDto().toDto(bookPS);
  }
  // 2. 책목록보기

  // 3. 책한건보기

  // 4. 책삭제

  // 5. 책수정
}
