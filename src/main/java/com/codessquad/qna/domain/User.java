package com.codessquad.qna.domain;

import com.codessquad.qna.domain.validationGroup.user.Login;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

@Entity
public class User extends BaseEntity {
    @Column(nullable = false, length = 20, unique = true)
    @NotBlank(groups = {Login.class, Default.class})
    private String userId;

    @Column(nullable = false, length = 20)
    @JsonIgnore
    @NotBlank(groups = {Login.class, Default.class})
    private String password;

    @Column(nullable = false, length = 20)
    @NotBlank
    private String name;

    @Column(nullable = false, length = 50)
    @NotBlank
    private String email;

    protected User() {

    }

    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void update(User user) {
        this.password = user.password;
        this.name = user.name;
        this.email = user.email;
    }

    public boolean verify(User toVerify) {
        return this.userId.equals(toVerify.userId) && this.password.equals(toVerify.password);
    }

    public boolean isSameId(User user) {
        return this.getId().equals(user.getId());
    }
}
