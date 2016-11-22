package com.ironyard.data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by reevamerchant on 11/10/16.
 */
@Entity
public class GoalUser {
    private String username;
    private String password;
    private String displayName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Permission> abilities;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Permission> getAbilities() {
        return abilities;
    }

    public void setAbilities(Set<Permission> abilities) {
        this.abilities = abilities;
    }

    public GoalUser(String username, String password, String displayName) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
    }

    public GoalUser() {
    }

}
