package com.kanban.repository;

import com.kanban.init.AppInit;
import com.kanban.model.Card;
import com.kanban.model.Comment;
import com.kanban.model.Comment;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInit.class)
public class CommentDaoTest {

    @Autowired
    private CommentDao commentDao;

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
        User user1 = new User("Tom", "123", "xxx", "Yihao", "Yang", "11@gmail.com", null);
        userDao.save(user1);
        userId = user1.getId();

        Card card1 = new Card("Gege", "GWU", "11@gmail.com", "202121",
                LocalDateTime.now(),0, 4, "Wang_resume", "/Wang_resume.pdf");
        cardDao.save(card1);
        cardId = card1.getId();

        Comment comment1 = new Comment(user1, card1, "great cadidate", LocalDateTime.now());
        commentDao.save(comment1);
        commentId = comment1.getId();
    }

    @After
    public void tearDown(){
        userDao.deleteById(userId);
        cardDao.deleteById(cardId);
    }

    @Test
    public void getTest(){
        Assert.assertEquals(4, commentDao.get().size());
    }

    @Test
    public void getByidTest(){
        Assert.assertEquals("great cadidate", commentDao.getById(commentId).getContent());
    }

    @Test
    public void deleteAllTest(){
        Assert.assertNotNull(commentId);
    }

    @Test
    public void getCommentByNameTest(){
        Assert.assertEquals(1, commentDao.deleteById(commentId));
    }

    @Test
    public void updateByNameTest(){
        Comment card = commentDao.getById(commentId);
        card.setContent("Exceptional");
        Assert.assertEquals("Exceptional", commentDao.update(card).getContent());
    }

}
