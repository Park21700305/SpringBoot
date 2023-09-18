package com.park.springboot.practice.api;

import com.park.springboot.practice.dto.ArticleForm;
import com.park.springboot.practice.entity.Article;
import com.park.springboot.practice.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅된다.
class ArticleApiControllerTest {
@Autowired
ArticleService articleService;
    @Test
    void index() {
        // 예상
        Article a = new Article(1L,"가가가가","1111");
        Article b = new Article(2L,"나나나나","2222");
        Article c = new Article(3L,"다다다다","3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        // 실제
        List<Article> articles= articleService.index();

        // 비교
        assertEquals(expected.toString(),articles.toString());
    }


    @Test
    void show_성공___존재하는__id입력() {
        //예상
        Long id = 1L;
        Article expected=new Article(id,"가가가가","1111");
        //실제
        Article article=articleService.show(id);
        //비교
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    void show_실패_존재하지않는_id입력() {

        //예상
        Long id = -1L;
        Article expected=null;
        //실제
        Article article=articleService.show(id);
        //비교
        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void create__성공_title과__content만_있는_dto입력() {
        //예상
        String title = "라라라라";
        String content ="4444";
        ArticleForm dto = new ArticleForm(null,title,content);
        Article expected= new Article(4L,title,content);
        //실제
        Article article=articleService.create(dto);
        //비교
        assertEquals(expected.toString(),article.toString());
    }
    @Test
    @Transactional
    void create__실패_id가_포함된_dto가_입력될때() {
        //예상
        String title = "라라라라";
        String content ="4444";
        ArticleForm dto = new ArticleForm(4L,title,content);
        Article expected= null;
        //실제
        Article article=articleService.create(dto);
        //비교
        assertEquals(expected,article);
    }
}