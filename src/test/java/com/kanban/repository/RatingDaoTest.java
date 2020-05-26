package com.kanban.repository;

import com.kanban.init.AppInit;
import com.kanban.model.Card;
import com.kanban.model.Comment;
import com.kanban.model.Rating;
import com.kanban.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInit.class)
public class RatingDaoTest {

    @Autowired
    private RatingDao ratingDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CardDao cardDao;

    private long cardId;
    private long userId;
    private long commentId;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void init(){
        User user1 = new User("Tom", "123", "xxx", "Gege", "Wang", "11@gmail.com", null);
        userDao.save(user1);
        userId = user1.getId();

        Card card1 = new Card("Tom", "GWU", "11@gmail.com", "2027895554",
                LocalDateTime.now(),0, 4, "Gege's resume", "/Gege's resume.pdf");
        cardDao.save(card1);
        cardId = card1.getId();

        Rating rating = new Rating(user1, card1, 4, LocalDateTime.now());
        ratingDao.save(rating);
        commentId = rating.getId();
    }

    @After
    public void tearDown(){
        userDao.deleteById(userId);
        cardDao.deleteById(cardId);
    }

    @Test
    public void getTest(){
        Assert.assertEquals(7, ratingDao.get().size());
    }

    @Test
    public void getByidTest(){
        Assert.assertTrue(4.0 == ratingDao.getById(commentId).getScore());
    }

    @Test
    public void deleteAllTest(){
        Assert.assertNotNull(commentId);
    }

    @Test
    public void getCommentByNameTest(){
        Assert.assertEquals(1, ratingDao.deleteById(commentId));
    }

    @Test
    public void updateByNameTest(){
        Rating rating = ratingDao.getById(commentId);
        rating.setScore(5);
        Assert.assertTrue(5.0 == ratingDao.update(rating).getScore());
    }

    @Test
    public void checkExistTest(){
        Assert.assertNotNull(ratingDao.checkExist(userId, cardId));
    }

}
