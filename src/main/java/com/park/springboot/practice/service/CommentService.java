package com.park.springboot.practice.service;

import com.park.springboot.practice.dto.CommentDto;
import com.park.springboot.practice.entity.Article;
import com.park.springboot.practice.entity.Comment;
import com.park.springboot.practice.respository.ArticleRepository;
import com.park.springboot.practice.respository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleID) {
//        // 조회: 댓글 목록
//        List<Comment> comments=commentRepository.findByArticleID(articleID);
//        // 변환: 엔티티 -> DTO
//        List<CommentDto> dtos =new ArrayList<CommentDto>();
//        for (int i=0; i< comments.size(); i++){
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }
//        // 반환
//        return dtos;
        return commentRepository.findByArticleID(articleID).stream()
                .map(comment -> CommentDto.createCommentDto(comment)).collect(Collectors.toList());
    }
@Transactional
    public CommentDto create(Long articleID, CommentDto dto) {
        // 게시글 조회 및 예외 발생
        Article article =articleRepository.findById(articleID).
                orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));
        //댓글 엔티티 생성
        Comment comment=Comment.createComent(dto,article);
        //댓글 엔티티를 DB로 저장
        Comment created =commentRepository.save(comment);
        //DTO로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }
@Transactional
    public CommentDto update(Long id, CommentDto dto) {

        //댓글 조회 및 예외 발생
          Comment target =commentRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));
        //댓글 수정
            target.patch(dto);
        // DB로 갱신
        Comment updated=commentRepository.save(target);
        // 댓글 엔티티를 DTO로 변환 및 반환
    return CommentDto.createCommentDto(updated);
    }


    public CommentDto delete(Long id) {
        //댓글 찾아오기
         Comment target = commentRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("댓글삭제 실패!! 댓글이 없습니다."));
        //댓글 DB에서 삭제하기
        commentRepository.delete(target);
        //댓글 엔티티를 DTO로 변환
        return CommentDto.createCommentDto(target);
    }
}
