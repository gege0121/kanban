package com.kanban.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kanban.model.Card;
import com.kanban.model.CardFE;
import com.kanban.model.view.ModelView;
import com.kanban.repository.CardDao;
import com.kanban.service.CardService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(value = {"/card","/cards"})
public class CardController {

    @Autowired
    private CardDao cardDao;

    @Autowired
    private CardService cardService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @JsonView({ModelView.listAllCard.class})
    public List listAllCard(){
        List<Card> t = cardService.listCard();
        return t;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Card addNewCard(@ModelAttribute CardFE card, HttpServletRequest request){

        try{
            System.out.println(card);
            MultipartFile resume = card.getResume();
            String newName = UUID.randomUUID() + "." + FilenameUtils.getExtension(resume.getOriginalFilename());

            String filePath = request.getServletContext().getRealPath("/");
            System.out.println(filePath);

            File f1 = new File(filePath+"/"+newName);
            resume.transferTo(f1);

            Card cardDB = new Card(card);
            cardDB.setResume_key(newName);
            cardDB.setCreateTime(LocalDateTime.now());
            cardDB = cardDao.save(cardDB);
            System.out.println(cardDB);

            return cardDB;
        }catch (Exception e){
            return null;
        }

    }

    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public Map changeStatus(@RequestBody Map body){
        long cardId = Long.valueOf((int) body.get("cardId"));
        int newStatus = (int) body.get("newStatus");

        Card t = cardService.changeStaus(cardId, newStatus);
        Map re = new HashMap();
        re.put("cardName", t.getName());
        re.put("newStatus", t.getStatus());
        return re;
    }



}
