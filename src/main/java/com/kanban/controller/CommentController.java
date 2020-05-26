package com.kanban.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kanban.model.Card;
import com.kanban.model.Comment;
import com.kanban.model.ProjectAttribute;
import com.kanban.model.User;
import com.kanban.model.view.ModelView;
import com.kanban.repository.CardDao;
import com.kanban.repository.CommentDao;
import com.kanban.repository.UserDao;
import com.kanban.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = {"/comment","/comments"})
public class CommentController {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CardDao cardDao;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Map addComment(@RequestBody Map body, HttpSession session){
        long cardId = Long.valueOf((int) body.get("cardId"));
        String content = (String) body.get("content");

        User user = userDao.getById((long) session.getAttribute(ProjectAttribute.SESSION_USER_ID));
        Card card = cardDao.getById(cardId);
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setCard(card);
        comment.setContent(content);
        comment.setCreateTime(LocalDateTime.now());
        Comment t = commentDao.save(comment);

        Map re = new HashMap();
        re.put("userName", t.getUser().getName());
        re.put("cadidate", t.getCard().getName());
        re.put("content", t.getContent());
        return re;
    }

}
