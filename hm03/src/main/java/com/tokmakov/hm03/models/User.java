package com.tokmakov.hm03.models;

import lombok.Getter;

@Getter
public class User {
    private final String firstName;
    private final String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
