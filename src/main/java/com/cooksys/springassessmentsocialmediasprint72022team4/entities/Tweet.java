package com.cooksys.springassessmentsocialmediasprint72022team4.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
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

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Tweet {

    @Id
    @GeneratedValue
    private Integer id;

    private boolean deleted;

    @ManyToOne
    private User author;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp posted;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private Tweet inReplyTo;

    @OneToMany(mappedBy = "inReplyTo")
    @EqualsAndHashCode.Exclude
    private List<Tweet> replies = new ArrayList<>();

    @ManyToOne
    private Tweet repostOf;

    @OneToMany(mappedBy = "repostOf")
    @EqualsAndHashCode.Exclude
    private List<Tweet> reposts = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "tweet_hashtags", joinColumns = @JoinColumn(name = "tweet_id"), inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
    @EqualsAndHashCode.Exclude
    private Set<Hashtag> hashtags = new HashSet<>();

    @ManyToMany(mappedBy = "likedTweets")
    @EqualsAndHashCode.Exclude
    private Set<User> likers = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_mentions", joinColumns = @JoinColumn(name = "tweet_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @EqualsAndHashCode.Exclude
    private Set<User> mentions = new HashSet<>();

}
