package com.kanban.repository;

import com.kanban.init.AppInit;
import com.kanban.model.Card;
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
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private long userId;

    @Before
    public void init(){
        User user1 = new User("Tom", "123", "xxx", "Gege", "Wang", "11@gmail.com", null);
        userDao.save(user1);
        userId = user1.getId();
    }

    @After
    public void tearDown(){
        userDao.deleteById(userId);
    }

    @Test
    public void getTest(){
        Assert.assertEquals(4, userDao.get().size());
    }

    @Test
    public void getByidTest(){
        Assert.assertEquals("Tom", userDao.getById(userId).getName());
    }

    @Test
    public void deleteAllTest(){
        Assert.assertNotNull(userId);
    }

    @Test
    public void getCardByNameTest(){
        Assert.assertEquals(1, userDao.deleteById(userId));
    }

    @Test
    public void updateByNameTest(){
        User user = userDao.getById(userId);
        user.setName("new name");
        Assert.assertEquals(1, userDao.updateById(userId, user));
    }


}
