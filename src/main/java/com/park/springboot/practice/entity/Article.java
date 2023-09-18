package com.park.springboot.practice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity //DB가 해당 객체를 인식 가능! (해당 클래스로 테이블을 만든다!)
@AllArgsConstructor // 생성자 따로 추가안해도됨 (매개변수가있는 생성자)
@NoArgsConstructor
@ToString
@Getter
public class Article {
    @Id             //대표값을 지정! ex)주민등록번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1,2,3, 자동으로 지정 // DB 가 id를 자동 생성 어노테이션!
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article article) {
        if (article.title != null) {
            this.title = article.title;
        }
        if (article.content != null) {
            this.content = article.content;
        }
    }


    //public Article(){};

}
