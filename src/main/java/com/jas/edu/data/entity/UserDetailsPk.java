package com.jas.edu.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
@NoArgsConstructor
@Getter
public class UserDetailsPk implements Serializable {

    private String firstName;
    private String email;

    public UserDetailsPk(String firstName, String email) {
        this.firstName = firstName;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDetailsPk userdetailsPK)) return false;
        return firstName == userdetailsPK.firstName && Objects.equals(email, userdetailsPK.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, email);
    }
}
