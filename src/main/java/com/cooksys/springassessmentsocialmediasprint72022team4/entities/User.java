package com.cooksys.springassessmentsocialmediasprint72022team4.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
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
    private Profile profile;

    @Column(nullable = false)
    private Timestamp joined;

    @OneToMany(mappedBy = "author")
    private List<Tweet> tweets;

}
