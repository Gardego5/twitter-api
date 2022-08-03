package com.cooksys.springassessmentsocialmediasprint72022team4.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Hashtag {

    @Id
    @GeneratedValue
    private int id;

    private boolean deleted;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp firstUsed;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp lastUsed;

    @ManyToMany(mappedBy = "hashtags")
    private List<Tweet> tweets = new ArrayList<>();

}
