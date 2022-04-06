package com.springauth.authapp.models.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springauth.authapp.models.auth.User;

import javax.persistence.*;

@Entity
public class Profile {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;

    @OneToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    public Profile() {
    }

    public Profile(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
