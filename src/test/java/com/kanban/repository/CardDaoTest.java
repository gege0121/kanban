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
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInit.class)
public class CardDaoTest {

    @Autowired
    private CardDao cardDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private long cardId;

    @Before
    public void init(){
        Card card1 = new Card("Gege", "GWU", "11@gmail.com", "202121",
                LocalDateTime.now(),0, 4, "Wang_resume", "/Wang_resume.pdf");
        cardDao.save(card1);
        cardId = card1.getId();
    }

    @After
    public void tearDown(){
        cardDao.deleteById(cardId);
    }

    @Test
    public void getTest(){
        Assert.assertEquals(3, cardDao.get().size());
    }

    @Test
    public void getByIdTest(){
        Assert.assertEquals("Gege", cardDao.getById(cardId).getName());
    }

    @Test
    public void deleteAllTest(){
        Assert.assertNotNull(cardId);
    }

    @Test
    public void getCardByNameTest(){
        Assert.assertEquals(1, cardDao.deleteById(cardId));
    }

    @Test
    public void updateByNameTest(){
        Card card = cardDao.getById(cardId);
        card.setName("new name");
        Assert.assertEquals("new name", cardDao.update(card).getName());
    }

    @Test
    public void getFetchAll(){
        List<Card> cards = cardDao.getFetchAll();
        for(Rating r : cards.get(0).getRatings()){
            System.out.println(r);
        }

        for(Comment c : cards.get(0).getComments()){
            System.out.println(c);
        }
    }

}
