package com.kanban.model;


import com.fasterxml.jackson.annotation.JsonView;
import com.kanban.model.view.ModelView;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ModelView.listAllCard.class})
    private long id;

    @Column(name = "name")
    @JsonView({ModelView.listAllCard.class})
    private String name;

    @Column(name = "education")
    @JsonView({ModelView.listAllCard.class})
    private String education;

    @Column(name = "email")
    @JsonView({ModelView.listAllCard.class})
    private String email;

    @Column(name = "phone")
    @JsonView({ModelView.listAllCard.class})
    private String phone;

    @Column(name = "create_time")
    @JsonView({ModelView.listAllCard.class})
    private LocalDateTime createTime;

    @Column(name = "status")
    @JsonView({ModelView.listAllCard.class})
    private int status;

    @Column(name = "rating")
    @JsonView({ModelView.listAllCard.class})
    private double rating;

    @Column(name = "resume_name")
    @JsonView({ModelView.listAllCard.class})
    private String resume_name;

    @Column(name = "resume_key")
    @JsonView({ModelView.listAllCard.class})
    private String resume_key;

    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonView({ModelView.listAllCard.class})
    private Set<Comment> comments;

    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Rating> ratings;


    public Card(String name, String education, String email, String phone, LocalDateTime createTime, int status, int rating, String resume_name, String resume_key) {
        this.name = name;
        this.education = education;
        this.email = email;
        this.phone = phone;
        this.createTime = createTime;
        this.status = status;
        this.rating = rating;
        this.resume_name = resume_name;
        this.resume_key = resume_key;
    }

    public Card(CardFE cardFE){
        this.name = cardFE.getName();
        this.education = cardFE.getEducation();
        this.email = cardFE.getEmail();
        this.phone = cardFE.getPhone();
        this.status = cardFE.getStatus();
        this.rating = cardFE.getRating();
        this.resume_name = cardFE.getResume().getOriginalFilename();

    }
    public Card(){}

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getResume_name() {
        return resume_name;
    }

    public void setResume_name(String resume_name) {
        this.resume_name = resume_name;
    }

    public String getResume_key() {
        return resume_key;
    }

    public void setResume_key(String resume_key) {
        this.resume_key = resume_key;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", education='" + education + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", rating=" + rating +
                ", resume_name='" + resume_name + '\'' +
                ", resume_key='" + resume_key + '\'' +
                '}';
    }
}
