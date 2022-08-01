package com.cooksys.springassessmentsocialmediasprint72022team4.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_table")
@Where(clause = "deleted = false")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue
    private int id;

    private boolean deleted;

    @Embedded
    @Column(nullable = false)
    private Credentials credentials;

    @Embedded
    @Column(nullable = false)
    private Profile profile;

    @Column(nullable = false)
    private Timestamp joined;

    @OneToMany(mappedBy = "author")
    private List<Tweet> tweets;

    @ManyToMany
    @JoinTable(
        name = "user_likes",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "tweet_id")
    )
    private List<Tweet> likedTweets;

    @ManyToMany(mappedBy = "mentioners")
    private List<Tweet> mentions;

    @ManyToMany
    @JoinTable(
        name = "followers_following",
        joinColumns = @JoinColumn(name = "follower_id"),
        inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private List<User> followers;
    
    @ManyToMany(mappedBy = "followers")
    private List<User> following;

}
