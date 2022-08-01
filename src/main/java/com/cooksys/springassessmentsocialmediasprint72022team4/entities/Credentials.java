package com.cooksys.springassessmentsocialmediasprint72022team4.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Data
public class Credentials {

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

}
