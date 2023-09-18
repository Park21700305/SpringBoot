package com.park.springboot.practice.api;

import com.park.springboot.practice.annotation.RunningTime;
import com.park.springboot.practice.dto.CommentDto;
import com.park.springboot.practice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CommentApiController {
    @Autowired
    private CommentService commentService;

    //댓글 목록 조회
    @GetMapping("/api/articles/{articleID}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleID) {
        //서비스 에게 위임
        List<CommentDto> dtos = commentService.comments(articleID);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    //댓글 생성
    @PostMapping("/api/articles/{articleID}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleID, @RequestBody CommentDto dto) {
        //서비스에게 위임
        CommentDto createDto = commentService.create(articleID, dto);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }

    //댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto) {
        //서비스에게 위임
        CommentDto updatedDto = commentService.update(id, dto);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    //댓글 삭제
    @RunningTime
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
        //서비스에게 위임
        CommentDto deletedDto = commentService.delete(id);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}