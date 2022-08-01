package com.cooksys.springassessmentsocialmediasprint72022team4.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Where(clause = "deleted = false")
@NoArgsConstructor
@Data
public class Hashtag {

    @Id
    @GeneratedValue
    @OneToOne()
    private int id;

    private boolean deleted;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private Timestamp firstUsed;

    @Column(nullable = false)
    private Timestamp lastUsed;

    @ManyToMany(mappedBy = "hashtags")
    private List<Tweet> tweets;

}
