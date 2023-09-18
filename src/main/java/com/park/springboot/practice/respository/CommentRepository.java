package com.park.springboot.practice.respository;

import com.park.springboot.practice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    //특정 게시물에 대한 모든 댓글 가져오기
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleID", nativeQuery = true )
    List<Comment> findByArticleID(Long articleID);

    //특정 닉네임에 대한 모든 댓글 가져오기
    List<Comment> findByNickname(String nickname); //"SELECT * FROM comment WHERE nickname = :nickname"
}
