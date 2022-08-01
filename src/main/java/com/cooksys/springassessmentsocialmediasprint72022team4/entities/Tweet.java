package com.cooksys.springassessmentsocialmediasprint72022team4.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Where(clause = "deleted = false")
@NoArgsConstructor
@Data
public class Tweet {

    @Id
    @GeneratedValue
    private int id;

    private boolean deleted;

    @ManyToOne
    @JoinColumn
    private User author;

    @Column(nullable = false)
    private Timestamp posted;

    private String content;

    @ManyToOne
    private Tweet inReplyTo;

    @ManyToOne
    private Tweet repostOf;

    @ManyToMany(mappedBy = "tweets")
    private List<Hashtag> hashtags;

}
