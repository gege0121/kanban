package com.kanban.model;

import org.springframework.web.multipart.MultipartFile;

public class CardBF {

    private String name;
    private String education;
    private String email;
    private String phone;
    private int status;
    private double rating;
    private String resume_key;

    public CardBF(Card card){
        this.name = card.getName();
        this.education = card.getEducation();
        this.email = card.getEmail();
        this.phone = card.getPhone();
        this.status = card.getStatus();
        this.rating = card.getRating();
        this.resume_key = card.getResume_key();
    }

}
