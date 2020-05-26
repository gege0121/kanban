package com.kanban.service;

import com.kanban.model.Rating;
import com.kanban.repository.RatingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    private RatingDao ratingDao;

    public Rating saveOrUpdate(Rating rating){
        Rating old = ratingDao.checkExist(rating.getUser().getId(), rating.getCard().getId());
        if(old!=null){
            old.setScore(rating.getScore());
            old.setCreateTime(rating.getCreateTime());
            rating = ratingDao.update(old);
        }else{
            rating = ratingDao.save(rating);
        }
        return rating;
    }

}
