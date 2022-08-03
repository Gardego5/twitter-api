package com.cooksys.springassessmentsocialmediasprint72022team4.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_table")
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
    @CreationTimestamp
    private Timestamp joined;

    @OneToMany(mappedBy = "author")
    @EqualsAndHashCode.Exclude
    private List<Tweet> tweets = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_likes", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "tweet_id"))
    @EqualsAndHashCode.Exclude
    private Set<Tweet> likedTweets = new HashSet<>();

    @ManyToMany(mappedBy = "mentions")
    @EqualsAndHashCode.Exclude
    private Set<Tweet> mentions = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "followers_following", joinColumns = @JoinColumn(name = "follower_id"), inverseJoinColumns = @JoinColumn(name = "following_id"))
    @EqualsAndHashCode.Exclude
    private Set<User> followers = new HashSet<>();

    @ManyToMany(mappedBy = "followers")
    @EqualsAndHashCode.Exclude
    private Set<User> following = new HashSet<>();

}
