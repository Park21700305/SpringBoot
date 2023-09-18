package com.park.springboot.practice.controller;

import com.park.springboot.practice.dto.ArticleForm;
import com.park.springboot.practice.dto.CommentDto;
import com.park.springboot.practice.entity.Article;
import com.park.springboot.practice.respository.ArticleRepository;
import com.park.springboot.practice.service.CommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
@Controller
@Log4j2
public class ArticleController {

    @Autowired //스프링부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결!!
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }
    @PostMapping("/articles/create")   //이 URL 은 어디로 받을지
    public String createArticle(ArticleForm form){
        //System.out.println(form.toString());
        log.info(form.toString());
        //DTO를 Entity로 변환 !

        Article article=form.toEntity();
        //System.out.println(article.toString());
        log.info(article.toString());

        //Repository에게 Entity를 DB안에 저장하게 함!
        Article saved=articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }
    @GetMapping("/articles/{id}")
        public String show(@PathVariable Long id, Model model){
        // 1. id로 데이터를 가져오기! (레파지토리 로 컨트롤러에 저장?)
        Article articleEntity=articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos= commentService.comments(id);
        //2. 가져온 데이터를 모델에 등록!
        model.addAttribute("article",articleEntity);
        model.addAttribute("commentDtos",commentDtos);
        //3. 보여줄 페이지를 설정!
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index2(Model model){
        // 1.모든 Article 을 가져오기 (Repository를 통해)
        ArrayList<Article> articleEntityList =  articleRepository.findAll();

        // 2. 받은 데이터를 뷰에게 전달(모델을 이용하여)  --> 이건 나의 의견이였고
        // 2. 가져온 Article 묶음을 뷰로 전달
       model.addAttribute("articleList",articleEntityList);

       // 3.최종적으로 보여줄 페이지 작성
        return "articles/index";
    }
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id,Model model){
        // 1. 레파지토리를 통해서 수정할 데이터를 가져온다.
        Article articleEntity =articleRepository.findById(id).orElse(null);

        // 2. 모델에 데이터를 등록
        model.addAttribute("article",articleEntity);

        //3. 최종적으로 보여줄 뷰
        return "articles/edit";
    }
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        //1. DTO를 Entity로 변경
        Article articleEntity =form.toEntity();
        log.info(articleEntity.toString());

        //2. 엔티티를 DB로 저장한다.

        //2-1: DB에서 기존 데이터를 가져온다!
        Article target=articleRepository.findById(articleEntity.getId()).orElse(null);

        //2-2: 기존 데이터 값을 갱신한다.
        if(target != null){
            articleRepository.save(articleEntity); //엔티티가 DB로 갱신
        }

        //3: 수정결과 페이지로 리다이렉트 한다
        return "redirect:/articles/" + articleEntity.getId();
    }
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제요청이 들어왔습니다.");
        //1: DB에서 삭제대상을 가져온다.
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2: 대상을 삭제한다.
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제가 되었습니다.");
        }

        //3: 결과페이지로 리다이렉트 한다.
        return "redirect:/articles";
    }
}
