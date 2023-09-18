package com.park.springboot.practice.api;

import com.park.springboot.practice.dto.ArticleForm;
import com.park.springboot.practice.entity.Article;
import com.park.springboot.practice.service.ArticleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Log4j2
@RestController // RestAPi 용 컨트롤러! 데이터(JSON)를 반환!
public class ArticleApiController {
    @Autowired // DI, 생성 객체를 가져와 연결!
    private ArticleService articleService;
  @GetMapping("/api/articles")
    public List<Article> index(){
      return articleService.index();
  }


    // GET 요청중 목록중 하나만 가져오기 (단일 Article 가져오기)
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){

        return articleService.show(id);
    }

    // POST 요청으로 Article 을 생성 ?
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
         Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto){
        Article updated=articleService.update(id,dto);
        return (updated != null) ? ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
//
    // Delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        // 1: 대상찾기
         Article deleted =articleService.delete(id);

        return (deleted != null) ? ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //  트랜잭션 -> 실패 -> 롤백!
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
      List<Article> createdList = articleService.createArticles(dtos);
      return (createdList != null) ? ResponseEntity.status(HttpStatus.OK).body(createdList):
              ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
