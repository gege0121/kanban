package com.kanban.service;

import com.kanban.init.AppInit;
import com.kanban.model.Card;
import com.kanban.model.Comment;
import com.kanban.model.Rating;
import com.kanban.model.User;
import com.kanban.repository.CardDao;
import com.kanban.repository.CommentDao;
import com.kanban.repository.RatingDao;
import com.kanban.repository.UserDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInit.class)
public class CardServiceTest {
    @Autowired
    private CardService cardService;

    @Autowired
    private CardDao cardDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private RatingDao ratingDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private long cardId;
    private long userId;
    private long userId2;
    private long commentId;
    private long ratingId;

    @Before
    public void init(){
        Card card1 = new Card("Tom", "GWU", "11@gmail.com", "2025916663",
                LocalDateTime.now(),0, 4, "Tom's resume", "/Tom's resume.pdf");
        cardDao.save(card1);
        cardId = card1.getId();

        User user1 = new User("Tom2", "123", "xxx", "Gege", "Wang", "t1@gmail.com", null);
        userDao.save(user1);
        userId = user1.getId();

        User user2 = new User("Solomon2", "123", "xxx", "Fisher", "Jessica", "s2@gmail.com", null);
        userDao.save(user2);
        userId2 = user2.getId();

        Comment comment1 = new Comment(user1, card1, "great cadidate", LocalDateTime.now());
        commentDao.save(comment1);
        commentId = comment1.getId();

        Rating rating = new Rating(user1, card1, 4, LocalDateTime.now());
        ratingService.saveOrUpdate(rating);

        Rating rating2 = new Rating(user2, card1, 2, LocalDateTime.now());
        ratingService.saveOrUpdate(rating2);

    }

    @After
    public void tearDown(){
        cardDao.deleteById(cardId);
        userDao.deleteById(userId);
        userDao.deleteById(userId2);
    }


    @Test
    public void getTest(){
        List<Card> cards = cardService.listCard();
        System.out.println(cards);

    }

    @Test
    public void changeStatusTest(){
        Card card = cardService.changeStaus(1, 4);
        Assert.assertEquals(4, card.getStatus());
    }

}
