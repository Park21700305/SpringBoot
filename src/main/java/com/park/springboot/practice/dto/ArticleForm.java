package com.park.springboot.practice.dto;

import com.park.springboot.practice.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString

public class ArticleForm {

    private Long id;  //id 필드 추가!!
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);  //id필드를 추가했으니 null --> id
    }


    //toEntity 메서드를 호출하면 ArticleForm 객체를가지고 Article 타입으로 리턴
    // DTO를 Entity로 반환하는 거 같다.
}
