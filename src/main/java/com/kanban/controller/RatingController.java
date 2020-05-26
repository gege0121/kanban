package com.kanban.controller;

import com.kanban.model.Card;
import com.kanban.model.ProjectAttribute;
import com.kanban.model.Rating;
import com.kanban.model.User;
import com.kanban.repository.RatingDao;
import com.kanban.repository.UserDao;
import com.kanban.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = {"/ratings","/rating"})
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserDao userDao;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public int addRating(@RequestParam("cardId") long cardId, @RequestParam("score") double score, HttpSession session){
        long userId = (long) session.getAttribute(ProjectAttribute.SESSION_USER_ID);
        User user = userDao.getById(userId);

        Card card = new Card();
        card.setId(cardId);

        Rating rating = new Rating(user, card, score, LocalDateTime.now());
        System.out.println(user);
        System.out.println(card);

        rating = ratingService.saveOrUpdate(rating);
        return rating==null?0:1;
    }


}
