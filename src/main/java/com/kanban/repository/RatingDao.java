package com.kanban.repository;

import com.kanban.model.Comment;
import com.kanban.model.Rating;

import java.util.List;

public interface RatingDao {

    public List<Rating> get();
    public Rating getById(long id);
    public Rating save(Rating rating);
    public int deleteById(long id);
    public Rating update(Rating rating);
    public Rating checkExist(long userId, long cardId);
    public Rating saveOrUpdate(Rating rating);
}
