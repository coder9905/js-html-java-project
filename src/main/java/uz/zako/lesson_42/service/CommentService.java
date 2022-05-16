package uz.zako.lesson_42.service;

import uz.zako.lesson_42.entity.entity.Comment;
import uz.zako.lesson_42.model.Result;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComment();


    List<Comment> getAllIdComment();

    Result saveComment(Comment comment, Long number);
}
