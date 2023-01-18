package com.example.demo.services;

import com.example.demo.dto.CommentDTO;
import com.example.demo.exceptions.PostAppException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDTO createComment(Long postId, CommentDTO commentDTO) {
        Comment comment = mappinEntity(commentDTO);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return mappingDTO(newComment);
    }

    @Override
    public List<CommentDTO> getCommentsById(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> mappingDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentByID(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new PostAppException(HttpStatus.BAD_REQUEST, "The comment does not belong to the publication");
        }
        return mappingDTO(comment);
    }

    @Override
    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO requestComment) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new PostAppException(HttpStatus.BAD_REQUEST, "The comment does not belong to the publication");
        }

        comment.setName(requestComment.getName());
        comment.setEmail(requestComment.getEmail());
        comment.setBody(requestComment.getBody());

        Comment updatedComment = commentRepository.save(comment);

        return mappingDTO(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new PostAppException(HttpStatus.BAD_REQUEST, "The comment does not belong to the publication");
        }
        commentRepository.delete(comment);
    }

    //Convert Entity to DTO
    private CommentDTO mappingDTO(Comment comment) {
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
        return commentDTO;
    }

    //Convert DTO to Entity
    private Comment mappinEntity(CommentDTO commentDTO) {
        Comment comment = modelMapper.map((Object) commentDTO, Comment.class);
        return comment;
    }
}
