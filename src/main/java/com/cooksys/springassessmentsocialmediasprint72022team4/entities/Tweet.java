package com.cooksys.springassessmentsocialmediasprint72022team4.entities;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

    @OneToMany(mappedBy = "inReplyTo")
    private List<Tweet> replies;

    @ManyToOne
    private Tweet repostOf;

    @OneToMany(mappedBy = "repostOf")
    private List<Tweet> reposts;

    @ManyToMany
    @JoinTable(
        name = "tweet_hashtags",
        joinColumns = @JoinColumn(name = "tweet_id"),
        inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    )
    private Set<Hashtag> hashtags;

}
