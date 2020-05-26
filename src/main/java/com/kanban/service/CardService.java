package com.kanban.service;

import com.kanban.model.Card;
import com.kanban.repository.CardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CardService {

    @Autowired
    private CardDao cardDao;

    public List<Card> listCard(){
        List<Card> cards = cardDao.getFetchAll();
        Set set = new HashSet();
        for(Card c:cards){
            double t = 0;
            try{
                t = c.getRatings().stream().map(e -> e.getScore()).mapToDouble(Double::doubleValue).average().getAsDouble();
            }catch (Exception e){
                t = 0;
            }

            c.setRating(t);
            set.add(c);
        }
        return new ArrayList(set);
    }

    public Card changeStaus(long cardId, int newStatus){
        Card card = cardDao.getById(cardId);
        if(card==null) return card;
        card.setStatus(newStatus);
        card = cardDao.update(card);
        return card;
    }

}
