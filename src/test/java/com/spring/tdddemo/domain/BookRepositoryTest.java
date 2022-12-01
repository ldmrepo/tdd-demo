package com.spring.tdddemo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest // DB와 관련된 콤포넌트만 메모리에 로디 // H2동작 // 자동 롤백
public class BookRepositoryTest {

  @Autowired
  private BookRepository bookRepository;

  // @Autowired
  // private EntityManager em;

  @BeforeEach
  public void 데이터준비() {
    String title = "junit";
    String author = "겟인데어";
    Book book = Book.builder().title(title).author(author).build();
    bookRepository.save(book);
  }

  // id를 비교하는 테스트는 id가 자동증가 하기때문에 실패하는 경우가 생긴다.
  // 해결방법은
  //  1. @AfterEach에서 직접 처리하는 방법
  //  2. @Sql 어노테이션으로 처리하는 방법

  //   @AfterEach
  //   public void 정리하기() {
  //     em
  //       .createNativeQuery("ALTER TABLE book ALTER COLUMN id RESTART WITH 1")
  //       .executeUpdate();
  //   }

  // 1. 책등록
  @Sql("classpath:db/tableinit.sql")
  @Test
  @Order(1)
  public void 책등록_Test() {
    // given (데이터 준비)
    String title = "junit5";
    String author = "메타코딩";
    Book book = Book.builder().title(title).author(author).build();
    // when (테스트 실행)
    Book bookPS = bookRepository.save(book);
    // then (검증)
    assertEquals(title, bookPS.getTitle());
    assertEquals(author, bookPS.getAuthor());
  }

  // 2. 책 목록보기
  @Sql("classpath:db/tableinit.sql")
  @Test
  @Order(2)
  public void 책목록_Test() {
    // given
    String title = "junit";
    String author = "겟인데어";

    // when
    List<Book> books = bookRepository.findAll();

    // then
    assertEquals(books.get(0).getTitle(), title);
    assertEquals(books.get(0).getAuthor(), author);
  }

  // 3. 책 한건보기
  @Sql("classpath:db/tableinit.sql")
  @Test
  @Order(3)
  public void 책한건보기_Test() {
    // given
    String title = "junit";
    String author = "겟인데어";

    // when
    Book bookPS = bookRepository.findById(1L).get();

    // then
    assertEquals(bookPS.getTitle(), title);
    assertEquals(bookPS.getAuthor(), author);
  }

  // 4. 책 삭제
  @Sql("classpath:db/tableinit.sql")
  @Test
  @Order(4)
  public void 책삭제_Test() {
    // given
    Long id = 1L;

    // when
    bookRepository.deleteById(id);

    // then
    Optional<Book> bookPS = bookRepository.findById(id);
    assertFalse(bookPS.isPresent());
  }

  // 책수정
  @Sql("classpath:db/tableinit.sql")
  @Test
  @Order(5)
  public void 책수정_Test() {
    // given
    Long id = 1L;
    String title = "junit5";
    String author = "메타코딩";
    Book book = new Book(id, title, author);

    // when
    // bookRepository
    //   .findAll()
    //   .stream()
    //   .forEach(b -> {
    //     System.out.println(b.getId());
    //     System.out.println(b.getTitle());
    //     System.out.println(b.getAuthor());
    //     System.out.println("=====================");
    //   });

    Book bookPS = bookRepository.save(book);

    // bookRepository
    //   .findAll()
    //   .stream()
    //   .forEach(b -> {
    //     System.out.println(b.getId());
    //     System.out.println(b.getTitle());
    //     System.out.println(b.getAuthor());
    //     System.out.println("=====================");
    //   });
    // then
    assertEquals(bookPS.getId(), book.getId());
    assertEquals(bookPS.getTitle(), book.getTitle());
    assertEquals(bookPS.getAuthor(), book.getAuthor());
  }
}
