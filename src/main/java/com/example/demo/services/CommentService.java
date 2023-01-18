package com.example.demo.services;

import com.example.demo.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    public CommentDTO createComment(Long postId, CommentDTO commentDTO);

    public List<CommentDTO> getCommentsById(Long postId);

    public CommentDTO getCommentByID(Long postId, Long commentId);

    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO requestComment);

    public void deleteComment(Long postId, Long commentId);
}
