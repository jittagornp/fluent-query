/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jittagornp.fluentquery;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author jitta
 */
public class User {

    @ColumnMapping("ID")
    private String id;

    @ColumnMapping("USERNAME")
    private String username;

    @ColumnMapping("PASSWORD")
    private String password;

    @ColumnMapping("CREATED_DATE")
    private LocalDateTime createdDate;

    @ColumnMapping("CREATED_USER")
    private String createdUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", createdDate=" + createdDate + ", createdUser=" + createdUser + '}';
    }

}
