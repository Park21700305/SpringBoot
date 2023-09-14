package com.park.springboot.practice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor   // 디폴트 생성자 추가
@ToString
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에 맞게 자동 생성 전략 선택
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

}
