package com.spring.tdddemo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Book {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @Column(length = 50)
  private String title;

  @Column(length = 20)
  private String author;

  @Builder
  public Book(Long id, String title, String author) {
    this.id = id;
    this.title = title;
    this.author = author;
  }
}
