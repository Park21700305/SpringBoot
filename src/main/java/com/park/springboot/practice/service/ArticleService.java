package com.park.springboot.practice.service;

import com.park.springboot.practice.dto.ArticleForm;
import com.park.springboot.practice.entity.Article;
import com.park.springboot.practice.respository.ArticleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service //서비스 선언 (스프링부트에서 객체를 선언!!)
@Log4j2
public class ArticleService {
    @Autowired // DI
    private ArticleRepository articleRepository;

    //GET 요청 !!
    public List<Article> index() {
        return articleRepository.findAll();
    }
    //GET 단일 요청!!
    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    //POST 요청!!
    public Article create(ArticleForm dto) {
         Article article=dto.toEntity();
         if(article.getId() != null){
             return null;
         }
        return articleRepository.save(article);
    }

    //PATCH 요청!!
    public Article update(Long id, ArticleForm dto) {

        // 1: 수정용 엔티티 생성
        Article article=dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());
        // 2: 대상 엔티티를 조회 (가져오기)
        Article target = articleRepository.findById(id).orElse(null);

        // 3: 잘못된 요청 처리 (대상이 없거나 ,id가 다른 경우)
        if(target ==null || id != article.getId()){
            //400, 잘못된 요청 응답!
            log.info("잘못된 요청! id:{}, article: {}",id ,article.toString());
            return null;
        }
        // 4: 업데이트
        target.patch(article);
        Article updated =articleRepository.save(target);
        return updated;
    }
    //DELETE 요청!
    public Article delete(Long id) {
        // id 값을 가져오기
        Article target =articleRepository.findById(id).orElse(null);
        // 잘못된 요청 처리
        if(target == null){
            return null;
        }
        // 2: 대상 삭제
         articleRepository.delete(target);
        return target;
    }

    @Transactional //해당 메서드를 트랜잭션으로 묶는다!
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //dto 묶음을 entity묶음으로 변환
        List<Article>articleList =dtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());

        // Entity묶음을 DB로 저장
        articleList.stream().forEach(article -> articleRepository.save(article));

        //강제 예외발생
        articleRepository.findById(-1L).orElseThrow( () -> new IllegalArgumentException("결제 실패!"));

        //결과값 반환:
        return articleList;
    }
}
