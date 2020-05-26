package com.kanban.repository;

import com.kanban.model.Comment;
import com.kanban.model.Role;

import java.util.List;

public interface CommentDao {

    public List<Comment> get();
    public Comment getById(long id);
    public Comment save(Comment comment);
    public int deleteById(long id);
    public Comment update(Comment comment);

}
