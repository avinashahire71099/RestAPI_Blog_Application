package com.avinash.blog.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avinash.blog.entities.Comment;
import com.avinash.blog.entities.Post;
import com.avinash.blog.exceptions.ResourceNotFoundException;
import com.avinash.blog.payloads.CommentDto;
import com.avinash.blog.repositories.CommentRepo;
import com.avinash.blog.repositories.PostRepo;
import com.avinash.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
       Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Comment Id", commentId));
       this.commentRepo.delete(comment);
	}

}
