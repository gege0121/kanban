package com.kanban.repository;

import com.kanban.model.Card;

import java.util.List;
import java.util.Set;

public interface CardDao {
    public List<Card> get();
    public List<Card> getFetchAll();
    public Card getById(long id);
    public Card save(Card card);
    public int deleteById(long id);
    public Card update(Card card);
}
